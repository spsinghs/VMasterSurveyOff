package in.nic.ceopunjab.voter_master_offline;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.format.Time;


/**
 * A login screen
 */
public class LoginActivity extends AppCompatActivity  {
    // UI references.
    private EditText Uname;
    private EditText Upass;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
            finish();
            System.exit(1);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_login);
            // Set up the login form.
            Uname = (EditText) findViewById(R.id.email);
            //  populateAutoComplete();
            Upass = (EditText) findViewById(R.id.password);


            Button btnLogin = (Button) findViewById(R.id.email_sign_in_button);
           // Button btnSignup = (Button) findViewById(R.id.signup);
            if(Prefs.getApplicationVersionCode(this) < AppVersionCode.getApkVersionCode(this))
                checkApplicationVersionCode();



        //______________________________________________________________________________________________

            btnLogin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    String usr = Uname.getText().toString();
                    String pas = Upass.getText().toString();

                    String res = "";
                    try {
                        if (usr.equals("super") && pas.equals("sup@12")) {
                            Intent user = new Intent(LoginActivity.this, SuperActivity.class);
                            startActivity(user);

                        } else {
                            if (usr.equals("") || pas.equals(""))return;
                            Time now = new Time();
                            now.setToNow();
                            String sTime = now.format("%Y_%m_%d_T:%H_%M_%S");
                            res = new CallService(LoginActivity.this).execute(usr, pas, sTime).get();
                            if (res.equals("1")) {
                                final GlobalData global = (GlobalData) getApplicationContext();
                                global.setName(usr);
                                Intent Search = new Intent(LoginActivity.this, search.class);
                                startActivity(Search);
                                Toast.makeText(LoginActivity.this, "You are Sign in sucessfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Sorry not a Authenticated User", Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (Exception e) {
                        Log.d("SOAP ", e.getStackTrace()[0].getLineNumber() +" "  + e.toString());
                    }

                }
            });

          /*  btnSignup.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    Intent user = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(user);
                    Toast.makeText(LoginActivity.this, "Signup a New user", Toast.LENGTH_LONG).show();
                }
            });*/

        }catch (Exception e){
            Log.d("Login Activity ", e.toString());
        }
    }
    void checkApplicationVersionCode() {
        switch(Prefs.getApplicationVersionCode(this)) {
            /**********   for the very first time only   **********/
            /**********   things that we need only once  **********/
            case PDefaultValue.VERSION_CODE:
                DBQuery db = new DBQuery(this);
                db.createDatabase();
                db.close();
                //put this apk version code in Shared Preference
                Prefs.putApplicationVersionCode(this, AppVersionCode.getApkVersionCode(this));
                //recall this method so that doWorkOnLatestVersion() can be called
                checkApplicationVersionCode();
                break;
            //**********************************************************//
            default:
                //do this for every version
                break;
        }
    }







    }

