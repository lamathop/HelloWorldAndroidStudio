package fr.eni.android.helloworldandroidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import fr.eni.android.helloworldandroidstudio.entity.user.*;

public class HomeActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = (ListView) findViewById(R.id.listUsers);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View v,int position, long id)
            {
                viewUser((BO_User) adapter.getItemAtPosition(position));
            }

        });

        refreshUserList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void onResume()
    {
        super.onResume();
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

    public void dropUsers(MenuItem item)
    {
        DAL_User bdd = new DAL_User(this);
        bdd.removeAll();
        refreshUserList();
    }

    public void createUsers(View v)
    {
        Intent intent = new Intent(HomeActivity.this,UserFormActivity.class);
        intent.putExtra("user",new BO_User());
        startActivity(intent);
    }

    public void viewUser(BO_User user)
    {
        Intent intent = new Intent(HomeActivity.this,UserFormActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

}
