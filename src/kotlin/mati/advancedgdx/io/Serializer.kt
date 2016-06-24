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

package mati.advancedgdx.io

import com.badlogic.gdx.utils.Json
import com.badlogic.gdx.utils.JsonWriter.OutputType

/**
 * You shouldn't use this class. Use [IOManager] instead.
 */
class Serializer() {
    private val json = Json()

    init {
        json.setOutputType(OutputType.json)
    }

    fun <T> serialize(obj: T, serializable: Serializable<T>, clazz: Class<out Serializable<T>>): String {
        serializable.preserialize(obj)
        return json.toJson(serializable, clazz)
    }

    fun <T> print(obj: T, serializable: Serializable<T>): String {
        serializable.preserialize(obj)
        return json.prettyPrint(obj)
    }

    fun <T> deserialize(str: String, clazz: Class<out Serializable<T>>): T {
        val ser: Serializable<T> = json.fromJson(clazz, str)
        return ser.recover()
    }
}
