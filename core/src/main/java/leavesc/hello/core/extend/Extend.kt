package leavesc.hello.core.extend

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * 作者：CZY
 * 时间：2019/12/24 17:45
 * 描述：
 */
fun ImageView.load(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadCorner(url: String, corner: Int = 20) {
    Glide.with(context).load(url).into(this)
}

fun Context.openGps() {
    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    startActivity(intent)
}

val Context.screenWidth: Int
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight: Int
    get() = resources.displayMetrics.heightPixels

fun Context.dp2px(dpValue: Float): Int {
    return (dpValue * resources.displayMetrics.density + 0.5f).toInt()
}

fun Context.dp2px(dpValue: Int): Int {
    return dp2px(dpValue.toFloat())
}

fun Context.px2dp(pxValue: Float): Int {
    return (pxValue / resources.displayMetrics.density + 0.5f).toInt()
}

fun Context.px2sp(pxValue: Float): Int {
    return (pxValue / resources.displayMetrics.scaledDensity + 0.5f).toInt()
}

fun Context.sp2px(spValue: Float): Int {
    return (spValue * resources.displayMetrics.scaledDensity + 0.5f).toInt()
}

@SuppressLint("MissingPermission")
fun Context.bluetoothOpened(): Boolean {
    return BluetoothAdapter.getDefaultAdapter()?.isEnabled ?: false
}

@SuppressLint("MissingPermission")
fun Context.openBluetooth() {
    BluetoothAdapter.getDefaultAdapter()?.apply {
        if (!isEnabled) {
            enable()
        }
    }
}