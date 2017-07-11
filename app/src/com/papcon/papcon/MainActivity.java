package com.papcon.papcon;

import android.os.Bundle;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.pager)
    ViewPager pager;

    private MyPagerAdapter adapter;
    private Drawable oldBackground = null;
    private int currentColor;
    private SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // create our manager instance after the content view is set
        mTintManager = new SystemBarTintManager(this);
        // enable status bar tint
        mTintManager.setStatusBarTintEnabled(true);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(1);
        changeColor(ContextCompat.getColor(getBaseContext(), R.color.light_black));

        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this, "등록하기", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "추천보기", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "환경설정", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
//
    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //    switch (item.getItemId()) {
    //        case R.id.action_contact:
    //            QuickContactFragment.newInstance().show(getSupportFragmentManager(), "QuickContactFragment");
    //            return true;
    //    }
    //    return super.onOptionsItemSelected(item);
    //}

    private void changeColor(int newColor) {
        tabs.setBackgroundColor(newColor);
        mTintManager.setTintColor(newColor);
        // change ActionBar color just if an ActionBar is available
        Drawable colorDrawable = new ColorDrawable(newColor);
        Drawable bottomDrawable = new ColorDrawable(ContextCompat.getColor(getBaseContext(), android.R.color.transparent));
        LayerDrawable ld = new LayerDrawable(new Drawable[]{colorDrawable, bottomDrawable});
        if (oldBackground == null) {
            getSupportActionBar().setBackgroundDrawable(ld);
        } else {
            TransitionDrawable td = new TransitionDrawable(new Drawable[]{oldBackground, ld});
            getSupportActionBar().setBackgroundDrawable(td);
            td.startTransition(200);
        }

        oldBackground = ld;
        currentColor = newColor;
    }

    //public void onColorClicked(View v) {
    //    int color = Color.parseColor(v.getTag().toString());
    //    changeColor(color);
    //}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentColor", currentColor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentColor = savedInstanceState.getInt("currentColor");
        changeColor(currentColor);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"등록하기", "추천보기", "환경설정"};

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }
    }

    //class BackgroundTask extends AsyncTask<Void, Void, String> {
    //    String target;
//
    //    @Override
    //    protected void onPreExecute() {
    //        target = "http://dbals1630.cafe24.com/List.php";
    //    }
//
    //    @Override
    //    protected String doInBackground(Void... params) {
    //        try{
    //            URL url = new URL(target);
    //            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    //            InputStream inputStream = httpURLConnection.getInputStream();
    //            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    //            String temp;
    //            StringBuilder stringBuilder = new StringBuilder();
    //            while((temp = bufferedReader.readLine()) != null){
    //                stringBuilder.append(temp + "\n");
    //            }
    //            bufferedReader.close();
    //            inputStream.close();
    //            httpURLConnection.disconnect();
    //            return stringBuilder.toString().trim();
    //        }
    //        catch(Exception e){
    //            e.printStackTrace();
    //        }
    //        return null;
    //    }
//
    //    @Override
    //    public void onProgressUpdate(Void... values) {
    //        super.onProgressUpdate(values);
    //    }
//
    //    @Override
    //    public void onPostExecute(String result) {
    //        Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
    //        intent.putExtra("userList", result);
    //        MainActivity.this.startActivity(intent);
    //        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    //    }
    //}
}
