package controllers

import javax.inject._

import play.api.mvc._
import services.RegistrationService

@Singleton
class RegistrationController @Inject() (service: RegistrationService) extends Controller {

  def registration = Action.async { implicit request =>
    service.getRegistration map { _ =>
      Ok(views.html.registration())
    }
  }
}
