package com.mercaritask.data;

import android.arch.lifecycle.LiveData;

import com.mercaritask.data.model.MasterData;
import com.mercaritask.data.model.TabData;

import java.util.List;

public interface DataSource {
    LiveData<List<MasterData>> getMasterData();

    LiveData<List<TabData>> getTabData(String url);
}
