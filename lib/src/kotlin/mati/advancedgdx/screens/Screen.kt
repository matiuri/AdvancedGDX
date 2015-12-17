/**
 *  Copyright 2015 Matías Steinberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mati.advancedgdx.screens

import mati.advancedgdx.AdvancedGame

/**
 * This class should be extended by all your screens, as the [ScreenManager] doesn't allow you to use
 * [the default in libGDX][com.badlogic.gdx.Screen], because this class adds a [load] method, to be called
 * before changing to the screen, so that you can load some heavy things only once, and dispose them in [dispose]
 * instead of [hide].
 *
 * You must **NOT** call the methods of the [Screen], use the [ScreenManager] ones instead.
 */
public abstract class Screen(protected val game: AdvancedGame) : com.badlogic.gdx.Screen {
	/**
	 * This method will be executed when you call [ScreenManager.load] or [ScreenManager.loadAll]. It allows you to
	 * load some assets or heavy things, so you can keep them in memory until you call [dispose].
	 */
	public open fun load() {
	}

	/**
	 * This method is called when you screen has just been set. You can create the
	 * [Stages][com.badlogic.gdx.scenes.scene2d.Stage] here, for example.
	 */
	override fun show() {
	}

	/**
	 * This method is called by [AdvancedGame.render] if the screen is set. You can update the physics and draw the
	 * sprites here.
	 *
	 * @param delta The time between the last frame and this frame.
	 */
	public override fun render(delta: Float) {
	}

	/**
	 * This method is called by [AdvancedGame.resize] if the screen is set. If you want to resize something, you can do
	 * it here. For example, if your GUI has to be as wide as the game's window, you can modify its width.
	 *
	 * @param width The new width.
	 * @param height The new height.
	 */
	public override fun resize(width: Int, height: Int) {
	}

	/**
	 * This method is called by [AdvancedGame.pause] if the screen is set. You can prevent the physics to be updated
	 * here.
	 */
	public override fun pause() {
	}

	/**
	 * This method is called by [AdvancedGame.resume] if the screen is set. You can allow the physics to be updated
	 * again here.
	 */
	public override fun resume() {
	}

	/**
	 * This method is called when you change to another [Screen]. You can dispose some minor things here.
	 */
	public override fun hide() {
	}

	/**
	 * This method will be executed when you call ScreenManager.remove or [ScreenManager.dispose]. You can dispose the
	 * things which you have loaded in [load] here.
	 */
	public override fun dispose() {
	}
}
