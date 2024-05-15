# CTF Write Ups

## General Knowledge

[picoCTF](https://docs.google.com/document/d/1JmjkpvIywCfmudMSs3kBxFYP2t5kwVuc7Gf2Rhm3MWs/edit?usp=drive_link)

## Reverse Engineering

### Safe Opener 2
#### Question
What can you do with this file?
I forgot the key to my safe but this file is supposed to help me with retrieving the lost key. Can you help me unlock my safe?

#### Answer
1. Download the required file
https://artifacts.picoctf.net/c/292/SafeOpener.class

2. Analyse the file
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/safe2]
└─$ file SafeOpener.class 
SafeOpener.class: compiled Java class data, version 52.0 (Java 1.8)
```

It shows a compiled Java Class data, and the hint ask us to decompile it. So it makes sense.

3. Get a look at the file
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/safe2]
└─$ cat SafeOpener.class 
����4�
CDE     FG
H
I
JL      FN
OP
Q
RS
.T
OU
VW
X
```
We can see that it is impossible to look at the content of the file because it is all compiled.

```bash
d
 uv
   wx
     yr
       >?java/lang/StringBuilder
You have  
          z{
            z| attempt(s) left
                              }t,picoCTF{SAf3_0p3n3rr_y0u_solv3d_it_0e57c117}
                                                                             ~
                                                                              Sesame openPassword is incorrect
```
However, if you are careful and patient enough, you would realize that amids the chaos, the flag is right there.

4. Decompile the file
To aquire the right tools for decompiling the file, we must download Java and CFR.  
```bash
sudo apt update
sudo apt install default-jdk

┌──(kali㉿kali)-[~/Desktop/picoctf/safe2]
└─$ wget https://github.com/leibnitz27/cfr/releases/download/0.152/cfr-0.152.jar
```

Now we use the CFR tool to decompile the Java Class Data.  

```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/safe2]
└─$ java -jar cfr-0.152.jar SafeOpener.class                                    
Picked up _JAVA_OPTIONS: -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true
/*
 * Decompiled with CFR 0.152.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

public class SafeOpener {
    public static void main(String[] args) throws IOException {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedkey = "";
        String key = "";
        for (int i = 0; i < 3; ++i) {
            System.out.print("Enter password for the safe: ");
            key = keyboard.readLine();
            encodedkey = encoder.encodeToString(key.getBytes());
            System.out.println(encodedkey);
            boolean isOpen = SafeOpener.openSafe(encodedkey);
            if (isOpen) break;
            System.out.println("You have  " + (2 - i) + " attempt(s) left");
        }
    }

    public static boolean openSafe(String password) {
        String encodedkey = "picoCTF{SAf3_0p3n3rr_y0u_solv3d_it_0e57c117}";
        if (password.equals(encodedkey)) {
            System.out.println("Sesame open");
            return true;
        }
        System.out.println("Password is incorrect\n");
        return false;
    }
}     
```
Now it will be much easier to see the logic in the code;thus, find the flag to this challenge.

### Bit-O-Asm
#### Question
Can you figure out what is in the eax register? Put your answer in the picoCTF flag format: picoCTF{n} where n is the contents of the eax register in the decimal number base. If the answer was 0x11 your flag would be picoCTF{17}.
Download the assembly dump [here](https://artifacts.picoctf.net/c/509/disassembler-dump0_a.txt).

As with most assembly, there is a lot of noise in the instruction dump. Find the one line that pertains to this question and don't second guess yourself!

#### Answer
1. Understanding the question  
We want to find something in the `eax register` that looks like `0x11`, and convert the hexdecimal number into decimal number.  
Finally we are to put the number we get into the template `picoCTF{}`

2. Analyse the information  

After downloading the required file, we need to analyze and try to understand its content.

```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/bitOAsm]
└─$ wget https://artifacts.picoctf.net/c/509/disassembler-dump0_a.txt
```

If we use the `file` command to analyze the file, we can see that it is in ASCII text format.  
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/bitOAsm]
└─$ file disassembler-dump0_a.txt 
disassembler-dump0_a.txt: ASCII text
```
We can also use `cat` command to see its full content.
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/bitOAsm]
└─$ cat disassembler-dump0_a.txt 
<+0>:     endbr64 
<+4>:     push   rbp
<+5>:     mov    rbp,rsp
<+8>:     mov    DWORD PTR [rbp-0x4],edi
<+11>:    mov    QWORD PTR [rbp-0x10],rsi
<+15>:    mov    eax,0x30
<+20>:    pop    rbp
<+21>:    ret
```

- code such as `endbr64`, `mov` and `push` are instructions.
- `rbp` is the current base pointer, and `rsp` is the stack pointer.
- the `mov` command is used to move the value of pointers.
- variables `edi` and `rsi` holds the value of the aguments of a funciton.
- The instruction `mov eax, 0x30` moves the value `0x30` into the eax register.
- The `eax` register usually holds the return value of a function.

3. Finding the flag  
From the above analysis, it is clear that `0x30` is the value we are looking for.  
After converting it into a decimal value, we get 48 as the final answer.
`picoCTF{48}`
### Bit-O-Asm-2
#### Question
Can you figure out what is in the eax register? Put your answer in the picoCTF flag format: picoCTF{n} where n is the contents of the eax register in the decimal number base. If the answer was 0x11 your flag would be picoCTF{17}.
Download the assembly dump [here](https://artifacts.picoctf.net/c/510/disassembler-dump0_b.txt).

PTR's or 'pointers', reference a location in memory where values can be stored.
#### Answer
1. Understanding the question  
Same as before, we need to find the hexdecimal number stored in the `eax` register and convert it into decimal.  

2. Analysing the information  

We can see that the content in the disassambler dump file is a bit more complex this time.
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/bito2]
└─$ cat disassembler-dump0_b.txt 
<+0>:     endbr64 
<+4>:     push   rbp
<+5>:     mov    rbp,rsp
<+8>:     mov    DWORD PTR [rbp-0x14],edi
<+11>:    mov    QWORD PTR [rbp-0x20],rsi
<+15>:    mov    DWORD PTR [rbp-0x4],0x9fe1a
<+22>:    mov    eax,DWORD PTR [rbp-0x4]
<+25>:    pop    rbp
<+26>:    ret
```
- The `mov a, b` comand will move the value of b into a.
- `[rbp-0x14]` tells the location of the local pointer (PTR) DWORD.  It is located 20 bytes below the base pointer.
- In the <+15> line, the value `0x9fela` is being moved into the pointer DWORD.  Which is later moved into the `eax register` on line <+22>.

3. Finding the flag
If we convert `0x9fela` into decimal and put the result into the flag template, we will get `picoCTF{654874}`.

### Bit-O-Asm-3

#### Question
Can you figure out what is in the eax register? Put your answer in the picoCTF flag format: picoCTF{n} where n is the contents of the eax register in the decimal number base. If the answer was 0x11 your flag would be picoCTF{17}.
Download the assembly dump [here](https://artifacts.picoctf.net/c/530/disassembler-dump0_c.txt).  
Not everything in this disassembly listing is optimal.

#### Answer
1. **Understanding the Question**  
Same rules as before, we need to find out the value that is stored in the register named `eax`.

2. **Analysing the content**  
After downloading and opeing the file we can see that it has became more complex this time.
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/BitOAsm3]
└─$ cat disassembler-dump0_c.txt 
<+0>:     endbr64 
<+4>:     push   rbp
<+5>:     mov    rbp,rsp
<+8>:     mov    DWORD PTR [rbp-0x14],edi
<+11>:    mov    QWORD PTR [rbp-0x20],rsi
<+15>:    mov    DWORD PTR [rbp-0xc],0x9fe1a
<+22>:    mov    DWORD PTR [rbp-0x8],0x4
<+29>:    mov    eax,DWORD PTR [rbp-0xc]
<+32>:    imul   eax,DWORD PTR [rbp-0x8]
<+36>:    add    eax,0x1f5
<+41>:    mov    DWORD PTR [rbp-0x4],eax
<+44>:    mov    eax,DWORD PTR [rbp-0x4]
<+47>:    pop    rbp
<+48>:    ret
```
Notably there are some new instructions, such as `imul` and `add`.
- `imul` means multiplication.   imul a, b means a = a * b.   imul a, b, c means a = b * c.
- `add` add a, b means a = a + b.

Let's have a closer look at the lines that have something to do with the `eax register`.
```bash
<+15>:    mov    DWORD PTR [rbp-0xc],0x9fe1a
<+22>:    mov    DWORD PTR [rbp-0x8],0x4
<+29>:    mov    eax,DWORD PTR [rbp-0xc]
<+32>:    imul   eax,DWORD PTR [rbp-0x8]
<+36>:    add    eax,0x1f5
<+41>:    mov    DWORD PTR [rbp-0x4],eax
<+44>:    mov    eax,DWORD PTR [rbp-0x4]
```

**0x9fela**(654874 in decimal) is stored at  `[rbp-0xc]` and **0x4**(4 in decimal) is stored at  `[rbp-0x8]`.  
0x9fe1a is then moved to the eax register.  
After that, the value held in the eax register is multiplied by the value stored in  `[rbp-0x8]`, which is **0x4**.  
Finally, **0x1f5**(501 in decimal) is added into the register.  
The last two lines are redundant, regarding this challenge.

```bash
654874 * 4 + 501 = 2619997
```
**picoCTF{2619997}** is the flag to this challenge.

### Bit-O-Asm-4

Let's look at the disassambler dump in this challenge.  
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/BitOAsm4]
└─$ cat disassembler-dump0_d.txt 
<+0>:     endbr64 
<+4>:     push   rbp
<+5>:     mov    rbp,rsp
<+8>:     mov    DWORD PTR [rbp-0x14],edi
<+11>:    mov    QWORD PTR [rbp-0x20],rsi
<+15>:    mov    DWORD PTR [rbp-0x4],0x9fe1a
<+22>:    cmp    DWORD PTR [rbp-0x4],0x2710
<+29>:    jle    0x55555555514e <main+37>
<+31>:    sub    DWORD PTR [rbp-0x4],0x65
<+35>:    jmp    0x555555555152 <main+41>
<+37>:    add    DWORD PTR [rbp-0x4],0x65
<+41>:    mov    eax,DWORD PTR [rbp-0x4]
<+44>:    pop    rbp
<+45>:    ret
```
- `cmp` and `jle` forms an if-statement(a conditional jump): comparing the value stored at `[rbp-0x4]` and `0x2710`. If the value stored at [`rbp-0x4]` is less than 0x2710, then the program will jump to line` <main+37>`.
- `sub` will subtract 0x65 from the value stored at [rbp-0x4].
- at line **<+35>** `jump` is an unconditional jump, it will automatically jump to line 41.
- The value stored at `[rbp-0x4]`, **0x9fe1a**, will **subtract 0x65** in line 31 but will not add 0x65 in line 37, and will be finally transferred to `eax`.

```bash
654042 - 101 = 653941
```

### GDB Baby Step 1
#### Question
Can you figure out what is in the eax register at the end of the main function? Put your answer in the picoCTF flag format: picoCTF{n} where n is the contents of the eax register in the decimal number base. If the answer was 0x11 your flag would be picoCTF{17}.
Disassemble [this](https://artifacts.picoctf.net/c/512/debugger0_a).  
gdb is a very good debugger to use for this problem and many others!  
main is actually a recognized symbol that can be used with gdb commands.

#### Answer
- Understanding the Question  
The question asks the value that is stored in eax register, but we are not provided with the assembler dump that can be analysed right away.  

If we download the file and analyse the file type using the `file` command, we can see that it is an ELF executable file.
``` bash
┌──(kali㉿kali)-[~/Desktop/picoctf/gdb1]
└─$ file debugger0_a                                    
debugger0_a: ELF 64-bit LSB pie executable, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/ld-linux-x86-64.so.2, BuildID[sha1]=15a10290db2cd2ec0c123cf80b88ed7d7f5cf9ff, for GNU/Linux 3.2.0, not stripped
```
This means that we can perhaps run it to look for any clue.
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/gdb1]
└─$ chmod +x debugger0_a 
                                                                                                                    
┌──(kali㉿kali)-[~/Desktop/picoctf/gdb1]
└─$ ./debugger0_a 
```
It does not provides any information, so let's try to disassamble it using gdb as recommended.
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/gdb1]
└─$ gdb debugger0_a 

(gdb) disassemble main
Dump of assembler code for function main:
   0x0000000000001129 <+0>:     endbr64
   0x000000000000112d <+4>:     push   %rbp
   0x000000000000112e <+5>:     mov    %rsp,%rbp
   0x0000000000001131 <+8>:     mov    %edi,-0x4(%rbp)
   0x0000000000001134 <+11>:    mov    %rsi,-0x10(%rbp)
   0x0000000000001138 <+15>:    mov    $0x86342,%eax
   0x000000000000113d <+20>:    pop    %rbp
   0x000000000000113e <+21>:    ret
End of assembler dump.
```
We can see that the value assocaiated with the `eax` register is **0x86342**, which translates to 549698 in decimal.  
Which makes the flag `picoCTF{549698}`.

### GDB Baby Step 2
#### Question
Can you figure out what is in the eax register at the end of the main function? Put your answer in the picoCTF flag format: picoCTF{n} where n is the contents of the eax register in the decimal number base. If the answer was 0x11 your flag would be picoCTF{17}.
Debug [this](https://artifacts.picoctf.net/c/520/debugger0_b).  
You could calculate eax yourself, or you could set a breakpoint for after the calculcation and inspect eax to let the program do the heavy-lifting for you.

#### Answer
Following the hind dropped by the question, we need to use gdb tool to set a breakpoint assembler dump to moniter the value of the eax register.

```bash
└─$ file debugger0_b 
debugger0_b: ELF 64-bit LSB executable, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/ld-linux-x86-64.so.2, BuildID[sha1]=95b0203be2982e75dbc01d1cc25b1309f7aec5f7, for GNU/Linux 3.2.0, not stripped
```
We can see that the debugger0_b file is a ELF executable, which means that we need to use the gdb tool to analyse it.  
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/gdb2]
└─$ gdb debugger0_b 
(gdb) disassemble main
Dump of assembler code for function main:
   0x0000000000401106 <+0>:     endbr64
   0x000000000040110a <+4>:     push   %rbp
   0x000000000040110b <+5>:     mov    %rsp,%rbp
   0x000000000040110e <+8>:     mov    %edi,-0x14(%rbp)
   0x0000000000401111 <+11>:    mov    %rsi,-0x20(%rbp)
   0x0000000000401115 <+15>:    movl   $0x1e0da,-0x4(%rbp)
   0x000000000040111c <+22>:    movl   $0x25f,-0xc(%rbp)
   0x0000000000401123 <+29>:    movl   $0x0,-0x8(%rbp)
   0x000000000040112a <+36>:    jmp    0x401136 <main+48>
   0x000000000040112c <+38>:    mov    -0x8(%rbp),%eax
   0x000000000040112f <+41>:    add    %eax,-0x4(%rbp)
   0x0000000000401132 <+44>:    addl   $0x1,-0x8(%rbp)
   0x0000000000401136 <+48>:    mov    -0x8(%rbp),%eax
   0x0000000000401139 <+51>:    cmp    -0xc(%rbp),%eax
   0x000000000040113c <+54>:    jl     0x40112c <main+38>
   0x000000000040113e <+56>:    mov    -0x4(%rbp),%eax
   0x0000000000401141 <+59>:    pop    %rbp
   0x0000000000401142 <+60>:    ret
End of assembler dump.
```
We can se that the value of the eax register is returned at the last line (<+60>), so we need to set a breakpoint just before the value is returned.

```bash
(gdb) break *0x0000000000401142
Breakpoint 1 at 0x401142
(gdb) run
Starting program: /home/kali/Desktop/picoctf/gdb2/debugger0_b 
[Thread debugging using libthread_db enabled]
Using host libthread_db library "/lib/x86_64-linux-gnu/libthread_db.so.1".
```
Now that we've run the code up *until the breakpoint* we can check eax using `info registers eax`.
```bash
Breakpoint 1, 0x0000000000401142 in main ()
(gdb) info registers eax
eax            0x4af4b             307019
```

The flag for this challenge is** picoCTF{307019}**.

### GDB Baby Steps 4
#### Question
main calls a function that multiplies eax by a constant. The flag for this challenge is that constant in decimal base. If the constant you find is 0x1000, the flag will be picoCTF{4096}.
Debug [this](https://artifacts.picoctf.net/c/532/debugger0_d).
A function can be referenced by either its name or its starting address in gdb.

#### Answer
The question asks us to find a constant in a function that multiplies eax.

We first need download the file and use gdb tool to disassemble it.
```bash
┌──(kali㉿kali)-[~/Desktop/picoctf/gdb4]
└─$ gdb debugger0_d 
(gdb) disassemble main
Dump of assembler code for function main:
   0x000000000040111c <+0>:     endbr64
   0x0000000000401120 <+4>:     push   %rbp
   0x0000000000401121 <+5>:     mov    %rsp,%rbp
   0x0000000000401124 <+8>:     sub    $0x20,%rsp
   0x0000000000401128 <+12>:    mov    %edi,-0x14(%rbp)
   0x000000000040112b <+15>:    mov    %rsi,-0x20(%rbp)
   0x000000000040112f <+19>:    movl   $0x28e,-0x4(%rbp)
   0x0000000000401136 <+26>:    movl   $0x0,-0x8(%rbp)
   0x000000000040113d <+33>:    mov    -0x4(%rbp),%eax
   0x0000000000401140 <+36>:    mov    %eax,%edi
   0x0000000000401142 <+38>:    call   0x401106 <func1>
   0x0000000000401147 <+43>:    mov    %eax,-0x8(%rbp)
   0x000000000040114a <+46>:    mov    -0x4(%rbp),%eax
   0x000000000040114d <+49>:    leave
   0x000000000040114e <+50>:    ret
End of assembler dump.
```
When we try to disassemble main, we can see that there is a function called in main. Following the hint, we can disassemble the function by using the commands (gdb)disassemble func1

```bash
Dump of assembler code for function func1:
   0x0000000000401106 <+0>:     endbr64
   0x000000000040110a <+4>:     push   %rbp
   0x000000000040110b <+5>:     mov    %rsp,%rbp
   0x000000000040110e <+8>:     mov    %edi,-0x4(%rbp)
   0x0000000000401111 <+11>:    mov    -0x4(%rbp),%eax
   0x0000000000401114 <+14>:    imul   $0x3269,%eax,%eax
   0x000000000040111a <+20>:    pop    %rbp
   0x000000000040111b <+21>:    ret
End of assembler dump.
```
We can see from the imul (integer mulplication) line that eax is multiplied by 0x3269, which translates to 12905 in decimal.  
The flag of this challenge is **picoCTF{12905}**

