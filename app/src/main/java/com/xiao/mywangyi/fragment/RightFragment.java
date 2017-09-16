package com.xiao.mywangyi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xiao.mywangyi.R;

import java.util.Map;

/**
 * Created by 张肖肖 on 2017/9/13.
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    private View mRootView;
    private TextView qq_name;
    private ImageView r_touxiang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mRootView == null){

            mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.right_item, null);
            r_touxiang = mRootView.findViewById(R.id.r_touxiang);
            qq_name = mRootView.findViewById(R.id.qq_name);


            r_touxiang.setOnClickListener(this);
        }
        return mRootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.r_touxiang:
                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getActivity(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            String name = data.get("name");
            String photoUrl = data.get("iconurl");

            ImageLoader.getInstance().displayImage(photoUrl,r_touxiang);
            qq_name.setText(name);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getActivity(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getActivity(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


}
