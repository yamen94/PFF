package com.gebril.yamen.pff.activities.activity;


import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gebril.yamen.pff.activities.adapters.mPagerAdapter;

import com.gebril.yamen.pff.R;

public class OnBoarding extends AppCompatActivity implements View.OnClickListener {

    private  ViewPager vPager;
    private int[] layouts =
            {R.layout.player_slide,R.layout.team_slide,R.layout.referee_slide,R.layout.tournament,R.layout.lets_start};

   private mPagerAdapter mPagerAdapter;
    private LinearLayout Dots_Layout;
    private ImageView[] dots;
    private Button skipButton , nextButton;
    private static final String logCheck = "CHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        vPager = (ViewPager) findViewById(R.id.viewPager);
        Dots_Layout = (LinearLayout) findViewById(R.id.dotsLayout);
        mPagerAdapter = new mPagerAdapter(layouts,this);
        skipButton = (Button) findViewById(R.id.btnSkip);
        nextButton = (Button) findViewById(R.id.btnNext);

        skipButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

       vPager.setAdapter(mPagerAdapter);



       createDots(0);

       vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
                 createDots(position);
                 if (position == layouts.length -1)
                 {
                     skipButton.setVisibility(View.INVISIBLE);
                     nextButton.setText(R.string.start);
                 }else
                 {
                     skipButton.setVisibility(View.VISIBLE);
                     nextButton.setText(R.string.next);
                 }
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

    }

    private void createDots(int current_position)
    {
        if (Dots_Layout != null)
        {
            Dots_Layout.removeAllViews();
        }

        dots = new ImageView[layouts.length];

        for (int i = 0; i < layouts.length ; i++)
        {
            dots[i] = new ImageView(this);
            if ( i == current_position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
            }else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);

            Dots_Layout.addView(dots[i] , params);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnNext:

                nextSlide();
                break;

            case R.id.btnSkip:
                nextActivity();
                break;

            default:
                break;
        }

    }

    private void nextActivity()
    {
        Log.i(logCheck , "going to next Activity");
       // startActivity(new Intent(this,));
       // finish();
    }

    private void nextSlide()
    {
        int next_slide = vPager.getCurrentItem() + 1;

        if(next_slide < layouts.length)
        {
            vPager.setCurrentItem(next_slide);
        }else
        {
            nextActivity();
        }
    }
}
