### Hexlet tests and linter status:
[![Actions Status](https://github.com/Daniell010/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/Daniell010/java-project-71/actions)
<a href="https://codeclimate.com/github/Daniell010/java-project-71/maintainability"><img src="https://api.codeclimate.com/v1/badges/9a82cf8b371b42dd4eb3/maintainability" /></a>
[![.github/workflows/main.yml](https://github.com/Daniell010/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/Daniell010/java-project-71/actions/workflows/main.yml)
<a href="https://codeclimate.com/github/Daniell010/java-project-71/test_coverage"><img src="https://api.codeclimate.com/v1/badges/9a82cf8b371b42dd4eb3/test_coverage" /></a>

# Difference Calculator

Difference Calculator is a program that determines the difference between two data structures. This is a popular task for which there are many online services, such as [http://www.jsondiff.com/](http://www.jsondiff.com/). A similar mechanism is used when displaying tests or when automatically tracking changes in configuration files.

## Features

- Support for different input formats: YAML and JSON
- Generation of reports in plain text, stylish and JSON formats

## Usage Example

```sh
# plain format
./app --format plain path/to/file.yml another/path/file.json

Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed

# stylish format
./app filepath1.json filepath2.json

{
  + follow: false
  + numbers: [1, 2, 3]
    setting1: Value 1
  - setting2: 200
  - setting3: true
  + setting3: {key=value}
  + setting4: blah blah
}
```
[![asciicast](https://asciinema.org/a/568661.svg)](https://asciinema.org/a/568661)

