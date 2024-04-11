# nmap Basic Commands and Examples

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

