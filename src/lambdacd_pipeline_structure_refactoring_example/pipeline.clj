(ns lambdacd-pipeline-structure-refactoring-example.pipeline
  (:use [lambdacd.steps.control-flow]
        [lambdacd-pipeline-structure-refactoring-example.steps])
  (:require
        [lambdacd.steps.manualtrigger :refer :all]))

(def pipeline-def
  `(
     (either
       wait-for-manual-trigger
       wait-for-commit)

     (with-repo
       run-unit-tests
       run-acceptance-tests
       build-artifact
       publish-artifact)

     check-preconditions-ci
     deploy-ci
     smoke-test-ci
     run-ci-tests

     check-preconditions-qa
     deploy-qa
     smoke-test-qa

     wait-for-manual-trigger

     check-preconditions-live
     deploy-live
     smoke-test-live

     report-live-deployment))
