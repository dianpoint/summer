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
        Minimized AOP,IoC kernel tools.
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
    * [Roadmap](#roadmap)
    * [Contributing](#contributing)
    * [License](#license)
    * [Contact](#contact)

<!-- TOC -->

## About The Project

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

