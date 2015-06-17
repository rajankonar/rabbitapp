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
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class activity_login extends ActionBarActivity {
    protected TextView mSignupTextView;
    protected EditText muname;
    protected EditText mpwd;
    protected Button mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_activity_login);

        mSignupTextView = (TextView)findViewById(R.id.link_signup);
        mSignupTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(activity_login.this,signup.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Your Message", Toast.LENGTH_LONG).show();
            }
        });
        muname = (EditText)findViewById(R.id.txt_username_login);
        mpwd = (EditText)findViewById(R.id.txt_password_login);
        mlogin = (Button)findViewById(R.id.bttn_login);

        mlogin.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String uname = muname.getText().toString();
                String pwd = mpwd.getText().toString();

                uname = uname.trim();
                pwd = pwd.trim();

                if(uname.isEmpty() || pwd.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity_login.this);
                    builder.setMessage("Please make sure you enter a username and password!").setTitle("opps!").setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else {
                    //setProgressBarIndeterminateVisibility(true);
                    ParseUser.logInInBackground(uname, pwd, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            //setProgressBarIndeterminateVisibility(false);
                            if(e==null){
                                //success
                                Intent intent = new Intent(activity_login.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity_login.this);
                                builder.setMessage(e.getMessage()).setTitle("oops!").setPositiveButton(android.R.string.ok,null);
                                AlertDialog dialog =builder.create();
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
        getMenuInflater().inflate(R.menu.menu_activity_login, menu);
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
