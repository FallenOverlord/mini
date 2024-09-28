# nmap Basic Commands and Examples

Service Enumeration
If we can somehow aquire the exact version number a service is running on, we might just be  
able to find known exploits and source code on the service.

The best practice is to run a quick scan for available ports first, because it doesn't involves  
heavy traffic and will not be blocked by the security mechanism.

We can use the following command to scan all ports, looking for service versions
sudo nmap <target_ip_address> -p- -sV

the command will take a long time, so press the space bar to check for the scan status  
the other way is to use --stats-every=<time>, where time is in s or m, to periodically  
check the scan stats.

sudo nmap 10.129.2.28 -p- -sV --stats-every=5s

Alternatively, we can use -v to increase the verbosity level that nmap will print out all  
the info once it aquires them.

sudo nmap <target_ip_address> -p -sV -v

The three-way handshape:
18:28:07.128564 IP 10.10.14.2.59618 > 10.129.2.28.smtp: `Flags [S]`, seq 1798872233, win 65535, options [mss 1460,nop,wscale 6,nop,nop,TS val 331260178 ecr 0,sackOK,eol], length 0 [SYN]
18:28:07.255151 IP 10.129.2.28.smtp > 10.10.14.2.59618: Flags [S.], seq 1130574379, ack 1798872234, win 65160, options [mss 1460,sackOK,TS val 1800383922 ecr 331260178,nop,wscale 7], length 0 [SYN-ACK]
18:28:07.255281 IP 10.10.14.2.59618 > 10.129.2.28.smtp: Flags [.], ack 1, win 2058, options [nop,nop,TS val 331260304 ecr 1800383922], length 0 [ACK]

The PSH flag states that the target server is sending data to us, while the ACK flag confirms the data is indeed sent.
The last ACK flag confirms the receipt of the data.
18:28:07.319306 IP 10.129.2.28.smtp > 10.10.14.2.59618: Flags [P.], seq 1:36, ack 1, win 510, options [nop,nop,TS val 1800383985 ecr 331260304], length 35: SMTP: 220 inlane ESMTP Postfix (Ubuntu) [PSH-ACK]
18:28:07.319426 IP 10.10.14.2.59618 > 10.129.2.28.smtp: Flags [.], ack 36, win 2058, options [nop,nop,TS val 331260368 ecr 1800383985], length 0 [ACK]



Nmap Scripting Engine (NSE)
It lets us to create scripts in Lua and interact with existing services, there're a total of 14 catagories.
auth	Determination of authentication credentials.
broadcast	Scripts, which are used for host discovery by broadcasting and the discovered hosts, can be automatically added to the remaining scans.
brute	Executes scripts that try to log in to the respective service by brute-forcing with credentials.
default	Default scripts executed by using the -sC option.
discovery	Evaluation of accessible services.
dos	These scripts are used to check services for denial of service vulnerabilities and are used less as it harms the services.
exploit	This category of scripts tries to exploit known vulnerabilities for the scanned port.
external	Scripts that use external services for further processing.
fuzzer	This uses scripts to identify vulnerabilities and unexpected packet handling by sending different fields, which can take much time.
intrusive	Intrusive scripts that could negatively affect the target system.
malware	Checks if some malware infects the target system.
safe	Defensive scripts that do not perform intrusive and destructive access.
version	Extension for service detection.
vuln	 Identification of specific vulnerabilities.

10.129.38.188
To use default scripts in nmap
sudo nmap <target> -sC

To use specificed scripts in nmap
sudo nmap <target> -p<target_port> --script <script1_name>,<script2_name>, ...
tip: there's no space between script1 and script2

To use aggressive scan in nmap
sudo nmap <target> -A
benefits, it can determine the web server, web application and the web-page title from the target, it can even guess the os the target is running on.

To use vuln scan in nmap
sudo nmap <target> -p<target_port> --script vuln -sV
this command will scan the target port number and the services that are running on it, it also uses scripts to find more info about the service
the -script vuln have the capability of checking the service versions against known vulnerabilites in the database




## Perform a basic scan
`-p-` is to scan all ports  
`-p<portnumber>` is to scan a specificed port
e.g. sudo nmap <ipv4_address> will perform a full TCP port scan


## Saving scan results
`-oN <file_name>`, <file_name>.nmap, default format normal output
`-oG <file_name>`, <file_name>.gnmp, grepable output
`-oX <file_name>`, <file_name>.xml, can convert to html page, ez to read
to proceed to convert into an html page, use the command
```bash
xsltproc <file_name>.xml -o <file_name>.html
```

`firefox <file_name>.html` will open up the report in firefox browser
`-oA <file_name>`, save in all formats
```{tip}
after adding these in serach commands, files will be created in the
current directory that contains the search results.
```

```{figure} /_static/test/tp.png
:width: 100px
:height: 100px
```

## Service Enumeration

If the attacker knows the exact service version the machine is runing on, it can reuse known vulnbilities, and it can aquare more information.
  
[markdown syntax](https://www.markdownguide.org/cheat-sheet/)

## Firewall Evasion
A firewall is a security measure against unauthorized connection attempts from external networks.   
 
Evasion: fragmentation of packets, the use of decoys  

IPS/IDS: based on pattern matching and signatures, if specific patterns are detected, such as a service detection scan, IPS may prevent the pending connection attempts.  

 dropped packets are ignored  
 rejected packets that are returned with an RST flag, contain different types of ICMP error codes or contain nothing at all  


Nmap's TCP ACK scan (-sA) method is much harder to filter for firewalls and IDS/IPS systems than regular SYN (-sS) or Connect scans (sT)  

```bash
//SYN Scan
sudo nmap 10.129.2.28 -p 21,22,25 -sS -Pn -n --disable-arp-ping --packet-trace

//ACK Scan
sudo nmap 10.129.2.28 -p 21,22,25 -sA -Pn -n --disable-arp-ping --packet-trace


```
 detection of IDS/IPS systems is much more difficult because these are passive traffic monitoring systems
One method to determine whether such IPS system is present in the target network is to scan from a single host (VPS).  

Decoy Scan
```bash

sudo nmap 10.129.2.28 -p 80 -sS -Pn -n --disable-arp-ping --packet-trace -D RND:5
```
--packet-trace	Shows all packets sent and received.
-Pn	Disables ICMP Echo requests.
-n	Disables DNS resolution.

### DNS Scan (IPS/IDS Evasion 2)
Submit the DNS server version of the target as the answer.  

 The -sV option enables service version detection, which attempts to determine the version of the running services on the target.  
 DNS typically operates on port 53, so you can specify this port in your scan.  
 use nmap's NSE (Nmap Scripting Engine) scripts tailored for DNS to gather more detailed information
```bash
nmap --dns-server 8.8.8.8 10.129.2.48
┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-c1qvcg4v7i]─[~]
└──╼ [★]$ nmap --dns-server 8.8.8.8 10.129.2.48
Starting Nmap 7.93 ( https://nmap.org ) at 2024-05-18 10:29 BST
Nmap scan report for 10.129.2.48
Host is up (0.067s latency).
Not shown: 992 closed tcp ports (conn-refused)
PORT    STATE    SERVICE
21/tcp  open     ftp
22/tcp  open     ssh
53/tcp  filtered domain
80/tcp  open     http
110/tcp open     pop3
139/tcp open     netbios-ssn
143/tcp open     imap
445/tcp filtered microsoft-ds

nmap -sV -p 53 --script=dns-nsid 10.129.2.48
┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-c1qvcg4v7i]─[~]
└──╼ [★]$ nmap -sV -p 53 --script=dns-nsid 10.129.2.48
Starting Nmap 7.93 ( https://nmap.org ) at 2024-05-18 10:32 BST
Nmap scan report for 10.129.2.48
Host is up (0.068s latency).

PORT   STATE    SERVICE VERSION
53/tcp filtered domain

Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .


nmap -sV -p 53 --script="dns* and not broadcast" 10.129.2.48
┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-c1qvcg4v7i]─[~]
└──╼ [★]$ nmap -sV -p 53 --script="dns* and not broadcast" 10.129.2.48
Starting Nmap 7.93 ( https://nmap.org ) at 2024-05-18 10:34 BST
Nmap scan report for 10.129.2.48
Host is up (0.067s latency).

PORT   STATE    SERVICE VERSION
53/tcp filtered domain

Host script results:
|_dns-brute: Can't guess domain of "10.129.2.48"; use dns-brute.domain script argument.
| dns-blacklist: 
|   SPAM
|     l2.apews.org - FAIL
|_    all.spamrats.com - FAIL


nmap -sV -p 53 -T1 -f --scan-delay 500ms --randomize-hosts -D RND:10 --dns-server 8.8.8.8 --script=dns-nsid 10.129.2.48

nmap -sV -p 53 -T1 -f --scan-delay 500ms --randomize-hosts -D RND:10 --dns-server 8.8.8.8 --script="dns* and not broadcast" 10.129.2.48

┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-c1qvcg4v7i]─[~]
└──╼ [★]$ nmap -sV -p 53 -T1 -f --scan-delay 500ms --randomize-hosts -D RND:10 --dns-server 8.8.8.8 --script="dns* and not broadcast" 10.129.2.48
Sorry, but fragscan requires root privileges.
QUITTING!

┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-c1qvcg4v7i]─[~]
└──╼ [★]$ sudo nmap -sV -p 53 -T1 -f --scan-delay 500ms --randomize-hosts -D RND:10 --dns-server 8.8.8.8 --script="dns* and not broadcast" 10.129.2.48
Starting Nmap 7.93 ( https://nmap.org ) at 2024-05-18 10:38 BST
Nmap scan report for 10.129.2.48
Host is up (0.065s latency).

PORT   STATE    SERVICE VERSION
53/tcp filtered domain

Host script results:
|_dns-brute: Can't guess domain of "10.129.2.48"; use dns-brute.domain script argument.
| dns-blacklist: 
|   SPAM
|     l2.apews.org - FAIL
|_    all.spamrats.com - FAIL


```

```sh

```



