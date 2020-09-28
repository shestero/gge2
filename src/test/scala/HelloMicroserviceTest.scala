import akka.http.scaladsl.model.{HttpRequest, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.testkit.TestDuration
import akka.http.scaladsl.server.Directives._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HelloMicroserviceTest extends AnyWordSpec with Matchers with ScalatestRouteTest {

  // Synthetic route to enable pathEnd testing
  val testRoute = {
    pathPrefix("test") {
      HelloMicroservice.route
    }
  }

  "The service" should {
    "return a greeting for GET requests" in {
      // tests:
      Get("/test/hello?name=Tester") ~> testRoute ~> check {
        responseAs[String] shouldEqual "Hello, Tester!\n"
      }
    }

    "return a NotFound error for PUT requests to the root path" in {
      // tests:
      Put("/test") ~> Route.seal(testRoute) ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }
  }
}
