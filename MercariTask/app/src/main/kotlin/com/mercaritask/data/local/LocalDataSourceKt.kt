package com.mercaritask.data.local

import android.arch.lifecycle.LiveData
import com.mercaritask.data.DataSourceKt
import com.mercaritask.data.model.MasterDataKt
import com.mercaritask.data.model.TabDataKt

class LocalDataSourceKt : DataSourceKt {
    override fun getMasterData(): LiveData<List<MasterDataKt>>? {
        return null
    }

    override fun getTabData(url: String): LiveData<List<TabDataKt>>? {
        return null
    }
}