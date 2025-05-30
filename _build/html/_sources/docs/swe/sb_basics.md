# Spring Boot Basics

## Prepration

`java -version` need to be 17 or 21  
`mvn -v`to test maven version

1. You will need a project template  
    -   find one on `https://start.spring.io`
    -   Project: Maven (default) Language: Java Spring Boot: 3.3.x (latest stable).

    -   Group: com.example Artifact: demo  (the site fills the rest).
    -   Dependencies – click these checkboxes: Spring Web (gives you REST + embedded Tomcat )(Optional) Lombok (removes boiler-plate; I’ll demo it).

    -   click generate and download the zip
    -   In IDEA, file->open->the unzipped folder
## Booting the first application
putting the controller files in the right place
```bash
demo
└─ src
   └─ main
      └─ java
         └─ com
            └─ example
               └─ demo
                  └─ controller   <-- put controller(s) here
```

## Testing
hot reload realized using 2 different methods
1. devTools
add `developmentOnly("org.springframework.boot:spring-boot-devtools")`
inside build.gradle -> dependencies
`File ▸ Settings ▸ Build, Execution, Deployment ▸ Compiler` click to checkmark `Build project automatically`

start the local server `.\gradlew.bat bootRun`

2. turbo-loop
```bash
# Terminal 1 – continuous compile
PS demo> .\gradlew.bat build --continuous

# Terminal 2 – run the app (DevTools handles restarts)
PS demo> .\gradlew.bat bootRun
```
