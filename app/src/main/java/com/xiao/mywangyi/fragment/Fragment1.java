package com.xiao.mywangyi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiao.mywangyi.R;

/**
 * Created by 张肖肖 on 2017/9/13.
 */

public class Fragment1 extends Fragment {
    private View mRootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment1, null);

        return mRootview;
    }
}
