# Jira

Use JIRA Rest API to access JIRA Issues.

This application gets data from JIRA and notifies users based on defined conditions in application and updates reuired data in google sheets.

Running the application:


properties :
################ JIRA Confuguration ############################
jira.username = JIRA Username

jira.password = Password

jira.url = http://team.company.com


################### JavaMail Configuration ##########################

spring.mail.username=gmail

spring.mail.password=password

support.email=fromUser@mail.com


Add google auth JSON file in resources folder :

 follow instuctions from :  https://developers.google.com/sheets/api/quickstart/java
 
 
 
 
 mvn install
   
