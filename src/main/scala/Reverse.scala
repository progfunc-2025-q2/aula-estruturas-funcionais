package reverse


/**
 * Demonstration of different list reversal implementations
 * 
 * This file shows various approaches to reverse a list:
 * 1. Naive implementation using append (inefficient)
 * 2. Iterative implementation using loops
 * 3. Optimized tail-recursive implementation
 * 
 * Important concepts:
 * - Reversal is a fundamental operation in list manipulation
 * - Different implementations have drastically different performance
 * - Trade-offs between simplicity and efficiency
 * - Importance of choosing the correct data structure
 */

/**
 * Naive list reversal implementation using append
 * 
 * @param xs List to reverse
 * @return New list with elements in reverse order
 * 
 * How it works:
 * - Base case: empty list returns empty list
 * - Recursive case: reverses tail and appends head at the end
 * 
 * PROBLEM: This implementation is very inefficient!
 * Complexity: O(n²) due to append being O(n) for each element
 * Demonstrates how NOT to reverse lists
 */
def reverseNaive[A](xs: List[A]): List[A] = {
  if (xs.isEmpty) Nil                           // Base case: empty list
  else reverseNaive(xs.tail) :+ xs.head        // Inefficient: append O(n) for each element
}

/**
 * Correct iterative reversal implementation
 * 
 * @param xs List to reverse
 * @return New list with elements in reverse order
 * 
 * How it works:
 * - Uses local mutable variables for efficiency
 * - Iterates through original list, consing onto result
 * - Each cons adds element at the beginning, inverting the order
 * 
 * Advantages:
 * - Efficient: O(n) linear
 * - Doesn't use recursion, avoids stack problems
 * - Easy to understand
 * 
 * Complexity: O(n) where n is the size of the list
 */
def reverseIterative[A](xs: List[A]): List[A] = {
  var ys: List[A] = Nil           // Result list, starts empty
  var remaining = xs              // Remaining list to process
  while (!remaining.isEmpty) {    // While there are elements to process
    ys = remaining.head :: ys     // Add head at beginning of result (inverts order)
    remaining = remaining.tail    // Move to next element
  }
  ys                              // Return reversed list
}

/**
 * Tail-recursive reversal implementation (RECOMMENDED)
 * 
 * @param xs List to reverse
 * @return New list with elements in reverse order
 * 
 * How it works:
 * - Uses helper function with accumulator
 * - Each recursive call adds head to accumulator
 * - Since cons adds at beginning, order is inverted
 * - Tail-recursive: recursive call is the last operation
 * 
 * Advantages:
 * - Tail-recursion: optimized by compiler
 * - Purely functional: no mutability
 * - Efficient: O(n) with constant stack
 * - Elegant and idiomatic in functional programming
 * 
 * Complexity: O(n) where n is the size of the list
 * Stack space: O(1) due to tail-recursion
 */
def reverseTailRec[A](xs: List[A]): List[A] = {
  /**
   * Tail-recursive helper function
   * 
   * @param remaining Remaining list to process
   * @param ys Accumulator with already processed elements (in reverse order)
   * @return Final reversed list
   */
  def loop(remaining: List[A], ys: List[A]): List[A] = {
    if (remaining.isEmpty) ys                      // Base case: return accumulator
    else loop(remaining.tail, remaining.head :: ys) // Move head to accumulator (inverts order)
  }
  loop(xs, Nil)                                    // Start with original list and empty accumulator
}

/**
 * Test and demonstration function for all implementations
 * 
 * Demonstrates:
 * - How to use each reversal function
 * - Comparison of results between correct implementations
 * 
 * Educational objective:
 * - Show that reverseNaive, reverseIterative and reverseTailRec produce correct results
 * - Allow visual comparison of results
 */
@main def testReverse(): Unit = {
  // Test data with different types and sizes
  val list1 = List(1, 2, 3, 4, 5)      // List of integers
  val list2 = List("a", "b", "c")      // List of strings
  val list3 = List(1)                  // Single element list
  val list4 = Nil                      // Empty list

  println("=== List Reversal Test ===\n")
  
  println("Original list1: " + list1)
  println("reverseNaive(list1):    " + reverseNaive(list1))    // List(5, 4, 3, 2, 1)
  println("reverseIterative(list1): " + reverseIterative(list1)) // List(5, 4, 3, 2, 1)
  println("reverseTailRec(list1):   " + reverseTailRec(list1))   // List(5, 4, 3, 2, 1)
  println()

  println("Original list2: " + list2)
  println("reverseNaive(list2):    " + reverseNaive(list2))    // List(c, b, a)
  println("reverseIterative(list2): " + reverseIterative(list2)) // List(c, b, a)
  println("reverseTailRec(list2):   " + reverseTailRec(list2))   // List(c, b, a)
  println()

  println("Original list3: " + list3)
  println("reverseNaive(list3):    " + reverseNaive(list3))    // List(1)
  println("reverseIterative(list3): " + reverseIterative(list3)) // List(1)
  println("reverseTailRec(list3):   " + reverseTailRec(list3))   // List(1)
  println()

  println("Original list4: " + list4)
  println("reverseNaive(list4):    " + reverseNaive(list4))    // List()
  println("reverseIterative(list4): " + reverseIterative(list4)) // List()
  println("reverseTailRec(list4):   " + reverseTailRec(list4))   // List()
}
