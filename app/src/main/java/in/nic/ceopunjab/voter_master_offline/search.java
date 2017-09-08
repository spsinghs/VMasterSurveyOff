package in.nic.ceopunjab.voter_master_offline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class search extends AppCompatActivity implements AdapterView.OnItemClickListener {
private EditText Ename,Efname,Ehno,Eepic;
private Spinner Sac;

    private LinearLayout ly;
    private int chkv;
    private ListView lv1;
    private Button btnLogin;
    private static String ac1[],name,fname,hn,epic;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        finish();
        //System.exit(0);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        if (chkv==1)
            new CallSerch(search.this).execute(ac1[0],name, fname, hn, epic,"1");
             super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    try
    {
        ListView gv;
        setContentView(R.layout.activity_search);
        lv1 = (ListView) findViewById(R.id.Listv);
        lv1.setVisibility(View.INVISIBLE);

        Ename = (EditText) findViewById(R.id.editName);
        Efname = (EditText) findViewById(R.id.editFather);
        Ehno = (EditText) findViewById(R.id.edithno);
        Eepic = (EditText) findViewById(R.id.editEPIC);
        Sac = (Spinner) findViewById(R.id.spnac);
        ly = (LinearLayout) findViewById(R.id.Stitle);


        Button btnLogin = (Button) findViewById(R.id.searchVoter);
        gv = (ListView) findViewById(R.id.Listv);

        gv.setOnItemClickListener(this);
        fillspn();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ac = Sac.getSelectedItem().toString();
                ac1 = ac.split("-");
                name = Ename.getText().toString();
                fname = Efname.getText().toString();
                hn = Ehno.getText().toString();
                epic = Eepic.getText().toString();

                String res = "";
                try {
                    if (chkv == 1) {

                        new CallSerch(search.this).execute(ac1[0], name, fname, hn, epic, "1");
                        ly.setVisibility(View.VISIBLE);
                        lv1.setVisibility(View.VISIBLE);
                    } else
                        Toast.makeText(search.this, "Please Choose Assembly Consistency.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.d("SOAP ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString());
                }
            }
        });

        Sac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    chkv = 0;
                } else
                    chkv = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }catch (Exception e) {
            Log.d("Search Activity", e.toString());
        }
    }
    public void fillspn()
    {
        Spinner s1 = (Spinner) findViewById(R.id.spnac);
        String Res = "";
        try {
            new CallSelect(search.this).execute("1").get();
        }    catch (Exception e) {
            Log.d("SOAP ", "Search");
        }

//        String[] spn1 = Res.split("\n");
//        List<String> slist = new ArrayList<String>(Arrays.asList(spn1));
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, slist);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s1.setAdapter(adapter);


    }
    public void hidegrid()
    {
        lv1.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {

            customTableAdapter.ViewHolder holder= (customTableAdapter.ViewHolder) view.getTag();
            Intent PartIntent = new Intent(search.this, VoterUpdateActivity.class);
            PartIntent.putExtra("EPIC", holder.Epic.getText().toString());
            PartIntent.putExtra("Name", holder.Name.getText().toString());  //Partname
            PartIntent.putExtra("Fname", holder.Fname.getText().toString());
            PartIntent.putExtra("Hno", holder.Hno.getText().toString());
            PartIntent.putExtra("Mobile", holder.mobile.getText().toString());
            PartIntent.putExtra("Cast", holder.cast.getText().toString());
            PartIntent.putExtra("Dob", holder.dob.getText().toString());
            PartIntent.putExtra("Dom", holder.dom.getText().toString());
            PartIntent.putExtra("RelTy", holder.RelTy.getText().toString());
            PartIntent.putExtra("AC", ac1[0]);

            PartIntent.putExtra("Relg",holder.TRelg.getText().toString() );
            PartIntent.putExtra("Habit",holder.THabit.getText().toString() );
            PartIntent.putExtra("Type",holder.Tvtype.getText().toString() );
            PartIntent.putExtra("Eco",holder.Teco.getText().toString() );
            PartIntent.putExtra("Org",holder.Torg.getText().toString() );
            PartIntent.putExtra("Rem",holder.TRem.getText().toString() );
            PartIntent.putExtra("Prof",holder.Tprof.getText().toString() );

            //PartIntent.putExtra("Fname", holder.getText().toString());
            startActivity(PartIntent);

        } catch (Exception e) {
            Log.d("CATCH", e.toString());
        }
    }
}
