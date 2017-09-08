package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SuperActivity extends AppCompatActivity {
Spinner spnAc,spnBt,spnBt2;
    Button btnImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);
        SuperActivity s1;
        spnAc = (Spinner) findViewById(R.id.spnac);
        spnBt= (Spinner) findViewById(R.id.spnBooth);
        spnBt2= (Spinner) findViewById(R.id.spnBooth2);
        Button btnClr=(Button) findViewById(R.id.btnClear);
        Button btnExp=(Button) findViewById(R.id.btnSave);
        btnImp=(Button) findViewById(R.id.btnimport);

        fillSpinner();
       //filldetails();
        spnAc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (spnAc.getSelectedItemPosition()!=0 )
                {
                    String[] ac=spnAc.getSelectedItem().toString().split("-");
                    fillBooth(ac[0]);
                }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

       btnExp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //exportDB();
                  new CallUpdate(SuperActivity.this).execute();

                /*DBQuery db= new DBQuery(SuperActivity.this.getApplication());
                 db.open();
                 db.Export();*/
                   // new CallExport(SuperActivity.this).execute("6");
               /*  try {

                     DBQuery db=new DBQuery(SuperActivity.this.getApplicationContext());
                     db.open();
                     String  R=db.exportXLS();
                     if (R.equals("1"))
                     {
                         Toast.makeText(SuperActivity.this.getApplicationContext(), "\"Backup.csv\" exported sucessfully.", Toast.LENGTH_LONG).show();
                     }
                        //exportDB();

                 }
                 catch (Exception e) {
                     Log.d("SuperActivity ", e.toString());

                 }*/
             }

         });
        btnImp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogevent(view);
            }
        });

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogevent2(view);
            }
        });

    }
    private void filldetails()
    {
        DBQuery db= new DBQuery(SuperActivity.this.getApplication());
        TextView t1= (TextView) (findViewById(R.id.textDetails));
        db.open();
        t1.setText(db.getDetails().toString());

    }
    private void fillSpinner()
    {
        String Res = "";
        try {
            new CallSelect1(SuperActivity.this).execute("1").get();
            /*String[] ac = Res.split("\n");
        // String[] users=CallSelect.s1.toString().split("\n");
            List<String> slist0 = new ArrayList<String>(Arrays.asList(ac));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, slist0);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnAc.setAdapter(adapter);*/
        }
        catch (Exception e) {
            Log.d("SOAP ", "Super Activity" + Res);
        }
    }
    private void fillBooth(String ac) {
        String Res = "";

        try {
            Res = new CallSelect1(SuperActivity.this).execute("4-"+ac).get();
        } catch (Exception e) {
            Log.d("SOAP ", "Super Activity" + Res);
        }
        String[] users=Res.split("\n");
        // String[] users=CallSelect.s1.toString().split("\n");
        List<String> slist0 = new ArrayList<String>(Arrays.asList(users));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,slist0);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBt.setAdapter(adapter);
        spnBt2.setAdapter(adapter);
    }
    public void dialogevent2(View view){


        AlertDialog.Builder altdial = new AlertDialog.Builder(SuperActivity.this);
        altdial.setMessage("Choose  Yes to clear database").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBQuery db1=new DBQuery(SuperActivity.this);
                        db1.Delete();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = altdial.create();
        alert.setTitle("Clear Database");
        alert.show();
    }

    public void dialogevent(View view){


                AlertDialog.Builder altdial = new AlertDialog.Builder(SuperActivity.this);
                altdial.setMessage("Choose Yes to Import database").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // String encodedData = \\ Read from file
                                // byte[] myBlobData = encoder.decode(encodedData);

                                if (spnAc.getSelectedItemPosition() ==0 || spnBt.getSelectedItemPosition()==0 || spnBt2.getSelectedItemPosition()==0)
                                {
                                    Toast.makeText(SuperActivity.this.getApplicationContext(), "Choose Assembly and Booth no.", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                String[] ac=spnAc.getSelectedItem().toString().split("-");
                                String bth=spnBt.getSelectedItem().toString();
                                String bth2=spnBt2.getSelectedItem().toString();
                                bth="5-"+ac[0]+"-"+ bth+ "-"+ bth2;
                                try {
                                    new CallSelect1(SuperActivity.this).execute(bth);
                                    //new CallSelect1(SuperActivity.this).execute("4-"+ac).get();
                                }
                                catch (Exception e)
                                {

                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               /* DBQuery db1=new DBQuery(SuperActivity.this);
                                db1.Delete();
                                Toast.makeText(SuperActivity.this.getApplicationContext(), "Database removed sucessfully.", Toast.LENGTH_LONG).show();
*/
                                /*if (spnAc.getSelectedItemPosition() ==0 || spnBt.getSelectedItemPosition()==0)
                                {
                                    Toast.makeText(SuperActivity.this.getApplicationContext(), "Choose Assembly and Booth no.", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                String[] ac=spnAc.getSelectedItem().toString().split("-");
                                String bth=spnBt.getSelectedItem().toString();
                                bth="6-"+ac[0]+"-"+ bth;
                                try {
                                    new CallSelect1(SuperActivity.this).execute(bth);
                                    //new CallSelect1(SuperActivity.this).execute("4-"+ac).get();

                                }
                                catch (Exception e)
                                {

                                }*/
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = altdial.create();
                alert.setTitle("Import Database");
                alert.show();
        }



    private void exportDB(){
        File sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
       // File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = this.getDatabasePath("ElectionSolution_40.db").getPath();
        //"/data/"+ "in.nic.ceopunjab.voter_master_offline" +"/databases/"+SAMPLE_DB_NAME;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date())    ;
        String backupDBPath = "Backup-"+ format + ".db";
        File currentDB = new File(currentDBPath);
        File backupDB = new File(sd,backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, backupDBPath + " Exported sucessfully in Downloads", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            Toast.makeText(this, backupDBPath + " Error:: Db not exported! ", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
