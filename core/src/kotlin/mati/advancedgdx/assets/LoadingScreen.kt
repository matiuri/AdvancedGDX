package mati.advancedgdx.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.math.MathUtils
import mati.advancedgdx.AdvancedGame
import mati.advancedgdx.AdvancedGame.Static.log
import mati.advancedgdx.screens.Screen

/**
 * This is the generic loading screen. If you want to create a better one, feel free to extend it. Also, don't forget
 * to set the astManager.screen as your own one's [Class].
 *
 * You are allowed to override all the [Screen]'s methods. Just remember that the code in [hide] is essential, so if
 * you override it, you must call "super.hide()"
 */
public open class LoadingScreen(game: AdvancedGame, private val manager: AssetManager,
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
			log.l("${this.javaClass.simpleName}", "Everything is loaded now")
		}
	}

	/**
	 * This method is essential. If you override, you must call "super.hide()"
	 */
	public override fun hide() {
		game.scrManager.remove("Loading")
	}
}
