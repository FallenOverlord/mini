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
It wouldnt connect no matter how evasive the scann is.
```sh
sudo nmap -sS -sV -p 53 -T1 --source-port 53 --scan-delay 1s --dns-server 8.8.8.8 --script="dns-service-discovery,dns-recursion,dns-zone-transfer" -v -d 10.129.2.48
PORT   STATE    SERVICE REASON      VERSION
53/tcp filtered domain  no-response
Final times for host: srtt: 65523 rttvar: 65523  to: 1000000

sudo nmap -sS -sV -p 53 -T1 --source-port 53 --scan-delay 1s --dns-server 8.8.8.8 --script="dns-service-discovery,dns-recursion,dns-zone-transfer" -v -d 10.129.2.48

```
#### Steps to Identify the Publicly Accessible DNS Server
Identify All Interfaces: Scan for other interfaces or IP addresses associated with the target.  
Check Public Records: Use tools like whois, nslookup, or dig to find public DNS records associated with the domain.  
Leverage Subdomain Enumeration: Use tools to enumerate subdomains that might reveal additional publicly accessible services.  
```sh
┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-c1qvcg4v7i]─[~]
└──╼ [★]$ sudo nmap -sP 10.129.2.0/24
Starting Nmap 7.93 ( https://nmap.org ) at 2024-05-18 10:46 BST
Nmap scan report for 10.129.2.48
Host is up (0.065s latency).
Nmap scan report for 10.129.2.210
Host is up (0.066s latency).
Nmap done: 256 IP addresses (2 hosts up) scanned in 10.71 seconds

sudo nmap -sS -sV -p 53 -T1 --source-port 53 --scan-delay 1s --dns-server 8.8.8.8 --script="dns-service-discovery,dns-recursion,dns-zone-transfer" -v -d 10.129.229.175
PORT   STATE  SERVICE REASON       VERSION
53/tcp closed domain  reset ttl 63
Final times for host: srtt: 65541 rttvar: 49239  to: 1000000

nslookup 10.129.2.210
dig 10.129.2.210 ANY

# Full port scan on 10.129.2.48
sudo nmap -sS -p- -T1 --source-port 53 --scan-delay 1s -v -d 10.129.2.48

# Full port scan on 10.129.2.210
sudo nmap -sS -p- -T1 --source-port 53 --scan-delay 1s -v -d 10.129.2.210

# Service version detection on discovered open ports (example ports 80 and 443)
sudo nmap -sV -p 80,443 --dns-server 8.8.8.8 --script="service-version" -v -d 10.129.2.48
sudo nmap -sV -p 80,443 --dns-server 8.8.8.8 --script="service-version" -v -d 10.129.2.210

http://10.129.2.48/status.php


sudo nmap -sS -sV -p 53 -T1 --source-port 53 --scan-delay 1s --dns-server 8.8.8.8 --script="dns-service-discovery,dns-recursion,dns-zone-transfer" -v -d 10.129.229.175 –packet-trace

nc 10.129.229.175 53

sudo nmap -sSU -p 53 --script dns-nsid 10.129.229.175
PORT   STATE    SERVICE
53/tcp filtered domain
53/udp open     domain
| dns-nsid: 
|_  bind.version: HTB{GoTtgUnyze9Psw4vGjcuMpHRp}

sudo nmap -sU -p 53 --scan-delay 1s --dns-server 8.8.8.8 --script="dns-service-discovery,dns-recursion,dns-zone-transfer" -v -d 10.129.229.175
PORT   STATE SERVICE REASON
53/udp open  domain  udp-response ttl 63
Final times for host: srtt: 65480 rttvar: 49175  to: 1000000

;; OPT PSEUDOSECTION:
; EDNS: version: 0, flags:; udp: 4096
; COOKIE: 20d5279e9b155f8b253c4a34664880bff2bcb050cf8b2372 (good)
;; QUESTION SECTION:
;version.bind.			CH	TXT

;; ANSWER SECTION:
version.bind.		0	CH	TXT	"HTB{GoTtgUnyze9Psw4vGjcuMpHRp}"

;; AUTHORITY SECTION:
version.bind.		0	CH	NS	version.bind.


```
### Service Version Detection IPS/IDS 3
 Now our client wants to know if it is possible to find out the version of the running services. Identify the version of service our client was talking about and submit the flag as the answer.  

FTP (File Transfer Protocol): Used for transferring large files over the internet.  
SFTP/FTPS (Secure FTP): Secure versions of FTP that use encryption to protect data during transfer.  
HTTP/HTTPS: Web servers that might host large files for download. 

```sh
┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-c1qvcg4v7i]─[~]
└──╼ [★]$ nmap 10.129.2.47
Starting Nmap 7.93 ( https://nmap.org ) at 2024-05-18 11:23 BST
Nmap scan report for 10.129.2.47
Host is up (0.065s latency).
Not shown: 869 closed tcp ports (conn-refused), 129 filtered tcp ports (no-response)
PORT   STATE SERVICE
22/tcp open  ssh
80/tcp open  http

Nmap done: 1 IP address (1 host up) scanned in 2.50 seconds

nmap -p 22,80 -sV 10.129.2.47
PORT   STATE SERVICE VERSION
22/tcp open  ssh     OpenSSH 7.6p1 Ubuntu 4ubuntu0.7 (Ubuntu Linux; protocol 2.0)
80/tcp open  http    Apache httpd 2.4.29 ((Ubuntu))
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel

sudo nmap -sS -sV -p 21,22,80,443,445,873,2049 --scan-delay 1s -v -d 10.129.220.154
PORT     STATE    SERVICE      REASON         VERSION
21/tcp   closed   ftp          reset ttl 63
22/tcp   open     ssh          syn-ack ttl 63 OpenSSH 7.6p1 Ubuntu 4ubuntu0.7 (Ubuntu Linux; protocol 2.0)
80/tcp   open     http         syn-ack ttl 63 Apache httpd 2.4.29 ((Ubuntu))
443/tcp  filtered https        no-response
445/tcp  filtered microsoft-ds no-response
873/tcp  filtered rsync        no-response
2049/tcp closed   nfs          reset ttl 63
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel

#Full port scan
sudo nmap -sS -p- --scan-delay 1s -v -d 10.129.220.154

#Service Version Detection
sudo nmap -sS -sV -p 22,80 --scan-delay 1s -v -d 10.129.220.154
PORT   STATE SERVICE REASON         VERSION
22/tcp open  ssh     syn-ack ttl 63 OpenSSH 7.6p1 Ubuntu 4ubuntu0.7 (Ubuntu Linux; protocol 2.0)
80/tcp open  http    syn-ack ttl 63 Apache httpd 2.4.29 ((Ubuntu))
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel
Final times for host: srtt: 8899 rttvar: 5147  to: 1000000

#Check DNS info
dig @10.129.220.154 version.bind txt chaos

#Scan for open ports
sudo nmap -sS -T4 -p- -v 10.129.220.154

#UDP probes
sudo nmap -sU -T4 -p- -v 10.129.220.154

#Evasive Scan
sudo nmap -sS -p- -T2 -f --scan-delay 500ms -D RND:10 --randomize-hosts --source-port 80 -v 10.129.220.154

#system
10.129.2.47


```
It seems that it there is only 2 open ports on the system, p22 and p80, and htb is not looking for their service versions.  
checking for UDP access  
```bash
sudo nmap -sSU -p- -sV 10.129.2.47

sudo nmap -p- -sA 10.129.2.47

sudo nmap 10.129.2.47 -p- -sA -Pn -n --disable-arp-ping --packet-trace

#decoy scan
sudo nmap 10.129.2.47 -p 22,80 -sV -Pn -n --disable-arp-ping --packet-trace -D RND:5
PORT   STATE SERVICE VERSION
22/tcp open  ssh     OpenSSH 7.6p1 Ubuntu 4ubuntu0.7 (Ubuntu Linux; protocol 2.0)
80/tcp open  http    Apache httpd 2.4.29 ((Ubuntu))
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel


```
IDS/IPS
Like the firewall, the intrusion detection system (IDS) and intrusion prevention system (IPS) are also software-based components. IDS scans the network for potential attacks, analyzes them, and reports any detected attacks. IPS complements IDS by taking specific defensive measures if a potential attack should have been detected. The analysis of such attacks is based on pattern matching and signatures. If specific patterns are detected, such as a service detection scan, IPS may prevent the pending connection attempts.

Detect IDS/IPS
Unlike firewalls and their rules, the detection of IDS/IPS systems is much more difficult because these are passive traffic monitoring systems. IDS systems examine all connections between hosts. If the IDS finds packets containing the defined contents or specifications, the administrator is notified and takes appropriate action in the worst case.

IPS systems take measures configured by the administrator independently to prevent potential attacks automatically. It is essential to know that IDS and IPS are different applications and that IPS serves as a complement to IDS.

Several virtual private servers (VPS) with different IP addresses are recommended to determine whether such systems are on the target network during a penetration test. If the administrator detects such a potential attack on the target network, the first step is to block the IP address from which the potential attack comes. As a result, we will no longer be able to access the network using that IP address, and our Internet Service Provider (ISP) will be contacted and blocked from all access to the Internet.

IDS systems alone are usually there to help administrators detect potential attacks on their network. They can then decide how to handle such connections. We can trigger certain security measures from an administrator, for example, by aggressively scanning a single port and its service. Based on whether specific security measures are taken, we can detect if the network has some monitoring applications or not.

One method to determine whether such IPS system is present in the target network is to scan from a single host (VPS). If at any time this host is blocked and has no access to the target network, we know that the administrator has taken some security measures. Accordingly, we can continue our penetration test with another VPS.

Consequently, we know that we need to be quieter with our scans and, in the best case, disguise all interactions with the target network and its services.

```bash
sudo nmap 10.129.2.47 -p 22,80,50000 -sV -sS -Pn -n --disable-arp-ping --packet-trace --source-port 53 -e tun0 -D RND:10
PORT      STATE SERVICE    VERSION
22/tcp    open  ssh        OpenSSH 7.6p1 Ubuntu 4ubuntu0.7 (Ubuntu Linux; protocol 2.0)
80/tcp    open  http       Apache httpd 2.4.29 ((Ubuntu))
50000/tcp open  tcpwrapped
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel
FallenOverlord@htb[/htb]$ ncat -nv --source-port 53 10.129.2.47 50000
ncat -nv --source-port 53 10.129.2.47 50000

sudo nmap 10.129.2.47 -n -Pn -p 445 -O
sudo nmap 10.129.2.47 -n -Pn -p 445 -O -S 10.129.2.200 -e tun0

sudo nmap 10.129.2.47 -p 50000 -sS -Pn -n --disable-arp-ping --packet-trace --source-port 53
PORT      STATE SERVICE
50000/tcp open  ibm-db2

nc -nv -p 53 10.129.2.47 50000

sudo nmap 10.129.2.47 -p 50000 -sS -Pn -n --disable-arp-ping --packet-trace

nc -nv -p 53 10.129.2.47 50000 
┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-78pmn8pedq]─[~/Desktop]
└──╼ [★]$ nc -nv -p 53 10.129.2.47 50000 
Ncat: Version 7.93 ( https://nmap.org/ncat )
libnsock mksock_bind_addr(): Bind to 0.0.0.0:53 failed (IOD #1): Permission denied (13)
Ncat: TIMEOUT.

sudo nc -nv -p 53 10.129.2.47 50000
┌─[us-academy-2]─[10.10.14.125]─[htb-ac-1136328@htb-78pmn8pedq]─[~/Desktop]
└──╼ [★]$ sudo nc -nv -p 53 10.129.2.47 50000
Ncat: Version 7.93 ( https://nmap.org/ncat )
Ncat: Connected to 10.129.2.47:50000.
220 HTB{kjnsdf2n982n1827eh76238s98di1w6}


10.129.42.195
# find other name server
dig ns inlanefreight.htb @10.129.42.195
# find the server version
dig CH TXT version.bind @10.129.42.195
# show all disclosable available entries(zone included)
dig any inlanefreight.htb @10.129.14.128

# Zone Trans
TCP -p 53 using rndc-key

# may show internal ip addr/ hostnames
dig axfr inlanefreight.htb @10.129.42.128

# brute force possible host names
dig axfr internal.inlanefreight.htb @10.129.42.128

# subdomain bruteforcing
for sub in $(cat /opt/useful/SecLists/Discovery/DNS/subdomains-top1million-110000.txt);do dig $sub.inlanefreight.htb @10.129.42.195 | grep -v ';\|SOA' | sed -r '/^\s*$/d' | grep $sub | tee -a subdomains.txt;done

#alternative
dnsenum --dnsserver 10.129.42.195 --enum -p 0 -s 0 -o subdomains.txt -f /opt/useful/SecLists/Discovery/DNS/subdomains-top1million-110000.txt inlanefreight.htb


```



