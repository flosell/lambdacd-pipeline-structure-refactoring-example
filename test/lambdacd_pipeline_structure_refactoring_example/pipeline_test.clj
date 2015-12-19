(ns lambdacd-pipeline-structure-refactoring-example.pipeline-test
  (:require [clojure.test :refer :all]
            [lambdacd-pipeline-structure-refactoring-example.pipeline :as pipeline]
            [lambdacd-pipeline-structure-refactoring-example.steps :refer :all]
            [lambdacd.steps.control-flow :refer :all]
            [lambdacd.steps.manualtrigger :refer [wait-for-manual-trigger]])
  (:refer-clojure :exclude [alias]))

(deftest pipeline-structure-test
  (testing "that our structure is as we expect it to be"
    (is (= `(
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
                       (check-preconditions :ci)
                       (deploy :ci)
                       (smoke-test :ci)
                       run-ci-tests))

              (alias "deploy to QA"
                     (run
                       (check-preconditions :qa)
                       (deploy :qa)
                       (smoke-test :qa)))

              (alias "wait for signoff"
                     wait-for-manual-trigger)

              (alias "deploy to LIVE"
                     (run
                       (check-preconditions :live)
                       (deploy :live)
                       (smoke-test :live)
                       report-live-deployment))) pipeline/pipeline-def))))
