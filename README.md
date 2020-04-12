# WP_Video_Games
# Kiril Kostov 163070
# University project for the purposes of the course Web Programming
## Project Info
This project is an application about buying video games made with Java Spring Boot(backend) and React(frontend). <br/>
## Backend startup
To start the backend application, you have to provide database information in the **application.properties** file.
The current database name is videogames. We can see this in
```
spring.datasource.url=jdbc:mysql://localhost:3306/videogames?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC <br/>
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

We use hibernate, so the tables will be created automatically from the models. <br/>
Now, you can start the application by running **DemoApplication.java** <br/>
In this application we can: <br>
- see all video games on **http://localhost:8080/videoGames**. <br/>
- create video games on **http://localhost:8080/videoGames/add**. <br/>





## Frontend startup
To start the frontend application, please open a terminal and write **npm run start**
