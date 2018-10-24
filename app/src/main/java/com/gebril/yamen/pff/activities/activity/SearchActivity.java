package com.gebril.yamen.pff.activities.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.adapters.SearchPagerAdapter;


public class SearchActivity extends AppCompatActivity {

    TextView tv_search ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        tv_search = findViewById(R.id.tv_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Player"));
        tabLayout.addTab(tabLayout.newTab().setText("Team"));
        tabLayout.addTab(tabLayout.newTab().setText("Tournament"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager =  findViewById(R.id.pager);
        final SearchPagerAdapter adapter = new SearchPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
                switch (position)
                {
                    case 0:
                        tv_search.setHint("Search player");
                        break;
                    case 1:
                        tv_search.setHint("Search team");
                        break;
                    case 2:
                        tv_search.setHint("Search tournament");
                        break;

                    default:
                        tv_search.setHint("Search");
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
                default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }
}