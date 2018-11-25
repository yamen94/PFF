package com.gebril.yamen.pff.activities.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.fragments.HomeFragment;
import com.gebril.yamen.pff.activities.fragments.InvitationFragment;
import com.gebril.yamen.pff.activities.fragments.LeaderBoardFragment;
import com.gebril.yamen.pff.activities.fragments.PurchaseFragment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.util.Objects;

public class MainActivity extends AppCompatActivity   {

    FrameLayout mainFrame;
    BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private InvitationFragment invFragment;
    private LeaderBoardFragment boardFragment;
    private PurchaseFragment purchaseFragment;
    private boolean doubleBackToExitPressedOnce;
    private Toolbar toolbar;
    private FloatingActionMenu fam;
    private FloatingActionButton fab_tournament;
    private FloatingActionButton fab_team;
    private ImageView iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_main);
         toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        initFragment(homeFragment);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setLogoAnimation();
    }



    private void init()
    {

        mainFrame =  findViewById(R.id.main_frame);
        bottomNavigationView =  findViewById(R.id.navigation);
        homeFragment = new HomeFragment();
        invFragment = new InvitationFragment();
        boardFragment = new LeaderBoardFragment();
        purchaseFragment = new PurchaseFragment();
        doubleBackToExitPressedOnce = false;
        fab_team =  findViewById(R.id.fab_team);
        fab_tournament =  findViewById(R.id.fab_tournament);
        fam =  findViewById(R.id.fab_menu);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i("check",Integer.toString(item.getItemId()));
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        setFragment(homeFragment , homeFragment.getFRAGMENT_TAG());
                        return true;

                    case R.id.nav_inv:
                        setFragment(invFragment , invFragment.getFRAGMENT_TAG());
                        return true;

                    case R.id.nav_board:
                        setFragment(boardFragment , boardFragment.getFRAGMENT_TAG());
                        return true;

                    case R.id.nav_purchase:
                        setFragment(purchaseFragment , purchaseFragment.getFRAGMENT_TAG());
                        return true;

                    default:
                        return false;

                }
            }
        });
        Log.i("check", "inside");

      /* fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    //do something
                } else {
                }
            }
        }); */

        fab_tournament.setOnClickListener(onButtonClick());
        fab_team.setOnClickListener(onButtonClick());
        fam.setClosedOnTouchOutside(true);

   /*     fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });*/


        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_bio) {
                    // Handle the bio action
                } else if (id == R.id.nav_my_teams) {

                } else if (id == R.id.nav_my_items) {

                } else if (id == R.id.nav_block) {

                } else if (id == R.id.nav_settings) {

                } else if (id == R.id.nav_logout) {

                }
                else if (id == R.id.nav_help) {

                }

                DrawerLayout drawer =  findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    private void setLogoAnimation()
    {
        iv_logo = findViewById(R.id.imageLogo);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv_logo.setTransitionName("imageTransition");

            iv_logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent SharedIntent = new Intent(MainActivity.this,AboutActivity.class);

//                    Pair[] pairs = new Pair[1];
  //                  pairs[0] = new Pair<View, String>(iv_logo , "imageTransition");
                    ActivityOptions options;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                new Pair<View, String>(iv_logo , "imageTransition"));
                        startActivity(SharedIntent, options.toBundle());
                    } else{
                        startActivity(SharedIntent);
                    }
                }
            });
        }

    }

    private void initFragment(HomeFragment homeFragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_frame , homeFragment , homeFragment.getFRAGMENT_TAG());
        fragmentTransaction.commit();
    }




    private void setFragment(Fragment fragment , String TAG) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();


        Fragment storedFragment = fragmentManager.findFragmentByTag(TAG);

        if (storedFragment == null)
        {
            storedFragment = fragment;
        }
        fragmentTransaction.replace(R.id.main_frame , storedFragment , TAG);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }

    //used for the bottom navigation and the fab
    @Override
    public void onBackPressed() {

        String currentMenuItem = bottomNavigationView.getMenu().findItem(bottomNavigationView.getSelectedItemId()).getTitle().toString();

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce || Objects.equals(currentMenuItem, "Home")) {
                finish();
                super.onBackPressed();
            } else {
                setFragment(homeFragment, homeFragment.getFRAGMENT_TAG());
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
            }

            this.doubleBackToExitPressedOnce = true;

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    //used for the fab on click


    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fab_team) {
                    //go to create team page
                } else if (view == fab_tournament) {
                    //go to create tournament page
                }
                fam.close(true);
            }
        };
    }




    public void goToSearch(View view) {

            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
    }
}
