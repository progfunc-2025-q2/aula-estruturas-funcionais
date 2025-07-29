// Trait Queue[T] defines the interface for a generic queue.
// It specifies the essential operations that any queue should support.
trait Queue[T] {
    // Adds an element to the queue and returns the new queue.
    def enqueue(element: T): Queue[T]
    // Removes the front element from the queue, returning an Option of the element and the new queue.
    def dequeue: (Option[T], Queue[T])
    // Returns the front element without removing it.
    def peekFront: Option[T]
    // Returns the back element without removing it.
    def peekBack: Option[T]
    // Checks if the queue is empty.
    def isEmpty: Boolean
    // Returns the number of elements in the queue.
    def size: Int
    // Returns a string representation of the queue.
    def toString: String
}

/**
  * A simple queue implementation using a List.
  * Elements are stored in a single List.
  * Enqueue adds to the end, dequeue removes from the front.
  *
  * @param elements The list of elements in the queue.
  */
class NaiveListQueue[T](elements: List[T] = List.empty) extends Queue[T] {

    // Returns the front element (first in the list), if any.
    override def peekFront: Option[T] = elements.headOption

    // Returns the back element (last in the list), if any.
    override def peekBack: Option[T] = elements.lastOption

    // Adds an element to the end of the list (back of the queue).
    def enqueue(element: T): Queue[T] = new NaiveListQueue(elements :+ element)

    // Removes the front element (first in the list).
    def dequeue: (Option[T], Queue[T]) = 
      if (elements.isEmpty) (None, this)
      else (Some(elements.head), new NaiveListQueue(elements.tail))

    // Returns the front element as Option.
    def front: Option[T] = elements.headOption

    // Checks if the queue is empty.
    def isEmpty: Boolean = elements.isEmpty

    // Returns the number of elements in the queue.
    def size: Int = elements.size

    // String representation: shows the elements from front to back.
    override def toString: String = s"front -> (${elements.mkString(", ")})"
}

// Main method to test NaiveListQueue functionality.
@main def testNaiveListQueue(): Unit = {
    val queue =  NaiveListQueue[Int]()
    println(queue) // Should print: front -> ()
    val (deqElem, newQueue) = queue.dequeue
    println(deqElem) // Should print: None
    println(newQueue) // Should print: front -> ()
    val queue2 = newQueue.enqueue(42).enqueue(43)
    println(queue2) // Should print: front -> (42, 43)

    val (deqElem2, newQueue2) = queue2.dequeue
    println(deqElem2) // Should print: Some(42)
    println(newQueue2) // Should print: front -> (43)
}

/**
  * Queue with two lists, one for enqueueing and one for dequeueing.
  * This implementation is more efficient than NaiveListQueue for enqueue and dequeue operations.
  * - 'front' list stores elements ready to be dequeued.
  * - 'rear' list stores elements recently enqueued (to be reversed when needed).
  */
private class TwoStacksQueue[T] private (val front: List[T] = List.empty, val rear: List[T] = List.empty, override val size: Int) extends Queue[T] {

    // Returns the front element (first to be dequeued).
    override def peekFront: Option[T] =
      if (front.nonEmpty) Some(front.head)
      else if (rear.nonEmpty) Some(rear.reverse.head)
      else None

    // Returns the back element (last enqueued).
    override def peekBack: Option[T] =
      if (rear.nonEmpty) Some(rear.head)
      else if (front.nonEmpty) Some(front.reverse.head)
      else None

    // Auxiliary constructors for convenience.
    private def this() = this(List.empty, List.empty, 0)
    private def this(elements: List[T]) = this(elements, List.empty, elements.size)

    // Adds an element to the rear list (back of the queue).
    def enqueue(element: T): TwoStacksQueue[T] = new TwoStacksQueue(front, element :: rear, size + 1)

    // Removes the front element. If front is empty, rear is reversed and used.
    def dequeue: (Option[T], TwoStacksQueue[T]) = 
      if (front.isEmpty && rear.isEmpty) (None, this)
      else if (front.isEmpty) {
        val newFront = rear.reverse
        (Some(newFront.head), new TwoStacksQueue(newFront.tail, List.empty, size - 1))
      } else {
        (Some(front.head), new TwoStacksQueue(front.tail, rear, size - 1))
      }

    // Checks if both lists are empty.
    def isEmpty: Boolean = front.isEmpty && rear.isEmpty

    // String representation: shows front and rear lists.
    override def toString: String = s"front -> (${front.mkString(", ")}) (${rear.reverse.mkString(", ")}) <- rear"
}

// Companion object for TwoStacksQueue, provides factory methods.
object TwoStacksQueue {
  // Returns an empty queue.
  def empty[T]: TwoStacksQueue[T] = new TwoStacksQueue[T]()
  // Creates a queue from given elements.
  def apply[T](elements: T*): TwoStacksQueue[T] = new TwoStacksQueue(elements.toList)
}

// Main method to test TwoStacksQueue functionality.
@main def testTwoStacksQueue  = {
  val queue = TwoStacksQueue(1, 2, 3)
  println(queue) // Should print: front -> (1, 2, 3) () <- rear
  val (deqElem, newQueue) = queue.dequeue
  println(deqElem) // Should print: Some(1)
  println(newQueue) // Should print: front -> (2, 3) () <- rear
  val queue2 = newQueue.enqueue(42).enqueue(43)
  println(queue2) // Should print: front -> (2, 3) (42, 43) <- rear
}