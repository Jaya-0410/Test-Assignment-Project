# General Purpose Flyweight Selenium Framework

Maintaining E2E tests is hard because the UI changes more often than for
instance a service.

The basic idea of this framework is to provide a lightweight and easy to
use selenium framework inspired by the Page Object Pattern. It separates
business page objects which describe actual user frontend actions from 
technical classes which implement selenium bindings.

The underlying idea is:
> "If your tests directly use Selenium you are doing it wrong."

Instead, the tests should as much reflect the manual business test as 
possible. The technical binding to Selenium calls are provided by page
objects and the respective super class.

The page objects in this framework are described by their identifying 
CSS selector. This serves the purpose to ease creating tests because the
test developer can directly copy the class/element/id names from the
browser. 

The page objects themselves are nestable so they can maintain their
tinyness by just reflecting their own small component scope. This should
remove a developer's possible desire to build a hute page object
reflecting a whole page instead of stackable and re-usable parts of said
web page.

Some page objects may be very simple, called "atoms", while other page
objects are 

# Features:

- lightweight component oriented structure
- the framework is meant to be very maintainable and extensible
- CSS selector support instead of XPath
- full page screenshots beyond the viewport
- easy integration into CI/CD due to Maven build system
- URL basic authentication support even for Chrome 64+
- few external dependencies
  - selenium
  - webdriver
  - Webbrowser
  - AssertJ assertion library
  - Screenshot library
  - TestNG
  - Maven

This is currently tested with Google Chrome 69.

# Building Instructions

## Preparation
 
Before you start you need to have Java and Maven installed.

## Start

        You can start the build/test process by calling from the project root directory like this:

```

        mvn -DIDHUB_HOST=vw-stage65.adobecqms.net -DIDHUB_CREDENTIAL_HOST=adobecqms.net -DIDHUB_APP_PATH=/content/tqata/tqata/en/tqa-magazine.html -DIDHUB_BASIC_AUTH_USER=ReplacewithUsername -DIDHUB_BASIC_AUTH_PASSWORD=ReplaceWithPassword test


```

# To set oneHub connection details for windows run the following commands

```
        set IDHUB_HOST=vw-stage65.adobecqms.net
        set IDHUB_CREDENTIAL_HOST=adobecqms.net
        set IDHUB_APP_PATH= content/tqata/tqata/en/tqa-magazine.html
        set IDHUB_BASIC_AUTH_USER=youknowwhich
        set IDHUB_BASIC_AUTH_PASSWORD=youknowwhich

```

# Changes for integration environment

        set imagePath and videoPath in editPage.java

        featureApp Item name in oneHubBasetest.java
        Variables in pom.xml -<ONEHUB_HOST> and <ONEHUB_HOST_AUTHOR>