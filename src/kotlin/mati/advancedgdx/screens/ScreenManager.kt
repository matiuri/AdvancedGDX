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

import com.badlogic.gdx.utils.Disposable
import mati.advancedgdx.AdvancedGame
import java.util.*

/**
 * This class manages the [Screens][Screen] of your game, allowing them to have "aliases". You shouldn't instance it,
 * because [AdvancedGame] has an instance ready for you.
 */
public class ScreenManager(private val game: AdvancedGame) : Disposable {
    private val map: MutableMap<String, Screen> = HashMap()

    /**
     * This method adds a [Screen], with the given "alias".
     *
     * @param key The "alias" of the screen.
     * @param screen An instance of the screen.
     *
     * @return this, so you can chain the calls.
     */
    public fun add(key: String, screen: Screen): ScreenManager {
        if (map.containsKey(key)) throw IllegalArgumentException("The key $key already exists")
        map.put(key, screen)
        return this
    }

    /**
     * This method loads a single screen.
     *
     * @param key The "alias" of the screen.
     */
    public fun load(key: String): ScreenManager {
        if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
        map[key]?.load()
        return this
    }

    /**
     * This method loads all the screens that have been added to this [ScreenManager]
     */
    public fun loadAll() {
        for ((k, v) in map)
            v.load()
    }

    /**
     * This method returns a screen. If it declares a special field or method, you'll have to cast it.
     *
     * @param key The "alias" of the screen.
     *
     * @return The screen, as [Screen]
     */
    public operator fun get(key: String): Screen {
        if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
        return map[key]!!
    }

    /**
     * This method changes the current screen of the [AdvancedGame].
     *
     * @param key The "alias" of the screen.
     */
    public fun change(key: String) {
        if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
        game.setCurrentScreen(map[key])
    }

    /**
     * This method dispose a screen.
     *
     * @param key The "alias" of the screen.
     */
    public fun remove(key: String): ScreenManager {
        if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
        map[key]?.dispose()
        map.remove(key)
        return this
    }

    /**
     * This method dispose all the screens which have been added in this [ScreenManager]. You **CAN** use this
     * [ScreenManager] after calling dispose.
     */
    public override fun dispose() {
        for ((k, v) in map)
            v.dispose()
        map.clear()
    }
}
