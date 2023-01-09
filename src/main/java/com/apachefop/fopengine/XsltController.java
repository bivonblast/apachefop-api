package com.apachefop.fopengine;

import org.apache.fop.apps.FOUserAgent;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Base64;


@RestController
public class XsltController {

	@RequestMapping(value = "/fop", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> fop(@RequestBody FopInput body) {
        try {
            // Construct a FopFactory by specifying a reference to the configuration file
            // (reuse if you plan to render multiple documents!)
            FopFactory fopFactory = FopFactory.newInstance(new File("/src/main/resources/fop.xconf.xml"));

            // Step 2: Set up output stream.
            ByteArrayOutputStream originalOutputStream = new ByteArrayOutputStream();

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, originalOutputStream);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new StringReader(new String(Base64.getDecoder().decode(body.Xslt)))));

            // Set the value of a <param> in the stylesheet
            transformer.setParameter("versionParam", "2.0");

            // Setup input for XSLT transformation
            //Source src = new StreamSource("src/main/resources/article.xml");
            Source src = new StreamSource(new StringReader(new String(Base64.getDecoder().decode(body.Xml))));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);

            var headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=generated.pdf");

            final PipedOutputStream out = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(out);
            // in a background thread, write the given output stream to the
            // PipedOutputStream for consumption
            new Thread(() -> {
                try {
                    originalOutputStream.writeTo(out);
                    originalOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(in));

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/fop/{templateName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> fop(@PathVariable String templateName, @RequestBody String body) {
        try {
            // Construct a FopFactory by specifying a reference to the configuration file
            // (reuse if you plan to render multiple documents!)
            FopFactory fopFactory = FopFactory.newInstance(new File("/src/main/resources/fop.xconf.xml"));

            // Step 2: Set up output stream.
            ByteArrayOutputStream originalOutputStream = new ByteArrayOutputStream();

            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, originalOutputStream);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource("/src/main/resources/templates/" + templateName));

            // Set the value of a <param> in the stylesheet
            transformer.setParameter("versionParam", "2.0");

            // Setup input for XSLT transformation
            //Source src = new StreamSource("src/main/resources/article.xml");
            Source src = new StreamSource(new StringReader(body));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);

            var headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=" + templateName + ".pdf");

            final PipedOutputStream out = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(out);
            // in a background thread, write the given output stream to the
            // PipedOutputStream for consumption
            new Thread(() -> {
                try {
                    originalOutputStream.writeTo(out);
                    originalOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(in));

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/fo", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> fo(@RequestBody String body) {
        try {
            // Construct a FopFactory by specifying a reference to the configuration file
            // (reuse if you plan to render multiple documents!)
            FopFactory fopFactory = FopFactory.newInstance(new File("/src/main/resources/fop.xconf.xml"));

            // Step 2: Set up output stream.
            ByteArrayOutputStream originalOutputStream = new ByteArrayOutputStream();

            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, originalOutputStream);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            // Set the value of a <param> in the stylesheet
            transformer.setParameter("versionParam", "2.0");

            // Setup input for XSLT transformation
            //Source src = new StreamSource("src/main/resources/article.xml");
            Source src = new StreamSource(new StringReader(body));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);

            var headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=generated.pdf");

            final PipedOutputStream out = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(out);
            // in a background thread, write the given output stream to the
            // PipedOutputStream for consumption
            new Thread(() -> {
                try {
                    originalOutputStream.writeTo(out);
                    originalOutputStream.close();
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(in));

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
}
