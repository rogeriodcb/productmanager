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

## Back-End-Server
The Back-End-Server is a Rest API responsible to execute CRUD in database and return the request. This server uses Spring Security and the requester only be able to execute CRUD through a user password protection.
This API create, retrieve, update, delete products.
Below the request and how to use:

Methods	Urls	Actions
POST	/api/products					create new product
GET		/api/products					retrieve all products
GET		/api/products?nome=[keyword]	find all products which name contains keyword
GET		/api/products/{id}				retrieve a product by id
PUT		/api/products/{id}				update a product by id
DELETE	/api/products/{id}				delete a product by id
DELETE	/api/products					delete all products

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

