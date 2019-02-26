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

    @annotation.tailrec
    def foldLeft[A,B](a: List[A], base: B)(f: (B, A) => B): B = a match {
        case Nil => base
        case Cons(h, t) => foldLeft(t, f(h,b)(f)
    }

    def concat[A](a: List[List[A]]): List[A] =
        foldRight(a, Nil:List[A])(append)

    def append2[A](a: List[A], b: List[A]): List[A] = 
        foldRgiht(a, b)(Cons(_,_))

    def incAll(a: List[Int]): List[Int] =
        foldRight(a, Nil: List[Int])((h,t) => Cons(h+1,t))

    def doubToStr(a: List[Double]): List[String] =
        foldRgiht(a, Nil: List[String])((h,t) => Cons(h.toString,t))

    def map[A,B](a: List[A])(f: A => B): List[B] = 
        foldRight(a, Nil: List[B])((h,t) => Cons(f(h),t))

    def filter[A](a: List[A])(f: A => Boolean): List[A] =
        foldRight(a, Nil: List[A])((h,t) => if f(h) Cons(h,t) else t)

    def flatMap[A,B](a: List[A])(f: A => List[B]): List[B] =
        concat(map(l)(f))

    def flatMapviaFilter[A,B](a: List[A])(f: A => Boolean): List[B] =
        flatMap(a)((h => if (f(h)) List(h) else Nil)

    def vecAdd[A](a: List[A], b: List[A]): List[A] = (a,b) match {
        case (Nil,_) => Nil
        case (_,Nil) => Nil
        case (Cons(h1,t1),Cons(h2,t2)) => Cons(h1+h2, vecAdd(t1,t2))
    }

    def zipWith[A,B,C](a: List[A], b: List[B])(f: (A,B) => C): List[C] = (a,b) match {
        case (Nil,_) => Nil 
        case (_,Nil) => Nil 
        case (Cons(h1,t1),Cons(h2,t2)) => Cons(f(h1,h2), zipWith(t1,t2)(f))
    }

    @annotation.tailrec
    def startsWith[A](l: List[A], prefix: List[A]): Boolean = (l,prefix) match {
        case (_,Nil) => true
        case (Cons(h,t),Cons(h2,t2)) if h == h2 => startsWith(t, t2)
        case _ => false
    }
        
    @annotation.tailrec
    def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
        case Nil => sub == Nil
        case _ if startsWith(sup, sub) => true
        case Cons(h,t) => hasSubsequence(t, sub)
    }
}

