/**
 * Demonstration of two implementations of the length function (calculate list size)
 * 
 * This implementation shows:
 * 1. Simple recursion (not tail-recursive)
 * 2. Tail-recursion with accumulator
 * 
 * Both implementations are generic (type A) and work with any type of list.
 */

/**
 * Simple recursive implementation to calculate the size of the list
 * 
 * @param xs List to calculate the size
 * @return Number of elements in the list
 * 
 * How it works:
 * - Base case: if the list is empty, return 0
 * - Recursive case: return 1 + size of tail
 * 
 * Complexity: O(n) where n is the size of the list
 * Problem: Not tail-recursive, can cause stack overflow on very large lists
 */
def lengthRec[A](xs: List[A]): Int = {
  if (xs.isEmpty) 0                    // Base case: empty list has size 0
  else 1 + lengthRec(xs.tail)         // Recursion: 1 + size of tail
}

/**
 * Tail-recursive implementation to calculate the size of the list
 * 
 * @param xs List to calculate the size
 * @return Number of elements in the list
 * 
 * How it works:
 * - Uses an auxiliary function (loop) that is tail-recursive
 * - Maintains an accumulator (acc) that counts the processed elements
 * - With each recursive call, increments the accumulator
 * 
 * Advantages:
 * - Tail-recursive: does not cause stack overflow
 * - Uses @scala.annotation.tailrec to ensure optimization
 * - More efficient for large lists
 * 
 * Complexity: O(n) where n is the size of the list
 */
def lengthTRec[A](xs: List[A]): Int = {
  @scala.annotation.tailrec
  def loop(xs: List[A], acc: Int): Int = {
    if (xs.isEmpty) acc                 // Base case: return the accumulator
    else loop(xs.tail, acc + 1)        // Recursion: increment counter and process tail
  }
  loop(xs, 0)  // Start with counter 0
}

/**
 * Main function to test the length implementations
 * 
 * Tests both implementations with:
 * - List of integers
 * - List of strings
 * - Empty list (Nil)
 * 
 * Both implementations should produce the same results.
 */
@main def testLength(): Unit = {
  println("=== Testing lengthRec (simple recursion) ===")
  println(lengthRec(List(1, 2, 3)))          // 3
  println(lengthRec(List("a", "b", "c")))    // 3
  println(lengthRec(Nil))                    // 0

  println("\n=== Testing lengthTRec (tail-recursive) ===")
  println(lengthTRec(List(1, 2, 3)))         // 3
  println(lengthTRec(List("a", "b", "c")))   // 3
  println(lengthTRec(Nil))                   // 0
}