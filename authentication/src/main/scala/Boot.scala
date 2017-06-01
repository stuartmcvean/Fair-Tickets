import akka.http.scaladsl.server.HttpApp
import api.TestRoute
import com.typesafe.config.{Config, ConfigFactory}

trait Server extends HttpApp with TestRoute {
  val config: Config
  override def routes = testRoute
}

object Boot extends Server with App {
  override val config = ConfigFactory.load()
  startServer(config.getString("http.host"), config.getInt("http.port"))
}