sealed trait List[+A]
case object Nil extends List[Nothing] // defining the empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
    def sum(ints: List[Int]): Int =
        foldRight(ints, 0)((x,y) => x + y)

    def product(ds: List[Double]): Double =
        foldRight(ds, 1.0)(_ * _)

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
        if (n == 0) x
        else x match {
            case Nil => Nil
            case Cons(_, y) => drop(y, n-1)
        }
    
    def dropWhile[A](x: List[A])(f: A => Boolean): List[A] = x match {
        case Cons(h, t) if (f(h)) => dropWhile(t, f)
        case _ => x
    }

    def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
        case Nil => a2 
        case Cons(h, t) => Cons(h, appends(t, a2))
    }

    def init[A](a: List[A]): List[A] = a match {
        case Nil => Nil
        case Cons(_, Nil) => Nil 
        case Cons(h, t) => Cons(h, init(t))
    }

    def foldRight[A,B](a: List[A], base: B)(f: (A, B) => B): B = a match {
        case Nil => base 
        case Cons(h, t) => f(h, foldRight(t, base)(f))
    }

    def length[A](as: List[A]): Int = 
        foldRight(as, 0)((_, y) => 1 + y)

    def foldLeft[A,B](a: List[A], base: B)(f: (B, A) => B): B = a match {
        case Nil => base
        case Cons(h, t) => foldLeft(t, f(h,b)(f)
    }
}

