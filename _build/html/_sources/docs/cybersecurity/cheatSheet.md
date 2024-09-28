# Commands Cheatsheet

## Footprinting
### IMAP/POP3
```bash
# Scan the server for ICMP/POP3 ports for server info
sudo nmap 10.129.84.93 -sV -p110,143,993,995 -sC

# find FQDN info
sudo nmap 10.129.84.93 -sV -p110,143,993,995 -sC | grep 'commonName'

# login to mailbox using imaps
curl -k 'imaps://10.129.84.93' --user robin:robin

# show TLS version and SSL cert when logging in
curl -k 'imaps://10.129.14.128' --user cry0l1t3:1234 -v

# interact with pop3 server
openssl s_client -connect 10.129.84.93:pop3s

USER <username> #Identify the user
PASS <password> #login using his/hers password
QUIT

# interact with ICMP server
openssl s_client -connect 10.129.84.93:imaps
1 LOGIN <usr>:<pswd> # login
1 LIST "" *         # list directories
1 CREATE "<dir_name>" #create new directory
1 DELETE "<dir>"
1 SELECT "<dir>"    # select to access a mailbox
1 FETCH <email_id> all # get sender info
1 FETCH <email_id> BODY[] # get email content
1 UNSELECT "<dir>"  # exits the selected mailbox
1 LOGOUT

```
### SMTP
```bash

# Interact with SMTP Server
telnet 10.129.84.93 25
HELO mail1.inlanefreight.htb # init session
EHLO mail1                  # init session
VRFY <usr_name>             # verify if the user exist


sudo nmap 10.129.84.93 -sC -sV -p25

sudo nmap 10.129.84.93 -p25 --script smtp-open-relay -v
```

### DNS
```bash

#grep admin email info
dig soa <target_ip>

# NS record: FQDN name
dig ns inlanefreight.htb @10.129.202.7

# dns server version
dig CH TXT version.bind 10.129.202.7

# all available records
dig any inlanefreight.htb @10.129.202.7

# performs a zone transfer for the DNS server at a target ip
dig axfr inlanefreight.htb @10.129.202.7

# may show internal ip addr
dig axfr internal.inlanefreight.htb @10.129.202.7

# subdomain brute forcing
for sub in $(cat /opt/useful/SecLists/Discovery/DNS/subdomains-top1million-110000.txt);do dig $sub.inlanefreight.htb @10.129.202.7 | grep -v ';\|SOA' | sed -r '/^\s*$/d' | grep $sub | tee -a subdomains.txt;done

dnsenum --dnsserver 10.129.202.7 --enum -p 0 -s 0 -o subdomains.txt -f /opt/useful/SecLists/Discovery/DNS/fierce-hostlist.txt dev.inlanefreight.htb

```

### Lab
```bash
PORT     STATE SERVICE
21/tcp   open  ftp
22/tcp   open  ssh
53/tcp   open  domain
2121/tcp open  ccproxy-ftp




```

```bash

ceil:qwer1234
```

#### SSH
```bash
cat /etc/ssh/sshd_config  | grep -v "#" | sed -r '/^\s*$/d'

dig soa 10.129.203.129

git clone https://github.com/jtesta/ssh-audit.git && cd ssh-audit

./ssh-audit.py 10.129.203.129

ssh -v ceil@10.129.203.129 -o PreferredAuthentications=password

cd ~/.ssh

ssh-keygen -t rsa -b 4096 -C "your_email@example.com"

ssh-copy-id ceil@10.129.136.243

chmod 700 ~/.ssh
chmod 600 ~/.ssh/id_rsa
chmod 644 ~/.ssh/id_rsa.pub

chmod 700 ~/.ssh
chmod 600 ~/.ssh/authorized_keys

ssh ceil@10.129.203.129

```
```sh

ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDMMIdh7gRCYQoImViBLcAtXzfPlgNmWVsFi5AyDz21U2+c+FqRgt9IPDDKGenuVVJzm+5zQ+ptNdehnKqCe68s5YkDmJTO7oAFqGv/eO1jQAYjsmcXIUr3cLeQI+BJgjKMtoVbDKN89qLJN/14cuQU2dtHjYXlrCu25O5F4pjWy4Jf6BuVtl6PA54isirbggjmurbY/1fBujLWPW1hliX+PjakL1ioQ56NRY+Z9EcOFvGoSTB1NrOhCEe6l+ZoyRIKC6DGaY6rre5raM9P34KMtgsYobQMV3jYJxswV0fKE/K6ymyCErjXYiMFz6K9TZ87RzryCNAK4A5m9h2jNuPV7KFvuI4BHtfZZIadBkwMeaRqs5d1C6lNIagjgm9sSWcwaEhjiPQCR8h4cFvrsHyH+sFKMu2bHXgZvZsButc+iTxK8/XjVQudSff1gPGjXS5LS1LxMkjXtkyniFPwSmqmmcGfDeObkPmBC/a+Yriw6VpCkRM7IixzFiIhl46IqrJpLxovGu665q+C+zg+RM+ATdDO+m4ONxo3vfIbvg8OG15FjRhjdkfiiVepAjf+unsblBMphbdXTv5P2cQk7eYmlGsCPiF50jZ+7rOm/k4ETaf4DxBOXQhkq48+rTE8VWggwuTKorwrfCfCoqtlWIyQejlBtj83D/uk0uUd7PnuYw== your_email@example.com
```


doenst work
```sh
ssh 10.129.136.243

ssh-copy-id 10.129.136.243

fPve51nX
```

#### SQL Lab
```bash
sudo nmap 10.129.202.41 -sV -sC -p3306 --script mysql*

sudo nmap --script ms-sql-info,ms-sql-empty-password,ms-sql-xp-cmdshell,ms-sql-config,ms-sql-ntlm-info,ms-sql-tables,ms-sql-hasdbaccess,ms-sql-dac,ms-sql-dump-hashes --script-args mssql.instance-port=1433,mssql.username=sa,mssql.password=,mssql.instance-name=MSSQLSERVER -sV -p 1433 10.129.202.41

sudo nmap 10.129.202.41 -p111,2049 -sV -sC

sudo nmap --script nfs* 10.129.202.41 -sV -p111,2049

showmount -e 10.129.202.41

mkdir target-NFS

sudo mount -t nfs 10.129.202.41:/ ./target-NFS/ -o nolock

cd target-NFS

tree .


```

## Info gathering web

```bash

export TARGET="tesla.com"
whois $TARGET | grep '@'

FallenOverlord@htb[/htb]$ export TARGET="facebook.com"
FallenOverlord@htb[/htb]$ nslookup $TARGET

FallenOverlord@htb[/htb]$ dig facebook.com @1.1.1.1

export TARGET="app.inlanefreight.local"
nslookup $TARGET | grep 'Apa'


```
