# API-REST-Smartwatch
## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Use](#use)

## General Info
The goal of this API REST is to simulate a smartwatch which send some datas to the RabbitMQ Broker, and get the datas using a website and show the personal graphs, or make some statistic.

## Technologies
***
List of technologies used within the project:
* [Payara Server](https://www.payara.fish/): Version 5.2021.10
* [Project Lombok](https://projectlombok.org/): Version 1.18.22
* [JSON Web Token](https://jwt.io/): Version 2.1.3
* [Swagger-Ui](https://swagger.io/tools/swagger-ui/): Version 3.0
* [Bootstrap](https://getbootstrap.com/): Version 5.1.3
* [RabbitMQ](https://www.rabbitmq.com/): Version 3.9.13

## Installation
A little intro about the installation. 
1. Clone the repository
```
$ git clone https://github.com/MattRodriguez64/API-REST-Smartwatch.git
```
## Use
1. Open the project with Java IDE (Eclipse IDE)
2. Compile the project with Maven Project (add to goals : clean install)
3. Start the docker container with : ```$ docker run --rm -it -p 15672:15672 -p 5672:5672 --name my-rabbit -d rabbitmq:3-management```
4. Go to the docs : http://localhost:8080/smartwatch/docs/index.html
5. Take the Auth URI and make a POST request with login and password in JSON format
6. Get your precious Token
7. Make all Requests you want (Launch as Java App the Producer to see RabbitMQ)
8. OR you can use the website : https://github.com/MattRodriguez64/Smartwatch-FlaskServer
