package myapp.rabbit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class signup extends ActionBarActivity {
    protected EditText muname;
    protected EditText mpwd;
    protected EditText memail;
    protected Button mbttn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_signup);
        muname = (EditText)findViewById(R.id.txt_username_login);
        mpwd = (EditText)findViewById(R.id.txt_password_login);
        memail =(EditText)findViewById(R.id.txt_email);
        mbttn_signup =(Button)findViewById(R.id.bttn_signup);
        mbttn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = muname.getText().toString();
                String password = mpwd.getText().toString();
                String email = memail.getText().toString();

                username = username.trim();
                password = password.trim();
                email = email.trim();
                if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(signup.this);
                    builder.setMessage("Please make sure you enter a username, password and email address!").setTitle("oops!").setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    //setSupportProgressBarIndeterminateVisibility(true);
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            //setSupportProgressBarIndeterminateVisibility(false);
                            if( e == null){
                                //success
                                Intent intent = new Intent(signup.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(signup.this);
                                builder.setMessage(e.getMessage()).setTitle("oops!").setPositiveButton(android.R.string.ok,null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            }

                    });
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
