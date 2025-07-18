package pair

/**
 * Demonstration of immutable classes in Scala with a generic Pair class
 * 
 * This file demonstrates:
 * 1. Generic immutable classes with type parameters
 * 2. Inheritance and method overriding
 * 3. Functional immutable data structures
 * 4. Constructor validation patterns (precondition vs smart constructor)
 * 5. Companion objects for controlled instance creation
 * 
 * Important concepts:
 * - Immutable data structures (all operations return new instances)
 * - Type safety through parametric polymorphism
 * - Multiple approaches to enforcing constraints on class instances
 */

/**
 * A generic class representing a pair of values of the same type
 * 
 * This immutable class holds two values of the same type T.
 * All operations (like copy) return new instances rather than modifying existing ones.
 * 
 * @tparam T The type of both elements in the pair
 * @param first The first element in the pair
 * @param second The second element in the pair
 */
class Pair[T](val first: T, val second: T) {
    
    /**
     * Creates a new copy of this pair, optionally changing one or both elements
     * 
     * @param first The value for the first element (defaults to current value)
     * @param second The value for the second element (defaults to current value)
     * @return A new Pair instance with the specified values
     */
    def copy(first: T = this.first, second: T = this.second): Pair[T] = {
        new Pair(first, second)
    }

    /**
     * Returns the name of this class, used in toString representation
     * Designed to be overridden by subclasses
     * 
     * @return The name of this class
     */
    def name = "Pair"

    /**
     * String representation of this pair
     * 
     * @return A string representation showing the class name and both elements
     */
    override def toString: String = s"${name}($first, $second)"
}

/**
 * A specialized pair class that only accepts positive integers
 * 
 * This class demonstrates the use of preconditions in constructors
 * to enforce constraints on instance creation.
 * 
 * @param first The first positive integer
 * @param second The second positive integer
 * @throws IllegalArgumentException if either value is not positive
 */
class PositivePair(override val first: Int, override val second: Int) extends Pair[Int](first, second) {
    // Anti-pattern: Constructor validation using require - will throw exception for invalid values
    require(first > 0 && second > 0, "Both elements must be positive integers")

    /**
     * Overrides the name method to provide specific class name
     * 
     * @return The name of this class ("PositivePair")
     */
    override def name = "PositivePair"
}

/**
 * A specialized pair class that only accepts non-negative integers
 * 
 * This class demonstrates the "smart constructor" pattern:
 * - Private constructor prevents direct instantiation
 * - Companion object controls instance creation
 * - Option type represents possible failure without exceptions
 * 
 * @param first The first non-negative integer
 * @param second The second non-negative integer
 */
private class PositivePair2 private (override val first: Int, override val second: Int) extends Pair[Int](first, second) {
    /**
     * Overrides the name method to provide specific class name
     * 
     * @return The name of this class ("PositivePair2")
     */
    override def name = "PositivePair2"
}

/**
 * Companion object for PositivePair2 that controls instance creation
 * 
 * This demonstrates the "smart constructor" pattern for safer instance creation:
 * - Validates inputs before creating instances
 * - Returns Option to represent possible failure
 * - No exceptions thrown for invalid inputs
 */
object PositivePair2 {
    /**
     * Creates a new PositivePair2 instance if both values are non-negative
     * 
     * @param first The first integer value
     * @param second The second integer value
     * @return Some(pair) if both values are non-negative, None otherwise
     */
    def apply(first: Int, second: Int): Option[PositivePair2] = {
        if (first >= 0 && second >= 0) {
            Some(new PositivePair2(first, second))
        } else {
            None
        }
    }
}


/**
 * Main function to demonstrate the PositivePair2 companion object
 * 
 * This demonstrates:
 * - Creating valid instances using the companion object
 * - Handling invalid inputs (negative values)
 * - How Option is used to represent success/failure
 */
@main def testPairCompanion(): Unit = {
    println("=== Pair Companion Object Demo ===\n")
    
    // Creating instances using the companion object
    val pp1 = PositivePair2(5, 10)
    val pp2 = PositivePair2(1, 1)
    val pp3 = PositivePair2(100, 200)
    
    println(s"pp1: $pp1")
    println(s"pp2: $pp2")
    println(s"pp3: $pp3")
    
    // Attempting to create invalid instances
    val invalidPair = PositivePair2(-1, 5)
    println(s"invalidPair: $invalidPair")  // Should be None
}

/**
 * Main function to demonstrate the generic Pair class
 * 
 * This demonstrates:
 * - Creating pairs of different types
 * - Using the immutable copy method
 * - Inheritance with PositivePair
 * - Handling constructor validation
 */
@main def testPair(): Unit = {
    println("=== Immutable Generic Pair Class Demo ===\n")
    
    // Creating instances with different types
    val intPair = new Pair[Int](1, 2)
    val stringPair = new Pair[String]("hello", "world")
    val doublePair = new Pair[Double](3.14, 2.71)
    
    println(s"intPair: $intPair")
    println(s"stringPair: $stringPair")
    println(s"doublePair: $doublePair")
    println()
    
    // Type inference - Scala can infer the type
    val inferredPair = new Pair(42, 99)  // Scala infers Pair[Int]
    println(s"inferredPair: $inferredPair")
    println()
    
    // Accessing values
    println("Accessing values:")
    println(s"intPair.getFirst: ${intPair.first}")
    println(s"stringPair.getSecond: ${stringPair.second}")
    println()
    
    // Immutable updates with Int
    println("Immutable updates with Int:")
    val intPair2 = intPair.copy(first = 10)
    val intPair3 = intPair.copy(second = 20)
    val intPair4 = intPair.copy(first = 100, second = 200)
    
    println(s"Original intPair: $intPair")
    println(s"intPair.copy(first = 10): $intPair2")
    println(s"intPair.copy(second = 20): $intPair3")
    println(s"intPair.copy(first = 100, second = 200): $intPair4")
    println()
    
    // Immutable updates with String
    println("Immutable updates with String:")
    val stringPair2 = stringPair.copy(first = "hi")
    val stringPair3 = stringPair.copy(second = "there")
    
    println(s"Original stringPair: $stringPair")
    println(s"stringPair.copy(first = \"hi\"): $stringPair2")
    println(s"stringPair.copy(second = \"there\"): $stringPair3")
    println()
    
    // Demonstrating immutability
    println("Demonstrating immutability:")
    println(s"intPair after updates: $intPair")
    println(s"stringPair after updates: $stringPair")
    println("Original pairs remain unchanged!")
    println()
    
    // Method chaining
    println("Method chaining:")
    val chained = intPair.copy(first = 5).copy(second = 10)
    println(s"intPair.copy(first = 5).copy(second = 10): $chained")
    println(s"Original intPair: $intPair")
    
    // Demonstrating toString vs getters
    println("\ntoString vs individual getters:")
    println(s"Using toString: $intPair")
    println(s"Using getters: first=${intPair.first}, second=${intPair.second}")
}

@main def testPositivePair(): Unit = {
    println("=== PositivePair Class Demo ===\n")
    
    // Creating valid PositivePair instances
    println("Creating valid PositivePair instances:")
    val pp1 = new PositivePair(5, 10)
    val pp2 = new PositivePair(1, 1)
    val pp3 = new PositivePair(100, 200)
    
    println(s"pp1: $pp1")
    println(s"pp2: $pp2")
    println(s"pp3: $pp3")
    println()
    
    // Demonstrating inheritance - PositivePair is a Pair[Int]
    println("Demonstrating inheritance:")
    val pairRef: Pair[Int] = pp1  // Polymorphism
    println(s"PositivePair as Pair[Int]: $pairRef")
    println(s"Accessing values: first=${pairRef.first}, second=${pairRef.second}")
    println()
    
    // Copy method inherited from Pair (but returns Pair[Int], not PositivePair)
    println("Using inherited copy method:")
    val copied = pp1.copy(first = 15)
    println(s"pp1.copy(first = 15): $copied (type: ${copied.getClass.getSimpleName})")
    println()
    
    // Attempting to create invalid PositivePair instances
    println("Attempting to create invalid PositivePair instances:")
    
    try {
        val invalid1 = new PositivePair(-1, 5)
        println(s"Created: $invalid1")
    } catch {
        case e: IllegalArgumentException => 
            println(s"Failed to create PositivePair(-1, 5): ${e.getMessage}")
    }
    
    try {
        val invalid2 = new PositivePair(5, -10)
        println(s"Created: $invalid2")
    } catch {
        case e: IllegalArgumentException => 
            println(s"Failed to create PositivePair(5, -10): ${e.getMessage}")
    }
    
    try {
        val invalid3 = new PositivePair(0, 0)
        println(s"Created: $invalid3")
    } catch {
        case e: IllegalArgumentException => 
            println(s"Failed to create PositivePair(0, 0): ${e.getMessage}")
    }
    
    try {
        val invalid4 = new PositivePair(-5, -10)
        println(s"Created: $invalid4")
    } catch {
        case e: IllegalArgumentException => 
            println(s"Failed to create PositivePair(-5, -10): ${e.getMessage}")
    }
    
    println()
    println("All validation tests completed!")
}