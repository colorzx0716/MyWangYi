package com.xiao.mywangyi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.kson.tablayout.widget.HorizontalScollTabhost;
import com.example.kson.tablayout.widget.bean.CategoryBean;
import com.kson.slidingmenu.SlidingMenu;
import com.xiao.mywangyi.fragment.Fragment1;
import com.xiao.mywangyi.fragment.LeftFragment;

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
    private List<ChannelBean> channelBeanList;

    private SharedPreferences pre;
    private String jsonstr;
    private CategoryBean bean;
    private ImageView cehua1;
    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏头部

        pre = getSharedPreferences("setting",MODE_PRIVATE);//创建sp

        tabhost = (HorizontalScollTabhost) findViewById(R.id.tabhost);
        cehua1 = (ImageView) findViewById(R.id.cehua1);

        cehua1.setOnClickListener(this);
        jiahao = (ImageView) findViewById(R.id.jiahao);

        jiahao.setOnClickListener(this);
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
       /* menu.setSecondaryMenu(R.layout.right_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_menu,new RightFragment()).commit();
*/
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);

    }

    //初始化头部数据
    private void initData() {
        beanList = new ArrayList<>();
        fragmentList = new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
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
                channelBeanList = new ArrayList<>();
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
        }
    }

    //回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
