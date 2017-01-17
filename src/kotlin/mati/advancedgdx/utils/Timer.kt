package mati.advancedgdx.utils

class Timer {
    var text: String = "Time: 00:00:00:00"
    var time: Float = 0f

    private var counting: Boolean = false
    private var colon: Boolean = true
    private var colonTimer: Float = 0f
    private val colonTime: Float = .25f

    fun start(reset: Boolean = false) {
        if (reset) reset()
        counting = true
        colon = true
        colonTimer = 0f
    }

    fun reset() {
        time = 0f
        text = "Time: 00:00:00:00"
    }

    fun stop() {
        counting = false
        colon = true
        colonTimer = 0f
    }

    fun update(delta: Float) {
        if (counting) {
            time += delta
            colonTimer += delta
            if (colonTimer >= colonTime) {
                colon = !colon
                colonTimer = 0f
            }
        }

        val formattedTime: Array<Int> = formatTime(time)

        val h: Int = formattedTime[0]
        val m: Int = formattedTime[1]
        val s: Int = formattedTime[2]
        val ms: Int = formattedTime[3]

        val colonS: String = if (colon) ":" else "-"
        text = "Time: ${add0(h)}$h$colonS${add0(m)}$m$colonS${add0(s)}$s$colonS${add0(ms)}$ms"
    }

    private fun formatTime(time: Float): Array<Int> {
        val hf: Float = (time / 3600f)
        val h: Int = hf.toInt()
        val mf: Float = (hf - h) * 60f
        val m: Int = mf.toInt()
        val sf: Float = (mf - m) * 60f
        val s: Int = sf.toInt()
        val msf: Float = (sf - s) * 60f
        val ms: Int = 100 * msf.toInt() / 60
        return arrayOf(h, m, s, ms)
    }

    private fun add0(value: Int): String = if (value < 10) "0" else ""
}
