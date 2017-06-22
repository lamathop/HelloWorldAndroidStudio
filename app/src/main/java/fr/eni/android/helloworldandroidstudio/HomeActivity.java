package fr.eni.android.helloworldandroidstudio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import fr.eni.android.helloworldandroidstudio.entity.user.*;

import fr.eni.android.helloworldandroidstudio.entity.user.BO_User;

public class HomeActivity extends Activity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = (ListView) findViewById(R.id.listUsers);

        refreshUserList();
    }

    private void refreshUserList()
    {
        DAL_User user = new DAL_User(this);
        UserAdapter adapter = null;

        List<BO_User> userList = user.getAllUsers();
        if ( userList != null ) {
            adapter = new UserAdapter(this, userList);
        }

        list.setAdapter(adapter);
    }

    public void dropUsers(View v)
    {
        DAL_User bdd = new DAL_User(this);

        bdd.removeAll();

        refreshUserList();
    }

    public void createUsers(View v)
    {
        DAL_User bdd = new DAL_User(this);
        bdd.insertUser(new BO_User("Hugues","ALLAIN",0));
        bdd.insertUser(new BO_User("Carole","MOREAU",0));

        refreshUserList();
    }

}
