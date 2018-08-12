package com.mercaritask.data

import android.arch.lifecycle.LiveData
import com.mercaritask.data.local.LocalDataSourceKt
import com.mercaritask.data.model.MasterDataKt
import com.mercaritask.data.model.TabDataKt
import com.mercaritask.data.remote.RemoteDataSourceKt

class DataRepositoryKt : DataSourceKt {
    private var mLocalDataSource = LocalDataSourceKt()
    private var mRemoteDataSource = RemoteDataSourceKt()

    companion object {
        private var INSTANCE: DataRepositoryKt? = null

        fun getInstance(): DataRepositoryKt? {
            if (INSTANCE == null)
                return DataRepositoryKt()
            return INSTANCE;
        }
    }

    override fun getMasterData(): LiveData<List<MasterDataKt>>? {
        return mRemoteDataSource.getMasterData()
    }

    override fun getTabData(url: String): LiveData<List<TabDataKt>>? {
        return mRemoteDataSource.getTabData(url)
    }
}