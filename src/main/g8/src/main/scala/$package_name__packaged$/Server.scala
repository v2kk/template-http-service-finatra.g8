package $package_name$

import com.fasterxml.jackson.databind.SerializationFeature
import com.google.inject.Module
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.jackson.ScalaObjectMapper
import $package_name$.controllers.CorsController
import $package_name$.filters.CorsFilter
import $package_name$.modules.AdditionalScalaObjectMapperModule
import $package_name$.services.sample.HelloWorldController

import $package_name$.utils.Config._

object ServerMain extends Server

class Server extends HttpServer {

  override val disableAdminHttpServer: Boolean = true
  override val defaultHttpPort: String = Http.PORT
  val objectMapper: ScalaObjectMapper = ScalaObjectMapper.builder.withAdditionalMapperConfigurationFn(
    _.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
  )
    .objectMapper

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CorsFilter[Request]]
      .add[HelloWorldController]
      .add[CorsController]
  }

  override def jacksonModule: Module = AdditionalScalaObjectMapperModule
}
