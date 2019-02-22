sealed trait List[+A]
case object Nil extends List[Nothing] // defining the empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
    def sum(ints: List[Int]): Int = ints match {
        case Nil => 0
        case Cons(x,xs) => x + sum(xs)
    }
}