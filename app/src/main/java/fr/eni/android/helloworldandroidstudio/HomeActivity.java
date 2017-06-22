package fr.eni.android.helloworldandroidstudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import fr.eni.android.helloworldandroidstudio.entity.user.*;

import fr.eni.android.helloworldandroidstudio.entity.user.BO_User;

public class HomeActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = (ListView) findViewById(R.id.listUsers);

        refreshUserList();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;

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

    public void dropUsers(MenuItem item)
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
