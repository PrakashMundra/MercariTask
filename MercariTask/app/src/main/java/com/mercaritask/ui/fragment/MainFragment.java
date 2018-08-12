package com.mercaritask.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercaritask.R;
import com.mercaritask.data.model.MasterData;
import com.mercaritask.data.model.TabData;
import com.mercaritask.databinding.FragmentMainBinding;
import com.mercaritask.ui.adapter.TabDataAdapter;
import com.mercaritask.viewmodel.MainViewModel;
import com.mercaritask.widget.GridItemDecoration;

import java.util.List;

public class MainFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    public static final String TAG = "main_fragment";
    private FragmentMainBinding mBinding;
    private MainViewModel mMainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mBinding.setViewModel(mMainViewModel);
        observeMasterData();
    }

    private void setUpRecyclerView() {
        if (getContext() != null) {
            int colSpan = (getContext().getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_PORTRAIT) ? 2 : 3;
            mBinding.recyclerView.setHasFixedSize(true);
            mBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), colSpan));
            int space = getResources().getDimensionPixelSize(R.dimen.grid_item_padding);
            mBinding.recyclerView.addItemDecoration(new GridItemDecoration(colSpan, space));
        }
    }

    private void observeMasterData() {
        mMainViewModel.getMasterData().observe(this, masterData -> {
                    if (masterData != null && masterData.size() > 0) {
                        setupTabs(masterData);
                        mMainViewModel.isTabsEmpty.set(false);
                    } else
                        mMainViewModel.isTabsEmpty.set(true);
                    mMainViewModel.isTabsLoading.set(false);
                }
        );
    }

    private void setupTabs(List<MasterData> masterData) {
        mBinding.tabLayout.addOnTabSelectedListener(this);
        for (int i = 0; i < masterData.size(); i++) {
            MasterData data = masterData.get(i);
            TabLayout.Tab tab = mBinding.tabLayout.newTab();
            tab.setText(data.name);
            tab.setTag(data.data);
            mBinding.tabLayout.addTab(tab);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getTag() != null) {
            mMainViewModel.isLoading.set(true);
            observeTabData(tab.getTag().toString());
        } else
            mMainViewModel.isEmpty.set(true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void observeTabData(String tabDataUrl) {
        mMainViewModel.getTabData(tabDataUrl).observe(this, tabData -> {
                    if (tabData != null && tabData.size() > 0) {
                        setAdapter(tabData);
                        mMainViewModel.isEmpty.set(false);
                    } else
                        mMainViewModel.isEmpty.set(true);
                    mMainViewModel.isLoading.set(false);
                }
        );
    }

    private void setAdapter(List<TabData> tabData) {
        TabDataAdapter adapter = new TabDataAdapter(tabData);
        mBinding.recyclerView.setAdapter(adapter);
    }
}
