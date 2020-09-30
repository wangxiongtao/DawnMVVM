package com.example.dawnmvvm.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseFragment;
import com.example.dawnmvvm.databinding.MainFragmentBinding;
import com.example.dawnmvvm.util.LogUtil;

public class MainFragment extends BaseFragment<MainFragmentBinding,FragmentVm> {

    private Main2VM mViewModel;//父类Activity的VM 不是子类的！！！！！！
    public static MainFragment newInstance() {

        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(Main2VM.class);
        mViewModel.string2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                getViewModel().string.set("我是fragment=获取Activity的数据==>"+s);

            }
        });

    }

    @Override
    public int initVariableId() {
        return BR.fragmentVm;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("==life==fragment====>onDestroy");
    }
}