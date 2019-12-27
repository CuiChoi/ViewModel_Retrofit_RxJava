package leavesc.hello.weather.core.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import leavesc.hello.core.http.RequestCallback
import leavesc.hello.core.http.datasource.WeatherDataSource
import leavesc.hello.core.http.viewmodel.BaseViewModel
import leavesc.hello.core.model.ForecastsBean

/**
 * 作者：leavesC
 * 时间：2019/6/7 21:13
 * 描述：
 * GitHub：https://github.com/leavesC
 * Blog：https://www.jianshu.com/u/9df45b87cfdf
 */

class WeatherViewModel : BaseViewModel() {

    private val weatherDataSource = WeatherDataSource(this)

    val forecastsBeanLiveData = MutableLiveData<ForecastsBean>()

    fun getWeather(city: String) {
        weatherDataSource.getWeather(city, object : RequestCallback<List<ForecastsBean>> {
            override fun onSuccess(data: List<ForecastsBean>) {
                if (data.isNotEmpty()) {
                    forecastsBeanLiveData.value = data[0]
                }
            }
        })

        weatherDataSource.test(object : RequestCallback<String> {
            override fun onSuccess(data: String) {
                Log.e("TAG", data)
            }
        })
    }

}