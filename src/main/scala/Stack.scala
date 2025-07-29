// Trait defining the interface for a generic stack data structure.
// A stack is a collection that supports push, pop, and peek operations.
trait Stack[T] {
  // Adds an element to the top of the stack.
  def push(x: T): Stack[T]
  // Removes and returns the top element, along with the new stack.
  def pop: (Option[T], Stack[T])
  // Returns the top element without removing it.
  def peek: Option[T]
  // Checks if the stack is empty.
  def isEmpty: Boolean
  // Returns the number of elements in the stack.
  def size: Int
}

// Implementation of Stack using a List to store elements.
// The constructor is private to enforce usage through the companion object.
// This class is private to prevent direct instantiation outside the companion object.
private class ListStack[T] private (val elements: List[T], override val size: Int) extends Stack[T] {

  // Auxiliary constructor for an empty stack.
  private def this() = this(Nil, 0)
  // Auxiliary constructor from a List.
  private def this(elements: List[T]) = this(elements, elements.size)

  // Adds an element to the top of the stack.
  override def push(x: T): Stack[T] = new ListStack(x :: elements, size + 1)

  // Removes and returns the top element, along with the new stack.
  override def pop: (Option[T], Stack[T]) = if (elements.isEmpty) {
    (None, this)
  } else {
    (Some(elements.head), new ListStack(elements.tail, size - 1))
  }

  // Returns the top element without removing it.
  override def peek: Option[T] = if (elements.isEmpty) {
    None
  } else {
    Some(elements.head)
  }

  // Checks if the stack is empty.
  override def isEmpty: Boolean = elements.isEmpty

  // String representation of the stack, showing the top and all elements.
  override def toString: String = s"top -> (${elements.mkString(", ")})"
}

// Companion object for ListStack, provides factory methods for creating stacks.
object ListStack {
  // Creates an empty stack.
  def apply[T](): ListStack[T] = new ListStack[T]()
  // Creates a stack from a variable number of elements.
  def apply[T](elements: T*): ListStack[T] = new ListStack(elements.toList)
}

// This test would fail because the ListStack constructor is private.
// It demonstrates why we use the companion object for construction.
// @main def testPrimaryConstructor(): Unit = {
//   val stack = new ListStack(List(1, 2, 3, 4), 4)
//   println(s"Stack created with elements: $stack")
//   println(s"Stack size: ${stack.size}")
// }

// Main method to test the ListStack implementation.
// Demonstrates stack creation, push, pop, peek, and empty checks.
@main def testListStack(): Unit = {
  val stack = ListStack[Int]() // Create an empty stack
  println(s"Initial stack: $stack")

  val stack1 = stack.push(1) // Push 1 onto the stack
  println(s"After pushing 1: $stack1")

  val stack2 = stack1.push(2) // Push 2 onto the stack
  println(s"After pushing 2: $stack2")

  val (top, stack3) = stack2.pop // Pop the top element
  println(s"Popped element: ${top.getOrElse("None")}, New stack: $stack3")

  val peeked = stack3.peek // Peek at the top element
  println(s"Peeked element: ${peeked.getOrElse("None")}")

  println(s"Is the stack empty? ${stack3.isEmpty}") // Check if stack is empty

  val (top1, stack4) = stack3.pop // Pop again
  println(s"Popped element: ${top1.getOrElse("None")}, New stack: $stack4")

  val (top2, stack5) = stack4.pop // Pop again
  println(s"Popped element: ${top2.getOrElse("None")}, New stack: $stack5")
  println(s"Is the stack empty after popping all elements? ${stack5.isEmpty}")

  val stack6 = ListStack[Int](1, 2, 3) // Create a stack with initial elements
  println(s"Stack created with elements: $stack6")
}


