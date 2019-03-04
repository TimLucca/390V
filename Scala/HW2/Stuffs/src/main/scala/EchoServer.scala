import java.net._
import java.io._
import scala.io._

object EchoServer {
  val fourOhfour = "404.html"
  val index = "index.html"

  def getFile(req: String): File = {
    val file = new File(req)
    req match {
      case "/" => new File(index)
      case _ if(file.exists()) => file
      case _ => new File(fourOhfour)
    }
  }

  def parseInput(input: String): Array[String] = {
    val tokens: Array[String] = input.split(" ")
    tokens
  }

  def fileBytes(file: File): Array[Byte] = {
    val size: Array[Byte] = new Array(file.length().toInt)
    size
  }

  def read_and_write(in: BufferedReader, out:BufferedWriter): Unit = {
    val input = in.readLine()
    val tokens = parseInput(input)
    if(tokens(0)=="GET"){
      val file = getFile(tokens(1))
      println(file.getPath())
      val fileIn = new FileInputStream(file)
      println(fileIn.read(fileBytes(file)))
      out.write(file.getPath())
      println("sent")
    }
    
    out.flush()
    in.close()
    out.close()
  }

  def serve(server: ServerSocket): Unit = {
      val s = server.accept()
      val in = new BufferedReader(new InputStreamReader(s.getInputStream))
      val out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream))

      read_and_write(in, out)
    
      s.close()
  }

  def main(args: Array[String]) {
    val server = new ServerSocket(9999)
    while(true) {
      serve(server)
    }
  }
}



