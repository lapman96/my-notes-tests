# DemoQA

This project involves my practice in automating tests using Java for the test website - [DemoQA](https://demoqa.com).

## Running Tests

To run the tests, use the below command in your terminal:

``
gradle test -Denv=<environment> -Dbrowser=<browser> -Dtags="<tagName1>,<tagName2>,..." -Drun_remotely=<boolean>
``
- **Denv** - specify the environment in which the tests will be run.
- **Dbrowser** - set the browser for running the tests.
- **Dtags** - specify test tags that should be executed.
- **Drun_remotely** - this indicates whether the tests should be executed locally (false) or in a remote server (true).

Example:

``
gradle test -Denv=prod -Dbrowser=chrome -Dtags="P1" -Drun_remotely=false
``