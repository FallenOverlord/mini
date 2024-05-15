# CompTIA CySA+ (CS0-003)

## CVE

cve metrics  
AV means attack vector, AV: N means attack vecotr is network  

L: low; H: high  
AC: attack complexity  
PR: privilege escalation  
UI: user inteaction  
S: scope  
C: impact on confidentiality
I: integrety
A: availability  

## exploits
### clickjack
put another html page on the victim's webpage and try to trick them to clic smthing they shouldnt click  
take advantage of iframe, if framing is not allowed, clickjacking wont work  

hardening solurion
```bash
# block requests without an X-Frame-Options header
this will instruct the browswer not to display content without in the frame
```

## vulnerability scan report
the report must include the affected hosts and the severity of the vuln  
affected hosts: ip addr, os, services  
severity: measured using risk scores


## Remeidation & Patching
Mean Time to Remediation    a.k.a MTTR  
measures how long the firm can fix a zero day error  
only if MTTR < average time it takes for exploition after patches are out will the sys be safe.  


## Scripting Languages
bash, pearl, ruby, powershell, node.js, python

### Key difference
scripting languages use intepreters, it is read one line at a time, which makes it slower than executing machine code.
sever side and client side scripting languages, dynamic  

