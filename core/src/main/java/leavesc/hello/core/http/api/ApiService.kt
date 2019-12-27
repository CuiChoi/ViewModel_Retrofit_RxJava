package leavesc.hello.core.http.api

import leavesc.hello.core.http.model.BaseResponse
import leavesc.hello.core.model.DistrictBean
import leavesc.hello.core.model.ForecastsBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 作者：leavesC
 * 时间：2019/5/31 11:17
 * 描述：
 */
interface ApiService {

    @GET("config/district")
    suspend fun getProvince(): BaseResponse<List<DistrictBean>>

    @GET("config/district")
    suspend fun getCity(@Query("keywords") keywords: String): BaseResponse<List<DistrictBean>>

    @GET("config/district")
    suspend fun getCounty(@Query("keywords") keywords: String): BaseResponse<List<DistrictBean>>

    @GET("weather/weatherInfo?extensions=all")
    suspend fun getWeather(@Query("city") city: String): BaseResponse<List<ForecastsBean>>

}