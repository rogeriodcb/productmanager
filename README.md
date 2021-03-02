# Product Manager Project
This is a demonstration of back-end, front-end and db servers in a Docker container. The main goal is to execute CRUD in a database called "produto" (postgres database).

This project is organized as follow:

```
Back-End-Server/
    src/
        controllers/
            ...
        models/
            ...
Front-End-Server/
	src/
		...
        
Docker-compose.yml
Dockerfile
```

The Back-End-Server is a Rest API responsible to execute CRUD in database and return the request. This server uses Spring Security and the requester only be able to execute CRUD through a user password protection.

## Running in Docker container
### You need to install:
```
- docker
- docker-compose (if it is not installed in your operating system)
```

### To execute the application : 
At the same "docker-compose.yml" folder type:
```
- docker-compose down (if containers were previously uploaded)
- docker-compose build --no-cache
- docker-compose up -d
```

