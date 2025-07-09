package append

/**
 * Demonstration of two implementations of the append function
 * 
 * This implementation shows:
 * 1. Simple recursion (not tail-recursive)
 * 2. Tail-recursion with accumulator
 * 
 * Both implementations are generic (type A) and work with any type of list.
 * The list is immutable and persistent, with no structual sharing.
 */

/**
 * Simple recursive implementation to append an element to the end of the list
 * 
 * @param xs Original list
 * @param elem Element to be appended at the end
 * @return New list with the element appended
 * 
 * How it works:
 * - Base case: if the list is empty, return a list with only the element
 * - Recursive case: reconstruct the list by placing the current head followed by the result
 *   of the recursive call with the tail
 * 
 * Complexity: O(n) where n is the size of the list
 * Problem: Not tail-recursive, can cause stack overflow on very large lists
 */
def appendRec[A](xs: List[A], elem: A): List[A] = {
  if (xs.isEmpty) List(elem)           // Base case: empty list
  else xs.head :: appendRec(xs.tail, elem)  // Recursion: reconstruct the list
}

/**
 * Tail-recursive implementation to append an element to the end of the list
 * 
 * @param xs Original list
 * @param elem Element to be appended at the end
 * @return New list with the element appended
 * 
 * How it works:
 * - Uses an auxiliary function (loop) that is tail-recursive
 * - Builds the list in reverse order in the accumulator
 * - Adds the element at the beginning of the accumulator and then reverses everything
 * 
 * Advantages:
 * - Tail-recursive: does not cause stack overflow
 * - Uses @scala.annotation.tailrec to ensure optimization
 * 
 * Time complexity: O(n) where n is the size of the list
 * Note: Requires a reverse operation at the end, which is also O(n)
 */
def appendTR[A](xs: List[A], elem: A): List[A] = {
  @scala.annotation.tailrec
  def loop(xs: List[A], acc: List[A]): List[A] = {
    if (xs.isEmpty) (elem :: acc).reverse  // Base case: add element and reverse
    else loop(xs.tail, xs.head :: acc)     // Accumulate elements in reverse order
  }
  loop(xs, Nil)  // Start with empty list as accumulator
}

/**
 * Main function to test the append implementations
 * 
 * Tests both implementations with:
 * - List of integers
 * - List of strings
 * - Empty list (Nil)
 * 
 * Both implementations should produce the same results.
 */
@main def testAppend(): Unit = {
  println("=== Testing appendRec (simple recursion) ===")
  println(appendRec(List(1, 2, 3), 4))       // List(1, 2, 3, 4)
  println(appendRec(List("a", "b"), "c"))    // List("a", "b", "c")
  println(appendRec(Nil, 42))                // List(42)

  println("\n=== Testing appendTR (tail-recursive) ===")
  println(appendTR(List(1, 2, 3), 4))        // List(1, 2, 3, 4)
  println(appendTR(List("a", "b"), "c"))     // List("a", "b", "c")
  println(appendTR(Nil, 42))                 // List(42)
}
