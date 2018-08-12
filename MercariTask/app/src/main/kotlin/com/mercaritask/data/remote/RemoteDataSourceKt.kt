package com.mercaritask.data.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.mercaritask.api.ApiKt
import com.mercaritask.data.DataSourceKt
import com.mercaritask.data.model.MasterDataKt
import com.mercaritask.data.model.TabDataKt
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceKt : DataSourceKt {
    private var mApi: ApiKt

    companion object {
        private const val BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/"
        private const val MASTER_DATA = "m-et/Android/json/master.json"
    }

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        mApi = retrofit.create(ApiKt::class.java)
    }

    override fun getMasterData(): LiveData<List<MasterDataKt>>? {
        val data = MediatorLiveData<List<MasterDataKt>>()
        mApi.getMasterData(BASE_URL + MASTER_DATA).enqueue(object : Callback<List<MasterDataKt>> {
            override fun onResponse(call: Call<List<MasterDataKt>>?, response: Response<List<MasterDataKt>>?) {
                response?.let {
                    data.value = it.body()
                } ?: kotlin.run { data.value = null }
            }

            override fun onFailure(call: Call<List<MasterDataKt>>?, t: Throwable?) {
                data.value = null
            }
        })
        return data
    }

    override fun getTabData(url: String): LiveData<List<TabDataKt>>? {
        val data = MediatorLiveData<List<TabDataKt>>()
        mApi.getTabData(url).enqueue(object : Callback<List<TabDataKt>> {
            override fun onResponse(call: Call<List<TabDataKt>>?, response: Response<List<TabDataKt>>?) {
                response?.let {
                    data.value = it.body()
                } ?: kotlin.run { data.value = null }
            }

            override fun onFailure(call: Call<List<TabDataKt>>?, t: Throwable?) {
                data.value = null
            }
        })
        return data
    }
}