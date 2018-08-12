package com.mercaritask.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.databinding.ObservableBoolean
import android.support.annotation.NonNull
import com.mercaritask.data.DataRepositoryKt
import com.mercaritask.data.model.MasterDataKt
import com.mercaritask.data.model.TabDataKt

class MainViewModelKt(@NonNull application: Application) : AndroidViewModel(application) {
    var isTabsLoading = ObservableBoolean(true)
    var isTabsEmpty = ObservableBoolean(false)
    var isLoading = ObservableBoolean(false)
    var isEmpty = ObservableBoolean(false)
    private var mDataRepository: DataRepositoryKt? = DataRepositoryKt.getInstance()
    private var mMasterData: MediatorLiveData<List<MasterDataKt>>? = null

    fun getMasterData(): LiveData<List<MasterDataKt>> {
        if (mMasterData == null) {
            mMasterData = MediatorLiveData<List<MasterDataKt>>()
            mDataRepository?.let {
                it.getMasterData()?.let {
                    mMasterData!!.addSource(it, mMasterData!!::setValue)
                }
            }
        }
        return mMasterData!!
    }

    fun getTabData(url: String): LiveData<List<TabDataKt>> {
        val observable = MediatorLiveData<List<TabDataKt>>()
        mDataRepository?.let {
            it.getTabData(url)?.let {
                observable.addSource(it, observable::setValue)
            }
        }
        return observable
    }
}