# Spring Boot Employee Management Project

CRUD RESTFul APIs for a Simple Employee Management System using Spring Boot 2 JPA and PostgreSQL database. Following are five REST APIs (Controller handler methods) are created for Employee resource.


# TechStack
* Spring Boot
* Hibernate/JPA
* REST API
* PostgreSQL Database

#Check out these two links to download and install a PostgreSQL database on your machine.
* https://www.postgresql.org/docs/9.3/tutorial-install.html
* http://www.postgresqltutorial.com/install-postgresql/

#We will also be registering this service to Eureka Service Registry

# Pom.xml entry for Eureka Client
```javascript
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			<version>2.2.2.RELEASE</version>
</dependency>
```

# Eureka Server URL in application.yml
```javascript
eureka:
    client:
        serviceUrl:
            defaultZone: http://localhost:8083/eureka
```            
            
# SpringBootApplication

* @EnableDiscoveryClient : Annotation used to enabling this service to be discovered by Eureka Server
```javascript
@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootRestHibernatePostgresCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestHibernatePostgresCrudApplication.class, args);	
	}
}
```
            
