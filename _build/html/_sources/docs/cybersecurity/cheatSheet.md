# Commands
## nmap Commands

`sudo nmap <target> <argumentns>` *target*: i.e. 10.129.2.18; 10.129.2.0/24  

Scanning Options
- `-F `Scan top 100 ports
- -`sn` Disables port scanning
- `-Pn` Disable ICMP Echo Requests
- `-n` Disable DNS Resolution
- `-p-` Scan all ports
- `-p25-38` Scan ports between 25 and 38
- `-p24, 34 `Scan Port 24 and 34
- `-sS` perfom TCP SYN-Scan
- `-sA` perform TCP ACK-Scan
- `-sU` perform UDP scan
- `-sV` scan against service versions
- `-O` scan the target Operating System
- `-A` OS Dectection, Service Detection, tracerout scans

Output Options

`sudo nmap 10.129.2.28 -p 25 --script banner,smtp-commands` Use defined scripts  

`sudo nmap 10.129.2.28 -p 80 -A` Agressive Scan: find **Webserver**, web app and the **title** of the **webpage**. It also guess the **Operating System**.  

`sudo nmap 10.129.2.28 -p 80 -sV --script vuln` Vulnerability Assessment: detect service **version**, check for **known vulnerabilities**.  


