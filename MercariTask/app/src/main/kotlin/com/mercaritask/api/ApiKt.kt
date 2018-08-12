package com.mercaritask.api

import com.mercaritask.data.model.MasterDataKt
import com.mercaritask.data.model.TabDataKt
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiKt {
    @GET
    fun getMasterData(@Url url: String): Call<List<MasterDataKt>>

    @GET
    fun getTabData(@Url url: String): Call<List<TabDataKt>>
}