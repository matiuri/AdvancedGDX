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

import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.utils.Disposable
import mati.advancedgdx.AdvancedGame
import java.util.*
import kotlin.reflect.KClass

/**
 * This class manages the assets of you game, allowing you to replace the long file name with an "alias" (actually a
 * key). You shouldn't instance it, because it's already instanced in [AdvancedGame], as [AdvancedGame.astManager].
 */
public class AssetLoader(private val game: AdvancedGame) : Disposable {
	private val map: MutableMap<String, String> = HashMap()
	private val manager: AssetManager = AssetManager()
	private val screen: Class<LoadingScreen> = LoadingScreen::class.java

	init {
		manager.setLoader(FreeTypeFontGenerator::class.java, FontGeneratorLoader(InternalFileHandleResolver()))
		manager.setLoader(BitmapFont::class.java, FontLoader(InternalFileHandleResolver()))
	}

	/**
	 * This method adds an asset in the [manager]'s queue. An asset must be queued so that it can be loaded.
	 *
	 * @param key The "alias" of the asset
	 * @param path The path of the asset
	 * @param clazz The asset type's [Class]
	 *
	 * @return this, so you can chain calls
	 */
	public fun <T> queue(key: String, path: String, clazz: Class<T>, par: AssetLoaderParameters<T>? = null)
			: AssetLoader {
		if (map.containsKey(key)) throw IllegalArgumentException("The key $key already exists")
		map.put(key, path)
		if (par != null)
			manager.load(path, clazz, par)
		else
			manager.load(path, clazz)
		return this
	}

	/**
	 * This method adds an asset in the [manager]'s queue. An asset must be queued so that it can be loaded.
	 *
	 * @param key The "alias" of the asset
	 * @param path The path of the asset
	 * @param clazz The asset type's [KClass]
	 */
	public fun <T : Any> queue(key: String, path: String, clazz: KClass<T>, par: AssetLoaderParameters<T>? = null)
			: AssetLoader {
		queue(key, path, clazz.java, par)
		return this
	}

	/**
	 * This method loads all the queued assets, via [LoadingScreen]. You can create your own screen that extends
	 * [LoadingScreen], and set the variable [screen] as the [Class] of your own one.
	 *
	 * @param after A lambda which contains the code that will be executed after loading the assets. You should
	 * load the screens using the ScreenManager here.
	 */
	public fun load(after: () -> Unit = { game.setCurrentScreen(null) }) {
		game.scrManager.add("Loading", screen.constructors[0].newInstance(game, manager, after) as LoadingScreen)
		game.scrManager.change("Loading")
	}

	/**
	 * This methods returns the asset that you want.
	 *
	 * In Kotlin, you can call it as "astManager[]", with the parameters between the [], because it's an operator
	 * method.
	 *
	 * @param key The "alias" of the asset
	 * @param clazz The asset type's [Class]
	 *
	 * @return An asset
	 */
	public operator fun <T> get(key: String, clazz: Class<T>): T {
		if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
		return manager.get(map[key], clazz)
	}

	/**
	 * This methods returns the asset that you want.
	 *
	 * In Kotlin, you can call it as "astManager[]", with the parameters between the [], because it's an operator
	 * method.
	 *
	 * @param key The "alias" of the asset
	 * @param clazz The asset type's [KClass]
	 *
	 * @return An asset
	 */
	public operator fun <T : Any> get(key: String, clazz: KClass<T>): T {
		return get(key, clazz.java)
	}

	/**
	 * Return the path of an asset.
	 *
	 * @param key The "alias" of the asset.
	 *
	 * @return The path, as string.
	 */
	public operator fun get(key: String): String = map[key]!!

	/**
	 * This method disposes an asset and deletes its "alias".
	 *
	 * @param key The "alias" of the asset
	 *
	 * @return this, so you can chain calls
	 */
	public fun remove(key: String): AssetLoader {
		if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
		manager.unload(map[key])
		return this
	}

	/**
	 * This method disposes all the assets and deletes its "aliases"
	 */
	public fun clear() {
		manager.clear()
		map.clear()
	}

	/**
	 * This method disposes the [manager], so you mustn't call any method of an instance of this class if you've
	 * already called [dispose].
	 */
	public override fun dispose() {
		manager.dispose()
		map.clear()
	}
}
