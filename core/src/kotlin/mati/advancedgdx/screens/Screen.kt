package mati.advancedgdx.screens

import mati.advancedgdx.AdvancedGame

abstract class Screen(protected val game: AdvancedGame) : com.badlogic.gdx.Screen {
	public open fun load() {
	}

	public override fun show() {
	}

	public override fun render(delta: Float) {
	}

	public override fun resize(width: Int, height: Int) {
	}

	public override fun pause() {
	}

	public override fun resume() {
	}

	public override fun hide() {
	}

	public override fun dispose() {
	}
}
