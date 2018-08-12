package com.mercaritask.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mercaritask.R;
import com.mercaritask.data.model.TabData;
import com.mercaritask.databinding.ItemGridBinding;

import java.util.List;

public class TabDataAdapter extends RecyclerView.Adapter<TabDataAdapter.ViewHolder> {
    private List<TabData> mTabData;

    public TabDataAdapter(List<TabData> tabData) {
        this.mTabData = tabData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGridBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_grid,
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBinding.setData(mTabData.get(position));
    }

    @Override
    public int getItemCount() {
        return mTabData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemGridBinding mBinding;

        ViewHolder(ItemGridBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
