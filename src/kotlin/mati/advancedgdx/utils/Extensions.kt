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

@file:JvmName("Extensions")

package mati.advancedgdx.utils

import com.badlogic.gdx.Application.ApplicationType.Android
import com.badlogic.gdx.Application.ApplicationType.Desktop
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent

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
fun Texture.split(width: Int): Array<TextureRegion> {
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
fun<T> Array<Array<T>>.to1D(): Array<Any?> {
    val size: Int = this.size * get(0).size
    var index: Int = 0
    val temp: Array<Any?> = arrayOfNulls(size)
    for (x in indices)
        for (y in get(x).indices)
            temp[index++] = get(x)[y]
    return temp
}

fun Actor.addListener1(fun_: (e: ChangeEvent?, a: Actor?) -> Unit) {
    addCaptureListener(object : ChangeListener() {
        override fun changed(event: ChangeEvent?, actor: Actor?) {
            fun_(event, actor)
            event?.cancel()
        }
    })
}

fun Actor.addListener2(fun_: (e: ChangeEvent?, a: Actor?) -> Unit) {
    addCaptureListener(object : ChangeListener() {
        override fun changed(event: ChangeEvent?, actor: Actor?) {
            fun_(event, actor)
        }
    })
}

fun isDesktop(): Boolean = Gdx.app.type == Desktop

fun isAndroid(): Boolean = Gdx.app.type == Android
