import dca.Protocol.CorrectRequest.getIntFromRequest
import dca.Protocol._
import dca.FactorialServer.respondToRequest
import dca.FactorialServer.factorial
import org.scalatest.{FlatSpec, Matchers}

class ProtocolSpec extends FlatSpec with Matchers {

  "getIntFromRequest" should "parse simple request" in {

    getIntFromRequest("GET /calc?n=10 HTTP/1.1") should be (Some(10))

    getIntFromRequest("GET /calc?n=00000001 HTTP/1.1") should be (Some(1))

    getIntFromRequest("GET /calc?n= HTTP/1.1") should be (None)

  }
}

class FactorialServerSpec extends FlatSpec with Matchers {

  "factorial" should "calculate correct values" in {

    factorial(0) should be (1)

    factorial(1) should be (1)

    factorial(2) should be (2)

    factorial(12) should be (BigInt(479001600))

  }


  "respondToRequest" should "respond to request correctly" in {

    respondToRequest(RequestGetFavicon) should be (ResponseOk)

    respondToRequest("GET /calc?n= HTTP/1.1") should be (ResponseBad)

    respondToRequest("GET /calc?n=0 HTTP/1.1") should be ("1")

    respondToRequest("GET /calc?n=1 HTTP/1.1") should be ("1")

    respondToRequest("GET /calc?n=2 HTTP/1.1") should be ("2")

    respondToRequest("GET /calc?n=12 HTTP/1.1") should be ("479001600")

  }
}
