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

     complete-ci-deployment
     run-ci-tests

     complete-qa-deployment

     wait-for-manual-trigger

     complete-qa-deployment

     report-live-deployment))
