package com.meituan;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.meituan.adapter.CategoryListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    private LinearLayout total_category, ly_search;//分类
    private ArrayList<HashMap<String, Object>> itemList;
    private ArrayList<HashMap<String, Object>> childitemList;
    private LinearLayout layout;
    private ListView rootList;
    private ListView childList;
    private FrameLayout flChild;
    private PopupWindow mPopWin;
    private LinearLayout category, ofprice;
    private boolean muUrlFlag;
    private String mUrl;
    private String selectplace = "全部分类";
    private TextView text_category, iv_price_status;
    private int flag = -1;
    private String firstItem[] = {"全部", "电影", "旅游", "酒店", "美食", "娱乐"};
    String secondItem[][] = new String[][]{
            new String[]{"全部", "铁路票务", "航空票务", "租车服务", "公路票务", "接车服务",
                    "包车服务", "游船"},
            new String[]{"全部", "导游预定", "工业园", "民俗庙会", "温泉养生", "签证办理", "景区门票",
                    "旅游向导", "景区餐饮"},
            new String[]{"全部", "酒店预订", "度假别墅", "农家乐预订", "景区户外露宿点", "提醒服务"},
            new String[]{"全部", "经典名吃", "旅游团餐", "自助餐", "特色美食", "特色美食咨询"},
            new String[]{"全部", "景区娱乐项目", "大型演出", "水上项目", "主题游乐园", "滑雪场",
                    "主题公园", "游轮"},
            new String[]{"全部", " 特色产品订购", "旅游纪念品订购", "户外用品订购", "工艺品订购",
                    " 收藏品订购", "奢侈品订购"}};
    private Handler hander = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                text_category.setText(selectplace);
            } else if (msg.what == 1) {
                if (mPopWin != null) {
                    mPopWin.dismiss();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        category = (LinearLayout) findViewById(R.id.total_category);
        ly_search = (LinearLayout) findViewById(R.id.ly_search);
        category.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupWindow(ly_search.getWidth(), ly_search.getHeight());

            }
        });
    }


    private void showPopupWindow(int width, int height) {
        itemList = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < firstItem.length; i++) {
            HashMap<String, Object> items = new HashMap<String, Object>();
            items.put("name", firstItem[i]);
            itemList.add(items);
        }
        layout = (LinearLayout) LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.popup_category, null);
        rootList = (ListView) layout.findViewById(R.id.rootcategory);

        CategoryListAdapter cla = new CategoryListAdapter(
                MainActivity.this, itemList);
        rootList.setAdapter(cla);
        cla.notifyDataSetChanged();
        flChild = (FrameLayout) layout.findViewById(R.id.child_lay);
        childList = (ListView) layout.findViewById(R.id.childcategory);
        flChild.setVisibility(View.INVISIBLE);

        mPopWin = new PopupWindow(layout, width, height * 2 / 3, true);
        mPopWin.setBackgroundDrawable(new BitmapDrawable());
        mPopWin.showAsDropDown(category, 5, 1);
        mPopWin.update();
//根菜单页面
        rootList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position != 0) {
                    childitemList = new ArrayList<HashMap<String, Object>>();
                    flChild.setVisibility(View.VISIBLE);
                    int childlength = secondItem[position - 1].length;
                    for (int i = 0; i < childlength; i++) {
                        HashMap<String, Object> items = new HashMap<String, Object>();
                        items.put("name", secondItem[position - 1][i]);
                        childitemList.add(items);
                    }
                    CategoryListAdapter childcla = new CategoryListAdapter(
                            MainActivity.this, childitemList);
                    childList.setAdapter(childcla);
                    childcla.notifyDataSetChanged();
                    flag = position;
                    Log.e("flag", "flag==" + position);
                } else {
                    if (mPopWin != null) {
                        mPopWin.dismiss();
                    }
                    text_category.setText("电影");
                    hander.sendEmptyMessage(1);
                }
            }
        });
        //二级菜单页面
        childList
                .setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Log.e("childList", "flag==" + flag);
                        switch (flag) {
                            case 1:
                                if (position == 0) {
                                    text_category.setText("租车");
                                } else if (position == 1) {
                                    text_category.setText("铁路票务");
                                } else if (position == 2) {
                                    text_category.setText("航空票务");
                                } else if (position == 3) {
                                    text_category.setText("租车服务");
                                } else if (position == 4) {
                                    text_category.setText("公路票务");
                                } else if (position == 5) {
                                    text_category.setText("接车服务");
                                } else if (position == 6) {
                                    text_category.setText("包车服务");
                                } else if (position == 7) {
                                    text_category.setText("游船");
                                }
                                break;
                            case 2:
                                if (position == 0) {
                                    text_category.setText("旅游");
                                } else if (position == 1) {
                                    text_category.setText("导游预定");
                                } else if (position == 2) {
                                    text_category.setText("工业园");
                                } else if (position == 3) {
                                    text_category.setText("民俗庙会");
                                } else if (position == 4) {
                                    text_category.setText("温泉养生");
                                } else if (position == 5) {
                                    text_category.setText("签证办理");
                                } else if (position == 6) {
                                    text_category.setText("景区门票");
                                } else if (position == 7) {
                                    text_category.setText("旅游向导");
                                } else if (position == 8) {
                                    text_category.setText("景区餐饮");
                                }
                                break;
                            case 3:
                                if (position == 0) {
                                    text_category.setText("酒店");
                                } else if (position == 1) {
                                    text_category.setText("酒店预订");
                                } else if (position == 2) {
                                    text_category.setText("度假别墅");
                                } else if (position == 3) {
                                    text_category.setText("农家乐预订");
                                } else if (position == 4) {
                                    text_category.setText("景区户外露宿点");
                                } else if (position == 5) {
                                    text_category.setText("提醒服务");
                                }
                                break;
                            case 4:
                                if (position == 0) {
                                    text_category.setText("美食");
                                } else if (position == 1) {
                                    text_category.setText("经典名吃");
                                } else if (position == 2) {
                                    text_category.setText("旅游团餐");
                                } else if (position == 3) {
                                    text_category.setText("自助餐");
                                } else if (position == 4) {
                                    text_category.setText("特色美食");
                                } else if (position == 5) {
                                    text_category.setText("特色美食咨询");
                                }
                                break;
                            case 5:
                                if (position == 0) {
                                    text_category.setText("娱乐");
                                } else if (position == 1) {
                                    text_category.setText("景区娱乐项目");
                                } else if (position == 2) {
                                    text_category.setText("大型演出");
                                } else if (position == 3) {
                                    text_category.setText("水上项目");
                                } else if (position == 4) {
                                    text_category.setText("主题游乐园");
                                } else if (position == 5) {
                                    text_category.setText("滑雪场");
                                } else if (position == 6) {
                                    text_category.setText("主题公园");
                                } else if (position == 7) {
                                    text_category.setText("游轮");
                                }
                                break;
                            case 6:
                                if (position == 0) {
                                    text_category.setText("购物");
                                } else if (position == 1) {
                                    text_category.setText("当地特色产品订购");
                                } else if (position == 2) {
                                    text_category.setText("旅游纪念品订购");
                                } else if (position == 3) {
                                    text_category.setText("户外用品订购");
                                } else if (position == 4) {
                                    text_category.setText("工艺品订购");
                                } else if (position == 5) {
                                    text_category.setText("收藏品订购");
                                } else if (position == 6) {
                                    text_category.setText("奢侈品订购");
                                }
                                break;

                            default:
                                break;
                        }
                        hander.sendEmptyMessage(1);
                        iv_price_status.setVisibility(View.GONE);
                        muUrlFlag = false;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
