package leavesc.hello.weather.core.http

/**
 * 作者：leavesC
 * 时间：2019/5/31 10:48
 * 描述：
 */
sealed class BaseException(val errorMessage: String, val code: Int = HttpConfig.CODE_UNKNOWN) :
        RuntimeException(errorMessage)

class ServerBadException(message: String, code: Int = HttpConfig.CODE_UNKNOWN) : BaseException(message, code)