# Overview
This repository provides code for basic operations on a bank account such as withdrawal, deposit and statements. Please note that features such as connection and data persistence in particular are not taken into account in this code.

# Prerequisites
To best run this microservice, you should have Java 8+ and add [AssertJ](https://assertj.github.io/doc/) library into your Java environment. 

# Content
- The package **model** contains an Enum that provides the type of transaction (deposit or withdrawal) and a class that describes a transaction.

- The package **dao** contains the business logic.

- The package **service** provides services for interaction with a bank account.

- The package **web** contains two sub-packages : *controller* provides REST API and *exceptions* handles exceptions.

- Everything relating to tests is in the *src/test* folder.
