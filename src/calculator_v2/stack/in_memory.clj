(ns calculator-v2.stack.in-memory
  (:require [calculator-v2.stack :as stack]
            [slingshot.slingshot :refer [throw+ try+]]))

(defn update-stacks*
  [!stacks stack id]
  (swap! !stacks assoc id stack))

(defn create-stack*
  [!stacks id]
  (swap! !stacks assoc id ())
  (@!stacks id))

(defn get-stack*
  [!stacks id]
  (or (@!stacks id)
      (create-stack* !stacks id)))

(defn peek**
  [!stacks id]
  (or (first (get-stack* !stacks id))
      (throw+ {:type :not-found :message "stack is empty"})))

(defn push**
  [!stacks id n]
  (let [stack (get-stack* !stacks id)]
    (if (and (nil? n) (not (integer? n)))
      (throw+ {:type :invalid :message "attr is empty or not int"})
      (update-stacks* !stacks (conj stack n) id))
    (peek** !stacks id)))

(defn pop**
  [!stacks id]
  (let [stack (get-stack* !stacks id)]
    (if (empty? stack)
      (throw+ {:type :not-found :message "can't pop from empty stack"})
      (let [poped-stack (peek** !stacks id)]
        (update-stacks* !stacks (pop stack) id)
        poped-stack))))

(defn in-memory-stack
  []
  (let [!stacks (atom {})]
    (reify stack/Stack
      (update-stacks [_ stack id] (update-stacks* !stacks stack id))
      (create-stack [_ id] (create-stack* !stacks id))
      (get-stack [_ id] (get-stack* !stacks id))
      (peek* [_ id] (peek** !stacks id))
      (push* [_ id n] (push** !stacks id n))
      (pop* [_ id] (pop** !stacks id)))))
