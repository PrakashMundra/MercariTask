package com.mercaritask.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.mercaritask.api.Api;
import com.mercaritask.data.DataSource;
import com.mercaritask.data.model.MasterData;
import com.mercaritask.data.model.TabData;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {
    private static final String BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/";
    private static final String MASTER_DATA = "m-et/Android/json/master.json";
    private Api mApi;

    public RemoteDataSource() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        mApi = retrofit.create(Api.class);
    }

    @Override
    public LiveData<List<MasterData>> getMasterData() {
        final MediatorLiveData<List<MasterData>> data = new MediatorLiveData<>();
        mApi.getMasterData(BASE_URL + MASTER_DATA).enqueue(new Callback<List<MasterData>>() {
            @Override
            public void onResponse(@NonNull Call<List<MasterData>> call, @NonNull Response<List<MasterData>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<MasterData>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    @Override
    public LiveData<List<TabData>> getTabData(String url) {
        final MediatorLiveData<List<TabData>> data = new MediatorLiveData<>();
        mApi.getTabData(url).enqueue(new Callback<List<TabData>>() {
            @Override
            public void onResponse(@NonNull Call<List<TabData>> call, @NonNull Response<List<TabData>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<TabData>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
