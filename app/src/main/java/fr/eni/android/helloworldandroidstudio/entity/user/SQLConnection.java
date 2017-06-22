package fr.eni.android.helloworldandroidstudio.entity.user;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import fr.eni.android.helloworldandroidstudio.entity.DAL;

public class SQLConnection extends SQLiteOpenHelper
{

    private String createQuery;
    private String tableName;
    private DAL dal;


    public SQLConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DAL dal)
    {
        super(context, name, factory, version);
        this.dal = dal;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        dal.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        dal.alterTable(db,oldVersion,newVersion);
    }

}
