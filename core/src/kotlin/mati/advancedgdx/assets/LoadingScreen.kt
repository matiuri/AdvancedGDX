package mati.advancedgdx.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.math.MathUtils
import mati.advancedgdx.AdvancedGame
import mati.advancedgdx.AdvancedGame.Static.log
import mati.advancedgdx.screens.Screen

public class LoadingScreen(game: AdvancedGame, private val manager: AssetManager,
                           private val after: () -> Unit) : Screen(game) {
	public override fun show() {
		log.l("${this.javaClass.simpleName}", "Attempting to load ${manager.queuedAssets} assets")
	}

	public override fun render(delta: Float) {
		if (!manager.update())
			log.l("${this.javaClass.simpleName}", "Loading: ${MathUtils.round(manager.progress * 100)}")
		else {
			log.l("${this.javaClass.simpleName}", "Loaded all the assets - Calling \"after\" method")
			after()
			log.l("${this.javaClass.simpleName}", "Everything is lodaded now")
		}
	}

	public override fun hide() {
		game.scrManager.remove("Loading")
	}
}
