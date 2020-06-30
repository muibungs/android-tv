package com.example.myapplication

import android.content.ContentResolver
import android.content.Context
import android.graphics.Point
import android.net.Uri
import android.view.WindowManager
import okhttp3.internal.and
import java.io.IOException
import java.io.InputStream
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Utils {
    fun convertDpToPixel(ctx: Context, dp: Int): Int {
        val density = ctx.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    /**
     * Will read the content from a given [InputStream] and return it as a [String].
     *
     * @param inputStream The [InputStream] which should be read.
     * @return Returns `null` if the the [InputStream] could not be read. Else
     * returns the content of the [InputStream] as [String].
     */
    fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }
    }

    fun getResourceUri(context: Context, resID: Int): Uri {
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    context.resources.getResourcePackageName(resID) + '/' +
                    context.resources.getResourceTypeName(resID) + '/' +
                    context.resources.getResourceEntryName(resID)
        )
    }

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    fun getTimeAgo(time: Long): String? {
        var timer = time
        if (time < 1000000000000L) { // if timestamp given in seconds, convert to millis
            timer *= 1000
        }
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return null
        }
        // TODO: localize
        val diff = now - timer
        return when {
            diff < MINUTE_MILLIS -> {
                "ใหม่ล่าสุด"
            }
            diff < 2 * MINUTE_MILLIS -> {
                "นาทีที่ผ่านมา"
            }
            diff < 50 * MINUTE_MILLIS -> {
                diff.div(MINUTE_MILLIS).toString() + " นาทีที่ผ่านมา"
            }
            diff < 90 * MINUTE_MILLIS -> {
                "ชั่วโมงที่ผ่านมา"
            }
            diff < 24 * HOUR_MILLIS -> {
                diff.div(HOUR_MILLIS).toString() + " ชั่วโมงที่ผ่านมา"
            }
            diff < 48 * HOUR_MILLIS -> {
                "ชั่วโมงที่ผ่านมา"
            }
            else -> {
                diff.div(DAY_MILLIS).toString() + " วันโมงที่ผ่านมา"
            }
        }
    }

    fun getDisplaySize(context: Context): Point? {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val width = display.width
        val height = display.height
        return Point(width, height)
    }

    fun getRealImageUrl(
        server_url: String,
        path: String,
        width: String,
        height: String,
        crop: String
    ): String {
        val _position =
            if (crop === "") "" else if (crop === "yes") "&position=tc" else ""
        var _url =
            if (server_url === "") "https://api.ch7.com/images/thumb/index.php" else server_url
        var _crop = ""
        if (crop === "movie") {
            _crop = "&crop=yes"
        } else {
            _crop = if (crop === "") "&crop=no" else "&crop=$crop"
        }
        val _full =
            "&full=yes$_crop$_position&v=3&role=thumb&platform=android"
        val _width = if (width === "") "" else "&width=$width"
        val _height = if (height === "") "" else "&height=$height"
        var _path = path
        val pathmd5: String = md5(_path + "ch7").toString()
        val code_pathmd5 = StringBuffer(pathmd5).reverse().toString()
        _url = "$_url?$_width$_height$_full"
        _path = URLEncoder.encode(_path)
        return "$_url&path=$_path&code=$code_pathmd5"
    }

    fun md5(`in`: String): String? {
        val digest: MessageDigest
        try {
            digest = MessageDigest.getInstance("MD5")
            digest.reset()
            digest.update(`in`.toByteArray())
            val a = digest.digest()
            val len = a.size
            val sb = StringBuilder(len shl 1)
            for (i in 0 until len) {
                sb.append(Character.forDigit(a[i] and 0xf0 shr 4, 16))
                sb.append(Character.forDigit(a[i] and 0x0f, 16))
            }
            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }

}