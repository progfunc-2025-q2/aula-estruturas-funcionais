package either

// This file demonstrates safe arithmetic operations using Scala's Either type.
// Either is used to represent a value of one of two possible types (a disjoint union).
// Typically, Either is used for error handling: Left for errors, Right for successful results.

def divide(x: Int, y: Int): Either[String, Int] = {
  // Divides x by y, returning an error message if y is zero.
  if (y == 0) Left("Division by zero")
  else Right(x / y)
}

// Algebraic data types to represent different kinds of arithmetic errors.
sealed trait ArithmeticError
case object DivisionByZero extends ArithmeticError // Error for division by zero
case class NegativeNumberError(value: Int) extends ArithmeticError // Error for negative numbers
case class OverflowError(value: Int) extends ArithmeticError // Error for integer overflow

// Divides x by y, but only if both are positive and y is not zero.
// Returns a specific ArithmeticError for each error case.
def dividePositive(x: Int, y: Int): Either[ArithmeticError, Int] = {
  if (y == 0) Left(DivisionByZero)
  else if (x < 0 || y < 0) Left(NegativeNumberError(x))
  else Right(x / y)
}

// Multiplies x by y, returning an OverflowError if the result would exceed Int.MaxValue.
def multiply(x: Int, y: Int): Either[ArithmeticError, Int] = {
  if (x > Int.MaxValue / y) Left(OverflowError(x))
  else Right(x * y)
}

// Main method to test dividePositive and multiply functions with various inputs.
@main def testDivideEitherWithErrors(): Unit = {
  println(dividePositive(10, 2)) // Right(5)
  println(dividePositive(10, 0)) // Left(DivisionByZero)
  println(dividePositive(-10, 2)) // Left(NegativeNumberError(-10))
  println(multiply(10, 2)) // Right(20)
  println(multiply(Int.MaxValue, 2)) // Left(OverflowError(Int.MaxValue))
}

// Main method to demonstrate monadic usage of Either with map for safe composition.
@main def testMonadicEither(): Unit = {
  val result1 = divide(10, 2)
  println(s"10 / 2 = ${result1}") // Should print Right(5)

  val result2 = divide(10, 0)
  println(s"10 / 0 = ${result2}") // Should print Left("Division by zero")

  // Safe composition using map: only applies if result is Right
  val safeResult = result1.map(_ + 20)
  println(s"Safe composition result: ${safeResult}") // Should print Right(25)

  val safeResult2 = result2.map(_ + 20)
  println(s"Safe composition with division by zero: ${safeResult2}") // Should print Left("Division by zero")
}


// Main entry point: runs basic tests and prints results for demonstration.
@main def run = {
  println("=== Safe Arithmetic with Either ===")
  
  // Test division by zero
  val result1 = divide(10, 2)
  println(s"10 / 2 = ${result1}") // Should print Right(5)
  
  val result2 = divide(10, 0)
  println(s"10 / 0 = ${result2}") // Should print Left("Division by zero")
  
  // Test safe composition
  val safeResult = divide(10, 2).map(_ + 20)
  println(s"Safe composition result: ${safeResult}") // Should print Right(25)

  val safeResult2 = divide(10, 0).map(_ + 20)
  println(s"Safe composition with division by zero: ${safeResult2}") // Should print Left("Division by zero")
}