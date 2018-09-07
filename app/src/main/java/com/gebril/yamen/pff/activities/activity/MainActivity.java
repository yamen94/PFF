package com.gebril.yamen.pff.activities.activity;

import android.os.Handler;
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
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

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
    private FloatingActionButton fab_match, fab_tournament, fab_team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        initFragment(homeFragment);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    private void init()
    {

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        homeFragment = new HomeFragment();
        invFragment = new InvitationFragment();
        boardFragment = new LeaderBoardFragment();
        purchaseFragment = new PurchaseFragment();
        doubleBackToExitPressedOnce = false;
        fab_team = (FloatingActionButton) findViewById(R.id.fab_team);
        fab_tournament = (FloatingActionButton) findViewById(R.id.fab_tournament);
        fab_match = (FloatingActionButton) findViewById(R.id.fab_match);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);


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

       fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                } else {
                }
            }
        });

        fab_tournament.setOnClickListener(onButtonClick());
        fab_match.setOnClickListener(onButtonClick());
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_camera) {
                    // Handle the camera action
                } else if (id == R.id.nav_gallery) {

                } else if (id == R.id.nav_slideshow) {

                } else if (id == R.id.nav_manage) {

                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                } else if (view == fab_tournament) {
                } else {
                }
                fam.close(true);
            }
        };
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
