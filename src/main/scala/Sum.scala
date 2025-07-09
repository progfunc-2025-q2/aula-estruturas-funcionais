package listSum

/**
 * Demonstration of two implementations of the sum function (sum list elements)
 * 
 * This implementation shows:
 * 1. Simple recursion (not tail-recursive)
 * 2. Tail-recursive recursion with accumulator
 * 
 * Both implementations work specifically with lists of integers.
 */

/**
 * Simple recursive implementation to sum all elements in the list
 * 
 * @param xs List of integers to sum
 * @return Sum of all elements in the list
 * 
 * How it works:
 * - Base case: if the list is empty, return 0 (neutral element of addition)
 * - Recursive case: return head + sum of tail
 * 
 * Complexity: O(n) where n is the size of the list
 * Problem: Not tail-recursive, can cause stack overflow on very large lists
 */
def sumRec(xs: List[Int]): Int = {
  if (xs.isEmpty) 0                      // Base case: empty list sums to 0
  else xs.head + sumRec(xs.tail)        // Recursion: head + sum of tail
}

/**
 * Tail-recursive implementation to sum all elements in the list
 * 
 * @param xs List of integers to sum
 * @return Sum of all elements in the list
 * 
 * How it works:
 * - Uses an auxiliary function (loop) that is tail-recursive
 * - Maintains an accumulator (acc) that stores the partial sum
 * - With each recursive call, adds the current head to the accumulator
 * 
 * Advantages:
 * - Tail-recursive: does not cause stack overflow
 * - More efficient for large lists
 * - Uses parameter with default value (acc = 0)
 * 
 * Complexity: O(n) where n is the size of the list
 * Note: Does not use @scala.annotation.tailrec, but could use it to ensure optimization
 */
def sumTR(xs: List[Int]): Int = {
  def loop(xs: List[Int], acc: Int = 0): Int = {
    if (xs.isEmpty) acc                    // Base case: return the accumulator
    else loop(xs.tail, acc + xs.head)     // Recursion: add head to accumulator
  }
  loop(xs)  // Start with default accumulator (0)
}

/**
 * Main function to test the sum implementations
 * 
 * Tests both implementations with:
 * - Regular list of integers
 * - Another list of integers
 * - Empty list (Nil)
 * - List with a single element
 * 
 * Both implementations should produce the same results.
 */
@main def testListSumTailRec(): Unit = {
  println("=== Testing sumRec (simple recursion) ===")
  println(sumRec(List(1, 2, 3)))    // 6
  println(sumRec(List(4, 5, 6)))    // 15
  println(sumRec(Nil))              // 0
  println(sumRec(List(10)))         // 10

  println("\n=== Testing sumTR (tail-recursive) ===")
  println(sumTR(List(1, 2, 3)))     // 6
  println(sumTR(List(4, 5, 6)))     // 15
  println(sumTR(Nil))               // 0
  println(sumTR(List(10)))          // 10
}
