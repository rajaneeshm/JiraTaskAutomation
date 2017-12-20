# Jira

Use JIRA Rest API to access JIRA Issues.

This application gets data from JIRA and notifies users based on defined conditions in application and updates reuired data in google sheets.

Running the application:


properties :

###### JIRA Confuguration ########

jira.username = JIRA Username

jira.password = Password

jira.url = http://team.company.com


###### JavaMail Configuration ########

spring.mail.username=gmail

spring.mail.password=password

support.email=fromUser@mail.com  (To and from email for testing)


Add google auth JSON file in resources folder :

 follow instuctions from :  https://developers.google.com/sheets/api/quickstart/java
 
  or 
  
  follow instructions:
  
  Use this  https://developers.google.com/sheets/api/quickstart/java 
   to create or select a project in the Google Developers Console and automatically turn on the API. 
   Click Continue, then Go to credentials.
	On the Add credentials to your project page, click the Cancel button.
	At the top of the page, select the OAuth consent screen tab. Select an Email address, enter a Product name if not already set, and 	click the Save button.
	Select the Credentials tab, click the Create credentials button and select OAuth client ID.
	Select the application type Other, enter the name "Google Sheets API Quickstart", and click the Create button.
	Click OK to dismiss the resulting dialog.
	Click the file_download (Download JSON) button to the right of the client ID.
	Move this file to your working directory and rename it client_secret.json.
 
 
 
 
 mvn install
   
