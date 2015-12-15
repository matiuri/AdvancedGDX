package mati.advancedgdx

import com.badlogic.gdx.Application.LOG_INFO
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import mati.advancedgdx.log.Logger
import mati.advancedgdx.screens.Screen
import mati.advancedgdx.screens.ScreenManager

open class AdvancedGame() : ApplicationAdapter() {
	public companion object Static {
		val log: Logger = Logger()
	}

	var screen: Screen? = null

	public val scrManager: ScreenManager = ScreenManager(this)

	override fun create() {
		Thread.setDefaultUncaughtExceptionHandler({ thread, throwable ->
			log.e("${thread.name}", "FATAL ERROR", throwable)
			System.exit(-1)
		})

		Gdx.app.logLevel = LOG_INFO
		log.init()
		log.l("${this.javaClass.simpleName}", "Starting Game")
	}

	override fun render() {
		screen?.render(Gdx.graphics.deltaTime)
	}

	override fun resize(width: Int, height: Int) {
		screen?.resize(width, height)
	}

	override fun pause() {
		screen?.pause()
	}

	override fun resume() {
		screen?.resume()
	}

	override fun dispose() {
		screen?.hide()
		screen?.dispose()
	}

	fun setCurrentScreen(screen: Screen?) {
		this.screen?.hide()
		this.screen = screen
		this.screen?.show()
		this.screen?.resize(Gdx.graphics.width, Gdx.graphics.height)
	}
}
