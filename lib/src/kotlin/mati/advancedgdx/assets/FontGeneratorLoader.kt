package mati.advancedgdx.assets

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import mati.advancedgdx.assets.FontGeneratorLoader.FontGeneratorLoaderParameter

/**
 * This class loads a [FreeTypeFontGenerator]. You shouldn't do anything with this.
 */
public class FontGeneratorLoader(resolver: FileHandleResolver)
: AsynchronousAssetLoader<FreeTypeFontGenerator, FontGeneratorLoaderParameter>(resolver) {
	override fun loadAsync(manager: AssetManager?, fileName: String?, file: FileHandle?,
	                       parameter: FontGeneratorLoaderParameter?) {
	}

	override fun loadSync(manager: AssetManager?, fileName: String?, file: FileHandle?,
	                      parameter: FontGeneratorLoaderParameter?): FreeTypeFontGenerator? {
		return FreeTypeFontGenerator(Gdx.files.internal(fileName))
	}

	override fun getDependencies(fileName: String?, file: FileHandle?, parameter: FontGeneratorLoaderParameter?)
			: com.badlogic.gdx.utils.Array<AssetDescriptor<out Any>>? {
		val deps: com.badlogic.gdx.utils.Array<AssetDescriptor<out Any>> = com.badlogic.gdx.utils.Array()
		deps.add(AssetDescriptor<FileHandle>(fileName, FileHandle::class.java))
		return deps
	}

	public class FontGeneratorLoaderParameter : AssetLoaderParameters<FreeTypeFontGenerator>()
}
