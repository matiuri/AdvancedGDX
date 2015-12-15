package mati.advancedgdx.screens

import com.badlogic.gdx.utils.Disposable
import mati.advancedgdx.AdvancedGame
import java.util.*

class ScreenManager(private val game: AdvancedGame) : Disposable {
	private val map: MutableMap<String, Screen> = HashMap()

	public fun add(key: String, screen: Screen): ScreenManager {
		if (map.containsKey(key)) throw IllegalArgumentException("The key $key is already in the map")
		map.put(key, screen)
		return this
	}

	public fun load(key: String): ScreenManager {
		if (map.containsKey(key)) throw IllegalArgumentException("The key $key is already in the map")
		map[key]?.load()
		return this
	}

	public fun loadAll() {
		for ((k, v) in map)
			v.load()
	}

	public fun get(key: String): Screen {
		if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
		return map[key]!!
	}

	public fun change(key: String) {
		if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
		game.setCurrentScreen(map[key])
	}

	public fun remove(key: String): ScreenManager {
		if (!map.containsKey(key)) throw IllegalArgumentException("The key $key doesn't exist")
		map[key]?.dispose()
		map.remove(key)
		return this
	}

	public override fun dispose() {
		for ((k, v) in map)
			v.dispose()
		map.clear()
	}
}
