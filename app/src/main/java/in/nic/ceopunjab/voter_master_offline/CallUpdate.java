package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;


/**
 * Created by admin on 05/11/2017.
 *
 */

public class CallUpdate extends AsyncTask<String,String,String> {

    private  String Result="";
    private Activity activity;
    private ProgressDialog progressDoalog ;
    private StringBuilder str;
    private int rowcount;
    CallUpdate(Activity a)
    {
        this.activity = a;
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
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(activity.getApplicationContext(), "Data Exported sucessfully. Total rows exported =" + (rowcount-1), Toast.LENGTH_LONG).show();
        progressDoalog.dismiss();

    }
    @Override
    protected String doInBackground(String... params) {

    String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    String OPERATION_NAME = "ExpUP";
    String SOAP_ACTION = "http://tempuri.org/ExpUP";
    String SOAP_ADDRESS = new GlobalData().getSOAP(); //"http://117.239.84.132/web/Service1.asmx";
     //String SOAP_ADDRESS = "http://220.158.164.252/app/Service1.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = false;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        PropertyInfo pi= new PropertyInfo();
        try {
            //Result = obj1.getProperty(0).toString();
            //DBQuery db=new DBQuery(activity);
                Cursor c;
                SQLiteDatabase db=null;
                try {

                    DBHelper dbHelper=new DBHelper(activity);
                    db = dbHelper.getReadableDatabase();
                    String qrey="SELECT ifnull([EPIC],'') as EPIC" +
                            "      ,ifnull([VCast],'') as Vcast" +
                            "      ,ifnull([VMobile],'') as VMobile" +
                            "      ,ifnull([VType],'') as VType" +
                            "      ,ifnull([Profession],'') as Profession" +
                            "      ,ifnull([vdob],'') as vdob" +
                            "      ,ifnull([vdom],'') as vdom" +
                            "      ,ifnull([vReligion],'') as vReligion" +
                            "      ,ifnull([vHabitation],'')as vHabitation" +
                            "      ,ifnull([vEco],'') as vEco" +
                            "      ,ifnull([vOrigin],'') as vOrigin" +
                            "      ,ifnull([vRemark],'') as vRemark" +
                            "      ,ifnull([vMember],'') as vMember" +
                            "      ,[user_date]"  +
                            "  FROM tblVoterMaster where length(user_date) >0";

                    c = db.rawQuery(qrey, null);
                    rowcount = 0;
                    int colcount = 0;
                    rowcount = c.getCount();
                    colcount = c.getColumnCount();
                    str = new StringBuilder();

                    for (int i = 0; i < rowcount; i++)
                    {
                        c.moveToPosition(i);
                        str.append("update tblVoterMaster set VCast ='");
                        str.append(c.getString(1));
                        str.append("',VMobile ='");
                        str.append(c.getString(2));
                        str.append("',Vtype ='");
                        str.append(c.getString(3));

                        str.append("',Profession ='");
                        str.append(c.getString(4));
                        str.append("',vdob ='");
                        str.append(c.getString(5));
                        str.append("',vdom ='");
                        str.append(c.getString(6));
                        str.append("',vReligion ='");
                        str.append(c.getString(7));
                        str.append("',vHabitation ='");
                        str.append(c.getString(8));
                        str.append("',vEco ='");
                        str.append(c.getString(9));
                        str.append("',vOrigin ='");
                        str.append(c.getString(10));
                        str.append("',vRemark ='");
                        str.append(c.getString(11));
                        str.append("',vMember ='");
                        str.append(c.getString(12));
                        str.append("',user_date ='");
                        str.append(c.getString(13));
                        str.append("' where epic like '");
                        str.append(c.getString(0));
                        str.append("'");
                        if (i != rowcount - 1)
                            str.append(";");


                        Log.d("str=",str.toString());
                    }
                    //request.addProperty("data",str.toString());
                    pi.setName("data");
                    pi.setValue(str.toString());
                    pi.setType(String.class);
                    request.addProperty(pi);

                    httpTransport = new HttpTransportSE(SOAP_ADDRESS);
                    httpTransport.setTimeout(60000);
                    httpTransport.call(SOAP_ACTION, envelope);
                    SoapObject  obj1 =  (SoapObject)envelope.bodyIn;

                }
                catch (Exception ignored)
                {
                     Log.e("DBquery " + ignored.getStackTrace()[0].getLineNumber(),ignored.toString() ); }


        } catch (Exception e) { Log.d ( "exp ctch: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString()); }

        return "1";
    }


}
