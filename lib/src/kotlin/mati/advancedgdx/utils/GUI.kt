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

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.Drawable

/**
 * This function creates a [BitmapFont]. However, it's rather inefficient, so use it carefully.
 *
 * @param path The path of the .ttf file.
 * @param param An instance of [FreeTypeFontParameter].
 *
 * @return The [BitmapFont]
 */
public fun createFont(path: String, param: FreeTypeFontParameter): BitmapFont {
	val generator: FreeTypeFontGenerator = FreeTypeFontGenerator(Gdx.files.internal(path))
	val font: BitmapFont = generator.generateFont(param)
	generator.dispose()
	return font
}

/**
 * This function creates a [BitmapFont]. However, it's rather inefficient, so use it carefully.
 *
 * @param path The path of the .ttf file.
 * @param param A lambda which sets the properties of the a [FreeTypeFontParameter], received as parameter.
 *
 * @return The [BitmapFont]
 */
public fun createFont(path: String, param: (p: FreeTypeFontParameter) -> Unit = {}): BitmapFont {
	val generator: FreeTypeFontGenerator = FreeTypeFontGenerator(Gdx.files.internal(path))
	val parameter: FreeTypeFontParameter = FreeTypeFontParameter()
	param(parameter)
	val font: BitmapFont = generator.generateFont(parameter)
	generator.dispose()
	return font
}

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
