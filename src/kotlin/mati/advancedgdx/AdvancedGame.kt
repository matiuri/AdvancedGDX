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

package mati.advancedgdx

import com.badlogic.gdx.Application.LOG_INFO
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import mati.advancedgdx.assets.AssetLoader
import mati.advancedgdx.io.IOManager
import mati.advancedgdx.log.Logger
import mati.advancedgdx.screens.Screen
import mati.advancedgdx.screens.ScreenManager
import kotlin.properties.Delegates

/**
 * This class should be extended by the main class of your game.
 *
 * If you want to override a method of this class, don't forget to call the original one via "super.«method»()".
 *
 * The methods that you should override are:
 * * [create]: It's called when your game is launched, so all the initial code (such as loading some assets, creating
 * some screens, loading the managers, etc) is going to be there. Also, you have to call [init] in this method.
 * * [render]: It only renders the current [Screen], so if you want to use a general glClear, you'll have to override
 * it, calling glClear and then "super.render()". You shouldn't render anything here, use a [Screen] to to that.
 * * [dispose]: It disposes the default managers, so if you have created your own ones, you'll have to override it,
 * disposing your managers and calling "super.dispose()"
 *
 * You shouldn't override the other methods, because you can manage everything using [Screens][Screen].
 */
open class AdvancedGame() : ApplicationAdapter() {
    companion object Static {
        val log: Logger = Logger("log")
    }

    private var screen: Screen<out AdvancedGame>? = null

    var scrManager: ScreenManager by Delegates.notNull<ScreenManager>()
    var astManager: AssetLoader by Delegates.notNull<AssetLoader>()
    var ioManager: IOManager by Delegates.notNull<IOManager>()

    /**
     * This method loads all the managers of [AdvancedGame]. You have to call it on your own so that they can receive
     * your class that extends [AdvancedGame].
     *
     * @param game Your game's main class
     */
    fun init(game: AdvancedGame) {
        scrManager = ScreenManager(game)
        astManager = AssetLoader(game)
        ioManager = IOManager("saves")
    }

    /**
     * This method is called each time you launch your game.
     *
     * Don't forget to call "super.create()" if you want this library to work properly.
     * Also, you must call [init] to load the managers: [scrManager], [astManager] and [ioManager].
     */
    override fun create() {
        Thread.setDefaultUncaughtExceptionHandler({ thread, throwable ->
            log.e("${thread.name}", "FATAL ERROR", throwable)
            System.exit(-1)
        })

        Gdx.app.logLevel = LOG_INFO
        log.init()
        log.l("${this.javaClass.simpleName}", "Starting Game")
    }

    /**
     * This method is called called repeatedly while your game is running, after calling [create].
     *
     * If you override it and forget the "super.render()", the current screen won't be rendered.
     */
    override fun render() {
        screen?.render(Gdx.graphics.deltaTime)
    }

    /**
     * This method is called when you resize the game's window.
     *
     * You shouldn't override it.
     */
    override fun resize(width: Int, height: Int) {
        screen?.resize(width, height)
    }

    /**
     * This method is called when your game is minimized. Sometimes, your game can be killed in this state and
     * [dispose] won't be called, so you should save here.
     *
     * You shouldn't override it. Use the [Screen.hide] method to save.
     */
    override fun pause() {
        screen?.pause()
    }

    /**
     * This method is called when your game is restored.
     *
     * You shouldn't override it. Use the [Screen.hide] method instead.
     */
    override fun resume() {
        screen?.resume()
    }

    /**
     * This method is called when you close your game.
     *
     * If you have to dispose something, override it and don't forget "super.dispose()"
     */
    override fun dispose() {
        log.l("${this.javaClass.simpleName}", "Exiting Game")
        scrManager.dispose()
        astManager.dispose()
    }

    /**
     * You shouldn't call this method, unless you want to change to a null [Screen], as [ScreenManager] doesn't allow
     * null values.
     */
    fun setCurrentScreen(screen: Screen<out AdvancedGame>?) {
        this.screen?.hide()
        this.screen = screen
        this.screen?.show()
        this.screen?.resize(Gdx.graphics.width, Gdx.graphics.height)
    }
}
