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

/**
 * This interface allows you to serialize objects.
 *
 * The class you want to serialize must **NOT** implement this. You have to create an auxiliary class implementing
 * this interface, which should be an inner class, so that you can get and set the private fields.
 *
 * This library uses this way of serialization because in libGDX some classes have reciprocal references. For example:
 * the class Stage has a reference to a Group, which has a reference to all the Actors in the Stage. Also, each Actor
 * has a reference to the Stage. So, if you try to serialize that, you'll get a [StackOverflowError], because the game
 * will never end the loop. If the code were yours, you'd add the "transient" keyword to those variables. However, the
 * classes are already compiled, so you can't do it. That's why you have to create an auxiliary class.
 *
 * If you use Kotlin, don't serialize arrays. If you must, you'll have to create a Java class and use Reflection to
 * get and set the private fields. There is a bug with Json, because it throws a [ClassCastException], probably
 * because an array is declared as Array<T> in Kotlin, and libGDX has its own array, also declared as Array<T>
 */
public interface Serializable<T> {
	/**
	 * This method is called before serializing an object.
	 *
	 * @param t The object to serialize
	 */
	fun preserialize(t: T)

	/**
	 * This method is called after deserializing this instance. You'll have to recreate the object manually.
	 *
	 * @return The object which you have serialized
	 */
	fun recover(): T
}
