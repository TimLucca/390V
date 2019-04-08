import java.net._
import java.io._
import scala.io._
import scala.util._
import java.util.Date
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global 

object EchoServer {
  val fourOhfour = "404.html"
  val index = "index.html"

  def getFile(req: String): File = req match {
    case "/" => new File(index)
    case _ => { val file = new File(".", req)
      println(req)
      req match {
        case "./" => new File(index)
        case _ if(file.exists()) => file
        case _ => new File(fourOhfour)
      }
    }
  }

  def parseInput(input: String): Array[String] = {
    val tokens: Array[String] = input.split(" ")
    tokens
  }

  def fileData(file: File): Array[Byte] = {
    val data: Array[Byte] = new Array(file.length().toInt)
    data
  }

  def fileChar(data: Array[Byte]): Array[Char] = {
    val dataChar: Array[Char] = data.map((b) => b.toChar)
    dataChar
  }

  def getStatus(file: File): Int = file.getName() match {
    case "404.html" => 404
    case _ => 200
  }

  def sendHeader(out: PrintWriter, status: Int, file: File): Unit = {
    if(status==200) out.println("HTTP/1.1 200 OK") 
    else out.println("HTTP/1.1 404 Not Found")
    out.println("Date: " + new Date())
    out.println("Server: Worst Server : 1.0")
    out.println("Content-type: text/html")
    out.println("Content-length: " + file.length.toInt)
    out.println()
    out.flush()
  }

  def read_and_write(in: BufferedReader, out:BufferedWriter, printer: PrintWriter): Unit = {
    val input = in.readLine()
    val tokens = parseInput(input)
    if(tokens(0)!="GET"){
      println("Invalid request command. Only implemented for GET")
    }
    else{
      val file = getFile(tokens(1))
      val statusCode = getStatus(file)
      val fileIn = new FileInputStream(file)
      var data = fileData(file)
      fileIn.read(data, 0, file.length().toInt)
      sendHeader(printer, statusCode, file)
      out.write(fileChar(data), 0, file.length().toInt)
      println("sent")
    }
    
    out.flush()
    in.close()
    out.close()
    printer.close()
  }

  def serve(s: Socket): Unit = {
      //val s = server.accept()
        val in = new BufferedReader(new InputStreamReader(s.getInputStream))
        val out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream))
        val printer = new PrintWriter(s.getOutputStream)

        read_and_write(in, out, printer)
      
        s.close()
      }
      

  def main(args: Array[String]) {
    val server = new ServerSocket(9999)
    while(true) {
      var future = Future { server.accept }
      future onComplete {
        case Success(s) => serve(s) 
        case Failure(t) => println("error")
      }
    }
  }
}
