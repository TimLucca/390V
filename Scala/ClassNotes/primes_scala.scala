import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.implicits.global 

object PrimeFactors {
    def result(number: Long, list: List[Long] = List()): List[Long] = { 
        // need to use a while loop with a long, because int is too small
        var n = 2L
        while (n <= number) {
            if (number % n == 0) {
                return result(number / n, list :+ n)
            }
            n += 1
        }
        list
    }
    
    def main(args: Array[String]) {
        val primes = Seq(3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53)
        var futures = List[Future[List[Long]]]()
        for (n <- primes) {
            val v = scala.math.pow(2,n).toLong-1
            val f: Future[List[Long]] = Future {
                result(v)
            }
            f.map{x=>println(x)}
            // or f.onComplete{x=>println(x)}
            futures = f :: futures 
        }
        for (future <- futures) {
            Await.result(future,Duration.Inf)
            println("Done with one")
        }
    }
}