package mati.advancedgdx.io

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Base64Coder.decodeString
import com.badlogic.gdx.utils.Base64Coder.encodeString
import kotlin.reflect.KClass

/**
 * This class allows you to save and load serialized objects. You shouldn't instance is, because
 * [mati.advancedgdx.AdvancedGame] has an instance ready for you.
 */
public class IOManager(private val dir: String) {
	private val serializer: Serializer = Serializer()

	/**
	 * This method serializes and saves an object instance, using a [Serializable] as auxiliary class.
	 *
	 * @param name The name of the file
	 * @param obj The object you want to serialize
	 * @param serializable The [Serializable] of the object that you want to serialize
	 */
	fun <T> save(name: String, obj: T, serializable: Serializable<T>) {
		val str: String = serializer.serialize(obj, serializable, serializable.javaClass)
		Gdx.files.local("$dir/$name.dat").writeString(encodeString(str), false)
	}

	/**
	 * This method deserializes an object instance, using a [Serializable] as auxiliary class.
	 *
	 * @param name The name of the file
	 * @param clazz The [Class] of the object's [Serializable]
	 *
	 * @return The object
	 */
	fun <T> load(name: String, clazz: Class<out Serializable<T>>): T {
		return serializer.deserialize(decodeString(Gdx.files.local("$dir/$name.dat").readString()), clazz)
	}

	/**
	 * This method deserializes an object instance, using a [Serializable] as auxiliary class.
	 *
	 * @param name The name of the file
	 * @param clazz The [KClass] of the object's [Serializable]
	 *
	 * @return The object
	 */
	fun <T> load(name: String, clazz: KClass<out Serializable<T>>): T = load(name, clazz.java)
}
