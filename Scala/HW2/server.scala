import java.net._
import java.io._
import scala.io._
import java.util.StringTokenizer

object EchoServer {
     val home = "index.html"
    val fourOhfour = "404.html" 
    def readFdata(file: File, fileLen: Int): Array[Byte] = {
        val fileData: Array[Byte] = new Array(fileLen)
        val fileIn = new FileInputStream(file)
        fileIn.read(fileData)
        fileData
    }

    def read_and_write(in: BufferedReader, out: BufferedOutputStream): Unit = {
        var x = in.readLine()
        println(x)
        val token = new StringTokenizer(x)
        val typ = token.nextToken()
        println("Request method: " + typ)
        val req = token.nextToken()
        val z: String = req match {
            case "/" => home
            case _ => req
        }
        println("Request file: " + z)
        val file = new File(z)
        val fileLen = file.length().toInt
        println(fileLen)
        if(typ != "GET"){
            println("Invalid Request")
        }
        if(file.exists()){
            println("it exists")
            if(file.isFile()){println("Is a file")}
            if(file.isDirectory()){println("Is a directory")}
            println(file.getName())
            try{
                val fileData: Array[Byte] = readFdata(file, fileLen)
                out.write(fileData, 0, fileLen)
                out.flush()
            } catch {
                case fnf: FileNotFoundException => {println("File could not be opened")}
                case ioe: IOException => {println("Error reading file")}
            }
        }
        else{
            println("Not found")
        }
    
        in.close()
        out.close()
    }

    def serve(server: ServerSocket): Unit = {
        val s = server.accept()
        val in = new BufferedReader(new InputStreamReader(s.getInputStream))
        val out = new BufferedOutputStream(s.getOutputStream)
        read_and_write(in, out)

        s.close()
    }

    def main(args: Array[String]): Unit = {
        val server = new ServerSocket(9999)
        while(true) {
            serve(server)
        }
    }
}