package services

import common.FFConfig
import play.api.Logger
import play.api.libs.json.{JsError, JsSuccess, Json, Reads}
import play.api.mvc.{AnyContent, Request}
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}

import scala.concurrent.{ExecutionContext, Future}
trait FairTicketsService {

  val logger = Logger("FairTicketsService")

  protected def ws: WSClient

  protected def config: FFConfig

  protected def get[T](path: String)(implicit request: Request[AnyContent], reads: Reads[T], ex: ExecutionContext): Future[T] = {
    requester(path).get map parseJson
  }

/*  def post[T](path: String)(implicit request: Request[AnyContent], wrt: Writeable[T], reads: Reads[T], ex: ExecutionContext): Future[T] = {
    requester(path).post map parseJson
  }*/

  private def requester(path: String)(implicit request: Request[AnyContent]): WSRequest = {
    ws.url(config.registrationURL + path)
  }

  private def parseJson[T](wsRes: WSResponse)(implicit rds: Reads[T], ex: ExecutionContext, request: Request[AnyContent]): T = {
    Json.parse(wsRes.body).validate[T] match {
      case JsSuccess(data, _) => data
      case JsError(errors) => logger.error("There was a problem with the JSON:\n" + errors)
        throw new RuntimeException()
    }
  }
}
