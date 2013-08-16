package jp.seraphr.graphviz

import org.scalatra.ScalatraFilter
import javax.servlet.FilterConfig

class HelloScalatra extends ScalatraFilter {
  get("/test/"){
    val tStream = response

    val tDataOption = params(request).get("data")

    tDataOption match {
      case None => {
        response.setStatus(400)
        "required param 'data' is not set."
      }
      case Some(tData) => s"hello ${tData}"
    }
  }

  get("/"){
    request.getRequestURL() + """   hoge
    fuga hoho
    aaa
    """
  }
}