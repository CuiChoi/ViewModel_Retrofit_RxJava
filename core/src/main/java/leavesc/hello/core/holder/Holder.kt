package leavesc.hello.core.holder

import android.content.Context
import android.widget.Toast

/**
 * 作者：leavesC
 * 时间：2019/5/31 11:07
 * 描述：
 */

class ContextHolder {

    companion object {

        lateinit var context: Context

    }

}

class ToastHolder {

    companion object {
        fun showToast(msg: String, context: Context = ContextHolder.context) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

}