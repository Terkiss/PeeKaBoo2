package com.terukiss.peekaboo2.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;

public class FragmentViewAdapter extends FragmentPagerAdapter {

    public ArrayList<Fragment> _FragItems = new ArrayList<>();

    private String[] title ;


    public FragmentViewAdapter(@NonNull FragmentManager fm, int behavior, String[] title) {
        super(fm, behavior);

        this.title = title;

        _FragItems.add( new Fragment_Server_List() );
        _FragItems.add( new Fragment_Chat() );
        _FragItems.add( new Fragment_Personal() );
        _FragItems.add( new Fragment_Setting() );
    }


    public CharSequence getPageTitle(int position)
    {
        return title[position];
    }


    @Override
    public Fragment getItem(int position)
    {
        return _FragItems.get(position);
    }

    @Override
    public int getCount()
    {
        return _FragItems.size();
    }

    public int findPosition(String name)
    {
        for(int i = 0 ; i < title.length; i++)
        {
            if(title[i].equals(name))
            {
                return i;
            }
        }
        return -1;
    }
}
