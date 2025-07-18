package traits

/**
 * Demonstration of traits and polymorphism in Scala with a stack data structure
 *
 * This file demonstrates:
 * 1. Using traits to define an abstract data type interface
 * 2. Multiple implementations of the same trait
 * 3. Functional style state handling (no mutation)
 * 4. Generic programming with type parameters
 *
 * Important concepts:
 * - Traits as interfaces for functional data structures
 * - Immutable stack implementations
 * - Type parameters for generic programming
 * - Subtyping polymorphism with trait inheritance
 */

/**
 * A trait defining the Stack abstract data type
 *
 * This trait defines the interface for an immutable stack implementation.
 * All operations return new Stack instances rather than modifying the existing one.
 * 
 * @tparam T The type of elements stored in the stack
 */
trait Stack[T] {
  /**
   * Pushes a new element onto the stack
   *
   * @param x The element to push onto the stack
   * @return A new stack with x on top
   */
  def push(x: T): Stack[T]
  
  /**
   * Removes the top element from the stack
   *
   * @return A tuple containing the top element and the new stack without that element
   */
  def pop: (T, Stack[T]) 
  
  /**
   * Checks if the stack is empty
   *
   * @return true if the stack is empty, false otherwise
   */
  def isEmpty: Boolean
  
  /**
   * Returns the number of elements in the stack
   *
   * @return The number of elements in the stack
   */
  def size: Int
}

/**
 * A stack implementation using a List as the underlying data structure
 *
 * @tparam T The type of elements stored in the stack
 */
class ListStack[T] extends Stack[T] {
  /**
   * The current implementation uses ??? (unimplemented) placeholders.
   * This indicates that these methods need to be implemented.
   *
   * A complete implementation would store the stack elements in a List[T].
   * The top of the stack would be the head of the list.
   */

  /**
   * Pushes an element onto this stack
   *
   * In a proper implementation, this would create a new list with the 
   * element as the head and the current stack elements as the tail.
   *
   * @param x The element to push
   * @return A new stack with x on top
   */
  override def push(x: T): Stack[T] = ???

  /**
   * Removes the top element from the stack
   *
   * In a proper implementation, this would return the head of the list
   * and a new stack containing the tail of the list.
   *
   * @return A tuple with the top element and the rest of the stack
   */
  override def pop: (T, Stack[T]) = ???

  /**
   * Checks if the stack is empty
   *
   * In a proper implementation, this would check if the underlying list is empty.
   *
   * @return true if the stack is empty, false otherwise
   */
  override def isEmpty: Boolean = ???

  /**
   * Returns the number of elements in the stack
   *
   * In a proper implementation, this would return the size of the underlying list.
   *
   * @return The number of elements in the stack
   */
  override def size: Int = ???
}

/**
 * A stack implementation using Array-based structure
 *
 * This implementation would use an immutable array-based structure (like ArraySeq)
 * to store the stack elements.
 *
 * @tparam T The type of elements stored in the stack
 */
class ArrayStack[T] extends Stack[T] {
  /**
   * The current implementation uses ??? (unimplemented) placeholders.
   * This indicates that these methods need to be implemented.
   *
   * A complete implementation would store the stack elements in an immutable
   * array-like structure such as ArraySeq or Vector.
   */

  /**
   * Pushes an element onto this stack
   *
   * In a proper implementation, this would create a new array-based
   * structure with the new element added.
   *
   * @param x The element to push
   * @return A new stack with x on top
   */
  override def push(x: T): Stack[T] = ???

  /**
   * Removes the top element from the stack
   *
   * In a proper implementation, this would return the last element
   * and a new stack without that element.
   *
   * @return A tuple with the top element and the rest of the stack
   */
  override def pop: (T, Stack[T]) = ???

  /**
   * Checks if the stack is empty
   *
   * In a proper implementation, this would check if the underlying array is empty.
   *
   * @return true if the stack is empty, false otherwise
   */
  override def isEmpty: Boolean = ???

  /**
   * Returns the number of elements in the stack
   *
   * In a proper implementation, this would return the size of the underlying array.
   *
   * @return The number of elements in the stack
   */
  override def size: Int = ???
}