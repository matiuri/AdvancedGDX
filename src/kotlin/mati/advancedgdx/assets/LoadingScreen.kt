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
open class LoadingScreen(game: AdvancedGame, private val manager: AssetManager,
                         private val after: () -> Unit) : Screen<AdvancedGame>(game) {
    override fun show() {
        log.l("${this.javaClass.simpleName}", "Attempting to load ${manager.queuedAssets} assets")
    }

    override fun render(delta: Float) {
        if (!manager.update())
            log.l("${this.javaClass.simpleName}", "Loading: ${MathUtils.round(manager.progress * 100)}%")
        else {
            log.l("${this.javaClass.simpleName}", "Loaded all the assets - Calling \"after\" method")
            after()
            log.l("${this.javaClass.simpleName}", "Everything is loaded now")
        }
    }

    /**
     * This method is essential. If you override, you must call "super.hide()"
     */
    override fun hide() {
        game.scrManager.remove("Loading")
    }
}
