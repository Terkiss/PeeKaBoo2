package com.terukiss.peekaboo2.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.Network.ConnectionInfo;
import com.terukiss.peekaboo2.helper.JeongLog;

public class ContentBindActivity extends AppCompatActivity {

    ViewPager viewPager;
    JeongLog jeongLog;
    BottomNavigationView bottomNavigationView ;
    @RequiresApi(api = Build.VERSION_CODES.O)
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


        // Todo 뷰페이저의 페이지 체인지 리스너
        // 지금은 이상이 없지만 좀더 깔끔한 방법을 찾아야함
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
                    if(ConnectionInfo.ServerNick.length() < 1)
                    {
                        Snackbar.make(viewPager, "채팅 서버 연결을 확인 하세요",Snackbar.LENGTH_LONG).show();
                    }
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






        // 처음 문자로 타이밍을 잡았던것을 id  값으로 잡게끔 변경
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentViewAdapter fragmentViewAdapter = (FragmentViewAdapter) viewPager.getAdapter();

                if(item.getItemId() == R.id.menu_serverlist)
                {
                    int position = fragmentViewAdapter.findPosition(getString(R.string.menu_serverlist));
                    viewPager.setCurrentItem(position, true);
                    return true;
                }
                if(item.getItemId() == R.id.menu_chat)
                {
                    int position = fragmentViewAdapter.findPosition(getString(R.string.menu_chat));
                    viewPager.setCurrentItem(position, true);
                    if(ConnectionInfo.ServerNick.length() < 1)
                    {
                        Snackbar.make(viewPager, "채팅 서버 연결을 확인 하세요",Snackbar.LENGTH_LONG).show();
                    }

                    jeongLog.logD("MenuItem "+item.toString()+"  "+item.getItemId());
                    return true;
                }
                if(item.getItemId() == R.id.menu_personal)
                {
                    int position = fragmentViewAdapter.findPosition(getString(R.string.menu_personal));
                    viewPager.setCurrentItem(position, true);
                    jeongLog.logD("MenuItem "+item.toString()+"  "+item.getItemId());
                    return true;
                }
                if(item.getItemId() == R.id.menu_serverSetting)
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
