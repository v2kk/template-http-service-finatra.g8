package $package_name$.controllers

import com.twitter.finagle.http.{Fields, Request}
import com.twitter.finatra.http.Controller

class CorsController extends Controller {

  options("/:*") { request: Request =>
    response
      .ok
      .header(Fields.AccessControlAllowOrigin, "*")
      .header(Fields.AccessControlAllowHeaders, "*")
      .header(Fields.AccessControlAllowMethods, "*")
      .header(Fields.AccessControlExposeHeaders, "Access-Control-Allow-Origin")
      .header(Fields.AccessControlMaxAge, "600")
  }
}
