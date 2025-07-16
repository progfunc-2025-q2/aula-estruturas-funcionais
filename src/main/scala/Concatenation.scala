package concatenation

import reverse.reverseTailRec

/**
 * Demonstration of different list concatenation implementations
 * 
 * This file shows three different approaches to concatenate two lists:
 * 1. Simple structural recursion
 * 2. Iterative implementation using loops
 * 3. Optimized tail-recursive implementation
 * 
 * Important concepts:
 * - Concatenation is a fundamental operation in functional programming
 * - Different implementations have different performance characteristics
 * - Trade-offs between simplicity and efficiency
 * - Use of reversal as auxiliary technique
 */

/**
 * Simple recursive concatenation implementation
 * 
 * @param xs First list
 * @param ys Second list  
 * @return New list with xs followed by ys
 * 
 * How it works:
 * - Base case: if xs is empty, return ys
 * - Recursive case: builds list with head of xs followed by recursive concatenation
 * 
 * Complexity: O(n) where n is the size of the first list
 * Problem: Not tail-recursive, may cause stack overflow
 */
def concatenate[A](xs: List[A], ys: List[A]): List[A] = {
  if (xs.isEmpty) ys                              // Base case: first list empty
  else xs.head :: concatenate(xs.tail, ys)       // Recursion: rebuilds xs with ys appended
}

/**
 * Iterative concatenation implementation using loops
 * 
 * @param xs First list
 * @param ys Second list
 * @return New list with xs followed by ys
 * 
 * How it works:
 * - Reverses the first list
 * - Iterates over reversed list, consing onto ys
 * - Final result is xs concatenated with ys
 * 
 * Advantages:
 * - Doesn't use recursion, avoids stack problems
 * Disadvantages:
 * - Uses local mutable variables
 * 
 * Complexity: O(n) where n is the size of the first list
 */
def concatenateIterative[A](xs: List[A], ys: List[A]): List[A] = {
  var zs = ys                    // Initial result is the second list
  var rs = xs.reverse           // First list reversed for processing

  while (rs.nonEmpty) {         // While there are elements to process
    zs = rs.head :: zs         // Add current element to result
    rs = rs.tail               // Move to next element
  }
  zs                            // Return concatenated list
}

/**
 * Tail-recursive concatenation implementation
 * 
 * @param xs First list
 * @param ys Second list
 * @return New list with xs followed by ys
 * 
 * How it works:
 * - Reverses the first list before processing
 * - Uses tail-recursive helper function
 * - Processes elements of first list from back to front
 * 
 * Advantages:
 * - Tail-recursive: optimized by compiler
 * - Doesn't cause stack overflow
 * - Pure functional style
 * 
 * Complexity: O(n) where n is the size of the first list
 * Note: Uses @scala.annotation.tailrec to ensure optimization
 */
def concatenateTailRec[A](xs: List[A], ys: List[A]): List[A] = {
  @scala.annotation.tailrec
  def loop(rs: List[A], zs: List[A]): List[A] = {
    if (rs.isEmpty) zs                         // Base case: processed list is empty
    else loop(rs.tail, rs.head :: zs)         // Tail recursion: move head to result
  }
  loop(xs.reverse, ys)  // start with reversed first list and second list as accumulator
}

/**
 * Main function to test concatenation implementations
 * 
 * Tests all three implementations with:
 * - Normal integer lists
 * - Edge cases with empty lists
 * - String lists
 * - Single element lists
 * - Comparison with native ++ operator
 * 
 * All implementations should produce the same results.
 */
@main def testConcatenate(): Unit = {
  val list1 = List(1, 2, 3)
  val list2 = List(4, 5, 6)

  println("=== List Concatenation Examples ===\n")
  
  // Basic concatenation tests
  println("1. Basic concatenation:")
  println(s"list1: $list1")
  println(s"list2: $list2")
  println(s"concatenate(list1, list2): ${concatenate(list1, list2)}")
  println(s"concatenateTailRec(list1, list2): ${concatenateTailRec(list1, list2)}")
  println(s"concatenateIterative(list1, list2): ${concatenateIterative(list1, list2)}")
  println()
  
  // Edge cases with empty lists
  println("2. Edge cases with empty lists:")
  val emptyList = List.empty[Int]
  println(s"concatenate([], list2): ${concatenate(emptyList, list2)}")
  println(s"concatenate(list1, []): ${concatenate(list1, emptyList)}")
  println(s"concatenateTailRec([], list2): ${concatenateTailRec(emptyList, list2)}")
  println(s"concatenateTailRec(list1, []): ${concatenateTailRec(list1, emptyList)}")
  println(s"concatenateIterative([], list2): ${concatenateIterative(emptyList, list2)}")
  println(s"concatenateIterative(list1, []): ${concatenateIterative(list1, emptyList)}")
  println()
  
  // Test with strings
  println("3. String concatenation:")
  val words1 = List("Hello", "beautiful")
  val words2 = List("world", "!")
  println(s"words1: $words1")
  println(s"words2: $words2")
  println(s"concatenate(words1, words2): ${concatenate(words1, words2)}")
  println(s"concatenateTailRec(words1, words2): ${concatenateTailRec(words1, words2)}")
  println(s"concatenateIterative(words1, words2): ${concatenateIterative(words1, words2)}")
  println()
  
  // Test with single elements
  println("4. Single element lists:")
  val single1 = List(10)
  val single2 = List(20)
  println(s"concatenate($single1, $single2): ${concatenate(single1, single2)}")
  println(s"concatenateTailRec($single1, $single2): ${concatenateTailRec(single1, single2)}")
  println(s"concatenateIterative($single1, $single2): ${concatenateIterative(single1, single2)}")
  println()
  
  // Comparison with native ++ operator
  println("5. Comparison with built-in ++ operator:")
  println(s"list1 ++ list2: ${list1 ++ list2}")
  println(s"Built-in matches our functions: ${(list1 ++ list2) == concatenate(list1, list2)}")
}