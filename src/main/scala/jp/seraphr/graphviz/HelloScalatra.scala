package jp.seraphr.graphviz

import org.scalatra.ScalatraFilter
import javax.servlet.FilterConfig

class HelloScalatra extends ScalatraFilter {
  get("/"){
    "hello scalatra!!"
  }
}