import org.scalatra.LifeCycle
import javax.servlet.ServletContext
import jp.seraphr.graphviz.HelloScalatra

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new HelloScalatra, "/*")
  }
}