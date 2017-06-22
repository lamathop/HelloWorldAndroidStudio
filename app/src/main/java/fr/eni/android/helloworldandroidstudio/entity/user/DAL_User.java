package fr.eni.android.helloworldandroidstudio.entity.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import fr.eni.android.helloworldandroidstudio.entity.*;

import java.util.ArrayList;

public class DAL_User extends DAL
{
    private static String colId = "ID";
    private static String colFirstName = "FIRSTNAME";
    private static String colLastName = "LASTNAME";

    public DAL_User(Context context)
    {
        versionNumber   = 1;
        bddName         = "user.db";
        tableName       = "USERS";

        initTable(context);
    }

    public void createTable(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + tableName + " (" +
                colId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                colFirstName + " TEXT, " +
                colLastName + " TEXT);");
    }

    public void alterTable(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion < 2) {
            bdd.execSQL("ALTER TABLE "+tableName+" ADD COLUMN PHOTO");
        }
        if (oldVersion < 3) {
            bdd.execSQL("ALTER TABLE "+tableName+" ADD COLUMN NICKNAME");
        }
    }

    public void removeAll()
    {
        bdd.execSQL("DELETE FROM "+tableName);
    }

    private ContentValues BOToContent(BO_User user)
    {
        ContentValues content = new ContentValues();
        content.put(colFirstName, user.firstName);
        content.put(colLastName, user.lastName);
        return content;
    }

    public long insertUser(BO_User user)
    {
        return bdd.insert(tableName,null,BOToContent(user));
    }

    public long updateUser(BO_User user)
    {
        return bdd.update(tableName,BOToContent(user),colId+" = "+user.id,null);
    }

    public int deleteUser(int id)
    {
        return bdd.delete(tableName,colId+" = "+id,null);
    }

    public BO_User cursorToUser(Cursor c)
    {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }

        BO_User user = new BO_User();
        user.id         = c.getInt(c.getColumnIndex(colId));
        user.firstName  = c.getString(c.getColumnIndex(colFirstName));
        user.lastName   = c.getString(c.getColumnIndex(colLastName));

        c.close();

        return user;
    }

    public ArrayList<BO_User> getAllUsers()
    {
        ArrayList<BO_User> userList = new ArrayList<>();
        Cursor c = bdd.query(tableName,new String[]{colId,colFirstName,colLastName},null,null,null,null,colFirstName);

        if (c.getCount() == 0)
        {
            c.close();
            return null;
        }

        while ( c.moveToNext() )
        {
            BO_User user = new BO_User();

            user.id         = c.getInt(c.getColumnIndex(colId));
            user.firstName  = c.getString(c.getColumnIndex(colFirstName));
            user.lastName   = c.getString(c.getColumnIndex(colLastName));

            userList.add(user);
        }

        return userList;
    }

}
