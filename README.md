# Robo Resume
## Description
This app works in both English and Spanish.  The default language is US English.  To switch to US Spanish, simply append _?lang=es_US_ to the end of any URL and refresh the page.  The app will stay in the last chosen language until the user manually switches by appending a lang parameter again, or the app is restarted.  To switch back to English, append _?lang=en_US_. A resource bundle is used to automatically handle the language text. To make this happen, I needed a couple extra Beans in the main app (SessionLocaleResolver and LocaleChangeInterceptor).  I also extended WebMvcConfigurerAdapter, and overrode addInterceptors.  Thymeleaf handles this nicely in the HTML with `th:text="#{[resource bundle key]}`.

A very simple home page allows a user to add a new resume, or view a list of all resumes currently in the db. 
 Form fields are: first and last name, email, organization, and start and end dates.  The user must enter a first and last name, and a start date. If any form data is invalid, an error message is shown (in the appropriate language).  If an email is entered, it will be validated, otherwise it may be empty.  The start date is always validated, for both null and correct format (MM/DD/YYYY).  If no end date is entered, it is assumed that the applicant of the resume is currently employed at that job.  After successfully entering resume detail info, the employment duration (in days) is calculated in MainController.java, displayed in a confirmation page, added to the Resume model object in question, and finally it is all saved to a Mysql database.

## Requirements to run
* Mysql server on you local machine with a database named _resumedb_

A new table is created every time this app is run, so that it doesn't crash the first time.
To persist the data, comment out the following line in application.properties file: 
`spring.jpa.hibernate.ddl-auto=create`
and remove the comment from this line:
`spring.jpa.hibernate.ddl-auto=none`

## Technologies used
* Java
* Spring Boot
* Thymeleaf
* Mysql
* Maven
