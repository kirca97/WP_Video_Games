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
The application is running on port 8080. <br/>
In this application we can: <br>
- see all video games on **http://localhost:8080/videoGames**. <br/>
- create video games on **http://localhost:8080/videoGames/add**. <br/>





## Frontend startup
To start the frontend application, please open a terminal and write **npm run start** <br/>
The application is running on port 3000 and is connected with the backend with *axios*.

## Project pictures

![home_page](https://i.ibb.co/BL6Mp9W/Screenshot-76.png)
![video_games](https://i.ibb.co/MnJzqWQ/Screenshot-78.png)
![details_page](https://i.ibb.co/P4K9frc/Screenshot-80.png)
![payment](https://i.ibb.co/5hfHpcS/Screenshot-82.png)

In order for stripe to always work, use these card number: **4242 4242 4242 4242**
