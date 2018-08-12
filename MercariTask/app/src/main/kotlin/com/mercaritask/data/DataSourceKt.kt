package com.mercaritask.data

import android.arch.lifecycle.LiveData
import com.mercaritask.data.model.MasterDataKt
import com.mercaritask.data.model.TabDataKt

interface DataSourceKt {
    fun getMasterData(): LiveData<List<MasterDataKt>>?

    fun getTabData(url: String): LiveData<List<TabDataKt>>?
}