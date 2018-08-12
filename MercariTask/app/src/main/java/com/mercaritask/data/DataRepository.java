package com.mercaritask.data;

import android.arch.lifecycle.LiveData;

import com.mercaritask.data.local.LocalDataSource;
import com.mercaritask.data.model.MasterData;
import com.mercaritask.data.model.TabData;
import com.mercaritask.data.remote.RemoteDataSource;

import java.util.List;

public class DataRepository implements DataSource {
    private static DataRepository INSTANCE;
    private RemoteDataSource mRemoteDataRepository;
    private LocalDataSource mLocalDataSource;

    private DataRepository() {
        this.mRemoteDataRepository = new RemoteDataSource();
        this.mLocalDataSource = new LocalDataSource();
    }

    public static DataRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                INSTANCE = new DataRepository();
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MasterData>> getMasterData() {
        return mRemoteDataRepository.getMasterData();
    }

    @Override
    public LiveData<List<TabData>> getTabData(String url) {
        return mRemoteDataRepository.getTabData(url);
    }
}
