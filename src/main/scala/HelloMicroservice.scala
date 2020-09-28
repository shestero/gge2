import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.typesafe.config.ConfigFactory

object HelloMicroservice extends App {
  val app = "gge2"
  val http_host = "localhost"
  val http_port = 9090 // 9000 conflict with Clickhouse

  println(s"HELLO\t$app. Listen $http_host:$http_port")

  val config = ConfigFactory.parseString("akka.loglevel = DEBUG")
  implicit val system = ActorSystem(app, config)
  //implicit val ec = system.dispatcher
  //implicit val materializer = ActorMaterializer()

  println("Akka: " + system.name)

  def route =
    logRequestResult("akka-http-microservice") {
      path("hello") {
        get {
          parameter("name") { name: String =>
            complete(s"Hello, $name!\n")
          }
        }
      }
    }

  Http().newServerAt(http_host, http_port).bindFlow(route)

  println(s"BYE\t$app (still alive)")

}
