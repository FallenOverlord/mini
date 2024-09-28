# Web Footprinting

```bash

#services & sharable drives
smbclient -N -L //10.129.14.128

#connect to a specific shareable service
smbclient //10.129.14.128/<service>

#download files
get <file>

Command	Description

5WrS4aEh

smbclient -N -L //10.129.32.142	Null session authentication on SMB.

nmap -sC -sV 10.129.32.142 -p 445

nmap -p 445 --script smb-os-discovery 10.129.32.142

smbclient -L //10.129.32.142 -N

smbclient //<FQDN/IP>/<share>	Connect to a specific SMB share.

sudo apt-get install samba-common-bin

rpcclient -U "" 10.129.32.142	Interaction with the target using RPC.

#Enumerate Users
enumdomusers
queryuser <user_rid>
enumdomgroups
querygroupmem <group_rid>
#get system path
netshareenum
rpcclient $> srvinfo
Query	Description
srvinfo	Server information.
enumdomains	Enumerate all domains that are deployed in the network.
querydominfo	Provides domain, server, and user information of deployed domains.
netshareenumall	Enumerates all available shares.
netsharegetinfo sambashare	#Provides information about a specific share.
enumdomusers	Enumerates all domain users.
queryuser <RID>	Provides information about a specific user.

samrdump.py 10.129.32.142	Username enumeration using Impacket scripts.

smbmap -H 10.129.32.142	Enumerating SMB shares.
nmap --script banner 10.129.32.142 -p445
crackmapexec smb 10.129.32.142 --shares -u '' -p ''	#Enumerating SMB shares using null session authentication.

enum4linux-ng.py 10.129.32.142 -A	SMB enumeration using enum4linux.
```


NFS
```bash
showmount -e 10.129.32.142	#Show available NFS shares.
┌─[us-academy-2]─[10.10.14.63]─[htb-ac-1136328@htb-dqbdmnddna]─[~]
└──╼ [★]$ showmount -e 10.129.32.142
Export list for 10.129.32.142:
/var/nfs      10.0.0.0/8
/mnt/nfsshare 10.0.0.0/8

FallenOverlord@htb[/htb]$ mkdir target-NFS
FallenOverlord@htb[/htb]$ sudo mount -t nfs 10.129.32.142:/ ./target-NFS/ -o nolock
FallenOverlord@htb[/htb]$ cd target-NFS
FallenOverlord@htb[/htb]$ tree .

mount -t nfs 10.129.32.142://var/nfs 

umount ./target-NFS	#Unmount the specific NFS share.
```

## DNS
```bash
10.129.250.195

Interact with the target DNS using its IP address and enumerate the Fully Qualified Domain Name (FQDN) of it for the "inlanefreight.htb" domain.

dig ns inlanefreight.htb @10.129.250.195

+ 1  Identify if its possible to perform a zone transfer and submit the TXT record as the answer. (Format: HTB{...))

+ 1  What is the IPv4 address of the hostname DC1?

+ 1  What is the FQDN of the host where the last octet ends with "x.x.x.203"?
```

Commands
```bash
dig soa www.inlanefreight.com
dig soa www.inlanefreight.com





```


