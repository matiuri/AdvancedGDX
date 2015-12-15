package mati.advancedgdx.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import mati.advancedgdx.AdvancedGame
import java.util.*
import kotlin.reflect.KClass

public class AssetLoader(private val game: AdvancedGame) : Disposable {
	private val map: MutableMap<String, String> = HashMap()
	private val manager: AssetManager = AssetManager()
	private val screen: Class<LoadingScreen> = LoadingScreen::class.java

	public fun <T> queue(key: String, path: String, clazz: Class<T>): AssetLoader {
		if (map.containsKey(key)) throw IllegalArgumentException("The key $key already exists")
		map.put(key, path)
		manager.load(path, clazz)
		return this
	}

	public fun <T : Any> queue(key: String, path: String, clazz: KClass<T>): AssetLoader {
		queue(key, path, clazz.java)
		return this
	}

	public fun load(after: () -> Unit = { game.setCurrentScreen(null) }) {
		game.scrManager.add("Loading", screen.constructors[0].newInstance(game, manager, after) as LoadingScreen)
		game.scrManager.change("Loading")
	}

	public operator fun <T> get(key: String, clazz: Class<T>): T {
		if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
		return manager.get(map[key], clazz)
	}

	public operator fun <T : Any> get(key: String, clazz: KClass<T>): T {
		return get(key, clazz.java)
	}

	public fun remove(key: String) {
		if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
		manager.unload(map[key])
	}

	public fun clear() {
		manager.clear()
		map.clear()
	}

	public override fun dispose() {
		manager.dispose()
		map.clear()
	}
}
