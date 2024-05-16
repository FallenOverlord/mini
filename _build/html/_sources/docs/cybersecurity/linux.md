---
jupytext:
  text_representation:
    extension: .md
    format_name: myst
kernelspec:
  display_name: Python 3
  language: python
  name: python3
---

# Linux Basics

connect to machine remotely using ssh:
```bash
ssh username@hostname
```
for example:
```bash
ssh milesw@123.92.105
```
Find out the hardware name:
```bash
uname -m
```
Find out the kernel version (generic) of the host:
```bash
uname -r
```

Find out the path to certain directories:
```bash
echo $MAIL
echo $HOME
```

Find out the name of network interface:
```bash
ip addr show
```

## Find the index/inode number of a file:
```bash
ls -i <path to the file>
ls -i /etc/sudoers
```
Find hidden files in a dir:
```bash
ls -la
```
This is a <span style="color: red;">red</span> word.

Find the files according to the time they're modified:
```bash
ls -lt
```
Get an overview of the file and folders in a dir:
```bash
tree .
```
Find the location of a software/tool:
```bash
which <name_of_the_tool>
which python
which netcat
```
### Locate a file:

```bash
find <starting_location> <param.>
find / -name "hello.txt" -type f -size +12 -size -20 -user root -newermt 2023-03-03 2>/dev/null
```
```{note}
explain: the command starts finding from the root directory, it detects sources with a file format, 
with a name of "hello.txt" and a size of over 12 but less than 20. In addition, the file has to be owned
 by root and is created later than march 3, 2023. 2>/dev/null disposes the warnning messages.
```
Find the number of files with certain names/extensions:
```bash
find <starting_location> <param.> | wc -l
find / -name *.config -type f 2>/dev/null | wc -l
```
```{tip}
tip: "wc -l" means "word count", with an argument of line count.
```

### Find the number of packages installed on the machine:
```bash
dpkg -l | grep -c '^ii'
```
```{tip}
the "-c" command count the number of outputs, '^ii' is the patternin packages that the computer should look for.
```
```bash
apt list --installed | wc -l
```
```{warning}
when using the second command, the header is also being counted, you have to deduct 1 from the total result.
```

### Redirecting outputs:
```{note}
```bash
find / -name "fun.exe" -type f 2>/dev/null >path.txt
```
tip: this redirects the garbage warnings into null and redirects the rest of the output into a newly created file name path.txt.
#### File Descriptions

1. Data Stream for Input
	`STDIN – 0`
2. Data Stream for Output
	`STDOUT – 1`
3. Data Stream for Output that relates to an error occurring.
	`STDERR – 2`

e.g. `2>/dev/null` means that error outputs are being redirected to null.

### Web Services
visite a website `[★]$ curl http://10.129.27.243/robots.txt`

