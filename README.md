# ApacheFOP Engine API
This is a project to run ApacheFOP as a dockerized java Api

It has two endpoints:

    POST    <root>/fop/

that takes a JSON object as a body looking like this:

    {
        "Xslt": "PD94bWwgdmVyc2lvbj0iMS4wI==...",
        "Xml": "PD94bWwgdmVyc2lvbj0iMS4wI==...",
    }

The strings are both Base64-encoded XML-strings.

The other endpoint is:

    POST <root>/fop/<templatename>

that takes the XML file as a body. The templatename added to the path needs 
to be added to the image beforehand. This means that to use this endpoint 
the templates need to be added to the image.

If you want to you can run the docker container with a volume bound here: 

    /src/main/resources/templates

and then you can maintain your templates in that volume.