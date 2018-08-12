package com.mercaritask.data.local;

import android.arch.lifecycle.LiveData;

import com.mercaritask.data.DataSource;
import com.mercaritask.data.model.MasterData;
import com.mercaritask.data.model.TabData;

import java.util.List;

public class LocalDataSource implements DataSource {
    @Override
    public LiveData<List<MasterData>> getMasterData() {
        return null;
    }

    @Override
    public LiveData<List<TabData>> getTabData(String url) {
        return null;
    }
}
