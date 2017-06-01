package api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}

trait TestRoute {

  self: Directives =>

  val testRoute: Route = {
    path("test") {
      get {
        complete(StatusCodes.OK)
      }
    }
  }

}
