package $package_name$.services.sample

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class HelloWorldController extends Controller {
  get("/helloworld") { request: Request =>
    "Hello Vinh Dang!"
  }

  get("/users") { request: Request =>
    response.status(220).header("vinhdp", "I can build").body(Mock.listUsers)
  }
}
