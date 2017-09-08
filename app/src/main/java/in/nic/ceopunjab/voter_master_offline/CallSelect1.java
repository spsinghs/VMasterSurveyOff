
package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by admin on 05/23/2017.
 */


public class CallSelect1  extends AsyncTask<String,String,String>
{
    private ProgressDialog progressDoalog;

    private Activity activity;
    private String par="";
    public static StringBuilder s1,s0,s2,s3,s4,s5;

    CallSelect1(Activity a)
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
    }

    @Override
    protected void onPostExecute(String s) {
        if (par.startsWith("5"))
            Toast.makeText(activity.getApplicationContext(), s1.toString() +" Rows Imported sucessfully.", Toast.LENGTH_LONG).show();
        else if (par.startsWith("1"))
        {
            String[] ac = s1.toString().split("\n");
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
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String OPERATION_NAME = "GetSpinner";
        String SOAP_ACTION = "http://tempuri.org/GetSpinner";
        String SOAP_ADDRESS =new GlobalData().getSOAP(); //"http://117.239.84.132/web/Service1.asmx";
        // String SOAP_ADDRESS = "http://220.158.164.252/app/Service1.asmx";
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        PropertyInfo pi = new PropertyInfo();
        pi.setName("qry");
        pi.setValue(params[0]);
        pi.setType(String.class);
        request.addProperty(pi);
        par=params[0];
        try {
            httpTransport.call(SOAP_ACTION, envelope);
            SoapObject obj1 = (SoapObject) envelope.getResponse();
            SoapObject obj11 = (SoapObject) obj1.getProperty(1);
            SoapObject obj2 = (SoapObject) obj11.getProperty(0);
            SoapObject obj3;

            s1 = new StringBuilder();
            if (params[0].equals("1") )
            {
                s1.append("--Choose Assembly Consistency--" + "\n");
                for (int i = 0; i < obj2.getPropertyCount(); i++) {
                    obj3 = (SoapObject) obj2.getProperty(i);
                    s1.append(obj3.getProperty("AC").toString().replace("anyType{}", ""));
                    s1.append("\n");
                }
            }
            else if (params[0].equals("2"))
            {
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
            else if (params[0].startsWith("4"))
            {
                s1.append("--Choose Booth / Part No. --" + "\n");
                for (int i = 0; i < obj2.getPropertyCount(); i++) {
                    obj3 = (SoapObject) obj2.getProperty(i);
                    s1.append(obj3.getProperty("Booth_no").toString().replace("anyType{}", ""));
                    s1.append("\n");
                }
            }
            else if (params[0].startsWith("5")) {

              //  DBQuery db1=new DBQuery(activity);
               // db1.Delete();
                s1 = new StringBuilder();
                s1.append(obj2.getPropertyCount());
                for (int i = 0; i < obj2.getPropertyCount(); i++)
                {
                    obj3 = (SoapObject) obj2.getProperty(i);
                   // String Photo=obj3.getProperty("Photo").toString().replace("anyType{}", "");
                    //byte[] decodedString = Base64.decode(Photo, Base64.DEFAULT);
                    ContentValues cv = new  ContentValues();
                    cv.put("LS_NO", obj3.getProperty("LS_NO").toString().replace("anyType{}", ""));
                    cv.put("AC_NO", obj3.getProperty("AC_NO").toString().replace("anyType{}", ""));
                    cv.put("Ward_No", obj3.getProperty("Ward_No").toString().replace("anyType{}", ""));
                    cv.put("Booth_No", obj3.getProperty("Booth_No").toString().replace("anyType{}", ""));
                    cv.put("Section_no", obj3.getProperty("Section_no").toString().replace("anyType{}", ""));
                    cv.put("VoterNo", obj3.getProperty("VoterNo").toString().replace("anyType{}", ""));
                    cv.put("EPIC", obj3.getProperty("EPIC").toString().replace("anyType{}", ""));
                    cv.put("VoterName", obj3.getProperty("VoterName").toString().replace("anyType{}", ""));
                    cv.put("FH_Name", obj3.getProperty("FH_Name").toString().replace("anyType{}", ""));
                    cv.put("HOUSE_NO", obj3.getProperty("HOUSE_NO").toString().replace("anyType{}", ""));
                    cv.put("GENDER", obj3.getProperty("GENDER").toString().replace("anyType{}", ""));
                    cv.put("DOB", obj3.getProperty("DOB").toString().replace("anyType{}", ""));
                    cv.put("VCast", obj3.getProperty("VCast").toString().replace("anyType{}", ""));
                    cv.put("VMobile", obj3.getProperty("VMobile").toString().replace("anyType{}", ""));
                    cv.put("VType", obj3.getProperty("VType").toString().replace("anyType{}", ""));
                    cv.put("Profession", obj3.getProperty("Profession").toString().replace("anyType{}", ""));
                    cv.put("PerResi", obj3.getProperty("PerResi").toString().replace("anyType{}", ""));
                    cv.put("IsWorker", obj3.getProperty("IsWorker").toString().replace("anyType{}", ""));
                    cv.put("IsFamilyHead", obj3.getProperty("IsFamilyHead").toString().replace("anyType{}", ""));
                    cv.put("RefBy", obj3.getProperty("RefBy").toString().replace("anyType{}", ""));
                    cv.put("Remarks", obj3.getProperty("Remarks").toString().replace("anyType{}", ""));
                   // cv.put("Photo",decodedString);
                    cv.put("HNo_New", obj3.getProperty("HNo_New").toString().replace("anyType{}", ""));
                    cv.put("GaliBlock_New", obj3.getProperty("GaliBlock_New").toString().replace("anyType{}", ""));
                    cv.put("SecVillCol_New", obj3.getProperty("SecVillCol_New").toString().replace("anyType{}", ""));
                    cv.put("OtherInformation_New", obj3.getProperty("OtherInformation_New").toString().replace("anyType{}", ""));
                    cv.put("AreaID", obj3.getProperty("AreaID").toString().replace("anyType{}", ""));
                    cv.put("VoterName_H", obj3.getProperty("VoterName_H").toString().replace("anyType{}", ""));
                    cv.put("FH_Name_H", obj3.getProperty("FH_Name_H").toString().replace("anyType{}", ""));
                    cv.put("House_No_H", obj3.getProperty("House_No_H").toString().replace("anyType{}", ""));
                    cv.put("Status_Type", obj3.getProperty("Status_Type").toString().replace("anyType{}", ""));
                    cv.put("PrintYN", obj3.getProperty("PrintYN").toString().replace("anyType{}", ""));
                    cv.put("Order_By", obj3.getProperty("Order_By").toString().replace("anyType{}", ""));
                    cv.put("vdob", obj3.getProperty("vdob").toString().replace("anyType{}", ""));
                    cv.put("vdom", obj3.getProperty("vdom").toString().replace("anyType{}", ""));
                   // cv.put("vphoto", obj3.getProperty("vphoto").toString().replace("anyType{}", ""));
                    cv.put("vReligion", obj3.getProperty("vReligion").toString().replace("anyType{}", ""));
                    cv.put("vHabitation", obj3.getProperty("vHabitation").toString().replace("anyType{}", ""));
                    cv.put("vEco", obj3.getProperty("vEco").toString().replace("anyType{}", ""));
                    cv.put("vOrigin", obj3.getProperty("vOrigin").toString().replace("anyType{}", ""));
                    cv.put("vRemark", obj3.getProperty("vRemark").toString().replace("anyType{}", ""));
                    cv.put("vMember", obj3.getProperty("vMember").toString().replace("anyType{}", ""));
                    cv.put("user_date", obj3.getProperty("user_date").toString().replace("anyType{}", ""));
                    DBQuery db= new DBQuery(activity);
                    db.ImportData(cv);

                    //Import Data
//                String str1 = "INSERT INTO tblVoterMaster ([LS_NO]\n" +
//                        "           ,[AC_NO]\n" +
//                        "           ,[Ward_NO]\n" +
//                        "           ,[Booth_NO]\n" +
//                        "           ,[SECTION_NO]\n" +
//                        "           ,[VoterNo]\n" +
//                        "           ,[EPIC]\n" +
//                        "           ,[VoterName]\n" +
//                        "           ,[FH_Name]\n" +
//                        "           ,[HOUSE_NO]\n" +
//                        "           ,[GENDER]\n" +
//                        "           ,[AGE]\n" +
//                        "           ,[DOB]\n" +
//                        "           ,[VCast]\n" +
//                        "           ,[VMobile]\n" +
//                        "           ,[VType]\n" +
//                        "           ,[Profession]\n" +
//                        "           ,[PerResi]\n" +
//                        "           ,[IsWorker]\n" +
//                        "           ,[IsFamilyHead]\n" +
//                        "           ,[RefBy]\n" +
//                        "           ,[Remarks]\n" +
//                        "           ,[Photo]\n" +
//                        "           ,[HNo_New]\n" +
//                        "           ,[GaliBlock_New]\n" +
//                        "           ,[SecVillCol_New]\n" +
//                        "           ,[OtherInformation_New]\n" +
//                        "           ,[AreaID]\n" +
//                        "           ,[VoterName_H]\n" +
//                        "           ,[FH_Name_H]\n" +
//                        "           ,[House_No_H]\n" +
//                        "           ,[RelationType]\n" +
//                        "           ,[Status_Type]\n" +
//                        "           ,[PrintYN]\n" +
//                        "           ,[Order_By]\n" +
//                        "           ,[vdob]\n" +
//                        "           ,[vdom]\n" +
//                        "           ,[vphoto]\n" +
//                        "           ,[vReligion]\n" +
//                        "           ,[vHabitation]\n" +
//                        "           ,[vEco]\n" +
//                        "           ,[vOrigin]\n" +
//                        "           ,[vRemark]\n" +
//                        "           ,[vMember]\n" +
//                        "           ,[user_date]) values(";

   /*                 str1+=obj3.getProperty("LS_NO").toString().replace("anyType{}", "") +",";
                    str1+=obj3.getProperty("AC_NO").toString().replace("anyType{}", "") +",";
                    str1+=obj3.getProperty("Ward_No").toString().replace("anyType{}", "") +",";
                    str1+=obj3.getProperty("Booth_No").toString().replace("anyType{}", "") +",";
                    str1+=obj3.getProperty("Section_no").toString().replace("anyType{}", "") +",";
                    str1+=obj3.getProperty("VoterNo").toString().replace("anyType{}", "") +",";
                    str1+="'"+ obj3.getProperty("EPIC").toString().replace("anyType{}", "") +"'"+",";
                    str1+="'"+ obj3.getProperty("VoterName").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("FH_Name").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("HOUSE_NO").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("GENDER").toString().replace("anyType{}", "")  +"'"+",";
                    str1+=obj3.getProperty("AGE").toString().replace("anyType{}", "")  +",";
                    str1+="'"+obj3.getProperty("DOB").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("VCast").toString().replace("anyType{}", "") +"'" +",";
                    str1+="'"+obj3.getProperty("VMobile").toString().replace("anyType{}", "") +"'" +",";
                    str1+="'"+obj3.getProperty("VType").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("Profession").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("PerResi").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("IsWorker").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("IsFamilyHead").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("RefBy").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("Remarks").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("Photo").toString().replace("anyType{}", "") +"'" +",";
                    str1+="'"+obj3.getProperty("HNo_New").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("GaliBlock_New").toString().replace("anyType{}", "") +"'" +",";
                    str1+="'"+obj3.getProperty("SecVillCol_New").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("OtherInformation_New").toString().replace("anyType{}", "")  +"'"+",";
                    str1+=obj3.getProperty("AreaID").toString().replace("anyType{}", "") +",";
                    str1+="'"+obj3.getProperty("VoterName_H").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("FH_Name_H").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("House_No_H").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("RelationType").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("Status_Type").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("PrintYN").toString().replace("anyType{}", "")  +"'"+",";
                    str1+=obj3.getProperty("Order_By").toString().replace("anyType{}", "") +",";
                    str1+="'"+obj3.getProperty("vdob").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vdom").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vphoto").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vReligion").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vHabitation").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vEco").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vOrigin").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vRemark").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("vMember").toString().replace("anyType{}", "")  +"'"+",";
                    str1+="'"+obj3.getProperty("user_date").toString().replace("anyType{}", "")  +"'"+")";*/
                   // Log.d("str=", str1)  ;
                }
            }
            else if (params[0].startsWith("6")) {

                s1 = new StringBuilder();
                s1.append(obj2.getPropertyCount());
                for (int i = 0; i < obj2.getPropertyCount(); i++) {
                    obj3 = (SoapObject) obj2.getProperty(i);
                    String Photo = obj3.getProperty("Photo").toString().replace("anyType{}", "");
                    byte[] decodedString = Base64.decode(Photo, Base64.DEFAULT);
                    ContentValues cv = new ContentValues();
                    cv.put("LS_NO", obj3.getProperty("LS_NO").toString().replace("anyType{}", ""));
                    cv.put("AC_NO", obj3.getProperty("AC_NO").toString().replace("anyType{}", ""));
                    cv.put("Ward_No", obj3.getProperty("Ward_No").toString().replace("anyType{}", ""));
                    cv.put("Booth_No", obj3.getProperty("Booth_No").toString().replace("anyType{}", ""));
                    cv.put("Section_no", obj3.getProperty("Section_no").toString().replace("anyType{}", ""));
                    cv.put("VoterNo", obj3.getProperty("VoterNo").toString().replace("anyType{}", ""));
                    cv.put("EPIC", obj3.getProperty("EPIC").toString().replace("anyType{}", ""));
                    cv.put("VoterName", obj3.getProperty("VoterName").toString().replace("anyType{}", ""));
                    cv.put("FH_Name", obj3.getProperty("FH_Name").toString().replace("anyType{}", ""));
                    cv.put("HOUSE_NO", obj3.getProperty("HOUSE_NO").toString().replace("anyType{}", ""));
                    cv.put("GENDER", obj3.getProperty("GENDER").toString().replace("anyType{}", ""));
                    cv.put("DOB", obj3.getProperty("DOB").toString().replace("anyType{}", ""));
                    cv.put("VCast", obj3.getProperty("VCast").toString().replace("anyType{}", ""));
                    cv.put("VMobile", obj3.getProperty("VMobile").toString().replace("anyType{}", ""));
                    cv.put("VType", obj3.getProperty("VType").toString().replace("anyType{}", ""));
                    cv.put("Profession", obj3.getProperty("Profession").toString().replace("anyType{}", ""));
                    cv.put("PerResi", obj3.getProperty("PerResi").toString().replace("anyType{}", ""));
                    cv.put("IsWorker", obj3.getProperty("IsWorker").toString().replace("anyType{}", ""));
                    cv.put("IsFamilyHead", obj3.getProperty("IsFamilyHead").toString().replace("anyType{}", ""));
                    cv.put("RefBy", obj3.getProperty("RefBy").toString().replace("anyType{}", ""));
                    cv.put("Remarks", obj3.getProperty("Remarks").toString().replace("anyType{}", ""));
                    cv.put("Photo", decodedString);
                    cv.put("HNo_New", obj3.getProperty("HNo_New").toString().replace("anyType{}", ""));
                    cv.put("GaliBlock_New", obj3.getProperty("GaliBlock_New").toString().replace("anyType{}", ""));
                    cv.put("SecVillCol_New", obj3.getProperty("SecVillCol_New").toString().replace("anyType{}", ""));
                    cv.put("OtherInformation_New", obj3.getProperty("OtherInformation_New").toString().replace("anyType{}", ""));
                    cv.put("AreaID", obj3.getProperty("AreaID").toString().replace("anyType{}", ""));
                    cv.put("VoterName_H", obj3.getProperty("VoterName_H").toString().replace("anyType{}", ""));
                    cv.put("FH_Name_H", obj3.getProperty("FH_Name_H").toString().replace("anyType{}", ""));
                    cv.put("House_No_H", obj3.getProperty("House_No_H").toString().replace("anyType{}", ""));
                    cv.put("Status_Type", obj3.getProperty("Status_Type").toString().replace("anyType{}", ""));
                    cv.put("PrintYN", obj3.getProperty("PrintYN").toString().replace("anyType{}", ""));
                    cv.put("Order_By", obj3.getProperty("Order_By").toString().replace("anyType{}", ""));
                    cv.put("vdob", obj3.getProperty("vdob").toString().replace("anyType{}", ""));
                    cv.put("vdom", obj3.getProperty("vdom").toString().replace("anyType{}", ""));
                    cv.put("vphoto", obj3.getProperty("vphoto").toString().replace("anyType{}", ""));
                    cv.put("vReligion", obj3.getProperty("vReligion").toString().replace("anyType{}", ""));
                    cv.put("vHabitation", obj3.getProperty("vHabitation").toString().replace("anyType{}", ""));
                    cv.put("vEco", obj3.getProperty("vEco").toString().replace("anyType{}", ""));
                    cv.put("vOrigin", obj3.getProperty("vOrigin").toString().replace("anyType{}", ""));
                    cv.put("vRemark", obj3.getProperty("vRemark").toString().replace("anyType{}", ""));
                    cv.put("vMember", obj3.getProperty("vMember").toString().replace("anyType{}", ""));
                    cv.put("user_date", obj3.getProperty("user_date").toString().replace("anyType{}", ""));
                    DBQuery db = new DBQuery(activity);
                    db.ImportData(cv);
                }
            }


        }
        catch  (Exception e)
        {
            Log.d ( "CallSelect1: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString());
            return "0";
        }
        return (s1.toString());
    }

}


