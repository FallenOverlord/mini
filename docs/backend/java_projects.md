# My Projects

## PDF generator
### Requirements
Generate an email consists of a PDF file generated using Java.

### Solutions

#### Specific Requirement Breakdown

##### Functions
1. Getting the info that needs to be put in the pdf file
2. Generate a PDF file/ generate a file of some kind of format and convert it into PDF format
3. Choose a base email accnount and choose a target email account
4. error handelling process
5. exception handelling process

##### Constraints
1. Must use Java Programming Language

#### Walkthrought
```sh

C:\Users\Mars\IdeaProjects


```

PDFgen.java
```bash
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class PDFgen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("PDF File Name: ");
        String file = scanner.nextLine() + ".pdf";

        // Recipient's email ID needs to be mentioned.
        String to0 = "mars.wang@mail.utoronto.ca";
        String to = "chenxl8@asiainfo.com";
        // Sender's email ID needs to be mentioned
        String from = "3330525778@qq.com";
        final String username = "3330525778";
        //auth code, not real password
        final String password = "txgpyfurohtociid";

        // SMTP server config
        String host = "smtp.qq.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {
            createPdf(file, scanner);


            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            System.out.print("Subject: ");
            message.setSubject(scanner.nextLine());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Set Subject: header field
            System.out.print("Subject: ");
            message.setSubject(scanner.nextLine());

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(file);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");



        } catch (IOException e) {
            e.printStackTrace();
        }catch (MessagingException e2) {
            throw new RuntimeException(e2);
        }







    }

    private static void createPdf(String file, Scanner scanner) throws IOException {
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        PdfFont titleFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLDOBLIQUE);
        PdfFont myFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        Paragraph p1 = new Paragraph();
        System.out.print("PDF Title: ");
        p1.add(scanner.nextLine());
        p1.setTextAlignment(TextAlignment.CENTER);
        p1.setFont(titleFont);
        p1.setFontSize(28);
        doc.add(p1);

        Paragraph p3 = new Paragraph();
        p3.add("yoeaalsirrr!");
        p3.setFont(myFont);
        p3.setFontSize(18);
        doc.add(p3);


        doc.close();
    }





}

```
pom.xml
```sh
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>PDFgen</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>


    <dependencies>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>barcodes</artifactId>
            <version>7.2.1</version>
            <!-- barcodes depends on kernel -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>font-asian</artifactId>
            <version>7.2.1</version>
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>forms</artifactId>
            <version>7.2.1</version>
            <!-- forms depends on kernel and layout -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>hyph</artifactId>
            <version>7.2.1</version>
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>io</artifactId>
            <version>7.2.1</version>
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>kernel</artifactId>
            <version>7.2.1</version>
            <!-- kernel depends on io -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>layout</artifactId>
            <version>7.2.1</version>
            <!-- layout depends on kernel -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>pdfa</artifactId>
            <version>7.2.1</version>
            <!-- pdfa depends on kernel -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>sign</artifactId>
            <version>7.2.1</version>
            <!-- sign depends on kernel, layout and forms -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>commons</artifactId>
            <version>7.2.1</version>
            <!-- sign depends on kernel, layout and forms -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>styled-xml-parser</artifactId>
            <version>7.2.1</version>
            <!-- sign depends on kernel, layout and forms -->
        </dependency>

        <dependency>
            <groupId>com.itextpdf</groupId>
            <artifactId>svg</artifactId>
            <version>7.2.1</version>
            <!-- sign depends on kernel, layout and forms -->
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.13.3</version>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.13.3</version>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
            <version>2.13.3</version>
        </dependency>









        <dependency>
            <groupId>com.sun.mail</groupId>
            <artifactId>javax.mail</artifactId>
            <version>1.6.2</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>








    </dependencies>



</project>



```










