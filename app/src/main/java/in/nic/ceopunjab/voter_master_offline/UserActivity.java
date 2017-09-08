package in.nic.ceopunjab.voter_master_offline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class UserActivity extends AppCompatActivity {
EditText Uname,Uid,Upass,Uepic,Udob,Uadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            setContentView(R.layout.activity_user);
            Uname = (EditText) findViewById(R.id.unam);
            Uid = (EditText) findViewById(R.id.uid);
            Upass = (EditText) findViewById(R.id.upas);
            Uadd = (EditText) findViewById(R.id.uaddress);
            Uepic = (EditText) findViewById(R.id.uepic);
            Udob = (EditText) findViewById(R.id.uDob);

            Button btnReg = (Button) findViewById(R.id.btnRegister);
            btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String usr = Uname.getText().toString().trim();
                    String uid = Uid.getText().toString().trim();
                    String pas = Upass.getText().toString().trim();
                    String add=Uadd.getText().toString().trim();
                    String epic=Uepic.getText().toString().trim();
                    String dob=Udob.getText().toString().trim();

                    String res = "";

                    if (usr.isEmpty())
                    {
                        Uname.requestFocus();
                        Uname.setError("Please Enter User Name");
                        return;
                    }
                    if(add.isEmpty()||add.length()<10)
                    {
                        Uadd.requestFocus();
                        Uadd.setError("Please Fill valid Mobile Number");
                        return;
                    }
                    if (epic.isEmpty())
                    {
                        Uepic.requestFocus();
                        Uepic.setError("Please Enter EPIC/Voter No. ");
                        return;
                    }
                    if (uid.isEmpty())
                    {
                        Uid.requestFocus();
                        Uid.setError("Please Enter UserId");
                        return;
                    }
                    if (pas.isEmpty())
                    {
                        Upass.requestFocus();
                        Upass.setError("Please Enter password");
                        return;
                    }


                    try {
                        res = new CallUser(UserActivity.this).execute("1",usr, uid, pas,add,epic,dob).get();
                    } catch (Exception e) {
                        Log.d("SOAP ", res);
                        return;
                    }

                    //CallSop cs=new CallSop();
                    if (res.equals("2")) {
                        Toast.makeText(UserActivity.this, "User already registered. Choose another Userid", Toast.LENGTH_LONG).show();

                    } else if (res.equals("1")) {
                        Intent login = new Intent(UserActivity.this, LoginActivity.class);
                        Toast.makeText(UserActivity.this, "User registered sucessfully", Toast.LENGTH_LONG).show();
                        startActivity(login);
                    } else
                        Toast.makeText(UserActivity.this, "User not registered try again ", Toast.LENGTH_LONG).show();

                }
            });
        }
        catch (Exception e) {
            Log.d("User Activity", e.toString());
        }
    }

}
