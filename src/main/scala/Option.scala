package option 

/**
 * This file demonstrates the use of Option in Scala for functional error handling.
 * 
 * Option is a container type that represents an optional value: a value that might be present or absent.
 * It is the functional programming alternative to using null references or exceptions.
 * 
 * An Option[A] can be either:
 * - Some(value) - Contains a value of type A
 * - None - Represents the absence of a value (similar to null but type-safe)
 * 
 * Benefits of using Option:
 * - Makes potential absence of values explicit in the type system
 * - Forces developers to handle both success and failure cases
 * - Enables composition of operations that might fail using functional combinators
 * - Prevents NullPointerExceptions and similar runtime errors
 */

/**
 * A division function that returns an Option to handle division by zero safely.
 * 
 * @param x The numerator
 * @param y The denominator
 * @return Some(x/y) if y is not zero, None otherwise
 */
def divide(x: Int, y: Int): Option[Int] = {
  if (y == 0) None  // Return None for division by zero (failure case)
  else Some(x / y)  // Return Some containing the result (success case)
}

/**
 * Example showing why Options can't be directly combined with arithmetic operators.
 * Options require special handling through their methods.
 */
// def expression1(x: Int, y: Int, z: Int): Option[Int] = {
//   divide(x, y) + divide(x, z) // compilation error - can't add Option[Int] directly
// }

/**
 * Example 1: Using getOrElse to extract values from Options with fallback values.
 * 
 * getOrElse extracts the value from an Option or returns a default value if the Option is None.
 * This approach always returns a value, but loses information about which operation failed.
 * 
 * @param x The numerator
 * @param y First denominator
 * @param z Second denominator
 * @return Sum of the two divisions, with 0 used for any failed division
 */
def expression2(x: Int, y: Int, z: Int): Int = {
  divide(x, y).getOrElse(0) + divide(x, z).getOrElse(0)
}

@main def testExpression2(): Unit = {
  println(expression2(10, 2, 3))  // 5 + 3 = 8
  println(expression2(10, 2, 0))  // 5 + 0 = 5 (second division fails)
}

/**
 * Example 2: Using map to transform values inside Options.
 * 
 * The map method transforms the value inside an Option if it exists,
 * without extracting it. This maintains the Optional nature of the result.
 * 
 * However, this approach leads to nested Options, which can be problematic.
 * Note that the result type is Option[Option[Int]].
 * 
 * @param x The numerator
 * @param y First denominator
 * @param z Second denominator
 * @return An Option containing another Option with the sum of divisions
 */
def expression3(x: Int, y: Int, z: Int) = {  // Type is Option[Option[Int]]
  divide(x, y).map { quotient1 =>
    divide(x, z).map { quotient2 =>
      quotient1 + quotient2
    }
  }
}

@main def testExpression3(): Unit = {
  println(expression3(10, 2, 3)) // Some(Some(6)) - Both divisions succeed
  println(expression3(10, 2, 0)) // Some(None) - First division succeeds but second fails
}

/**
 * Example 3: Using flatMap to combine Options without nesting.
 * 
 * The flatMap method is used for sequential operations where each step might fail.
 * It flattens nested Options into a single Option, preventing the nesting issue seen in expression3.
 * 
 * The first division's result is processed only if it succeeds (is Some).
 * The second division is attempted, and if it succeeds, both results are combined.
 * If either division fails, the final result is None.
 * 
 * @param x The numerator
 * @param y First denominator
 * @param z Second denominator
 * @return Option containing sum of both divisions if both succeed, None otherwise
 */
def expression4(x: Int, y: Int, z: Int): Option[Int] = {
  divide(x, y).flatMap { quotient1 =>
    divide(x, z).map { quotient2 =>
      quotient1 + quotient2
    }
  }
}

@main def testExpression4(): Unit = {
  println(expression4(10, 2, 3)) // Some(6) - Both divisions succeed
  println(expression4(10, 2, 0)) // None - Second division fails
}

/**
 * Example 4: Using for-comprehensions with Options.
 * 
 * For-comprehensions provide a more readable syntax for chaining operations with flatMap and map.
 * This is semantically equivalent to the flatMap/map approach in expression4, but more readable.
 * 
 * Under the hood, the Scala compiler transforms this into flatMap and map calls:
 * - quotient1 <- divide(x, y) becomes divide(x, y).flatMap(quotient1 => ...)
 * - quotient2 <- divide(x, z) becomes divide(x, z).map(quotient2 => ...)
 * 
 * @param x The numerator
 * @param y First denominator
 * @param z Second denominator
 * @return Option containing sum of both divisions if both succeed, None otherwise
 */
def expression5(x: Int, y: Int, z: Int): Option[Int] = {
  for {
    quotient1 <- divide(x, y)  // First division must succeed
    quotient2 <- divide(x, z)  // Second division must succeed
  } yield quotient1 + quotient2  // Combine results if both succeed
}

@main def testExpression5(): Unit = {
  println(expression5(10, 2, 3)) // Some(6) - Both divisions succeed
  println(expression5(10, 2, 0)) // None - Second division fails
}



/**
 * Example 5: Another flatMap approach with a different order of operations.
 * 
 * This example shows how to chain multiple flatMap operations when you have
 * multiple operations that might fail. It's functionally similar to expression4
 * but with a different order of operations.
 * 
 * After both divisions succeed, we wrap the result in Some using an explicit Some constructor.
 * This isn't necessary (we could just return the sum directly), but it demonstrates
 * how to manually create a Some value.
 * 
 * @param x The numerator
 * @param y First denominator
 * @param z Second denominator
 * @return Option containing sum of both divisions if both succeed, None otherwise
 */
def expression(x: Int, y: Int, z: Int): Option[Int] = {
  divide(x, z).flatMap { quotient1 =>
    divide(x, y).flatMap { quotient2 =>
      Some(quotient1 + quotient2)  // Wrap result in Some
    }
  }
}

/**
 * Main entry point demonstrating the expression function.
 * Shows both success and failure cases:
 * 1. When both divisions succeed (z and y are non-zero)
 * 2. When one division fails (z is zero)
 */
@main def run = {
    println(expression(10, 5, 2)) // Some(15) - Both divisions succeed (10/2 + 10/5 = 5 + 2 = 7)
    println(expression(10, 5, 0)) // None - First division fails (division by zero)
}