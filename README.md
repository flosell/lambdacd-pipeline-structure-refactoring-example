# lambdacd-pipeline-structure-refactoring-example

Examples on how to refactor pipeline code to be more easier understand and maintain. Accompanies the [guide in the LambdaCD Wiki](https://github.com/flosell/lambdacd/wiki/Guide:-Pipeline-Structure-Refactoring)

The master branch contains the initial state, the other branches contain the end result of each refactoring step described. 

## Usage

* `lein run` will start your pipeline with a web-ui listening on port 8080
* `lein test` to run tests

## Files

* `pipeline.clj` contains your pipeline-definition
* `steps.clj` contains your custom build-steps

* `core.clj` contains the `main` function that wires everything together

## References

* [Guide in the LambdaCD Wiki](https://github.com/flosell/lambdacd/wiki/Guide:-Pipeline-Structure-Refactoring)
