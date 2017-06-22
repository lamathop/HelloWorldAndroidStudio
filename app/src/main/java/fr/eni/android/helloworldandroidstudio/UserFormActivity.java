package fr.eni.android.helloworldandroidstudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import fr.eni.android.helloworldandroidstudio.entity.user.BO_User;
import fr.eni.android.helloworldandroidstudio.entity.user.DAL_User;

public class UserFormActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private BO_User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userform);

        Intent intent = getIntent();
        user = (BO_User) intent.getExtras().getSerializable("user");

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);

        firstName.setText(user.firstName);
        lastName.setText(user.lastName);

        if( user.id > 0) {
            setTitle(R.string.titleUserUpdate);
        } else {
            setTitle(R.string.titleUserCreate);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_userform, menu);
        return true;
    }

    public void saveUser(MenuItem item)
    {
        DAL_User bdd = new DAL_User(this);

        user.firstName  = firstName.getText().toString();
        user.lastName   = lastName.getText().toString();

        if( user.id > 0) {
            bdd.updateUser(user);
        } else {
            bdd.insertUser(user);
        }

        closeActivity(item);
    }

    public void closeActivity(MenuItem item)
    {
        finish();
    }

    public void deleteUser(MenuItem item)
    {
        DAL_User bdd = new DAL_User(this);
        bdd.deleteUser(user.id);
        closeActivity(item);
    }

}
