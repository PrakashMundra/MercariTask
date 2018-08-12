package com.mercaritask.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.mercaritask.data.DataRepository;
import com.mercaritask.data.model.MasterData;
import com.mercaritask.data.model.TabData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public ObservableBoolean isTabsLoading = new ObservableBoolean(true);
    public ObservableBoolean isTabsEmpty = new ObservableBoolean(false);
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public ObservableBoolean isEmpty = new ObservableBoolean(false);
    private DataRepository mDataRepository;
    private MediatorLiveData<List<MasterData>> mMasterData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.mDataRepository = DataRepository.getInstance();
    }

    public LiveData<List<MasterData>> getMasterData() {
        if (mMasterData == null) {
            mMasterData = new MediatorLiveData<>();
            LiveData<List<MasterData>> masterData = mDataRepository.getMasterData();
            mMasterData.addSource(masterData, mMasterData::setValue);
        }
        return mMasterData;
    }

    public LiveData<List<TabData>> getTabData(String url) {
        MediatorLiveData<List<TabData>> observable = new MediatorLiveData<>();
        LiveData<List<TabData>> tabData = mDataRepository.getTabData(url);
        observable.addSource(tabData, observable::setValue);
        return observable;
    }
}
