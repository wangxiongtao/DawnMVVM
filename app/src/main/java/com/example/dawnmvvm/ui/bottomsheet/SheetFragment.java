package com.example.dawnmvvm.ui.bottomsheet;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.dawnmvvm.BR;
import com.example.dawnmvvm.R;
import com.example.dawnmvvm.base.BaseFragment;
import com.example.dawnmvvm.databinding.FragmentSheetLayoutBinding;
import com.example.dawnmvvm.ui.UIVm;

public class SheetFragment extends BaseFragment<FragmentSheetLayoutBinding, UIVm> {


    private int p;

    public static SheetFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position",position);
        SheetFragment fragment = new SheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sheet_layout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        p=getArguments().getInt("position");
        
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getViewModel().fragmentPos.set(p);
    }

    @Override
    public int initVariableId() {
        return BR.uiVm;
    }
}
