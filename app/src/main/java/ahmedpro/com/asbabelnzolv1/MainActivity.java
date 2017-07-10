package ahmedpro.com.asbabelnzolv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Locale;

import ahmedpro.com.asbabelnzolv1.Adapter.ListViewAdapter;
import ahmedpro.com.asbabelnzolv1.Model.Data;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    EditText searchText;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = (EditText) findViewById(R.id.searchEt);
        lv = (ListView) findViewById(R.id.lv);
        //suppress the soft-keyboard until the user actually touches the editText View
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchText.setCursorVisible(true);
                if (searchText.getText().length() == 0) {
                    searchText.setCursorVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });

        adapter = new ListViewAdapter(MainActivity.this, Data.getList());
        lv.setAdapter(adapter);

    }

}
