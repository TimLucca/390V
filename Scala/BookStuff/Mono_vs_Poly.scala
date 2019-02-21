// MONOMORPHIC FUNCTION
def findFirst(ss: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
        if (n >= ss.length) -1    // return -1 if not in the string
        else if (ss(n) == key) n // return the index the key was found at
        else loop(n+1)          //recurse
    loop(0)
}

// POLYMORPHIC FUNCTION
// uses type 'A' arrays instead of explicitly stating Int or String
def findFirst[A](arr: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
        if (n >= arr.length) -1
        else if (p(arr(n))) n 
        else loop(n+1)
    loop(0)
}

def isSorted[A](arr: Array[A], greater: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean =
        if (n >= arr.length-1) true
        else if(greater(arr(n), arr(n+1))) false
        else go(n+1)
    go(0)
}

