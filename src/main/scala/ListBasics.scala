/**
 * Complete demonstration of fundamental concepts about lists in Scala
 * 
 * This file contains practical examples of the following concepts:
 * 1. Creating lists with elements
 * 2. Creating empty lists
 * 3. Extracting head and tail
 * 4. Size verification
 * 5. Adding elements using cons (::)
 * 6. List concatenation
 * 7. Pattern matching with lists
 * 8. Recursive functions with lists
 * 9. List immutability
 * 
 * Important concepts:
 * - Lists in Scala are immutable (cannot be modified after creation)
 * - Operations return new lists
 * - head/tail are O(1) operations, but tail can cause exception on empty list
 * - Cons (::) is an O(1) operation that adds element at the beginning
 * - Concatenation (++) is O(n) where n is the size of the first list
 */
object ListBasics {
  
  def main(args: Array[String]): Unit = {
    println("=== Basic List Manipulation Examples ===\n")
    
    // Creating lists with elements
    // In Scala, lists are created with List(elements...)
    // Lists are homogeneous (all elements of the same type)
    println("Creating lists with elements:")
    val numbers = List(1, 2, 3, 4, 5)        // List of Int
    val fruits = List("apple", "banana", "orange")  // List of String
    val booleans = List(true, false, true)    // List of Boolean
    
    println(s"Numbers: $numbers")
    println(s"Fruits: $fruits")
    println(s"Booleans: $booleans")
    println()
    
    // Creating empty lists
    // Multiple ways to create an empty list
    println("Creating empty lists:")
    val emptyList1: List[Int] = List()        // Empty list with type annotation
    val emptyList2: List[String] = Nil        // Nil is the empty list
    val emptyList3 = List.empty[Double]       // Factory method for empty list
    
    println(s"Empty list 1: $emptyList1")
    println(s"Empty list 2: $emptyList2")
    println(s"Empty list 3: $emptyList3")
    println(s"Are they empty? ${emptyList1.isEmpty}, ${emptyList2.isEmpty}, ${emptyList3.isEmpty}")
    println()
    
    // Extracting head and tail
    // head: first element of the list (O(1))
    // tail: list without the first element (O(1))
    // WARNING: head and tail throw exception if the list is empty!
    println("Extracting head and tail:")
    println(s"Original list: $numbers")
    
    if (numbers.nonEmpty) {
      println(s"Head: ${numbers.head}")      // First element
      println(s"Tail: ${numbers.tail}")     // List without the first element
      println(s"Head type: ${numbers.head.getClass.getSimpleName}")
      println(s"Tail type: ${numbers.tail.getClass.getSimpleName}")
    }
    
    // Safe head extraction using headOption
    // headOption returns Some(head) or None if the list is empty
    println(s"Safe head extraction: ${numbers.headOption}")        // Some(1)
    println(s"Safe head of empty list: ${emptyList1.headOption}")  // None
    println()
    
    // Checking list length
    // length/size are O(n) - need to traverse the entire list!
    println("Checking list length:")
    println(s"Length of numbers list: ${numbers.length}")
    println(s"Length of fruits list: ${fruits.length}")
    println()

    // Adding elements using cons (::)
    // The :: (cons) operator adds an element at the BEGINNING of the list
    // It's an O(1) operation and very efficient
    // Syntax: element :: list
    println("Adding elements using cons (::):")
    val originalList = List(2, 3, 4)
    println(s"Original list: $originalList")
    
    // Prepending single elements (adding at the beginning)
    val withOne = 1 :: originalList          // Add 1 at the beginning
    val withZero = 0 :: withOne             // Add 0 at the beginning
    
    println(s"After prepending 1: $withOne")
    println(s"After prepending 0: $withZero")
    
    // Prepending multiple elements (multiple cons operations)
    val withNegatives = -2 :: -1 :: withZero  // Add -2 and -1 at the beginning
    println(s"After prepending -2 and -1: $withNegatives")
    
    // Building a list from scratch using cons
    // Always ends with Nil (empty list)
    val builtFromScratch = 1 :: 2 :: 3 :: Nil
    println(s"Built from scratch: $builtFromScratch")
    println()
    
    // List concatenation
    // The ++ operator concatenates two lists
    // Complexity: O(n) where n is the size of the first list
    println("List concatenation:")

    val list1 = List(1, 2, 3)
    val list2 = List(4, 5, 6)
    val list3 = List(7, 8, 9)
    
    println(s"List 1: $list1")
    println(s"List 2: $list2")
    println(s"List 3: $list3")
    
    // Using ++ operator for concatenation
    val concatenated1 = list1 ++ list2
    println(s"list1 ++ list2: $concatenated1")
    
    // Chaining multiple concatenations
    val concatenated2 = list1 ++ list2 ++ list3
    println(s"list1 ++ list2 ++ list3: $concatenated2")
    
    // Concatenating with empty lists
    // Empty list is the neutral element of concatenation
    val withEmpty1 = list1 ++ List.empty[Int]
    val withEmpty2 = List.empty[Int] ++ list1
    println(s"list1 ++ empty: $withEmpty1")
    println(s"empty ++ list1: $withEmpty2")
    
    // Using ::: operator (alternative concatenation)
    // ::: is equivalent to ++, but specific to lists
    val concatenated3 = list1 ::: list2
    println(s"list1 ::: list2: $concatenated3")
    
    // Concatenating lists of different types (same base type)
    val stringNumbers = List("1", "2", "3")
    val moreStringNumbers = List("4", "5")
    val allStrings = stringNumbers ++ moreStringNumbers
    println(s"String concatenation: $allStrings")
    
    // Concatenating mixed content (same type)
    val fruits1 = List("apple", "banana")
    val fruits2 = List("orange", "grape")
    val allFruits = fruits1 ++ fruits2
    println(s"Fruit concatenation: $allFruits")
    
    // Performance note: ++ creates a new list
    // The original lists remain unchanged (immutability)
    println("Note: Concatenation creates new lists (immutable)")
    println(s"Original list1 after concatenation: $list1")
    println(s"Original list2 after concatenation: $list2")
    println()
    
    // More advanced examples
    // Demonstrates important functional programming concepts
    println("More advanced examples:")

    // Pattern matching with head and tail
    // Pattern matching is a powerful technique for structure decomposition
    def describeList[T](list: List[T]): String = list match {
      case Nil => "Empty list"                    // Empty list
      case head :: Nil => s"Single element: $head"  // List with 1 element
      case head :: tail => s"Head: $head, Tail has ${tail.length} elements"  // List with multiple elements
    }
    
    println(s"Describe empty list: ${describeList(emptyList1)}")
    println(s"Describe single element: ${describeList(List(42))}")
    println(s"Describe multiple elements: ${describeList(numbers)}")
    println()
    
    // Recursive function using head and tail
    // Demonstrates typical structural recursion in functional programming
    def sumList(list: List[Int]): Int = list match {
      case Nil => 0                        // Base case: empty list
      case head :: tail => head + sumList(tail)  // Recursive case: head + sum of tail
    }
    
    println(s"Sum of $numbers: ${sumList(numbers)}")
    
    // Building lists with different cons patterns
    // Mixes List() with :: operator
    val mixedConstruction = "first" :: "second" :: List("third", "fourth")
    println(s"Mixed construction: $mixedConstruction")
    
    // Demonstrating immutability
    // Operations on lists always create new lists
    val original = List(1, 2, 3)
    val modified = 0 :: original          // Does not modify 'original'
    println(s"Original after cons operation: $original")
    println(s"New list after cons: $modified")
  }
  
  // Helper methods for demonstration
  // Demonstrates additional useful operations with lists
  def demonstrateListOperations(): Unit = {
    println("\n=== Additional List Operations ===")
    
    val sample = List(10, 20, 30, 40)
    
    // Multiple ways to access elements
    println(s"Sample list: $sample")
    println(s"First element (head): ${sample.head}")      // First element
    println(s"Last element: ${sample.last}")              // Last element (O(n))
    println(s"All but first (tail): ${sample.tail}")      // All except the first
    println(s"All but last (init): ${sample.init}")       // All except the last (O(n))
    
    // Size and indices
    println(s"Length: ${sample.length}")                  // Size of the list (O(n))
    println(s"Element at index 2: ${sample(2)}")          // Access by index (O(n))
    
    // Checking properties
    println(s"Is empty? ${sample.isEmpty}")               // Check if empty
    println(s"Is non-empty? ${sample.nonEmpty}")          // Check if non-empty
  }
}