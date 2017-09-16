package com.xiao.mywangyi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.kson.tablayout.widget.HorizontalScollTabhost;
import com.example.kson.tablayout.widget.bean.CategoryBean;
import com.kson.slidingmenu.SlidingMenu;
import com.umeng.socialize.UMShareAPI;
import com.xiao.mywangyi.bean.Popup;
import com.xiao.mywangyi.fragment.Fragment1;
import com.xiao.mywangyi.fragment.LeftFragment;
import com.xiao.mywangyi.fragment.RightFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //头部
    private String[] titles = {"头条","视频","娱乐","网易号","薄荷",
            "财经","科技","汽车","社会","军事",
            "时尚","直播","图片","跟帖","热点",
            "房产","股票","话题","漫画","特卖",
            "NBA","历史","时尚","辟谣","探索","美国",
            "搞笑","故事","奇葩","情感", "数码","轻松一刻",
            "女人","阳光法院","双创","二次元",
            "亲子","读书","萌宠","独家"};

    private HorizontalScollTabhost tabhost;
    private List<CategoryBean> beanList;
    private List<Fragment> fragmentList;
    private ImageView jiahao;

    private SharedPreferences pre;
    private String jsonstr;
    private CategoryBean bean;
    private ImageView cehua1,touxiang,gengduo;
    private SlidingMenu menu;
    private ListView lsvMore;
    private View popupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏头部

        pre = getSharedPreferences("setting",MODE_PRIVATE);//创建sp

        tabhost = (HorizontalScollTabhost) findViewById(R.id.tabhost);
        cehua1 = (ImageView) findViewById(R.id.cehua1);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        jiahao = (ImageView) findViewById(R.id.jiahao);
        gengduo = (ImageView) findViewById(R.id.gengduo);


        cehua1.setOnClickListener(this);//左侧拉
        touxiang.setOnClickListener(this);//头像--右侧拉
        jiahao.setOnClickListener(this);//加号--频道
       gengduo.setOnClickListener(this);//更多图标

        initData();//初始化数据
        initMenu();//侧拉方法

    }

    //侧拉方法
    private void initMenu() {
        menu = new SlidingMenu(this);
        menu.setMenu(R.layout.left_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.left_menu,new LeftFragment()).commit();

        //设置slidingmenu相关属性
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);  // 设置滑动菜单视图的宽度

        //设置右菜单
        menu.setSecondaryMenu(R.layout.right_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_menu,new RightFragment()).commit();
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);

    }

    //初始化头部数据
    private void initData() {
        beanList = new ArrayList<>();
        fragmentList = new ArrayList<>();

        for (int i = 0; i <titles.length ; i++) {
            bean = new CategoryBean();
            bean.name = titles[i];
            beanList.add(bean);
            fragmentList.add(new Fragment1());
        }
        tabhost.diaplay(beanList,fragmentList);
    }


    //点击监听事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jiahao://加号，频道的点击增加删除
                jsonstr = pre.getString("user_setting", null);
                List<ChannelBean> channelBeanList = new ArrayList<>();
                if(jsonstr == null){
                    ChannelBean channelBean;
                    for (int i = 0; i <titles.length ; i++) {
                        if(i<10){
                            channelBean = new ChannelBean(titles[i],true);
                            bean.name = titles[i];
                        }else{
                            channelBean = new ChannelBean(titles[i],false);
                        }
                        channelBeanList.add(channelBean);
                    }
                }else{
                    //不为空使用之前回传的数据
                    try {
                        JSONArray arr = new JSONArray(jsonstr);
                        System.out.println("arr.toString() = " + arr.toString());
                        for (int i = 0; i <arr.length() ; i++) {
                            JSONObject o = (JSONObject) arr.get(i);
                            String name = o.getString("name");
                            boolean isSelect = o.getBoolean("isSelect");
                            ChannelBean channelBean = new ChannelBean(name,isSelect);
                            channelBeanList.add(channelBean);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ChannelActivity.startChannelActivity(MainActivity.this,channelBeanList);
                break;

            case R.id.cehua1:
                menu.showMenu();
                break;
            case R.id.touxiang:
                menu.showSecondaryMenu();
                break;
            case R.id.gengduo://更多的图片按钮popwindow

                List<Popup> popupList=new ArrayList<>();
                Popup popup1=new Popup("天气",R.drawable.p_tianqi);
                Popup popup2=new Popup("离线",R.drawable.p_lixian);
                Popup popup3=new Popup("夜间",R.drawable.p_yejian);
                Popup popup4=new Popup("搜索",R.drawable.p_sousuo);
                Popup popup5=new Popup("扫一扫",R.drawable.p_sousou);
                Popup popup6=new Popup("设置",R.drawable.p_shezhi);

                popupList.add(popup1);
                popupList.add(popup2);
                popupList.add(popup3);
                popupList.add(popup4);
                popupList.add(popup5);
                popupList.add(popup6);

                popupView = MainActivity.this.getLayoutInflater().inflate(R.layout.pop_item, null);
                lsvMore = popupView.findViewById(R.id.lsvMore);
                //适配器
                PopupAdapter popupAdapter=new PopupAdapter(MainActivity.this,popupList);
                lsvMore.setAdapter(popupAdapter);

                //创建pop，指定宽度和高度
                PopupWindow popupWindow=new PopupWindow(popupView, 400, 500);
                //设置动画
                popupWindow.setAnimationStyle(R.style.popup_window_anim);
                //设置背景颜色
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F0F0F0")));
                //获取焦点
                popupWindow.setFocusable(true);
                //设置可以触摸弹出框意外的区域
                popupWindow.setOutsideTouchable(true);
                //更新状态
                popupWindow.update();
                //以下拉的方式显示，并且可以设置显示的位置
                popupWindow.showAsDropDown(gengduo,0,0);

               // 点击每个条目展示它的功能
                lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i==0){
                            Toast.makeText(MainActivity.this, "天气", Toast.LENGTH_SHORT).show();
                        }
                        if(i==1){
                            Toast.makeText(MainActivity.this, "离线", Toast.LENGTH_SHORT).show();
                        }
                        if(i==2){
                            Toast.makeText(MainActivity.this, "夜间", Toast.LENGTH_SHORT).show();
                            //夜间模式
                            int uiMode;
                            uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                            switch (uiMode){
                                case Configuration.UI_MODE_NIGHT_YES:
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                    getSharedPreferences("theme", MODE_PRIVATE).edit().putBoolean("night_theme", false).commit();
                                    break;
                                case Configuration.UI_MODE_NIGHT_NO:
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                                    getSharedPreferences("theme", MODE_PRIVATE).edit().putBoolean("night_theme", true).commit();
                                    break;
                            }
                            //重建
                            recreate();

                        }
                        if(i==3){
                            Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                        }
                        if(i==4){
                            Toast.makeText(MainActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                        }
                        if(i==5){
                            Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
        }
    }

    //回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);//友盟回调接口


        //判断回传吗是否相同
        if(requestCode == ChannelActivity.REQUEST_CODE && resultCode == ChannelActivity.RESULT_CODE){
            //获取回传的值
            jsonstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            //存入到sp
            pre.edit().putString("user_setting",jsonstr).commit();
            //清空
            beanList.clear();
            List<Fragment> fragmentList2 = new ArrayList<>();
            try {
                JSONArray arr = new JSONArray(jsonstr);
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject oo = (JSONObject) arr.get(i);
                    String name = oo.getString("name");
                    boolean isSelect = oo.getBoolean("isSelect");
                    if(isSelect){
                        CategoryBean c = new CategoryBean();
                        c.name = name;
                        beanList.add(c);
                        fragmentList2.add(fragmentList.get(i));
                    }
                }
                tabhost.remove();
                tabhost.diaplay(beanList,fragmentList2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
