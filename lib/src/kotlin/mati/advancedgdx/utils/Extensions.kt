@file:JvmName("Extensions")

package mati.advancedgdx.utils

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
 * This function allows you to split a Texture into a 1D Array. It's really useful when you have some different frames
 * in a single image, such as a .png, because you can avoid using the [TextureRegion.split], which returns a 2D array.
 *
 * If you are using Kotlin, you can write «aTextureInstance».split(«int»).
 *
 * @param width The width of each column.
 *
 * @return An array containing the frames.
 */
public fun Texture.split(width: Int): Array<TextureRegion> {
	return Array(this.width / width) { i ->
		TextureRegion(this, i * width, 0, width, height)
	}
}

/**
 * This function is an experimental way of converting a 2D array into a 1D one. You'll have to cast the return, as it's
 * [Any]?
 *
 * If you are using Kotlin, you can write «a2DArrayInstance».to1D().
 *
 * @return An array of [Any]?
 */
public fun<T> Array<Array<T>>.to1D(): Array<Any?> {
	val size: Int = this.size * get(0).size
	var index: Int = 0
	val temp: Array<Any?> = arrayOfNulls(size)
	for (x in indices)
		for (y in get(x).indices)
			temp[index++] = get(x)[y]
	return temp
}