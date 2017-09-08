package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


/**
 * Created by admin on 05/11/2017.
 *
 */

public class CallExport extends AsyncTask<String,String,String> {

    private  String Result="";
    private Activity activity;
    private ProgressDialog progressDoalog ;

    CallExport(Activity a)
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
        progressDoalog.dismiss();

    }
    @Override
    protected String doInBackground(String... params) {

    String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    String OPERATION_NAME = "Exp";
    String SOAP_ACTION = "http://tempuri.org/Exp";
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
                    String qrey="SELECT [LS_NO]" +
                            "      ,ifnull([AC_NO],'') as AC_NO" +
                            "      ,ifnull([Ward_NO],'')" +
                            "      ,ifnull([Booth_NO],'')" +
                            "      ,ifnull([SECTION_NO],'')" +
                            "      ,ifnull([VoterNo],'')" +
                            "      ,ifnull([EPIC],'')" +
                            "      ,ifnull([VoterName],'')" +
                            "      ,ifnull([FH_Name],'')" +
                            "      ,ifnull([HOUSE_NO],'')" +
                            "      ,ifnull([GENDER],'')" +
                            "      ,ifnull([AGE],'')" +
                           "      ,ifnull([DOB],'')" +
                            "      ,ifnull([VCast],'')" +
                            "      ,ifnull([VMobile],'')" +
                            "      ,ifnull([VType],'')" +
                            "      ,ifnull([Profession],'')" +
                            "      ,ifnull([PerResi],'')" +
                            "      ,ifnull([IsWorker],'')" +
                            "      ,ifnull([IsFamilyHead],'')" +
                            "      ,ifnull([RefBy],'')" +
                            "      ,ifnull([Remarks],'')" +
                            "      ,ifnull([Photo],'') as Photo" +  //23
                            "      ,ifnull([HNo_New],'')" +
                            "      ,ifnull([GaliBlock_New],'')" +
                            "      ,ifnull([SecVillCol_New],'')" +
                            "      ,ifnull([OtherInformation_New],'')" +
                            "      ,ifnull([AreaID],'')" +
                            "      ,ifnull([VoterName_H],'')" +
                            "      ,ifnull([FH_Name_H],'')" +
                            "      ,ifnull([House_No_H],'')" +
                            "      ,ifnull([RelationType],'')" +
                            "      ,ifnull([Status_Type],'')" +
                            "      ,ifnull([PrintYN],'')" +
                            "      ,ifnull([Order_By],'')" +
                            "      ,ifnull([vdob],'')" +
                            "      ,ifnull([vdom],'')" +
                            "      ,ifnull([vphoto],'') as vphoto" +  //38
                            "      ,ifnull([vReligion],'')" +
                            "      ,ifnull([vHabitation],'')" +
                            "      ,ifnull([vEco],'')" +
                            "      ,ifnull([vOrigin],'')" +
                            "      ,ifnull([vRemark],'')" +
                            "      ,ifnull([vMember],'')" +
                            "      ,ifnull([user_date],'')" +
                            "      ,ifnull([_id],'')" +
                            "  FROM [tblVoterMaster]";

                    c = db.rawQuery(qrey, null);
                    int rowcount = 0;
                    int colcount = 0;
                    rowcount = c.getCount();
                    colcount = c.getColumnCount();

                    for (int i = 0; i < rowcount; i++)
                    {
                        c.moveToPosition(i);
                        String[] str =new String[colcount];

                        for (int j = 0; j < colcount; j++)
                        {
                            if (j != colcount - 1)
                            {
                                if (j == 22 || j == 37)
                                {
                                    //  Log.d("col=", c.getColumnName(j));
                                    byte[] myBlobData = c.getBlob(j);
                                    if (myBlobData != null)
                                    {
                                        //String encodedData = Base64.encodeToString(myBlobData, Base64.DEFAULT);
                                        // str.append(encodedData);
                                        str[j] = "";
                                    }
                                    else
                                        str[j] = "";
                                }
                                else
                                str[j]=c.getString(j);

                            }
                            else
                            {
                                str[j]=c.getString(j);


                                for(int k=0;k<colcount;k++) {
                                request.addProperty("_"+(Integer.toString(k+1)),str[k]);
                            }

                                httpTransport = new HttpTransportSE(SOAP_ADDRESS);
                                httpTransport.setTimeout(60000);
                                httpTransport.call(SOAP_ACTION, envelope);
                                SoapObject  obj1 =  (SoapObject)envelope.bodyIn;
                                request=null;
                                request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
   envelope.setOutputSoapObject(request);

                                httpTransport.reset();
                                httpTransport=null;


                               // HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
                            }
                        }

                        //bw.newLine(); Cal
                    }
                }
                catch (Exception ignored)
                {
                     Log.e("DBquery " + ignored.getStackTrace()[0].getLineNumber(),ignored.toString() ); }


        } catch (Exception e) { Log.d ( "exp ctch: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString()); }

        return "";
    }


}
