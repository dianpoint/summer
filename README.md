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

Summer is a minimized AOP (Aspect-Oriented Programming) and IoC (Inversion of Control) kernel tool library. It provides a set of validators and related utility classes to simplify the validation logic of objects and collections. Additionally, it includes some functions for batch task processing.

### Validation Module

+ **Rich Validation Rules**: The validation module offers a wide range of validation rules, including length checks, regular expression matching, and 
collection size validations. For example, you can easily validate strings for minimum and maximum lengths, and collections for size ranges.

+ **Annotation-Based Validation**: Supports annotation-based validation. You can use custom annotations to mark fields in classes and perform 
  validation operations on objects. For instance, use the @NotNull and @Pattern annotations to validate user-defined objects.

+ **Collection Validation**: Provides specific validation capabilities for collections. You can set validation rules for the entire collection as 
  well 
  as for each element in the collection.


### Batch Task Processing Module

+ **Main and Sub-Task Management**: Includes entities such as MainTask and SubTask, and corresponding data access objects (MainTaskDao). It can 
  manage 
the status, progress, and related information of main and sub-tasks.

+ **Task Monitoring and Recovery**: The TaskWatchdog class periodically checks for stuck tasks and attempts to recover them, ensuring the reliability 
  of task processing.

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
Make sure you have Java and Maven installed in your development environment.

### Installation

Clone the repository to your local machine:

```bash
git clone https://github.com/dianpoint/summer.git

```

Navigate to the project directory and build the project:

```bash
cd summer
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

- [x] Implement basic bean definition and injection
- [x] Add AOP support
- [ ] Improve validation module with more rules
- [ ] Add support for annotation-based configuration
  + [ ] Implement @Autowired and @Component annotations
  + [ ]  Provide annotation scanning functionality

See the issues for a full list of proposed features and known issues.


### Contributing
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.

1. Fork the Project
2. Create your Feature Branch (git checkout -b feature/AmazingFeature)
3. Commit your Changes (git commit -m 'Add some AmazingFeature')
4. Push to the Branch (git push origin feature/AmazingFeature)
5. Open a Pull Request


### License
Summer is distributed under the Apache License 2.0 License. See License.txt for more information.

### Contact
+ Email: congccoder@gmail.com
+ Github: https://github.com/ccoderJava
## Usage

## Roadmap

- [x] xxx
- [x] xxx
- [ ] xxx
- [ ] xxx
    - [ ] xxx
    - [ ] xxx

See the [issues](https://github/dianpoint/summer/issues) for a full list of proposed features and known issues.

## Contributing

## License

`Summer` distributed under the [`Apache License 2.0`](https://github.com/dianpoint/summer/blob/main/LICENSE) License.
See `License.txt` for more information.

## Contact

+ Email: [congccoder@gmail.com](mailto://congccoder@gmail.com)
+ Github: [https://github.com/ccoderJava](https://github.com/ccoderJava)

[//]: # (Markdown Links & Images)

[contributors-shield]: https://img.shields.io/github/contributors/dianpoint/summer.svg?style=for-the-badge

[contributors-url]: https://github.com/dianpoint/summer/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/dianpoint/summer.svg?style=for-the-badge

[forks-url]: https://github.com/dianpoint/summer/forks

[stars-shield]: https://img.shields.io/github/stars/dianpoint/summer.svg?style=for-the-badge

[stars-url]: https://github.com/dianpoint/summer/stargazers

[issues-shield]: https://img.shields.io/github/issues/dianpoint/summer.svg?style=for-the-badge

[issues-url]: https://github.com/dianpoint/summer/issues

[license-shield]: https://img.shields.io/github/license/dianpoint/summer.svg?style=for-the-badge

[license-url]: https://github.com/dianpoint/summer/blob/main/LICENSE

[language-shield]: https://img.shields.io/github/languages/count/dianpoint/summer?style=for-the-badge

[language-url]: https://img.shields.io/github/languages/count/dianpoint/summer

