package ahmedpro.com.asbabelnzolv1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ahmedpro.com.asbabelnzolv1.Model.Data;

/**
 * Created by hp on 02/07/2017.
 */

public class DataSource {
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = {dbHelper.COLUMN_ID,
            dbHelper.COLUMN_Title};

    public DataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addData(Data data) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_Title, data.getTitel());
        database.insert(dbHelper.TABLE_Name, null, values);
    }

    public void deleteCard(String title) {

        database.delete(dbHelper.TABLE_Name, dbHelper.COLUMN_Title + " = ?",
                new String[]{title});
    }

    public List<Data> getAllCards() {
        List<Data> cards = new ArrayList();

        Cursor cursor = database.query(dbHelper.TABLE_Name,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Data data = cursorToCard(cursor);
            cards.add(data);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return cards;
    }

    private Data cursorToCard(Cursor cursor) {
        Data data = new Data();
        data.setId(cursor.getInt(0));
        data.setTitel(cursor.getString(1));
        return data;
    }
}
