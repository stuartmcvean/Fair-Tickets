package services

import javax.inject.Inject

import common.FFConfig
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class RegistrationService @Inject()(val ws: WSClient, val config: FFConfig) extends FairTicketsService {
  def getRegistration: Future[String] = get("/registration")
}
