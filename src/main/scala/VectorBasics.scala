/**
 * Complete demonstration of fundamental concepts about Vectors in Scala
 * 
 * Vector is an immutable functional data structure that offers:
 * 1. Efficient random access (effectively O(1))
 * 2. Efficient prepend and append operations (O(log n))
 * 3. Efficient updates (O(log n))
 * 4. Balance between different types of operations
 * 
 * Important Vector characteristics:
 * - Implemented as a Trie (prefix tree) with branching factor 32
 * - Maximum tree height is log₃₂(n), resulting in very efficient operations
 * - Default recommended choice for general-purpose immutable sequences
 * - Excellent when access patterns are not known in advance
 * 
 * Comparison with other structures:
 * - List: better for frequent prepend and sequential processing
 * - ArraySeq: better for pure random access and memory usage
 * - Vector: better for balanced general use
 */
object VectorBasics {
  
  def main(args: Array[String]): Unit = {
    
    /**
     * 1. VECTOR CREATION
     * 
     * Vector is an immutable data structure with:
     * - Efficient random access (effectively O(1))
     * - Efficient append/prepend operations
     * - Good general performance for most operations
     */
    println("=== 1. Creating Vectors ===")
    val numbers = Vector(1, 2, 3, 4, 5)           // Direct construction with elements
    val fruits = Vector("apple", "banana", "orange", "grape")  // Vector of strings
    val empty = Vector.empty[Int]                  // Empty vector with explicit type
    val range = Vector.range(1, 10)               // Vector generated from range
    
    println(s"Numbers: $numbers")
    println(s"Fruits: $fruits")
    println(s"Empty: $empty")
    println(s"Range: $range")
    println()
    
    /**
     * 2. RANDOM ACCESS TO ELEMENTS
     * 
     * Vector offers very efficient index access:
     * - Complexity: effectively O(1) 
     * - Much faster than List for positional access
     * - Important: indices start at 0
     */
    println("=== 2. Element Access ===")
    
    // Index access - efficient!
    println(s"numbers(0): ${numbers(0)}")         // First element - effectively O(1)
    println(s"numbers(3): ${numbers(3)}")         // Direct access by arbitrary index
    println(s"fruits(1): ${fruits(1)}")           // Works with any type
    
    // Safe access methods
    println(s"numbers.head: ${numbers.head}")     // First element (throws exception if empty)
    println(s"numbers.last: ${numbers.last}")     // Last element (efficient in Vector!)
    println(s"fruits.headOption: ${fruits.headOption}") // Safe: Some(value) or None
    println(s"empty.headOption: ${empty.headOption}")   // None for empty vector
    println()
    
    /**
     * 3. ELEMENT ADDITION
     * 
     * Vector has a big advantage over List:
     * - List: prepend O(1), append O(n) 
     * - Vector: both prepend and append are O(log n) effectively constant
     * 
     * This makes Vector ideal when you need to add elements at both ends
     */
    println("=== 3. Adding Elements ===")
    val original = Vector(2, 3, 4)
    println(s"Original: $original")
    
    // Prepending (adding at the beginning) - efficient O(log n)
    val prepended = 1 +: original                 // Operator +: adds to the left
    println(s"Prepended 1: $prepended")
    
    // Appending (adding at the end) - efficient O(log n)
    val appended = original :+ 5                  // Operator :+ adds to the right
    println(s"Appended 5: $appended")
    
    // Multiple chained operations
    val extended = 0 +: original :+ 5 :+ 6        // Can chain operations
    println(s"Extended: $extended")
    println()
    
    // 4. Immutability - updates create new vectors
    // Like all functional structures, Vector is immutable
    // Operations return new vectors, preserving the original
    println("4. Immutability - updates create new vectors:")
    val vec = Vector(10, 20, 30, 40)
    val updated = vec.updated(2, 99)              // Updates index 2 to value 99
    println(s"Original: $vec")                   // Original vector remains unchanged
    println(s"Updated:  $updated")               // New vector with the change
    println(s"Original unchanged: ${vec(2)}")    // Verify that original didn't change
    println()
    
    // 5. Slicing and taking operations
    println("5. Slicing operations:")
    val seq = Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(s"Original: $seq")
    println(s"slice(2, 6): ${seq.slice(2, 6)}")
    println(s"take(5): ${seq.take(5)}")
    println(s"drop(3): ${seq.drop(3)}")
    println(s"takeRight(3): ${seq.takeRight(3)}")
    println(s"dropRight(2): ${seq.dropRight(2)}")
    println()
    
    // 6. Functional operations
    println("6. Functional operations:")
    val nums = Vector(1, 2, 3, 4, 5, 6)
    println(s"Original: $nums")
    println(s"map(_ * 2): ${nums.map(_ * 2)}")
    println(s"filter(_ > 3): ${nums.filter(_ > 3)}")
    println(s"filterNot(_ % 2 == 0): ${nums.filterNot(_ % 2 == 0)}")
    println(s"foldLeft(0)(_ + _): ${nums.foldLeft(0)(_ + _)}")
    println(s"reduce(_ + _): ${nums.reduce(_ + _)}")
    println(s"zipWithIndex: ${nums.zipWithIndex}")
    println()
    
    // 7. Concatenation
    println("7. Concatenation:")
    val vec1 = Vector(1, 2, 3)
    val vec2 = Vector(4, 5, 6)
    val concatenated = vec1 ++ vec2
    println(s"vec1: $vec1")
    println(s"vec2: $vec2")
    println(s"vec1 ++ vec2: $concatenated")
    println()
    
    // 8. Performance characteristics
    println("8. Performance characteristics:")
    val large = Vector.range(1, 1000)
    println(s"Large vector (size: ${large.size})")
    println(s"Random access: large(500) = ${large(500)} (effectively O(1))")
    println(s"Prepend: 0 +: large takes O(log n)")
    println(s"Append: large :+ 1000 takes O(log n)")
    println(s"Update: large.updated(500, 999) takes O(log n)")
    println()
    
    // 9. Comparison with other collections
    println("9. Comparison with other collections:")
    val vector = Vector(1, 2, 3, 4, 5)
    val list = List(1, 2, 3, 4, 5)
    val arraySeq = scala.collection.immutable.ArraySeq(1, 2, 3, 4, 5)
    
    println("Vector advantages:")
    println(s"  - Balanced: good for both prepend and append")
    println(s"  - Random access: vector(3) = ${vector(3)} (effectively O(1))")
    println(s"  - Efficient updates: vector.updated(2, 99) = ${vector.updated(2, 99)}")
    
    println("List advantages:")
    println(s"  - Prepend: 0 :: list = ${0 :: list} (O(1))")
    println(s"  - Pattern matching: head/tail")
    println(s"  - Recursive algorithms")
    
    println("ArraySeq advantages:")
    println(s"  - True O(1) random access")
    println(s"  - Memory efficient for large collections")
    println()
    
    // 10. Conversion operations
    println("10. Conversion operations:")
    val vectorNums = Vector(1, 2, 3, 4, 5)
    println(s"Vector: $vectorNums")
    println(s"To List: ${vectorNums.toList}")
    println(s"To Array: ${vectorNums.toArray.mkString("Array(", ", ", ")")}")
    println(s"To Set: ${vectorNums.toSet}")
    
    // Converting from other collections
    val listToVector = List(1, 2, 3).toVector
    val arrayToVector = Array(1, 2, 3).toVector
    println(s"From List: $listToVector")
    println(s"From Array: $arrayToVector")
    println()
    
    // 11. Use cases
    println("11. When to use Vector:")
    println("✓ Need both efficient prepend AND append")
    println("✓ Frequent random access by index")
    println("✓ Mix of reads and updates")
    println("✓ General-purpose immutable sequence")
    println("✓ Don't know access patterns in advance")
    println()
    println("Vector is often the best default choice for immutable sequences!")
  }
  
  // Helper function to demonstrate Vector's balanced performance
  def demonstrateBalance(): Unit = {
    println("\n=== Vector Balance Demonstration ===")
    
    val size = 10000
    val vector = Vector.range(0, size)
    
    println(s"Vector size: $size")
    
    // Show that both prepend and append are efficient
    println("\nBoth prepend and append are efficient:")
    
    val startPrepend = System.nanoTime()
    val prepended = -1 +: vector
    val prependTime = System.nanoTime() - startPrepend
    
    val startAppend = System.nanoTime()
    val appended = vector :+ size
    val appendTime = System.nanoTime() - startAppend
    
    println(s"Prepend time: ${prependTime}ns")
    println(s"Append time: ${appendTime}ns")
    println(s"Both operations are O(log n) - very efficient!")
    
    // Random access
    val randomIndex = size / 2
    val startAccess = System.nanoTime()
    val value = vector(randomIndex)
    val accessTime = System.nanoTime() - startAccess
    
    println(s"Random access vector($randomIndex): $value (${accessTime}ns)")
    println("Random access is effectively O(1)")
  }
}