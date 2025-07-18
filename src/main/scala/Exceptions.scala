/**
 * COUNTER-EXAMPLE: Exception Handling in Scala (NOT Recommended for Functional Programming)
 * 
 * ⚠️  WARNING: This file demonstrates IMPERATIVE error handling patterns that are
 * generally DISCOURAGED in functional programming!
 * 
 * This file shows traditional exception handling:
 * 1. Throwing exceptions when invalid conditions occur
 * 2. Catching and handling exceptions with try-catch blocks
 * 3. Pattern matching in exception handling
 * 4. Why this approach is problematic in functional programming
 * 
 * PROBLEMS with exceptions in functional programming:
 * - Exceptions break referential transparency (same input doesn't guarantee same output type)
 * - Exception throwing makes functions partial (not defined for all inputs)
 * - Try-catch blocks introduce side effects and imperative control flow
 * - Exceptions are "invisible" in function signatures - callers can't see what might fail
 * - Stack unwinding and exception propagation are expensive operations
 * 
 */

/**
 * ANTI-PATTERN: Division function using exceptions (NOT recommended in FP)
 * 
 * @param x Dividend (number to be divided)
 * @param y Divisor (number to divide by)
 * @return Result of x divided by y
 * @throws ArithmeticException when y is zero (division by zero)
 * 
 */
def divide(x: Int, y: Int): Int = {
  if (y == 0) throw new ArithmeticException("Division by zero")  // ANTI-PATTERN: Exception throwing
  x / y                                                          // Only works for valid inputs
}

/**
 * COUNTER-EXAMPLE: Imperative exception handling (NOT functional programming style)
 * 
 */
@main def testDivide(): Unit = {
  println("=== COUNTER-EXAMPLE: Exception Handling (Anti-Pattern in FP) ===\n")
  println("⚠️  This demonstrates what NOT to do in functional programming!\n")
  
  // ANTI-PATTERN: Imperative try-catch block
  try {
    println("Testing normal division:")
    println(s"divide(10, 2) = ${divide(10, 2)}")        // 5 - works by accident
    
    println("\nTesting division by zero:")
    println(s"divide(10, 0) = ${divide(10, 0)}")        // Exception breaks normal flow
    
    println("This line will NEVER be reached")          // Unreachable code due to exception
    
  } catch {
    // ANTI-PATTERN: Exception handling with side effects
    case e: ArithmeticException => 
      println(s"Caught ArithmeticException: ${e.getMessage}")  // Side effect: printing
    case e: Exception => 
      println(s"Caught unexpected exception: ${e.getMessage}") // Generic catch-all
  }
  
  println("\nProgram continues after exception handling")
  println("Exception was handled, but this approach is NOT functional!")
}