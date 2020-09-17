# jada_lib Library
![jada_lib logo](assets/jada_lib_logo.png)
<br>
<br>
## About
The jada_lib library is a collection of util classes. Those classes are for the most part wrappers of standard Java
classes or other libraries.
<br>
## How to import
The jada_lib library is made using Maven and is published on [Github Packages](https://github.com/DaniloMurer/jada_lib/packages/400579).
<br>
To import the library you need to add the dependency into your Maven Project as following:
<br>
```xml
<dependency>
  <groupId>com.danilojakob</groupId>
  <artifactId>jada_lib</artifactId>
  <version>1.0.0</version>
</dependency>
```
After adding the dependency install the package by running `maven install`.
<br>
## Classes
The library contains following classes (version 1.0.0):
<br>
- FileHelper (read/write files and Object Serialization)
- PropertyFileHelper (read/write `*.properties` files)
- EncryptionHelper (encrypt/decrypt files and text)
- KeyGenerator (create and save RSA Private and Public Keys)
- Hasher (hash Strings)

