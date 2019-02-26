sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
    def size[A](t: Tree[A]): Int = t match {
        case Leaf(_) => 1
        case Branch(left,right) => size(left) + size(right) + 1
    }

    def maximum[A](t: Tree[A]): A = t match {
        case Leaf(x) => x
        case Branch(left,right) => maximum(left) max maximum(right)
    }

    def depth[A](t: Tree[A]): Int = t match {
        case Leaf(_) => 1
        case Branch(left,right) => 1 + (depth(left) max depth(right))
    }

    def map[A,B](t: Tree[A])(f: A => B): Tree[B] = t match {
        case Leaf(x) => f(x)
        case Branch(left,right) => Branch(map(left)(f) map(right)(f))
    }

    def fold[A,B](t: Tree[A])(f: A => B)(b: (B,B) => B): B = t match {
        case Leaf(_) => f(_)
        case Branch(_,_) => b(fold(_)(f)(b), fold(_)(f)(b))
    }
    
}