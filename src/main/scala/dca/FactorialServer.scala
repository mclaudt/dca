package dca

import java.io._
import java.net._

import scala.annotation.tailrec
import scala.io._


object FactorialServer {

  def factorial(n: Int): BigInt = {
    @tailrec
    def factorialAccum(number: Int, accum: BigInt): BigInt = {
      if (number <= 1)
        accum
      else
        factorialAccum(number - 1, number * accum)
    }
    factorialAccum(n, 1)
  }


  def main(args: Array[String]) {

    val server = new ServerSocket(8089)
    println("Server is ready - go to http://localhost:8089/calc?n=42 for example.")

    while (true) {
      val socket = server.accept()
      val in = new BufferedSource(socket.getInputStream()).getLines()
      val out = new PrintStream(socket.getOutputStream())

      if (in.hasNext) {
        val request = in.next() println(s"Request: ${request}")

        val response = responseToRequest(request) println(s"Response: ${response}")

        out.println(response) out.flush()
      }
      socket.close()

    }
  }

  def responseToRequest(request: String) = {
    import Protocol._
    request match {
      case RequestGetFavicon => ResponseOk
      case CorrectRequest(number) => factorial(number).toString()
      case _ => ResponseBad
    }
  }
}

object Protocol {
  val RequestGetFavicon = "GET /favicon.ico HTTP/1.1"
  val ResponseOk = "HTTP/1.1 200 OK"
  val ResponseBad = "HTTP/1.1 400 Bad Request"


  object CorrectRequest {
    val numberRE = """GET /calc\?n=([0-9]+) HTTP/1\.1""".r

    def unapply(request: String) = getIntFromRequest(request)

    def getIntFromRequest(request: String) = {
      (request match {
        case numberRE(i) => Some(i)
        case _ => None
      }).flatMap(toInt(_))
    }


    def toInt(s: String): Option[Int] = {
      try {
        Some(s.toInt)
      } catch {
        case e: Exception => None
      }
    }
  }


}
