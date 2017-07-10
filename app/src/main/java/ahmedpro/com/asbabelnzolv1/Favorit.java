package ahmedpro.com.asbabelnzolv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ahmedpro.com.asbabelnzolv1.Model.Data;
import ahmedpro.com.asbabelnzolv1.database.DataSource;

public class Favorit extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<Data> adapter;
    DataSource dataSource = new DataSource(this);
    List<Data> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        dataSource.open();
        datas = dataSource.getAllCards();
        lv = (ListView) findViewById(R.id.favoritLV);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Favorit.this, ViewPagerActivity.class);
                // Pass all data rank
                intent.putExtra("pos", adapter.getItem(position).getTitel());
                Toast.makeText(Favorit.this, adapter.getItem(position).getTitel(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
        }
        return (super.onOptionsItemSelected(item));
    }
}
