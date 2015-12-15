package mati.advancedgdx.io

public interface Serializable<T> {
	fun preserialize(t: T)

	fun recover(): T
}
