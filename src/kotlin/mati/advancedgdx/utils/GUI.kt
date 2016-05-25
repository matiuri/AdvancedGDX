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

@file:JvmName("GUI")

package mati.advancedgdx.utils

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable

/**
 * This function creates a [TextButton].
 *
 * @param text The text of the button.
 * @param font The [BitmapFont] of the button.
 * @param up An optional [Drawable].
 * @param down An optional [Drawable].
 * @param checked An optional [Drawable].
 *
 * @return A [TextButton].
 */
public fun createButton(text: String, font: BitmapFont, up: Drawable? = null, down: Drawable? = null,
                        checked: Drawable? = null): TextButton {
    return TextButton(text, TextButtonStyle(up, down, checked, font))
}

/**
 * This function creates a [Label].
 *
 * @param text The text of the label.
 * @param font The font of the label.
 * @param color An optional [Color]
 *
 * @return A [Label].
 */
public fun createLabel(text: String, font: BitmapFont, color: Color = Color.WHITE): Label {
    return Label(text, LabelStyle(font, color))
}

/**
 * This function creates a [NinePatchDrawable] so that you can use as the background of the [TextButtons][TextButton].
 * This library has it because, although you can do it in a single line, it can be really long, as you have to create a
 * [NinePatch] with a [Texture] that you've loaded before.
 *
 *  @param tex [The Texture][Texture]
 *  @param top
 *  @param bottom
 *  @param left
 *  @param right
 *
 *  @return [A NinePatchDrawable][NinePatchDrawable]
 */
public fun createNPD(tex: Texture, top: Int, bottom: Int, left: Int, right: Int): NinePatchDrawable {
    return NinePatchDrawable(NinePatch(tex, left, right, top, bottom))
}

public fun createNPD(tex: Texture, px: Int): NinePatchDrawable {
    return createNPD(tex, px, px, px, px)
}
