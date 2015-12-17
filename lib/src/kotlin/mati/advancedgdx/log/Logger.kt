package mati.advancedgdx.log

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*
import kotlin.properties.Delegates

/**
 * This class was created to allow the [Application][com.badlogic.gdx.Gdx.Application]'s log to export its Strings in
 * a file.
 *
 * Also, it adds a header, which includes de type of log, the time and, optionally, a name.
 *
 * You shouldn't instance it, because [AdvancedGame][mati.advancedgdx.AdvancedGame] has an instance ready for you.
 */
public class Logger(private val dir: String) {
	private var file: FileHandle by Delegates.notNull<FileHandle>()

	public fun init() {
		file = Gdx.files.local("$dir/log.l")
		if (file.exists()) file.delete()
	}

	/**
	 * Debug level. Prints a header, a text and, optionally, a Throwable.
	 */
	public fun d(name: String = "", text: String, t: Throwable? = null) {
		val header = createHeader(name, "DEBUG")
		if (t != null) {
			Gdx.app.debug(header, text, t)
			file.writeString("$header: $text\n", true)
			exportStackTrace(t)
		} else {
			Gdx.app.debug(header, text)
			file.writeString("$header: $text\n", true)
		}
	}

	/**
	 * Log (info) level. Prints a header, a text and, optionally, a Throwable.
	 */
	public fun l(name: String = "", text: String, t: Throwable? = null) {
		val header = createHeader(name, "INFO")
		if (t != null) {
			Gdx.app.log(header, text, t)
			file.writeString("$header: $text\n", true)
			exportStackTrace(t)
		} else {
			Gdx.app.log(header, text)
			file.writeString("$header: $text\n", true)
		}
	}

	/**
	 * Error level. Prints a header, a text and, optionally, a Throwable.
	 */
	public fun e(name: String = "", text: String, t: Throwable? = null) {
		val header = createHeader(name, "ERROR")
		if (t != null) {
			Gdx.app.error(header, text, t)
			file.writeString("$header: $text\n", true)
			exportStackTrace(t)
		} else {
			Gdx.app.error(header, text)
			file.writeString("$header: $text\n", true)
		}
	}

	private fun createHeader(name: String, level: String): String {
		return "<$level> [${Calendar.getInstance().time.toString()}] $name"
	}

	private fun exportStackTrace(t: Throwable) {
		val sw: StringWriter = StringWriter()
		t.printStackTrace(PrintWriter(sw))
		file.writeString("${sw.toString()}\n", true)
	}
}