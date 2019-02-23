sealed trait List[+A]
case object Nil extends List[Nothing] // defining the empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
    def sum(ints: List[Int]): Int = ints match {
        case Nil => 0
        case Cons(x,xs) => x + sum(xs)
    }

    def product(ds: List[Double]): Double = ds match {
        case Nil => 1.0
        case Cons(0.0, _) => 0.0
        case Cons(x,xs) => x * product(xs)
    }

    def apply[A](as: A*): List[A] = 
        if (as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))

    def tail[A](x: List[A]): List[A] = x match {
        case Nil => Nil
        case Cons(_, y) => y
    }

    def setHead[A](x: List[A], h: A): List[A] = x match {
        case Nil => Cons(h, Nil)
        case Cons(_, x) => Cons(h, x)
    }

    def drop[A](x: List[A], n: Int): List[A] = 
}

