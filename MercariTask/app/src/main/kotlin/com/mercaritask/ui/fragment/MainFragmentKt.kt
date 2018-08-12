package com.mercaritask.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercaritask.R
import com.mercaritask.data.model.MasterDataKt
import com.mercaritask.data.model.TabDataKt
import com.mercaritask.databinding.FragmentMainKtBinding
import com.mercaritask.ui.adapter.TabDataAdapterKt
import com.mercaritask.viewmodel.MainViewModelKt
import com.mercaritask.widget.GridItemDecorationKt

class MainFragmentKt : Fragment(), TabLayout.OnTabSelectedListener {
    companion object {
        val TAG = "main_fragment";
    }

    private lateinit var mBinding: FragmentMainKtBinding
    private lateinit var mMainViewModel: MainViewModelKt

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_kt, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModelKt::class.java)
        mBinding.viewModel = mMainViewModel
        observeMasterData()
    }

    private fun setUpRecyclerView() {
        context?.let {
            val colSpan = if (it.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
            mBinding.recyclerView.setHasFixedSize(true)
            mBinding.recyclerView.layoutManager = GridLayoutManager(context, colSpan)
            val space = resources.getDimensionPixelSize(R.dimen.grid_item_padding)
            mBinding.recyclerView.addItemDecoration(GridItemDecorationKt(colSpan, space))
        }
    }

    private fun observeMasterData() {
        mMainViewModel.getMasterData().observe(this, Observer { masterData ->
            if (masterData != null && !masterData.isEmpty()) {
                setupTabs(masterData)
                mMainViewModel.isTabsEmpty.set(false)
            } else
                mMainViewModel.isTabsEmpty.set(true)
            mMainViewModel.isTabsLoading.set(false)
        })
    }

    private fun setupTabs(masterData: List<MasterDataKt>) {
        mBinding.tabLayout.addOnTabSelectedListener(this)
        for (i in masterData.indices) {
            val data = masterData[i]
            val tab = mBinding.tabLayout.newTab()
            tab.text = data.name
            tab.tag = data.data
            mBinding.tabLayout.addTab(tab)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        if (tab.tag != null) {
            mMainViewModel.isLoading.set(true)
            observeTabData(tab.tag!!.toString())
        } else
            mMainViewModel.isEmpty.set(true)
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {

    }

    override fun onTabReselected(tab: TabLayout.Tab) {

    }

    private fun observeTabData(tabDataUrl: String) {
        mMainViewModel.getTabData(tabDataUrl).observe(this, Observer { tabData ->
            if (tabData != null && !tabData.isEmpty()) {
                setAdapter(tabData)
                mMainViewModel.isEmpty.set(false)
            } else
                mMainViewModel.isEmpty.set(true)
            mMainViewModel.isLoading.set(false)
        })
    }

    private fun setAdapter(tabData: List<TabDataKt>) {
        val adapter = TabDataAdapterKt(tabData)
        mBinding.recyclerView.adapter = adapter
    }
}