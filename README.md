# Summer

English | [中文](README_cn.md)

[//]: # (porject shields)
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stars][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![License][license-shield]][license-url]
[![Languages][Language-shield]][language-url]

<div align="center">
    <h3 align="center">Summer</h3>
    <p align="center">
        Minimized AOP, IoC kernel tools.
    </p>
    <br>
    <a href="https://github.com/dianpoint/summer"><strong>Summer Docs >></strong> </a>
    <br>
    <br>
    <a href="https://github.com/dianpoint/summer">View Demo</a>
    ·
    <a href="https://github.com/dianpoint/summer/issues">Report Bug</a>
    ·
    <a href="https://github.com/dianpoint/summer/issues">Request Feature</a>
</div>

<!-- TOC -->

* [Summer](#summer)
  * [About The Project](#about-the-project)
  * [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
  * [Usage](#usage)
    * [Bean Definition and Injection](#bean-definition-and-injection)
    * [AOP Usage](#aop-usage)
    * [Validation](#validation)
    * [RequestMapping Annotation](#requestmapping-annotation)
    * [Autowired Annotation](#autowired-annotation)
  * [Project Structure](#project-structure)
  * [Roadmap](#roadmap)
  * [Contributing](#contributing)
  * [License](#license)
  * [Contact](#contact)

<!-- TOC -->

## About The Project
Summer is a minimized AOP and IoC kernel toolset. It provides a lightweight and efficient solution for building Java applications, supporting XML-based bean definition and dependency injection, as well as AOP functionality. The project consists of two main modules: `summer-beans` and `summer-validator`.

### Modules
- **summer-beans**: Core module for bean management, including bean definition, creation, and dependency injection. It also supports AOP features such as proxy creation and advice execution.
- **summer-validator**: A validation module that provides various validation rules for collections and objects.

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven

### Installation
1. Clone the repository:

```shell
git clone https://github.com/dianpoint/summer.git
cd summer
```

2. Build the project using Maven:
```shell
mvn clean install
```


### Usage

#### Bean Definition and Injection
You can define beans in an XML file and use the XmlBeanDefinitionReader to load and register them. Here is an example:
```java
// Create a bean factory
AbstractBeanFactory beanFactory = new DefaultListableBeanFactory();
// Create a resource for the XML file
Resource resource = new ClassPathResource("beans.xml");
// Create a reader to load bean definitions from the resource
XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
reader.loadBeanDefinitions(resource);
// Get a bean from the factory
Object bean = beanFactory.getBean("beanName");
```

#### AOP Usage
Summer supports AOP through proxy creation and advice execution. You can use the ProxyFactoryBean to create a proxy for a target object and apply advice before or after method execution.

```java
// Create a proxy factory bean
ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
proxyFactoryBean.setTarget(target);
proxyFactoryBean.setInterceptorName("interceptorBeanName");
proxyFactoryBean.setBeanFactory(beanFactory);
// Get the proxy object
Object proxy = proxyFactoryBean.getSingletonInstance();
```

#### Validation
The summer-validator module provides validation rules for collections and objects. You can use the ValidationRules and Validators classes to perform validation.

```java
// Validate a collection
ValidationRule<Collection<?>> rule = ValidationRules.minsize(5);
ValidationResult result = rule.validate(collection);
if (result.isSuccess()) {
    // Collection meets the validation rule
} else {
    // Collection does not meet the validation rule
}

// Validate an object using annotations
User user = new User(null, "123@mail.com");
List<ValidationResult> validationResults = Validators.annotated(User.class).validate(user);
```

#### RequestMapping Annotation
The RequestMapping annotation can be used to map HTTP requests to specific methods in a controller.
```java
import com.dianpoint.summer.web.RequestMapping;

public class MyController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```


#### Autowired Annotation
The Autowired annotation can be used to automatically inject dependencies into fields.
```java
import com.dianpoint.summer.beans.factory.annotation.Autowired;

public class MyService {

    @Autowired
    private AnotherService anotherService;

    // ...
}
```


### Project Structure
```java
summer/
├── summer-beans/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── com/dianpoint/summer/
│   │   │           ├── beans/
│   │   │           ├── context/
│   │   │           ├── web/
│   │   │           └── ...
│   │   └── test/
│   │       └── java/
│   │           └── com/dianpoint/summer/test/
│   │               ├── xml/
│   │               └── ...
│   └── pom.xml
├── summer-validator/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── com/dianpoint/summer/validator/
│   │   │           ├── annotations/
│   │   │           ├── constraintvalidators/
│   │   │           └── ...
│   │   └── test/
│   │       └── java/
│   │           └── com/dianpoint/summer/validator/test/
│   │               └── ...
│   └── pom.xml
├── docs/
│   └── readme.md
├── .github/
│   └── workflows/
│       └── maven-test.yml
├── .gitignore
├── pom.xml
├── README.md
└── README_cn.md
```

### Roadmap

Implement basic bean definition and injection
Add AOP support
Improve validation module with more rules
Add support for annotation-based configuration
Implement @Autowired and @Component annotations
Provide annotation scanning functionality

See the issues for a full list of proposed features and known issues.


### Contributing
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.

Fork the Project
Create your Feature Branch (git checkout -b feature/AmazingFeature)
Commit your Changes (git commit -m 'Add some AmazingFeature')
Push to the Branch (git push origin feature/AmazingFeature)
Open a Pull Request


### License
Summer is distributed under the Apache License 2.0 License. See License.txt for more information.

### Contact
Email: congccoder@gmail.com
Github: https://github.com/ccoderJava
