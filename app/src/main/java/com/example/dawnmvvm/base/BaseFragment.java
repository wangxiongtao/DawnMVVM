package com.example.dawnmvvm.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dawnmvvm.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<VDB extends ViewDataBinding,VM extends BaseViewModel> extends Fragment {
    private VM viewModel;
    private VDB viewDataBinding;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewDataBinding=DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        viewDataBinding.setLifecycleOwner(this);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        handlerVM();
        progressDialog = new ProgressDialog(requireActivity());
        initData(savedInstanceState);
    }




    private void handlerVM() {
        Class<BaseViewModel> viewModelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            viewModelClass = (Class<BaseViewModel>) ((ParameterizedType) type).getActualTypeArguments()[1];//获取第1个注解即VM的注解类型
        } else {
            //使用父类的类型
            viewModelClass = BaseViewModel.class;
        }
        viewModel = (VM) new ViewModelProvider(this).get(viewModelClass);//fragment自己的VM 不是Activity
        viewModel.loadingEvent.observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        });
        if(initVariableId()>0){
            viewDataBinding.setVariable(initVariableId(),viewModel);
        }

    }


    public VM getViewModel() {
        return viewModel;
    }

    public VDB getViewDataBinding() {
        return viewDataBinding;
    }

    public abstract int getLayoutId();


    public abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public  int initVariableId(){
        return -1;
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(viewDataBinding!=null){
            viewDataBinding.unbind();
        }

    }
}
