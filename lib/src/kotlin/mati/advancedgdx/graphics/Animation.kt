package mati.advancedgdx.graphics

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import kotlin.properties.Delegates

/**
 * This class allows you to create 2D animations. To create it, you have to pass an array of [textures][Texture], a 2D
 * array of [textures][Texture], an array of [texture regions][TextureRegion] or a 2D array of
 * [texture regions][TextureRegion], and the amount of time required to change the frame, as float, and whether it's
 * repeteable or not, as boolean.
 */
public class Animation private constructor(private val speed: Float, private val repeatable: Boolean) {
	private var frames: Array<TextureRegion> by Delegates.notNull<Array<TextureRegion>>()
	private var framesAmount: Int by Delegates.notNull<Int>()
	private var current: Int = 0
	private var time: Float = 0f

	/**
	 * @see Animation
	 */
	public constructor(frames: Array<Texture>, speed: Float, repeatable: Boolean = true) : this(speed, repeatable) {
		this.frames = Array(frames.size) { i ->
			TextureRegion(frames[i])
		}
		framesAmount = this.frames.size
	}

	/**
	 * @see Animation
	 */
	public constructor(frames: Array<Array<Texture>>, speed: Float, repeatable: Boolean = true)
	: this(speed, repeatable) {
		val temp: Array<Texture?> = arrayOfNulls(frames.size * frames[0].size)
		var index: Int = 0
		for (x in frames.indices) {
			for (y in frames[x].indices) {
				temp[index++] = frames[x][y]
			}
		}
		this.frames = Array(temp.size) { i ->
			TextureRegion(temp[i])
		}
		framesAmount = this.frames.size
	}

	/**
	 * @see Animation
	 */
	public constructor(frames: Array<TextureRegion>, speed: Float, repeatable: Boolean = true)
	: this(speed, repeatable) {
		this.frames = Array(frames.size) { i ->
			frames[i]
		}
		framesAmount = this.frames.size
	}

	/**
	 * @see Animation
	 */
	public constructor(frames: Array<Array<TextureRegion>>, speed: Float, repeatable: Boolean = true)
	: this(speed, repeatable) {
		val temp: Array<TextureRegion?> = arrayOfNulls(frames.size * frames[0].size)
		var index: Int = 0
		for (x in frames.indices) {
			for (y in frames[x].indices) {
				temp[index++] = frames[x][y]
			}
		}
		this.frames = Array(temp.size) { i ->
			temp[i]!!
		}
		framesAmount = this.frames.size
	}

	/**
	 * This function returns the current frame.
	 *
	 * @return The current frame as [TextureRegion]
	 */
	public fun get(): TextureRegion = frames[current]

	/**
	 * This function calls [update] and then returns the current frame.
	 *
	 * @param delta The time between the last frame and the current one.
	 *
	 * @return The current frame as [TextureRegion]
	 */
	public operator fun get(delta: Float): TextureRegion {
		update(delta)
		return get()
	}

	/**
	 * This function updates the timer and the current frame.
	 *
	 * @param delta The time between the last frame and the current one.
	 */
	public fun update(delta: Float) {
		if (!repeatable && current == framesAmount - 1) return
		time += delta
		if (time >= speed) {
			time -= speed
			current = (current + 1) % framesAmount
		}
	}
}
