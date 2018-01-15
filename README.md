# Cielo LIO Payment Remote Java Client

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.frekele.cielo/cielo-lio-remote-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.frekele.cielo/cielo-lio-remote-client) 
[![Build Status](https://travis-ci.org/frekele/cielo-lio-remote-client.svg?branch=master)](https://travis-ci.org/frekele/cielo-lio-remote-client)
[![Coverage](https://codecov.io/gh/frekele/cielo-lio-remote-client/branch/master/graph/badge.svg)](https://codecov.io/gh/frekele/cielo-lio-remote-client)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8e49f3317817425abe7f67b02c590f1b)](https://www.codacy.com/app/frekele/cielo-lio-remote-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=frekele/cielo-lio-remote-client&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/8e49f3317817425abe7f67b02c590f1b)](https://www.codacy.com/app/frekele/cielo-lio-remote-client?utm_source=github.com&utm_medium=referral&utm_content=frekele/cielo-lio-remote-client&utm_campaign=Badge_Coverage)

### Remote integration with Cielo LIO Payment Order Management

Built-based on the documentation: [https://developercielo.github.io/manual/cielo-lio](https://developercielo.github.io/manual/cielo-lio)


#### Supported Java Versions:
- requires Java 8 (or higher) at runtime.

### Cielo Lio Order Management Tests CallBack (Travis CI Captured HTTP requests)
- https://hookbin.com/bin/Z8abPwXo

#### Maven dependency:
```xml
<dependency>
    <groupId>org.frekele.cielo</groupId>
    <artifactId>cielo-lio-remote-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle dependency:
```gradle
compile 'org.frekele.cielo:cielo-lio-remote-client:1.0.0'
```

#### Usage

```java
public class MyService {
    
    public void call() {
        //First create CieloLioAuth
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        String environment = System.getenv("CIELO_LIO_ENVIRONMENT");
        CieloLioAuth auth = new CieloLioAuth(clientId, accessToken, merchantId, environment);

        //Build one client per thread, or use CDI Injection.
        ResteasyClient client = new ResteasyClientBuilder().build();
        CieloLioPaymentRepository repository = new CieloLioPaymentRepositoryImpl(client, auth);

        List<OrderEntity> resultList = repository.orderGetAll();
        OrderEntity orderEntity = repository.orderGet(new OrderId("5f182dec98-1866-47b0-b69d-471448911f"));

        //Is important to close on finish, or use CDI.
        client.close();
    }
}
```

#### Usage with CDI (Contexts and Dependency Injection)

```java
public class CieloLioProducer {

    @Produces
    @CieloLio
    public CieloLioAuth producesCieloLioAuth() {
        String clientId = System.getenv("CIELO_LIO_CLIENT_ID");
        String accessToken = System.getenv("CIELO_LIO_ACCESS_TOKEN");
        String merchantId = System.getenv("CIELO_LIO_MERCHANT_ID");
        String environment = System.getenv("CIELO_LIO_ENVIRONMENT");
        return new CieloLioAuth(clientId, accessToken, merchantId, environment);
    }

    @Produces
    @CieloLio
    public ResteasyClient producesResteasyClient() {
        ResteasyClient client = new ResteasyClientBuilder()
                //Add proxy
                //.defaultProxy("192.168.56.67", 3456)
                //Change connection Pool size.
                //.connectionPoolSize(3)
                //Change connection TTL.
                //.connectionTTL(30, TimeUnit.MINUTES)
                .build();
        return client;
    }

    public void closeResteasyClient(@Disposes @CieloLio ResteasyClient client) {
        client.close();
    }
}

//Then you just need to @inject.
public class MyService {

    @Inject
    @CieloLio
    private CieloLioPaymentRepository repository;

    public void call() {
        List<OrderEntity> resultList = repository.orderGetAll();
        OrderEntity orderEntity = repository.orderGet(new OrderId("5f182dec98-1866-47b0-b69d-471448911f"));
    }
}

```

### Order Status Lifecycle
![Order Status Lifecycle](https://raw.githubusercontent.com/frekele/cielo-lio-remote-client/master/docs/img/lifecycle.png)

frekele/cielo-lio-remote-client is **licensed** under the **[MIT License]**. The terms of the license are as follows:

    MIT License
    
    Copyright (c) 2018 @frekele<Leandro Kersting de Freitas>
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
