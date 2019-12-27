package leavesc.hello.core.extend

import android.content.Context
import com.tencent.mmkv.MMKV
import leavesc.hello.core.holder.ContextHolder

/**
 * 作者：CZY
 * 时间：2019/12/25 18:05
 * 描述：
 */
sealed class IKVHolder {

    abstract fun put(key: String, value: String)

    abstract fun get(key: String, default: String): String

    //移除指定对象
    abstract fun remove(key: String)

    //删除全局的缓存数据
    abstract fun clear()
}

class MMKVKVHolder internal constructor(context: Context) : IKVHolder() {

    init {
        MMKV.initialize(context)
    }

    private val kv = MMKV.defaultMMKV()

    override fun put(key: String, value: String) {
        kv.putString(key, value)
    }

    override fun get(key: String, default: String): String {
        return kv.getString(key, default) ?: ""
    }

    override fun remove(key: String) {
        kv.removeValueForKey(key)
    }

    override fun clear() {
        kv.clearAll()
    }

}

class SPKVHolder internal constructor(context: Context, dbName: String) : IKVHolder() {

    private val sp = context.getSharedPreferences(dbName, Context.MODE_PRIVATE)

    private val edit = sp.edit()

    override fun put(key: String, value: String) {
        edit.putString(key, value)
        edit.commit()
    }

    override fun get(key: String, default: String): String {
        return sp.getString(key, default) ?: ""
    }

    override fun remove(key: String) {
        edit.remove(key)
        edit.commit()
    }

    override fun clear() {
        edit.clear()
        edit.commit()
    }

}

//外部直接引用 kvHolder 开放出来的接口来进行存储即可
//即使以后缓存方案有变，直接改变其指向的变量即可
val kvHolder = MMKVKVHolder(ContextHolder.context)