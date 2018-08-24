(ns calculator-v2.stack-test
  (:require [clojure.test :refer :all]
            [calculator-v2.stack :refer :all]
            [slingshot.slingshot :refer [throw+ try+]]
            [calculator-v2.stack.in-memory :refer [in-memory-stack]]))

(defn is-valid-stack
  "Given an implementation of the Storage protocol, assert that it fulfills the
   contract."
  [stack]
  (testing "Testing that peeking epmty stack throws exception"
    (try+
     (peek* stack 0)
     (catch [:type :not-found] _
       (is 1 1))
     (else (is 1 0))))

  (testing "Testing throwing exception when giving empty attribute"
    (try+
     (push* stack 0 nil)
     (catch [:type :invalid] _
       (is 1 1))
     (else (is 1 0))))

  (testing "Testing that push returns the pushed element"
    (is 1 (push* stack 0 1)))
  
  (testing "Testing that peek after push returns pushed element"
    (is 1 (peek* stack 0)))

  (testing "Testing pop empty stack"
    (try+
      (pop* stack 1)
      (catch [:type :not-found] _
        (is 1 1))
      (else (is 1 0))))
  
  (testing "Testing that pop returns the top element and deletes it"
    (do
      (push* stack 1 1)
      (push* stack 1 2)
      (is 2 (pop* stack 1))
      (is 1 (peek* stack 1)))))


(deftest in-memory-stack-test
  (let [stack (in-memory-stack)]
    (is-valid-stack stack)))
