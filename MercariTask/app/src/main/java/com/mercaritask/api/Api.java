package com.mercaritask.api;

import com.mercaritask.data.model.MasterData;
import com.mercaritask.data.model.TabData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
    @GET
    Call<List<MasterData>> getMasterData(@Url String url);

    @GET
    Call<List<TabData>> getTabData(@Url String url);
}
