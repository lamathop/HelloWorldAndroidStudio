package fr.eni.android.helloworldandroidstudio.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.eni.android.helloworldandroidstudio.entity.user.SQLConnection;

public abstract class DAL {

    protected SQLiteDatabase bdd;
    protected int versionNumber;
    protected String bddName;
    protected String tableName;

    protected void initTable(Context context)
    {
        SQLConnection connection;
        connection = new SQLConnection(context, bddName, null, versionNumber,this);
        bdd = connection.getWritableDatabase();
    }

    public void finalise()
    {
        bdd.close();
    }

    public abstract void createTable(SQLiteDatabase db);
    public abstract void alterTable(SQLiteDatabase db, int oldVersion, int newVersion);

}
