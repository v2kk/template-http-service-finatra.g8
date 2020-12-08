package $package_name$.modules

import com.fasterxml.jackson.databind.{ Module, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.twitter.finatra.jackson.modules.ScalaObjectMapperModule

object AdditionalScalaObjectMapperModule extends ScalaObjectMapperModule {
  override def additionalMapperConfiguration(mapper: ObjectMapper): Unit = {
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
  }

  override def additionalJacksonModules: Seq[Module] = {
    Seq(new JavaTimeModule())
  }
}
