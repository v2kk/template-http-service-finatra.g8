package $package_name$.utils

import com.typesafe.config.ConfigFactory

object Config {

  val config = ConfigFactory.load()

  object Http {
    def PORT = config.getString("http.port")
  }
}
