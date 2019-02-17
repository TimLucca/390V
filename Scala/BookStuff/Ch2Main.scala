object MyModule {
    def abs(n: Int): Int = 
        if (n < 0) -n 
        else n 

    def factorial(n: Int): Int = {
        def go(n: Int, acc: Int): Int = 
            if (n <= 0) acc
            else go(n-1, n*acc)
        
        go(n, 1)
    }

    def fib(n: Int): Int = {
        def go(a: Int, b: Int, rem: Int): Int =
            if(rem <= 0) b
            else go(b, a+b, rem-1)
        if(n == 1) 0
        else go(0, 1, n-2)
    }

    def formatRes(name: String, x: Int, f: Int => Int) = {
        val msg = "The %s of %d is %d"
        msg.format(name, x, f(x))
    }

    // Unit is similar to a 'void' return type
    def main(args: Array[String]): Unit = 
        println(formatRes("absolute value", -20, abs))
        println(formatRes("factorial", 6, factorial))
        println(formatRes("fibonacci", 10, fib))
}