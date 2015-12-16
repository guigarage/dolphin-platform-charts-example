# Dolphin Platform Charts Sample

![Dolphin Platform Logo](http://www.guigarage.com/wordpress/wp-content/uploads/2015/10/logo.png)

This project contains a Dolphin Platform based client server application that shows how JavaFX charts can be used with Dolphin Platform.
The project is currently based on Dolphin Platform 0.7 and uses a Spring Boot based server and a JavaFX based client. Currently the use of converters for JavaFX List bindings is not part of Dolphin Platform and will be added to the next release. Therefore the helper class FXWrapper2 is added to the client module. In future Dolphin Platform releases this functionallity will be part of the platform API.  

![Client Preview](http://www.guigarage.com/wordpress/wp-content/uploads/2015/12/dolphin-charts.png)


The Maven projects contains 3 modules: 

* The __common__ module that contains the model taht is shared between client and server
* The __server__ module that contains the Spring Boot Application and a first Dolphin Platform controller 
* The __client__ module a JavaFX application that creates a view that is bound to the Dolphin Platform controller in the server module


## How to use it
The project is created as a Maven project. You can directly import it in any IDE that supports Maven (like Eclipse, Netbeans or IntelliJ).
If you want to start the application you need to start the __ServerApplication__ class in the server module and than the __ClientApplication__ class in the client module. The client can be started several times.

If you want to start the application from commandline without IDE support you need to call a Maven install first in the main project folder:

```
mvn install
```

Once this is done you can simply install the server from the server folder
```
cd server
mvn spring-boot:run
```

A client instance can be started by Maven, too:
```
cd client
mvn exec:java
```
