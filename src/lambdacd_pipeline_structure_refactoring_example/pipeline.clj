(ns lambdacd-pipeline-structure-refactoring-example.pipeline
  (:use [lambdacd.steps.control-flow]
        [lambdacd-pipeline-structure-refactoring-example.steps])
  (:require
        [lambdacd.steps.manualtrigger :refer :all])
  (:refer-clojure :exclude [alias]))

(defn deploy-steps [environment]
  `((check-preconditions ~environment)
     (deploy ~environment)
     (smoke-test ~environment)))

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
              ~@(deploy-steps :ci)
              run-ci-tests))

     (alias "deploy to QA"
            (run
              ~@(deploy-steps :qa)))

     (alias "wait for signoff"
            wait-for-manual-trigger)

     (alias "deploy to LIVE"
            (run
              ~@(deploy-steps :live)
              report-live-deployment))))
