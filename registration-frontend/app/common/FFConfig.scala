package common

import javax.inject.Inject

import play.api.Configuration

class FFConfig @Inject()(config: Configuration) {
  lazy val registrationURL: String = config.underlying.getString("external.registration.url")
}
