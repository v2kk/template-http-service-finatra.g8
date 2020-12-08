package $package_name$.services.sample

case class User(name: String, email: String)

object Mock {
  val listUsers = Seq(
    User("Vinh Dang", "vinhdp4@fpt.com.vn"),
    User("Vinh Dang 2", "vinhdangphuc@gmail.com")
  )
}
