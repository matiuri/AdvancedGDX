package mati.advancedgdx.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import mati.advancedgdx.assets.FontLoader.FontLoaderParameter

/**
 * This class creates a [BitmapFont]. You shouldn't do anything with this, but you must create a [FontLoaderParameter]
 * with the [FreeTypeFontGenerator] file name and a [FreeTypeFontParameter]. If you are in Kotlin, you can set the
 * fields of the [FreeTypeFontParameter] with a lambda.
 *
 * To load the [FreeTypeFontGenerator], you'll have to queue it:
 *
 * astManager.queue("«name of your generator»", "«path to your ttf»", FreeTypeFontGenerator::class)
 *
 * Then, you'll have to queue a [BitmapFont]:
 *
 * astManager.queue("«name of your font»", "«a single path»", BitmapFont::class,
 * FontLoaderParameter(astManager.get("«alias of the ttf»")) { it.color = ...
 * /*Set all the properties here.*/
 * })
 *
 * Note: the path won't be used, but must be unique so that the [AssetManager] can get exactly the font you want,
 * because it uses the path as identifier.
 *
 * Remember: In Java, *::class* must be written as *.class*. Also, you can't use lambdas, so you'll have to use the
 * default non-arguments constructor and set the properties manually.
 */
public class FontLoader(resolver: FileHandleResolver)
: AsynchronousAssetLoader<BitmapFont, FontLoaderParameter>(resolver) {
	override fun loadSync(manager: AssetManager?, fileName: String?, file: FileHandle?,
	                      parameter: FontLoaderParameter?): BitmapFont? {
		if (parameter == null) throw IllegalArgumentException("Parameter mustn't be null")
		val generator: FreeTypeFontGenerator = manager!![parameter.path, FreeTypeFontGenerator::class.java]
		return generator.generateFont(parameter.param)
	}

	override fun loadAsync(manager: AssetManager?, fileName: String?, file: FileHandle?,
	                       parameter: FontLoaderParameter?) {
		if (parameter == null) throw IllegalArgumentException("Parameter mustn't be null")
	}

	override fun getDependencies(fileName: String?, file: FileHandle?, parameter: FontLoaderParameter?)
			: com.badlogic.gdx.utils.Array<AssetDescriptor<out Any>>? {
		val deps: com.badlogic.gdx.utils.Array<AssetDescriptor<out Any>> = com.badlogic.gdx.utils.Array()
		deps.add(AssetDescriptor<FreeTypeFontGenerator>(parameter?.path, FreeTypeFontGenerator::class.java))
		return deps
	}

	/**
	 * You must pass an instance of this class to the [AssetLoader]
	 */
	public class FontLoaderParameter() : AssetLoaderParameters<BitmapFont>() {
		public var path: String = ""
		public var param: FreeTypeFontParameter = FreeTypeFontParameter()

		public constructor(path: String, fun_: (FreeTypeFontParameter) -> Unit) : this() {
			this.path = path
			fun_(param)
		}
	}
}
