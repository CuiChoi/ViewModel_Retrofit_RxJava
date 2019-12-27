package leavesc.hello.weather

import android.app.Application
import leavesc.hello.core.holder.ContextHolder

/**
 * 作者：leavesC
 * 时间：2019/5/31 11:07
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = this
    }

}