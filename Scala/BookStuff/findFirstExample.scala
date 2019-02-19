// MONOMORPHIC FUNCTION
def findFirst(ss: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
        if (n >= ss.length) -1    // return -1 if not in the string
        else if (ss(n) == key) n // return the index the key was found at
        else loop(n+1)          //recurse
}

// POLYMORPHIC FUNCTION
// uses type 'A' arrays instead of explicitly stating Int or String
def findFirst[A](arr: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
        if (n >= arr.length) -1
        else if (p(arr(n))) n 
        else loopI(n+1)
}

