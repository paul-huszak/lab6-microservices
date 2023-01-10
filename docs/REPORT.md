# LAB 6 - MICROSERVICES REPORT

### Eureka server

Booting the Eureka server. It's a server for microservices registration, location, load balancing and fault tolerance. 
Eureka register the different instances of existing microservices in the system.

```bash
./gradlew registration:bootRun
```

![registration](resources/registration.png)

It's exposed at `http:localhost:1111`

![port_1111](resources/port_1111.png)

### Account service

The account service uses Spring Data to implement de repository and sprint 
Rest to provide a RESTful interface to account information.

It registers itself with the `discovery-server` at start-up. In this example the name it's `accounts-service`.

Booting the Account service
```bash
./gradlew accounts:bootRun
```

![accounts](resources/accounts.png)

It's exposed at `http:localhost:2222`

![port_2222](resources/port_2222.png)

### Web service

The web controller is a typical Spring MVC view-based controller returning HTML.

It also registers itself with the `discovery-server`. In this example the name it's `web-service`.

Booting the Web service
```bash
./gradlew web:bootRun
```

![web](resources/web.png)

It's exposed at `http:localhost:3333`

![port_3333](resources/port_3333.png)

### Extra account service

We're adding another accounts services to the service changing the port in the `application.yaml` file where it's configurated.
![port_4444_setup](resources/port_4444_setup.png)

Then we boot again the extra account services
```bash
./gradlew accounts:bootRun
```
![accounts_2](resources/accounts_2.png)

It's exposed at `http:localhost:4444`

![port_4444](resources/port_4444.png)

### Using the application
We can see that in the Eureka registration server the new service it's registered with the correct port.
![port_1111_2](resources/port_1111_2.png)

### Killing server 2222

What happens when we kill the accounts server `2222` and do requests to web server `3333`?

When we kill the accounts server `2222`:

![kill_port_2222_command](resources/kill_port_2222_command.png)

It won't be exposed at `http:localhost:2222` anymore.

![kill_port_2222](resources/kill_port_2222.png)

On the other side, the first time that you do a request to web it will display:

![first_fetch_3333](resources/first_fetch_3333.png)

But the next time, the request will still be sent to the client.

![second_fetch_3333](resources/second_fetch_3333.png)

### Why is it working?
It's working because Eureka searches if there is one application available to do the task, it won't search for the exact server.
If it fails, it redirects the next requests to the service that it knows is registered and available.