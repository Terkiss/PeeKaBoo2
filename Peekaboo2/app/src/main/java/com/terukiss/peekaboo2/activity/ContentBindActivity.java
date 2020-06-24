package com.terukiss.peekaboo2.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentViewHolder;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.JeongLog;

public class ContentBindActivity extends AppCompatActivity {

    ViewPager viewPager;
    JeongLog jeongLog;
    BottomNavigationView bottomNavigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_bind);

        jeongLog = new JeongLog("ContentsBind");

        viewPager = findViewById(R.id.pager);

        FragmentViewAdapter fragmentViewAdapter = new FragmentViewAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                new String[]{
                        getString(R.string.menu_serverlist),
                        getString(R.string.menu_chat),
                        getString(R.string.menu_personal),
                        getString(R.string.menu_serverSetting)
                });

        viewPager.setAdapter(fragmentViewAdapter);


        bottomNavigationView = findViewById(R.id.nav_view);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                jeongLog.logD(position+" onpagescrolled");
            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onPageSelected(int position) {
                String pagerTitle = (String) viewPager.getAdapter().getPageTitle(position);
                if(pagerTitle.equals(getString(R.string.menu_serverlist)))
                {
                    bottomNavigationView.setSelectedItemId(R.id.menu_serverlist);
                }
                else if(pagerTitle.equals(getString(R.string.menu_chat)))
                {
                    bottomNavigationView.setSelectedItemId(R.id.menu_chat);
                }
                else if(pagerTitle.equals(getString(R.string.menu_personal)))
                {
                    bottomNavigationView.setSelectedItemId(R.id.menu_personal);
                }
                else if(pagerTitle.equals(getString(R.string.menu_serverSetting)))
                {
                    bottomNavigationView.setSelectedItemId(R.id.menu_serverSetting);
                }
                jeongLog.logD(position+" onpageselecte");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                jeongLog.logD(state+" onpagescrollstatechange");
            }
        });







        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentViewAdapter fragmentViewAdapter = (FragmentViewAdapter) viewPager.getAdapter();



//                if(item.getItemId() == R.id.menu_serverlist)
                if(item.toString().equals(getString(R.string.menu_serverlist)))
                {

                    int position = fragmentViewAdapter.findPosition(getString(R.string.menu_serverlist));

                    viewPager.setCurrentItem(position, true);

                    jeongLog.logD("포지션 값 :: "+position);
                    jeongLog.logD("MenuItem "+item.toString()+"  "+item.getItemId());

                    return true;
                }
//                if(item.getItemId() == R.id.menu_chat)
                if(item.toString().equals(getString(R.string.menu_chat)))
                {
                    int position = fragmentViewAdapter.findPosition(getString(R.string.menu_chat));

                    viewPager.setCurrentItem(position, true);

                    jeongLog.logD("MenuItem "+item.toString()+"  "+item.getItemId());
                    return true;
                }
//                if(item.getItemId() == R.id.menu_personal)
                if(item.toString().equals(getString(R.string.menu_personal)))
                {
                    int position = fragmentViewAdapter.findPosition(getString(R.string.menu_personal));

                    viewPager.setCurrentItem(position, true);

                    jeongLog.logD("MenuItem "+item.toString()+"  "+item.getItemId());
                    return true;
                }
//                if(item.getItemId() == R.id.menu_serverSetting)
                if(item.toString().equals(getString(R.string.menu_serverSetting)))
                {
                    int position = fragmentViewAdapter.findPosition(getString(R.string.menu_serverSetting));

                    viewPager.setCurrentItem(position, true);

                    jeongLog.logD("MenuItem "+item.toString()+"  "+item.getItemId());
                    return true;
                }
                return false;
            }
        });

    }

}
