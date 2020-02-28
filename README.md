# jumping-into-clouds
Small thread-safe java library to maintain time and stats for a generic set of actions. Actions are fed to the library via JSON
strings and the return value is a serialized JSON array of the average time for each action.

## Requirements
* The latest version of the [Java 13 OpenJDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    * `java`, `javac` and `jar` must be on the PATH.

* The latest version of [Apache Maven 3.6](https://maven.apache.org/download.cgi)

## Building
In order to build jumping-into-clouds, you'll use the `mvn` command from the Apache Software Foundation. `mvn` is a Java tool so you must have Java
installed in order to use it.

From a command-line terminal:
1. Clone this repo into a directory of your choice.
2. `cd` into the repo's directory.
3. execute `mvn clean install` from the root of the repo's directory.
4. This builds and stores the jumpy-fun-core jar artifact into the local maven repository.

If local api documentation is desired, it may be built using `mvn`.
1. `cd` into the repo's directory.
2. execute `mvn javadoc:javadoc`

The api documentation may be accessed by opening the `index.html` file for the desired module in the `target/site/apidocs`
build folder.

## Build Output
jumping-into-clouds is separated into modules. Each module has a `target` build folder that contains all the artifacts
for that module.

For example, the `jumpy-fun-core` module artifacts are located in:

```sh
jumping-into-clouds/jumpy-fun-core/target
```

## Documentation
* How to use
```java
import io.fmsoftware.jumpyfun.KrisKross;
public class YourApplicationNameHere
{
    private KrisKross jumpyLib = new KrisKross();
    // The rest of your code.
}
```
* Refer to included jumpy-fun-demo for a full demo of how to call and structure the JSON as well as get statistics from the
api library.

* Logging
    * There is a logging facility included to help debug any errors encountered with the api library. The log file is created
in the following location:

```sh
jumping-into-clouds/logs/jumpyfun.log
```

* Intended use
    * The api library is intended to be used with well-formed JSON with the action name and time values not equal to
    NULL. The action name is expected to be a non-empty string and the time value must be an integer > 0. When the 
    average stat times are returned, it will be an integer. Any remainder that was calculated as a part of the average
    will be silently discarded.
    * Return order of action names for the JSON array of average times is NOT guaranteed.
    * Refer to the local api documentation for more information on methods and usage.
---
## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**