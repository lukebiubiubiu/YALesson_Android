package com.shanguang.lesson;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanguang.lesson.modules.center.CenterFragment;
import com.shanguang.lesson.modules.communion.CommFragment;
import com.shanguang.lesson.modules.lesson.LessonFragment;
import com.shanguang.lesson.modules.main.MainFragment;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private IndicatorViewPager indicatorViewPager;
    @BindView(R.id.tabmain_viewPager)
    SViewPager viewPager;
    @BindView(R.id.tabmain_indicator)
    FixedIndicatorView indicator;
    boolean isExit;
    boolean isDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setIndicator();
    }
    private void setIndicator() {
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(MainActivity.this, R.color.blank, R.color.white));
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        // 禁止viewpager的滑动事件
        viewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(4);
    }
    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] tabNames = {getResources().getString(R.string.main),
                getResources().getString(R.string.lesson),
                getResources().getString(R.string.commit),
                getResources().getString(R.string.center)};
        private int[] tabIcons = { R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
                R.drawable.maintab_4_selector};
        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new MainFragment();
            } else if (position == 1) {
                fragment = new LessonFragment();
            } else if (position == 2) {
                fragment = new CommFragment();
            } else if (position == 3) {
                fragment = new CenterFragment();
            }
            return fragment;
        }
    }
}
