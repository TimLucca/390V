def fib(n: Int): Int = {
    def go(a: Int, b: Int, rem: Int): Int =
        if(rem <= 0) b
        else go(b, a+b, rem-1)
    if(n == 1) 0
    else go(0, 1, n-2)
}
