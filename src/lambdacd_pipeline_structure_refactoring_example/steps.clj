(ns lambdacd-pipeline-structure-refactoring-example.steps
  (:require [lambdacd.steps.shell :as shell]
            [lambdacd.steps.git :as git]
            [lambdacd.steps.support :as step-support]))

(def repo "git@github.com:flosell/lambdacd")
(def branch "master")

; git
(defn wait-for-commit [args ctx]
  (git/wait-for-git ctx repo branch))

(defn with-repo [& steps]
  (git/with-git repo steps))

; tests
(defn run-unit-tests [args ctx]
  (step-support/capture-output ctx
                               (println "running unit tests...")
                               {:status :success}))
(defn run-acceptance-tests [args ctx]
  (step-support/capture-output ctx
                               (println "running acceptance tests...")
                               {:status :success}))
(defn run-ci-tests [args ctx]
  (step-support/capture-output ctx
                               (println "running ci tests...")
                               {:status :success}))
(defn smoke-test [environment]
  (fn [args ctx]
    (step-support/capture-output ctx
                                 (println "running smoke tests against " environment " environment...")
                                 {:status :success})))

; build and publish
(defn build-artifact [args ctx]
  (step-support/capture-output ctx
                               (println "building artifact...")
                               {:status :success}))

(defn publish-artifact [args ctx]
  (step-support/capture-output ctx
                               (println "publishing artifact...")
                               {:status :success}))

; deployment
(defn check-preconditions [environment]
  (fn [args ctx]
    (step-support/capture-output ctx
                                 (println "checking preconditions for deployment to " environment " environment...")
                                 {:status :success})))
(defn deploy [environment]
  (fn [args ctx]
    (step-support/capture-output ctx
                                 (println "deploying to " environment " environment...")
                                 {:status :success})))

(defn report-live-deployment [args ctx]
  (step-support/capture-output ctx
                               (println "reporting deployment to external reporting systems..")
                               {:status :success}))