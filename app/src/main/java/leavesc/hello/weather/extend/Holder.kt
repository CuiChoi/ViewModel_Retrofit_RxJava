package leavesc.hello.weather.extend

import android.app.Application
import leavesc.hello.weather.MainApplication

/**
 * 作者：CZY
 * 时间：2019/12/25 18:21
 * 描述：
 */
object ContextHolder {

    val context: Application by lazy {
        MainApplication.context
    }

}