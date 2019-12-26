package leavesc.hello.weather.core.http.datasource

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import leavesc.hello.weather.core.http.BaseRemoteDataSource
import leavesc.hello.weather.core.http.HttpConfig
import leavesc.hello.weather.core.http.IBaseViewModeScope
import leavesc.hello.weather.core.http.RequestCallback
import leavesc.hello.weather.core.http.api.ApiService
import leavesc.hello.weather.core.model.DistrictBean
import leavesc.hello.weather.core.model.ForecastsBean

/**
 * 作者：leavesC
 * 时间：2019/5/31 14:27
 * 描述：
 */
class MapDataSource(baseViewModelEvent: IBaseViewModeScope) : BaseRemoteDataSource(baseViewModelEvent) {

    fun getProvince(callback: RequestCallback<List<DistrictBean>>) {
        execute({
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getProvince()
        }, callback)
    }

    fun getCity(keywords: String, callback: RequestCallback<List<DistrictBean>>) {
        execute({
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getCity(keywords)
        }, callback)
    }

    fun getCounty(keywords: String, callback: RequestCallback<List<DistrictBean>>) {
        execute({
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getCounty(keywords)
        }, callback)
    }

}

class WeatherDataSource(baseViewModelEvent: IBaseViewModeScope) : BaseRemoteDataSource(baseViewModelEvent) {

    fun getWeather(city: String, callback: RequestCallback<List<ForecastsBean>>) {
        execute({
            getService(ApiService::class.java, HttpConfig.BASE_URL_MAP).getWeather(city)
        }, callback)
    }

    //协程生命周期测试
    fun test(callback: RequestCallback<String>) {
        scope.launch {
            repeat(20000) {
                delay(100)
                callback.onSuccess("${it}")
            }
        }
    }

}