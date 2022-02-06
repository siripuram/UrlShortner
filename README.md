# UrlShortner Project

This project is intended to build a URL shortening Service as given per Apple's team.
These are the below functionalities supported with this code.
1. A user should be able to load the index page of your site and be presented with an input field where they can enter a URL.
2. Upon entering the URL, a "shortened" version of that url is created and shown to the user as a URL to the site you are building.
3. When visiting that "shortened" version of the URL, the user is redirected to the original URL.
4. Additionally, if a URL has already been shortened by the system, and it is entered a second time, the first shortened URL should be given back to the user.
   For example, if I enter http://www.apple.com/iphone/ into the input field, and I'm running the app locally on port 9000, I'd expect to be given back a URL that looked something like http://localhost:9000/1. Then when I visit
   http://localhost:9000/1,

## CODE BASE : 

https://github.com/siripuram/UrlShortner.git 

## TECHNOLOGIES

This application is built using Springboot with below modules:
* Sprig framework (Springboot, SpringWeb, Annotations)
* H2DB local In-memory DB
* Mockito Junit Test Cases
* SonarLint validation for code coverage 
* Apache Maven for build and deployments
* Standard HTMl for Front End along with Simple JavaScript for client side validations.

## THE MAIN Modules
The main parts of the code base are

* main java classes focusing on the MVC architecture appraoch 
    * controllers
    * services + services Implementations
    * Data Models + Entity Objects (respositories)
    * DTO's
    * Util Classes
* Test Cases:
    * Junit Mockito
* SonarLint
    * For Coverage 
* Resources for Static files + JS and CSS filess
* Application Properties: for the H2 DB configurations and other Spring configs. 

## How to build the Application 

To build all the modules run in the project root directory the following command with Maven :
    mvn clean install

If the build iss succesful, you'll see a war file build in the target folder. 

You can also see the Test Cases passed along with the build details.

The war file name : UrlShortner-0.0.1-SNAPSHOT.war

## How to run the Appilcation 

To run the application please use the below command

java -jar  /target-folder/UrlShortner-0.0.1-SNAPSHOT.war

Ex: java -jar /Users/hari/Desktop/HariAssignment02042022/git/UrlShortner/target/UrlShortner-0.0.1-SNAPSHOT.war 


## DESIGN Choices :

I chose Spring Framework (Web & bootstrap) as Spring framework is pretty proven industry standard for the stand alone applications.

I used H2DB which is memory Database, since this is just a stand alone demo project. 
Please note that, with each run, the memory and the previous data will be reset since it is an in-memory DB.

I used below 2 DB tables  that persistent the data using the fields I defined in my entity object.
DB SCHEMAS:
1. TableName -> UrlEntity
   1. id (long)  # This is the AUTO Generated ID  which is used for creating unique short URL. 
   2. inputURL (String), #Original URL
   3. createdDate (Date) # Today's Date 
   4. expiryDate (Date)  # For now, I hard coded 7 Days expiry days and that can be extended easily. And if the expiry date is over, then you'll see Link Expired Message.
2. TableName -> UrlCheckSum 
   1. checkSum (String) # URL Checksum for unique shortened URL identifier.
   2. shortURL (String) # The Base62 encoded URL of the auto generated Id. 

For Use Case #1, 

    I created a simple index.html pages with basic JavaScript validation for URL validation and which will call the contorllber when the action is invoked.

For Use Case #2,

    For creating unique short URL's, I used an auto incremented ID in DB and encoding that value into  Base62 encoding to return the shorted URL string. 

For Use Case #3,

    When visiting the shortened URL, I decode  the shortened URL and get the original URL from the DB and redirect the request to 
    302 return code adding the original url to the location field.

For Use Case #4,

    I use URL Checksum (MD5 Hash) to make sure that, if a URL is shortened earlier and to return the same shortened key.


## Scalability Details 
Please refer to my Scalability Section when you consider for a full  production scale  deployments.

In the Realistic Production scenarios, for the sites like apple.com where we have huge traffic, we expect more redirects than creating unique URL's
Ex: The possible Read/Write ration can be 20:1 per sec

If we assume  1 write per sec, it'll be roughly 100K per day approxmately and for an year will be 365K writes. 
And reads 20 per sec : it'll be around 2M re-directs per day which is around 700M re-directs per year.

Based on above DB schemas, I expect it'll take around 500 Bytes for creating 1 URL entry and per year it'll be around 
180MB storage per year and for 5 years it'll be around 1TB.

For reads as discussed above with the assumption of 2M per day and  with a ration of 80%/20% , where 80% are same URL's and 20% are unique URL's,
I'll use a Cache in front of my DB and the cache estimation will be around 20% 1TB which is going to around (20%*1TB) = ~200 GB Cache.

For the DB design, since I do not have any relations between the Schemas, this can be easily scaled. 

And for scalability, I'll prefer a NoSQL DB with a Key/Value pair preferably Dynamo for storing the shorted key with URL's.
And for cache it'll be MemCache or AWS Elastic Cache. 

And for auto-generating Keys, whihc is used for creating the shortend URL's, I prefer to go with a Relational DB front ended by a Key generation service. 



