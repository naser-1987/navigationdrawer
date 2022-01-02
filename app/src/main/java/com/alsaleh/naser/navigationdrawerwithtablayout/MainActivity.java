package com.alsaleh.naser.navigationdrawerwithtablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, AddExperiencefragment.DialogFragmentNewExper,ThirdFragment.contactActivityListener {
    ThirdFragment contact;
    SecondFragment experience_new;
    private ViewPager2 pager;
    public ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    protected   ArrayList<Fragment> fragments = new ArrayList<>();
    protected DrawerLayout dlayout;
    protected View  navigationView;
    protected Toolbar toolbar;

    protected ArrayList<Gatogery> categories = new ArrayList<>();
    protected RecyclerView options_menu;
    protected int [] icons =
            {R.drawable.ic_home, R.drawable.ic_list, R.drawable.ic_contact_phone,
                    R.drawable.ic_email };
    SecondFragment experience;
    ArrayList<String> experiences_data= new ArrayList<>();
    protected String [] titles ={"Home", "Experiences", "Contact", "Help"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // access to photo with camera //

        setContentView(R.layout.activity_main);
        // Toast.makeText(this, experiences_data.size()+"main", Toast.LENGTH_LONG).show();
        Intent info = this.getIntent();

        toolbar= findViewById(R.id.content_toolbar);
        setupActionBar();
        dlayout = findViewById(R.id.drawer_layout);
        navigationView =findViewById(R.id.drawer_part);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,dlayout,toolbar,R.string.open,R.string.close);
        dlayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        pager = this.findViewById(R.id.content_viewpager2);
        tabLayout = this.findViewById(R.id.content_tablayout);
        options_menu = findViewById(R.id.drawer_listview);
        ListAdapter listAdapter = new ListAdapter(titles, name -> {
            int position= -1;
            for(int i = 0; i<titles.length;i++){
                if(titles[i].equalsIgnoreCase(name))
                    position =i;
            }
            pager.setCurrentItem(position);
            dlayout.closeDrawer(GravityCompat.START);
        });

        options_menu.setAdapter(listAdapter);
        options_menu.setLayoutManager(new LinearLayoutManager(this));
        for(int i= 0; i< 4;i++){

            String title = titles[i];

            tabLayout.addTab(tabLayout.newTab().setText(title).setIcon(icons[i]));
        }

        FirstFragment home_frag=FirstFragment.newInstance(  info.getStringExtra(MainActivity2.FNAME),
                                                            info.getStringExtra(MainActivity2.LNAME),
                                                            info.getStringExtra(MainActivity2.JOB_TITLE),
                                                            info.getStringExtra(MainActivity2.IMAGE_URI),
                                                            info.getIntExtra(MainActivity2.FNAME,0));
        fragments.add(home_frag);
        experience = SecondFragment.newInstance(experiences_data);
        fragments.add(experience);
        contact= ThirdFragment.newInstance(null, -1);
        fragments.add(contact);
        fragments.add(new FourthFragment());

        setupViewPager();
        tabLayout.addOnTabSelectedListener(this);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position,0f,true);
            }
        });
    }

    //setup custom toolbar
    public void setupActionBar(){
        this.setSupportActionBar(toolbar);
        ActionBar actionbar = this.getSupportActionBar();
        assert actionbar != null;
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_headline);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);
    }



    @Override
    public void onDialogFragmentNewExper(String exper) {
        // to add new Experience to second fragment to experiences list
        experiences_data.add(exper);
        experience_new = SecondFragment.newInstance(experiences_data);
        onUpdateExperiencesAdapter();
    }
    public void onUpdateExperiencesAdapter(){
        fragments.set(1,experience_new);
        setupViewPager();
        pager.setCurrentItem(1);
    }
    public void onUpdateContact(){
        fragments.set(2,contact);
        setupViewPager();
        pager.setCurrentItem(2);
    }

    // setup View Pager
    public void setupViewPager(){
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        pager.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        int list_size = experiences_data.size();
        outState.putInt("size",list_size);
        for(int i = 0 ; i<list_size;i++)
            outState.putString(i+"",experiences_data.get(i));
    }



    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int list_size= savedInstanceState.getInt("size",-1);
        if(list_size != -1){
            for(int i = 0 ; i<list_size;i++)
            experiences_data.add(savedInstanceState.getString(i+""));
            onUpdateExperiencesAdapter();
        }
    }

    //  start tab  listener methods //

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
        if(dlayout.isDrawerVisible(GravityCompat.START)){
            dlayout.closeDrawer(GravityCompat.START);
        }
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }
    @Override
    public void onTabReselected(TabLayout.Tab tab) { }



    //  end  tab  listener methods //


    // start interface from third fragment contact //
    @Override
    public void onClickContactInfos(String[] infos,int x) {
        contact =ThirdFragment.newInstance(infos, x);
        onUpdateContact();
    }
    @Override
    public void onClickContactInfosEdit(String[] infos,int x) {
        contact =ThirdFragment.newInstance(infos, x);
        onUpdateContact();
    }
    // end interface from third fragment contact //
}

