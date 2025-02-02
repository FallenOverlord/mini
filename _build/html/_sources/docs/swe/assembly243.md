# ECE243 Lab

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
