# Coding Exercise - Calculating Sales Taxes on Items

This project takes a JSON file of items and calculates sales taxes for them.
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

What things you need to install the software and how to install them

```
JDK 8 and JRE installed and on PATH
Maven 3+ installed and on PATH
Git 2.9+ installed and on PATH
```

### Installing/Building/Running App

A step by step series of examples that tell you how to get a development env running

1.Clone Repo

```
git clone https://github.com/santande/SalesTax.git
```

2.Build project

```
cd SalesTax
mvn clean package
```

3.Run program to demonstrate scenarios

```
java -jar target/CalculateSalesTax.jar input1.json
java -jar target/CalculateSalesTax.jar input2.json
java -jar target/CalculateSalesTax.jar input3.json
```

4.Input data format (JSON) is demonstrated here:

[input1.json](./input1.json)

*Notes:*  
Possible values for field **`source`**:  _Domestic_, _Import_

Possible values for field **`type`**:   _Book_, _Food_, _Medical_, _Other_

## Running the tests

There are some Unit tests coded that cover the more important functionality

### Unit tests

* Verify reading in/writing out JSON.  
* Verify tax calculations for items are correct.

```
CD SalesTax
mvn clean test
```
