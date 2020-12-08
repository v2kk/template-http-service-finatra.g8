package $package_name$.filters

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Fields, MediaType, Request, Response}
import com.twitter.finatra.http.filters.HttpResponseFilter
import com.twitter.util.Future

class CorsFilter[R <: Request] extends HttpResponseFilter[R] {
  override def apply(request: R, service: Service[R, Response]): Future[Response] = {
    for (response <- service(request)) yield {
      setResponseHeaders(response)
      response
    }
  }

  // https://github.com/twitter/finatra/blob/develop/http/src/main/scala/com/twitter/finatra/http/filters/HttpResponseFilter.scala
  private def setResponseHeaders(response: Response): Unit = {
    response.headerMap.setUnsafe(Fields.AccessControlAllowOrigin, "*")
    response.headerMap.setUnsafe(Fields.AccessControlAllowHeaders, "*")
    response.headerMap.setUnsafe(Fields.AccessControlAllowMethods, "DELETE, POST, GET, OPTIONS, PUT")
    response.headerMap.setUnsafe(Fields.AccessControlExposeHeaders, "Access-Control-Allow-Origin")
    response.headerMap.setUnsafe(Fields.AccessControlMaxAge, "600")

    if (response.contentType.isEmpty && response.length != 0) {
      // see: https://www.w3.org/Protocols/rfc2616/rfc2616-sec7.html#sec7.2.1
      response.headerMap.setUnsafe(Fields.ContentType, MediaType.OctetStream)
    }
  }
}
