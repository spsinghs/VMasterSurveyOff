package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;*/



/**
 * Created by admin on 05/23/2017.
 */


public class CallSelect  extends AsyncTask<String,String,String>
{
    private ProgressDialog progressDoalog;
    private String par="";
    private String res="";
    private Activity activity;
    public static StringBuilder s1,s0,s2,s3,s4,s5;

    CallSelect(Activity a)
    {
        activity = a;
        progressDoalog = new ProgressDialog(this.activity);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDoalog.setTitle("Processing...");
        progressDoalog.setMessage("Please wait.");
        progressDoalog.setCancelable(false);
        progressDoalog.setIndeterminate(true);
        progressDoalog.show();
        s0 = new StringBuilder();s2 = new StringBuilder();s3 = new StringBuilder();s4 = new StringBuilder();s5 = new StringBuilder();
        s1=new StringBuilder();
    }

    @Override
    protected void onPostExecute(String s) {

         if (par.startsWith("1"))
        {
            String[] ac = res.split("\n");
            // String[] users=CallSelect.s1.toString().split("\n");
            List<String> slist0 = new ArrayList<String>(Arrays.asList(ac));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, slist0);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spn=(Spinner) activity.findViewById(R.id.spnac);
            spn.setAdapter(adapter);
        }
          progressDoalog.dismiss();
    }


    @Override
    protected String doInBackground(String... params) {
    /*
                 s1 = new StringBuilder();
                if (params[0].equals("1")) {
                    s1.append("--Choose Assembly Consistency--" + "\n");
                    for (int i = 0; i < obj2.getPropertyCount(); i++) {
                        obj3 = (SoapObject) obj2.getProperty(i);
                        s1.append(obj3.getProperty("AC").toString().replace("anyType{}", ""));
                        s1.append("\n");
                    }
                }
                else if (params[0].equals("2")) {
                    s0 = new StringBuilder();s2 = new StringBuilder();s3 = new StringBuilder();s4 = new StringBuilder();s5 = new StringBuilder();
                    s0.append("--Choose Voter Eco Status--" + "\n");
                    s2.append("--Choose Voter Habitation--" + "\n");
                    s3.append("--Choose Voter Religion--" + "\n");
                    s4.append("--Choose Voter Cast--" + "\n");
                    s5.append("--Choose Voter Type--" + "\n");

                    for (int i = 0; i < obj2.getPropertyCount(); i++) {
                        obj3 = (SoapObject) obj2.getProperty(i);
                        if (obj3.getProperty("DDType").toString().replace("anyType{}", "").toLowerCase().equals("eco status")) {
                            s0.append(obj3.getProperty("DDValue").toString().replace("anyType{}", ""));
                            s0.append("\n");
                        } else if (obj3.getProperty("DDType").toString().replace("anyType{}", "").toLowerCase().equals("habitation")) {
                            s2.append(obj3.getProperty("DDValue").toString().replace("anyType{}", ""));
                            s2.append("\n");
                        } else if (obj3.getProperty("DDType").toString().replace("anyType{}", "").toLowerCase().equals("voter religion")) {
                            s3.append(obj3.getProperty("DDValue").toString().replace("anyType{}", ""));
                            s3.append("\n");
                        }
                        else if (obj3.getProperty("DDType").toString().replace("anyType{}", "").toLowerCase().equals("voter cast")) {
                            s4.append(obj3.getProperty("DDValue").toString().replace("anyType{}", ""));
                            s4.append("\n");
                        }
                        else if (obj3.getProperty("DDType").toString().replace("anyType{}", "").toLowerCase().equals("voter type")) {
                            s5.append(obj3.getProperty("DDValue").toString().replace("anyType{}", ""));
                            s5.append("\n");
                        }
                    }
                    s1.append(s0);
                    s1.append(s2);
                    s1.append(s3);
                    s1.append(s4);
                    s1.append(s5);
                }
                else if (params[0].equals("3"))
                {
                    s1.append("--Choose User--" + "\n");
                    for (int i = 0; i < obj2.getPropertyCount(); i++) {
                        obj3 = (SoapObject) obj2.getProperty(i);
                        s1.append(obj3.getProperty("user").toString().replace("anyType{}", ""));
                        s1.append("\n");
                    }
                }


            }  catch (Exception e) { Log.d ( "CallSelect: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString()); }
           */
        String qry1 = "";

        DBQuery db = new DBQuery(activity);
        db.open();
        par=params[0];

        if (params[0].equals("1")) {
            qry1 = "select cast(ac_no as char) || \"-\" || ac_name as 'AC' FROM [tblACMaster]";
            res=db.getAC(qry1);
        } else if (params[0].equals("2")) {
            qry1 = "select DDType,DDValue from [tblDDItem]";
            res=db.getSpinner(qry1);
        }
        return res;
    }
    }


