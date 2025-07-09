# Functional Data Structures - Lists in Scala

A comprehensive educational project demonstrating fundamental concepts of functional data structures, specifically focusing on immutable lists and their operations in Scala.

## Project Overview

This project provides practical implementations and examples of essential operations on functional data structures, focusing on:

- **Immutable list operations** and their characteristics
- **Functional programming patterns** for data manipulation
- **Performance analysis** of different implementation approaches
- **Structural recursion** as a natural way to process data structures
- **Pattern matching** for data structure decomposition

## Project Structure

```
src/main/scala/
├── Append.scala      # Append operation on immutable lists
├── Length.scala      # Length computation for lists
├── Sum.scala         # Aggregation operations on lists
└── ListBasics.scala  # Fundamental list operations and patterns
```

## Core Implementations

### 1. Append Operations (`Append.scala`)
Demonstrates how to extend immutable lists by appending elements:
- **`appendRec`**: Structural recursion approach
- **`appendTR`**: Tail-recursive approach with accumulator pattern

### 2. Length Computation (`Length.scala`)
Shows different approaches to compute the size of a list:
- **`lengthRec`**: Direct structural recursion
- **`lengthTRec`**: Iterative-style computation using tail recursion

### 3. Aggregation Operations (`Sum.scala`)
Implements reduction operations on list elements:
- **`sumRec`**: Structural recursion for element reduction
- **`sumTR`**: Accumulator-based reduction pattern

### 4. List Fundamentals (`ListBasics.scala`)
Comprehensive demonstration of core list operations:
- **List construction** using cons (`::`) and factory methods
- **List deconstruction** with head/tail operations
- **List combination** through concatenation (`++` and `:::`)
- **Pattern matching** for structural analysis
- **Immutability** principles in action

## Key Learning Concepts

### Functional Data Structure Properties
- **Immutability**: Data structures cannot be modified after creation
- **Persistence**: Operations return new structures while preserving the original
- **Structural Sharing**: Efficient memory usage through shared structure components
- **Algebraic Data Types**: Lists as recursive data structures with base and inductive cases

### List Operations and Complexity
- **O(1) Operations**: `head`, `tail`, `isEmpty`, `cons (::)` - constant time access
- **O(n) Operations**: `length`, `last`, `init`, `concatenation (++)` - linear time traversal
- **Structural Recursion**: Natural pattern for processing recursive data structures
- **Tail Recursion**: Stack-safe alternative for iterative-style processing

### Functional Programming Patterns
- **Pattern Matching**: Decomposing data structures to access their components
- **Higher-Order Functions**: Functions that operate on other functions
- **Fold Operations**: Generic way to reduce data structures to single values
- **Constructor Patterns**: Building data structures using fundamental operations

## Usage

### Building and Running

This is a standard sbt project with Scala 3. You can:

```bash
# Compile the project
sbt compile

# Run a specific main function
sbt "runMain testAppend"      # Test append implementations
sbt "runMain testLength"      # Test length implementations
sbt "runMain testListSumTailRec"  # Test sum implementations

# Start Scala 3 REPL
sbt console

# Run tests
sbt test
```

### Example Usage

```scala
// Basic list construction and operations
val list1 = List(1, 2, 3)
val list2 = 0 :: list1                    // List(0, 1, 2, 3)
val list3 = list1 ++ List(4, 5)          // List(1, 2, 3, 4, 5)

// Structural operations
val result1 = appendRec(List(1, 2, 3), 4)  // List(1, 2, 3, 4)
val length1 = lengthRec(List("a", "b", "c"))  // 3
val sum1 = sumRec(List(1, 2, 3, 4, 5))  // 15

// Alternative implementations (tail-recursive)
val result2 = appendTR(List(1, 2, 3), 4)  // List(1, 2, 3, 4)
val length2 = lengthTRec(List("a", "b", "c"))  // 3
val sum2 = sumTR(List(1, 2, 3, 4, 5))  // 15
```
