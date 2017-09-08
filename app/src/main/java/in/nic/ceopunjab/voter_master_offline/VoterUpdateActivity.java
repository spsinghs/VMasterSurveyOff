package in.nic.ceopunjab.voter_master_offline;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class VoterUpdateActivity extends AppCompatActivity {

    private EditText mobile,org,rem,prof;
    private Spinner Scast,Srel,Shab,Seco,Svtype,Smemb;
    private String epic1;
    private in.nic.ceopunjab.voter_master_offline.DatePickerView db,dm;
   // private final String epic1="";
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
        finish();
        //System.exit(1);
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_voter_update);

            fillspinner();
            Initalize(getIntent().getExtras().getString("Name"),getIntent().getExtras().getString("Fname"),getIntent().getExtras().getString("RelTy"),getIntent().getExtras().getString("EPIC"),getIntent().getExtras().getString("Dob"),getIntent().getExtras().getString("Dom"),getIntent().getExtras().getString("Org"),getIntent().getExtras().getString("Rem"),getIntent().getExtras().getString("Prof"),getIntent().getExtras().getString("Mobile"),getIntent().getExtras().getString("Cast"),getIntent().getExtras().getString("Relg"),getIntent().getExtras().getString("Habit"),getIntent().getExtras().getString("Type"),getIntent().getExtras().getString("Eco") );
            epic1 = getIntent().getExtras().getString("EPIC");
            String r = "";
            try {
                r = new CallPic(VoterUpdateActivity.this).execute(epic1).get();
            } catch (Exception e) {
                Log.d("SOAP ", r);
            }
            Spinner Spnfam= (Spinner) findViewById(R.id.spnFamily);

            Spnfam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
             @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    if (position==0) return;

                     String selectedItem = parent.getItemAtPosition(position).toString();
                    String e[]=selectedItem.split("-");
                    try {
                        String r = new CallSerch(VoterUpdateActivity.this).execute("", "", "", "", e[1], "2").get();
                    }
                    catch (Exception e1) {
                        Log.d("VoterUpdate ", "vo");
                    }


                    String s[] = CallSerch.stringBuilder.toString().split("\n");
                    epic1=e[1];
                    Initalize(s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7],s[8],s[9],s[10],s[11],s[12],s[13],s[14]);
                    String r1="";
                    try {
                        r1 = new CallPic(VoterUpdateActivity.this).execute(epic1).get();
                    } catch (Exception e1) {
                        Log.d("VoterUpdate ", r1);
                    }

                  //  Log.d("",CallSerch.CustomListView.toString());

                    /*    customTableAdapter.ViewHolder.Name.getText().toString();
                    customTableAdapter holder =new customTableAdapter(this, CustomListView);

                  //  customTableAdapter.ViewHolder holder= (customTableAdapter.ViewHolder) view.getTag();
                    String epc=holder.Epic.getText().toString();
                    String nam= holder.Name.getText().toString();
                    String Fnam=holder.Fname.getText().toString();
                    String Rel=holder.RelTy.getText().toString();
                    String hno= holder.Hno.getText().toString();
                    String mob = holder.mobile.getText().toString();
                    //String cast= holder.cast.getText().toString();
                    String dob =holder.dob.getText().toString();
                    String dom= holder.dom.getText().toString();
                    //PartIntent.putExtra("RelTy", holder.RelTy.getText().toString());
                    //String relg =holder.TRelg.getText().toString() ;
                    //String habit =holder.THabit.getText().toString();
                    //String type=holder.Tvtype.getText().toString() ;
                    //String Eco =holder.Teco.getText().toString() ;
                    String Org=holder.Torg.getText().toString();
                    String Rem =holder.TRem.getText().toString() ;
                    String Prof=holder.Tprof.getText().toString() ;
                    Initalize(nam,Fnam,Rel,epc,dob,dom,Org,Rem,Prof,mob,holder.cast.getText().toString(),holder.TRelg.getText().toString(),holder.THabit.getText().toString(),holder.Tvtype.getText().toString(),holder.Teco.getText().toString()  );
*/
                     }
                     // to close the onItemSelected
              @Override
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });

            Button btnLogin = (Button) findViewById(R.id.btnUp);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String res = "";
                    try {
                        String cast = "", relg = "", habit = "", vtype = "", eco = "", memb = "";
                        if (Scast.getSelectedItemPosition() > 0) {
                            cast = Scast.getSelectedItem().toString();
                        }
                        if (Srel.getSelectedItemPosition() > 0) {
                            relg = Srel.getSelectedItem().toString();
                        }
                        if (Shab.getSelectedItemPosition() > 0) {
                            habit = Shab.getSelectedItem().toString();
                        }
                        if (Svtype.getSelectedItemPosition() > 0) {
                            vtype = Svtype.getSelectedItem().toString();
                        }
                        if (Seco.getSelectedItemPosition() > 0) {
                            eco = Seco.getSelectedItem().toString();
                        }

                        if (Smemb.getSelectedItemPosition() > 0) {
                            memb = Smemb.getSelectedItem().toString();
                        }
                        final GlobalData global = (GlobalData) getApplicationContext();
                        String stamp=global.getName();
                        stamp= stamp + " " + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());

                        res = new CallSop(VoterUpdateActivity.this).execute(epic1, mobile.getText().toString(), cast, db.getText().toString(), dm.getText().toString(), relg, habit, eco, org.getText().toString(), rem.getText().toString(), vtype, prof.getText().toString(), memb,stamp).get();
                        if (res.equals("1"))
                            Toast.makeText(VoterUpdateActivity.this, "Record Updated", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Log.d("SOAP ", e.getStackTrace()[0].getLineNumber() +" "  + e.toString());
                    }
                }
            });

            Button btnBack = (Button) findViewById(R.id.btnBk);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent PartIntent = new Intent(VoterUpdateActivity.this, search.class);
                    //startActivity(PartIntent);
                    VoterUpdateActivity.super.onBackPressed();
                }
            });
        }
        catch (Exception e) {
            Log.d("Update Activity",e.getStackTrace()[0].getLineNumber() +" "  + e.toString());
        }
    }
    public void spinnerSet(Spinner s1,String val)
    {
        s1.setSelection(0);
        for(int i = 0; i < s1.getCount(); i++)
        {
            if(s1.getItemAtPosition(i).toString().equals(val))
            {
                s1.setSelection(i);
                break;
            }
        }

    }
    public void Initalize(String name1,String fname1,String Rel,String epic1,String dob1,String dom1,String org1,String rem1,String prof1,String mobile1,String cast1,String relg1,String habit1,String type1,String eco1)
    {
        TextView epic;


        db = (in.nic.ceopunjab.voter_master_offline.DatePickerView) findViewById(R.id.sDob);
        dm = (in.nic.ceopunjab.voter_master_offline.DatePickerView) findViewById(R.id.sDom);
        epic = (TextView) findViewById(R.id.VEpic);
        TextView name = (TextView) findViewById(R.id.VName);
        TextView fname = (TextView) findViewById(R.id.VFname);
        mobile = (EditText) findViewById(R.id.Vmobile);
        org = (EditText) findViewById(R.id.Vorigin);
        rem = (EditText) findViewById(R.id.vRemark);
        prof = (EditText) findViewById(R.id.vProf);


        epic.setText("EPIC/Voter ID: " + epic1);
        name.setText("Voter Name: " + name1);
        if(Rel.equals("F"))
            fname.setText("Father's Name: " + fname1);
        else if (Rel.equals("H"))
            fname.setText("Husband's Name: " + fname1);
        else
            fname.setText("Rel Name: " + fname1);

        db.setText(dob1);
        dm.setText(dom1);
        org.setText(org1);
        rem.setText(rem1);
        prof.setText(prof1);
        mobile.setText(mobile1);

        spinnerSet(Scast, cast1 );
        spinnerSet(Srel, relg1 );
        spinnerSet(Shab, habit1 );
        spinnerSet(Svtype, type1 );
        spinnerSet(Seco, eco1 );
    }
    public void fillspinner()
    {
        String Res="";

        Srel = (Spinner) findViewById(R.id.spnRelg);
        Shab = (Spinner) findViewById(R.id.spnHab);
        Svtype = (Spinner) findViewById(R.id.spnVtype);
        Seco = (Spinner) findViewById(R.id.spnVEco);
        Smemb = (Spinner) findViewById(R.id.spnVMemb);
        Scast = (Spinner) findViewById(R.id.spnCast);

        try
        {
            Res  = new CallSelect(VoterUpdateActivity.this).execute("2").get();
        }  catch (Exception e) { Log.d("SOAP ", "Voter Update" + Res);     }

        String[] eco=CallSelect.s0.toString().split("\n");
        String[] hab=CallSelect.s2.toString().split("\n");
        String[] rel=CallSelect.s3.toString().split("\n");
        String[] cst=CallSelect.s4.toString().split("\n");
        String[] vt=CallSelect.s5.toString().split("\n");


        //populate an arraylist with array values

        List<String> slist0 = new ArrayList<String>(Arrays.asList(eco));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,slist0);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Seco.setAdapter(adapter);

        List<String> slist1 = new ArrayList<String>(Arrays.asList(hab));
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,slist1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Shab.setAdapter(adapter1);
        //Scast,Srel,Shab,Seco,Svtype;

        List<String> slist2 = new ArrayList<String>(Arrays.asList(rel));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,slist2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Srel.setAdapter(adapter2);

        List<String> slist3 = new ArrayList<String>(Arrays.asList(cst));
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,slist3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Scast.setAdapter(adapter3);

        List<String> slist4 = new ArrayList<String>(Arrays.asList(vt));
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,slist4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Svtype.setAdapter(adapter4);

    }
}
