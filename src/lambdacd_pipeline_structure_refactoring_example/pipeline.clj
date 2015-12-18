(ns lambdacd-pipeline-structure-refactoring-example.pipeline
  (:use [lambdacd.steps.control-flow]
        [lambdacd-pipeline-structure-refactoring-example.steps])
  (:require
        [lambdacd.steps.manualtrigger :refer :all]))

(def pipeline-def
  `(
     (alias "triggers"
            (either
              wait-for-manual-trigger
              wait-for-commit))

     (alias "test and build"
            (with-repo
              run-unit-tests
              run-acceptance-tests
              build-artifact
              publish-artifact))

     (alias "deploy to CI"
            (run
              check-preconditions-ci
              deploy-ci
              smoke-test-ci
              run-ci-tests))

     (alias "deploy to QA"
            (run
              check-preconditions-qa
              deploy-qa
              smoke-test-qa))

     (alias "wait for signoff"
            wait-for-manual-trigger)

     (alias "deploy to LIVE"
            (run
              check-preconditions-live
              deploy-live
              smoke-test-live
              report-live-deployment))))
