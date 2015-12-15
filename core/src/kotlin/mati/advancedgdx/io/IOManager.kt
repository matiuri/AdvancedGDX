package mati.advancedgdx.io

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Base64Coder.decodeString
import com.badlogic.gdx.utils.Base64Coder.encodeString
import kotlin.reflect.KClass


public class IOManager(private val dir: String) {
	private val serializer: Serializer = Serializer()

	fun <T> save(name: String, obj: T, serializable: Serializable<T>) {
		val str: String = serializer.serialize(obj, serializable, serializable.javaClass)
		Gdx.files.local("$dir/$name.dat").writeString(encodeString(str), false)
	}

	fun <T> load(name: String, clazz: Class<out Serializable<T>>): T {
		return serializer.deserialize(decodeString(Gdx.files.local("$dir/$name.dat").readString()), clazz)
	}

	fun <T> load(name: String, clazz: KClass<out Serializable<T>>): T = load(name, clazz.java)
}
