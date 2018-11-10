# Coding Exercise - Calculating Sales Taxes on Items

This project takes a JSON file of items and calculates sales taxes for them.
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
JDK 8 and JRE installed and on path
Maven 3+ installed and on path
```

### Installing

A step by step series of examples that tell you how to get a development env running

1.Clone Repo

```
https://github.com/santande/SalesTax.git
```

2.Build project

```
CD SalesTax
mvn clean package
```

3.Run program to demonstrate scenarios

```
java -jar target/SalesTax-1.0-SNAPSHOT.jar input1.json
java -jar target/SalesTax-1.0-SNAPSHOT.jar input2.json
java -jar target/SalesTax-1.0-SNAPSHOT.jar input3.json
```

4.The correct format of the input JSON is demonstrated here

```
SalesTax/input1.json
```

## Running the tests

There are some Unit tests coded

### Unit tests

* Verify reading in/writing out JSON.  
* Verify tax calculations for items are correct.

```
CD SalesTax
mvn clean test
```
