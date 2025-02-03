# Mit6.102: Software Construction

## 1. Test and Specification

- [Static checking](https://web.mit.edu/6.102/www/sp24/classes/01-static-checking/)
  - checking: static check > dynamic check > no check
  - *Goal: safe from bugs, easy to understand, ready for change.*

- [Testing](https://web.mit.edu/6.102/www/sp24/classes/02-testing/)
  - ***Iterative test first programming***
  - Systematic testing: *partition* on testing samples.

- [Code review](https://web.mit.edu/6.102/www/sp24/classes/03-code-review/)

- [Specifications](https://web.mit.edu/6.102/www/sp24/classes/04-specifications/)

- [Design specifications](https://web.mit.edu/6.102/www/sp24/classes/05-designing-specs/)
  

## 2. Abstract Data Type

- [Abstract data types](https://web.mit.edu/6.102/www/sp24/classes/06-abstract-data-types/)
  > The key idea of data abstraction is that a type is characterized by the operations you can perform on it. A number is something you can add and multiply; a string is something you can concatenate and take substrings of; a boolean is something you can negate, and so on. In a sense, users could already define their own types in early programming languages: you could create a record type date, for example, with integer fields for day, month, and year. But what made abstract types new and different was the focus on operations: the user of the type would not need to worry about how its values were actually stored, in the same way that a programmer can ignore how the compiler actually stores integers. All that matters is the operations.

- [Abstract functions and Rep invariants](https://web.mit.edu/6.102/www/sp24/classes/07-abstraction-functions-rep-invariants/)
  - Abstract functions
  - Representation invariants
  - Avoid rep exposure

- [Defining ADTs with Interfaces, Generics, Enums and Functions](https://web.mit.edu/6.102/www/sp24/classes/08-interfaces-subtyping/)
  - Interface
  - Subtyping
  - **Generic types**
  - Enum class

- [Functional Programming](https://web.mit.edu/6.102/www/sp24/classes/09-functional-programming/)
  - Iterator
  - Map/filter/reduce abstraction

- [Equality](https://web.mit.edu/6.102/www/sp24/classes/10-equality/)
  - Immutable type
  - Mutable type
  - Hash functions

- [Recursive data types](https://web.mit.edu/6.102/www/sp24/classes/11-recursive-data-types/)
  - Multi classes implementing one interface
    ```Java
    /**
     * An immutable data type representing a polynomial expression of:
     *   + and *
     *   nonnegative integers and floating-point numbers
     *   variables (case-sensitive nonempty strings of letters)
     */
    public interface Expression {
       
       // Datatype definition
       //    Expression = Constant(value: double)
       //                 + Variable(symbol: String)
       //                 + Plus(leftExpr: Expression, rightExpr: Expression)
       //                 + Times(leftExpr: Expression, rightExpr: Expression)
       public static Expression parse(String input) { }
       public Expression differentiate(String variable);
       public Expression simplify(Map<String, Double> environment);
       @Override public String toString();
       @Override public boolean equals(Object thatObject);
       @Override public int hashCode();
    }
    ```

- [Grammars and Parsing](https://web.mit.edu/6.102/www/sp24/classes/12-grammars-parsing/)
  - Antlr (grammars, parse generators)
  - Regular expressions
  - Parse tree
  - Abstract syntax tree