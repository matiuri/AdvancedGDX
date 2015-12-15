package mati.advancedgdx.io

import com.badlogic.gdx.utils.Json
import com.badlogic.gdx.utils.JsonWriter.OutputType

class Serializer() {
	private val json = Json()

	init {
		json.setOutputType(OutputType.json)
	}

	fun <T> serialize(obj: T, serializable: Serializable<T>, clazz: Class<out Serializable<T>>): String {
		serializable.preserialize(obj)
		return json.toJson(serializable, clazz)
	}

	fun <T> deserialize(str: String, clazz: Class<out Serializable<T>>): T {
		val ser: Serializable<T> = json.fromJson(clazz, str)
		return ser.recover()
	}
}
