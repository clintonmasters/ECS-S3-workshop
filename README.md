# ECS-S3-workshop
Java examples for interacting with EMC ECS and the S3 protocol.  Originally created by [Jason Cwik](https://github.com/jasoncwik).


To run:

1. Add Java certificate.

2. Run from a command prompt or terminal: `./gradlew eclipse`.  On Windows this will be: `gradlew.bat eclipse`

4. Download "Eclipse IDE for Java Developers": http://www.eclipse.org/downloads/

5. Launch Eclipse

6. Use File->Import...->Existing projects

7. Choose the directory you download and select the vipr-s3-workshop project to import

8. Edit ViPRS3Factory.java with your credentials and endpoint. Change S3_ACCESS_KEY_ID, S3_SECRET_KEY, and S3_ENDPOINT.

8. Change S3_BUCKET to a unique identifier. 

9. Right-click on the sample java (e.g. _0_GetServiceInfo.java) and select Run As..->Java Application

