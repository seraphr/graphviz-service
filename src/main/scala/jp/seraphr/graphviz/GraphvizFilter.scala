package jp.seraphr.graphviz

import org.scalatra.ScalatraFilter
import org.scalatra.Http11
import java.io.OutputStream
import java.io.ByteArrayInputStream
import java.nio.charset.Charset
import java.io.OutputStreamWriter
import jp.seraphr.common.LoanPatterns
import java.io.Writer
import javax.servlet.http.HttpServletResponse

trait GraphvizFilterConf {
  val dotDataParam: String = "data"
  val imageTypeParam: String = "type"
  val layoutParam: String = "layout"
  val postPath: String = "/graphviz"
  val loanPatterns: LoanPatterns
}

trait GraphvizFilter extends ScalatraFilter { self: GraphvizFilterConf with GraphvizConf =>
  val CHARSET = Charset.forName("UTF-8")

  import loanPatterns._

  post(postPath) {
    val tParams = params(request)
    val tDataOption = tParams.get(dotDataParam)
    val tImageType = tParams.get(imageTypeParam).getOrElse(imageType)
    val tLayout = tParams.get(layoutParam).getOrElse(layout)
    val tGraphviz = createGraphviz(tImageType, tLayout, dotPath, command)

    tDataOption match {
      case None => {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
        s"required param '${dotDataParam}' is not set."
      }
      case Some(d) => genGraph(tGraphviz, d, response.getOutputStream())
    }
  }

  get("/"){
    s"""POST ${request.getRequestURI()}${postPath.tail}
    @param ${dotDataParam} "dot language String. required"
    @param ${imageTypeParam} "output image format. default is png"
    @param ${layoutParam} "layout algorithm to use. default is dot"
    """
  }

  def genGraph(aGraphviz: Graphviz, aData: String, aOutput: OutputStream): Unit = {

    val tResult = aGraphviz.generateGraph(new ByteArrayInputStream(aData.getBytes(CHARSET)), aOutput)

    tResult match {
      case Some(tList) => {
        var tStream: Writer = null
        try {
          response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
          tStream = new OutputStreamWriter(aOutput, CHARSET)
          tStream.write(tList.mkString("\n"))
        } finally {
          closeQuietly(tStream)
        }
      }
      case _ =>
    }
  }

  def createGraphviz(aImageType: String, aLayout: String, aDotPath: String, aCommand: String) = new Graphviz with GraphvizConf {
    override lazy val imageType = aImageType
    override lazy val layout = aLayout
    override lazy val dotPath = aDotPath
    override lazy val command = aCommand
    override val loanPatterns = self.loanPatterns
  }
}
