import java.net._
import java.io._
import scala.io._

object EchoServer {
    def main(args: Array[String]) {
        val server = new ServerSocket(9999)
        println("Socket created at port: " + server.getInetAddress().getHostAddress())
        while (true) {
            var s = server.accept()
            println("Accepted")
            var in = new BufferedSource(s.getInputStream()).getLines()
            var out = new PrintStream(s.getOutputStream())

            out.println(in.next)
            out.flush()
            s.close()
        }
    }
}