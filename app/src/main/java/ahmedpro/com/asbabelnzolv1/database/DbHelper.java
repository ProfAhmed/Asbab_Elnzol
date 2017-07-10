package ahmedpro.com.asbabelnzolv1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 02/07/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_Name = "data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_Title = "title";
    private static final String DATABASE_NAME = "DATA.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_Name + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_Title
            + " string);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
