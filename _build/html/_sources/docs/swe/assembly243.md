# ECE243 Lab
```{note}
Cpulator link: https://cpulator.01xz.net/?sys=rv32-de1soc
```

## Lab 4 I/O

1. How Do Embedded Systems Communicate with External Devices?  
In embedded systems (like the DE1-SoC board you're using), data is sent to and received from external devices (like LEDs, switches, and buttons) using input/output (I/O) ports.  

There are two ways to synchronize with I/O devices:  

`Polling`: Continuously checking if the device needs attention (used in this lab).  
`Interrupts`: The device signals when it needs attention (covered in the next lab).  
2. What is a Parallel Port?  
A parallel port is a hardware interface that can transfer multiple bits of data at the same time (unlike a serial port, which transfers one bit at a time).
```{tip}
The DE1-SoC board has parallel ports for:  

Switches (SW)
Pushbuttons (KEY)
LEDs
Seven-segment displays
```

3. How Does a Parallel Port Work?  
A parallel port has four important registers (special memory locations) that control how data is sent and received:  

Register Name	Purpose  
Data Register	Holds the data being transferred between the processor and the I/O device.  
Direction Register	Defines if data is going in (input) or out (output) when using a bidirectional interface.  
Interrupt-mask Register	Enables or disables interrupts for input lines (not used in this lab).  
Edge-capture Register	Detects when a signal changes (useful for buttons and switches).  
Each of these registers has a specific memory address. The Data Register is at the base address, and the other registers are located at offsets (4, 8, and 12 bytes away).  

Understanding the NIOS V Subroutine Calling Convention  
When writing assembly programs for the NIOS V processor, there are rules about how to use registers when calling subroutines (functions):  
```{tip}
Register Type	Who Saves It?	Purpose
t0 – t6 (Temporary Registers)	The caller (main program) must save them before calling a subroutine.	Used for temporary calculations.
s0 – s11 (Saved Registers)	The subroutine itself must save and restore them.	Used for values that must be preserved across subroutine calls.
a0 – a7 (Argument Registers)	The caller puts function parameters here.	Used to pass up to 8 parameters to a subroutine.
a0 – a1 (Return Registers)	The subroutine puts return values here.	Used to return up to 2 values.
```

What Happens If There Are More Than 8 Parameters?  
If a subroutine needs more than 8 parameters, they must be stored in the stack in reverse order before calling the function.  

What Happens If There Are More Than 2 Return Values?  
If more than 2 values need to be returned, the extra values must be stored in the stack.  

```{warning}
CPUlator Warning  
If you overwrite s0 – s11 without saving them first, CPUlator will give an error saying you have "clobbered" a register.
```

### Part 1
```bash
        .section .text
        .global _start

        /* Define constants using proper syntax */
        .equ LED_BASE, 0xFF200000
        .equ KEY_BASE, 0xFF200050

_start:
        /* Initialize the display value to 1 and write it to the LED port */
        li      t0, 1             # t0 holds the current display value
        li      t3, LED_BASE      # t3 = LED port base address
        li      t5, KEY_BASE      # t5 = KEY port base address (for lw instructions)
        sw      t0, 0(t3)

Main_Loop:
        /* Poll the KEY port until a key press is detected */
        lw      t2, 0(t5)         # Read the KEY port into t2 using base register t5
        beq     t2, zero, Main_Loop  # If no key pressed, keep polling

        /* Check which KEY is pressed.
           KEY0: Bit 0 (mask = 0x1) */
        li      t4, 0x1
        and     t4, t2, t4        # Isolate bit0
        bnez    t4, KEY0_Handler

        /* KEY1: Bit 1 (mask = 0x2) */
        li      t4, 0x2
        and     t4, t2, t4        # Isolate bit1
        bnez    t4, KEY1_Handler

        /* KEY2: Bit 2 (mask = 0x4) */
        li      t4, 0x4
        and     t4, t2, t4        # Isolate bit2
        bnez    t4, KEY2_Handler

        /* KEY3: Bit 3 (mask = 0x8) */
        li      t4, 0x8
        and     t4, t2, t4        # Isolate bit3
        bnez    t4, KEY3_Handler

        /* If no recognized key, wait until keys are released */
        j       Wait_Release

/*----------------------------------------------------------------------
  KEY0_Handler:  If KEY0 is pressed, set display to 1.
----------------------------------------------------------------------*/
KEY0_Handler:
        li      t0, 1             # Set display value to 1
        j       Update_LED

/*----------------------------------------------------------------------
  KEY1_Handler:  If KEY1 is pressed, increment display (unless blank or already 15).
                If display is blank (0), then set it to 1.
----------------------------------------------------------------------*/
KEY1_Handler:
        beq     t0, zero, KEY1_SetTo1  # If blank, set to 1
        li      t4, 15
        beq     t0, t4, Wait_Release    # If already 15, do nothing
        addi    t0, t0, 1         # Otherwise, increment
        j       Update_LED

KEY1_SetTo1:
        li      t0, 1
        j       Update_LED

/*----------------------------------------------------------------------
  KEY2_Handler:  If KEY2 is pressed, decrement display (unless blank or already 1).
                If display is blank (0), then set it to 1.
----------------------------------------------------------------------*/
KEY2_Handler:
        beq     t0, zero, KEY2_SetTo1  # If blank, set to 1
        li      t4, 1
        beq     t0, t4, Wait_Release    # If already 1, do nothing
        addi    t0, t0, -1        # Otherwise, decrement
        j       Update_LED

KEY2_SetTo1:
        li      t0, 1
        j       Update_LED

/*----------------------------------------------------------------------
  KEY3_Handler:  If KEY3 is pressed, blank the display (i.e. set it to 0).
----------------------------------------------------------------------*/
KEY3_Handler:
        li      t0, 0             # Blank the display
        j       Update_LED

/*----------------------------------------------------------------------
  Update_LED:  Write the current display value (in t0) to the LED port.
----------------------------------------------------------------------*/
Update_LED:
        li      t3, LED_BASE      # Reload LED port base address (or reuse t3 if it wasn't clobbered)
        sw      t0, 0(t3)
        j       Wait_Release

/*----------------------------------------------------------------------
  Wait_Release:  Wait until all keys are released (i.e. KEY port reads 0).
----------------------------------------------------------------------*/
Wait_Release:
Release_Loop:
        lw      t2, 0(t5)         # Read KEY port using base register t5
        bnez    t2, Release_Loop
        j       Main_Loop
```