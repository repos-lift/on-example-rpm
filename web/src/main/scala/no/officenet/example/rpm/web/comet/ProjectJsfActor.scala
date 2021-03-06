package no.officenet.example.rpm.web.comet

import dto.ProjectCometDto
import net.liftweb._
import http.js.JE._
import http.js.{JsExp, JsCmd}
import http.S
import server.{ProjectCometCreatedMessage, ProjectCometMasterServer}
import util.ClearNodes
import util.Helpers._
import net.liftweb.http.js.JsCmds._

import no.officenet.example.rpm.support.infrastructure.logging.Loggable
import org.springframework.beans.factory.annotation.Configurable
import no.officenet.example.rpm.projectmgmt.domain.model.entities.Project
import net.liftweb.common.{Box, Empty, Full}
import org.springframework.context.i18n.LocaleContextHolder
import xml.NodeSeq

object fisk {

	case class Fix(content: NodeSeq) extends JsExp {
		def toJsCmd = fixHtmlFunc ("inline", content)(str => str)
	}
}

@Configurable
class ProjectJsfActor extends RpmCometActor with Loggable {

	lazy val projectId = asLong(nameParts(1)).get
	protected def registerWith = ProjectCometMasterServer.registerWithProjectCometServer(projectId)
	var cachedProject: Box[ProjectCometDto] = Empty

	override def lowPriority = {
		case ProjectCometCreatedMessage(project) =>
			cachedProject = Full(project)
			reRender()
		case project: ProjectCometDto => {
			cachedProject = Full(project)
			partialUpdate(
				Call("parent.sayHi", "bolle", fisk.Fix(potetJammar.apply(defaultHtml)))
			)
		}
	}

	def potetJammar = {
		val bolle = cachedProject.map(project => {
			ProjectDetailCometRenderer.getNodeSeqNodeSeq(project)
		}).openOr(ClearNodes)
		bolle
	}

	def render = {
		trace("Using locale: " + S.locale + " (LocaleContextHolder=" + LocaleContextHolder.getLocale + " for actor: " + name)
		cachedProject.map(project => {
			Call("parent.sayHi", "bolle", fisk.Fix(potetJammar.apply(defaultHtml)))
				.cmd
		}).openOr(Noop):JsCmd
	}

}