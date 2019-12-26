package leavesc.hello.weather.extend

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 作者：CZY
 * 时间：2019/12/24 14:20
 * 描述：
 */
sealed class ISerializableHolder {

    abstract fun toJson(ob: Any): String

    abstract fun <T> fromJson(json: String, t: Class<T>): T

    abstract fun <T> fromJsonArray(json: String, clazz: Class<T>): MutableList<T>

}

//这是目前选择的序列化方案
private object LocalSerializableHolder : ISerializableHolder() {

    private val gson = Gson()

    override fun toJson(ob: Any): String {
        return gson.toJson(ob)
    }

    override fun <T> fromJson(json: String, t: Class<T>): T {
        return gson.fromJson<T>(json, t)
    }

    override fun <T> fromJsonArray(json: String, clazz: Class<T>): MutableList<T> {
        val type: Type = ParameterizedTypeImpl(clazz)
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    private class ParameterizedTypeImpl constructor(private val clazz: Class<*>) :
            ParameterizedType {

        override fun getActualTypeArguments(): Array<Type> {
            return arrayOf(clazz)
        }

        override fun getRawType(): Type {
            return MutableList::class.java
        }

        override fun getOwnerType(): Type? {
            return null
        }

    }

}

//外部直接引用 serializableHolder 开放出来的接口来进行序列化即可
//即使以后序列化方案有变，直接改变其指向的变量即可
val serializableHolder: ISerializableHolder = LocalSerializableHolder
