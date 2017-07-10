package ahmedpro.com.asbabelnzolv1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ahmedpro.com.asbabelnzolv1.Adapter.MyAdapter;
import ahmedpro.com.asbabelnzolv1.Model.Data;
import ahmedpro.com.asbabelnzolv1.database.DataSource;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    MyAdapter mAdapter;
    private boolean flag = true;
    DataSource dataSource = new DataSource(this);
    List<Data> datas = new ArrayList<>();
    final List<String> s = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        dataSource.open();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertapstrip);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.WHITE);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mAdapter);
        String title = getIntent().getStringExtra("pos");
        final int pos = mAdapter.getPosition(title);
        mViewPager.setCurrentItem(pos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        final MenuItem item = menu.findItem(R.id.fav);
        datas = dataSource.getAllCards();
        for (int i = 0; i < datas.size(); i++) {
            s.add(datas.get(i).getTitel());
        }
        if (s.contains(mAdapter.getPageTitle(mViewPager.getCurrentItem()))) {
            item.setIcon(R.drawable.ic_favorite_black);
            flag = false;
        } else {
            item.setIcon(R.drawable.ic_favorite_border_black_);
            flag = true;
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                datas = dataSource.getAllCards();
                for (int i = 0; i < datas.size(); i++) {
                    s.add(datas.get(i).getTitel());
                }
                if (s.contains(mAdapter.getPageTitle(mViewPager.getCurrentItem()))) {
                    item.setIcon(R.drawable.ic_favorite_black);
                    flag = false;
                } else {

                    item.setIcon(R.drawable.ic_favorite_border_black_);
                    flag = true;
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fav:
                if (flag) {
                    item.setIcon(R.drawable.ic_favorite_black);
                    Toast.makeText(this, " اضافه " + mAdapter.getPageTitle(mViewPager.getCurrentItem()) + " الى المفضله ", Toast.LENGTH_SHORT).show();
                    Data data = new Data();
                    data.setTitel((String) mAdapter.getPageTitle(mViewPager.getCurrentItem()));
                    dataSource.addData(data);
                    flag = false;
                } else {
                    item.setIcon(R.drawable.ic_favorite_border_black_);
                    Toast.makeText(this, " حذف " + mAdapter.getPageTitle(mViewPager.getCurrentItem()) + " من المفضله ", Toast.LENGTH_SHORT).show();
                    dataSource.deleteCard((String) mAdapter.getPageTitle(mViewPager.getCurrentItem()));
                    flag = true;
                }

                break;
            case R.id.مفضله:
                startActivity(new Intent(this, Favorit.class));
                break;
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));

            default:

        }
        return (super.onOptionsItemSelected(item));
    }


    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }
}
