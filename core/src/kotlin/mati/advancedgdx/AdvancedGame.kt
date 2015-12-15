package mati.advancedgdx

import com.badlogic.gdx.Application.LOG_INFO
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import mati.advancedgdx.log.Logger

open class AdvancedGame() : ApplicationAdapter() {
	companion object Static {
		val log: Logger = Logger()
	}

	override fun create() {
		Gdx.app.logLevel = LOG_INFO
		log.init()
		log.l("${this.javaClass.simpleName}", "Starting Game")
	}
}
