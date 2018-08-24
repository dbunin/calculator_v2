(ns calculator-v2.stack)

(defprotocol Stack
  (update-stacks [this stack id]
    "Updates stack in collection
      Params:
        int stack - stack to update
        int id - id of a stack
      Returns: stacks")
  (create-stack [this id]
    "Creates a new stack
     Params:
       int id - id of a stack
     Returns: newly created stack")
  (get-stack [this id]
    "Returns: stack with id :id")
  (peek* [this id]
    "Gets top element of the stack
     Params:
       int id - id of a stack
     Returns: top element of the stack
     Throws: throws error of :type :not-found if stack is empty")
  (push* [this id n]
    "Pushes a number onto a stack
     Params:
       int id - id of a stack
       int n - number to add to the stack
     Returns: top element of the stack
     Throws: error of :type :invalid if n is empty")
  (pop* [this id]
   "Removes the last element of the stack
     Params:
       int id - id of a stack
     Returns: removed element of the stack
     Throws: error of :type :invalid if n is empty"))