package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;



/**
 * Created by admin on 05/11/2017.
 *
 */

public class CallSop   extends AsyncTask<String,String,String> {
//    String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
//    String OPERATION_NAME = "update";
//    String SOAP_ACTION = "http://tempuri.org/update";
//    String SOAP_ADDRESS = new GlobalData().getSOAP(); //"http://117.239.84.132/web/Service1.asmx";
    //String SOAP_ADDRESS = "http://220.158.164.252/app/Service1.asmx";
    private  String Result="";
    private Activity activity;
    private ProgressDialog progressDoalog ;

    CallSop(Activity a)
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


   /*     SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS,600000);
        try {
            PropertyInfo pi = new PropertyInfo();
            pi.setName("epic");
            pi.setValue(params[0]);
            pi.setType(String.class);

            PropertyInfo pi1 = new PropertyInfo();
            pi1.setName("mobile");
            pi1.setValue(params[1]);
            pi1.setType(String.class);

            PropertyInfo pi2 = new PropertyInfo();
            pi2.setName("cast");
            pi2.setValue(params[2]);
            pi2.setType(String.class);

            PropertyInfo pi3 = new PropertyInfo();
            pi3.setName("dob");
            pi3.setValue(params[3]);
            pi3.setType(String.class);

            PropertyInfo pi4 = new PropertyInfo();
            pi4.setName("dom");
            pi4.setValue(params[4]);
            pi4.setType(String.class);

            PropertyInfo pi5 = new PropertyInfo();
            pi5.setName("Relig");
            pi5.setValue(params[5]);
            pi5.setType(String.class);

            PropertyInfo pi6 = new PropertyInfo();
            pi6.setName("Habit");
            pi6.setValue(params[6]);
            pi6.setType(String.class);

            PropertyInfo pi7 = new PropertyInfo();
            pi7.setName("Eco");
            pi7.setValue(params[7]);
            pi7.setType(String.class);

            PropertyInfo pi8 = new PropertyInfo();
            pi8.setName("Origin");
            pi8.setValue(params[8]);
            pi8.setType(String.class);

            PropertyInfo pi9 = new PropertyInfo();
            pi9.setName("Rem");
            pi9.setValue(params[9]);
            pi9.setType(String.class);

            PropertyInfo pi10 = new PropertyInfo();
            pi10.setName("vType");
            pi10.setValue(params[10]);
            pi10.setType(String.class);

            PropertyInfo pi11 = new PropertyInfo();
            pi11.setName("vprof");
            pi11.setValue(params[11]);
            pi11.setType(String.class);

            PropertyInfo pi12 = new PropertyInfo();
            pi12.setName("vmemb");
            pi12.setValue(params[12]);
            pi12.setType(String.class);

            PropertyInfo pi13 = new PropertyInfo();
            pi13.setName("udate");
            pi13.setValue(params[13]);
            pi13.setType(String.class);

            request.addProperty(pi);
            request.addProperty(pi1)  ;
            request.addProperty(pi2);
            request.addProperty(pi3)  ;
            request.addProperty(pi4);
            request.addProperty(pi5);
            request.addProperty(pi6);
            request.addProperty(pi7);
            request.addProperty(pi8);
            request.addProperty(pi9);
            request.addProperty(pi10);
            request.addProperty(pi11);
            request.addProperty(pi12);
            request.addProperty(pi13);

            //httpTransport.setTimeout(25000);
            httpTransport.call(SOAP_ACTION, envelope);
            SoapObject  obj1 =  (SoapObject)envelope.bodyIn;
            Result = obj1.getProperty(0).toString();
        } catch (Exception e) { Log.d ( "exp ctch: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString());; }*/

        String qry= "update [tblVoterMaster] set vmobile='" + params[1] + "', vcast= '" + params[2] + "', vdob='" + params[3] + "', vdom='" + params[4] + "', vReligion='" + params[5] + "' , vHabitation='" + params[6] + "', vEco='" + params[7] + "', vOrigin='" + params[8] + "', vRemark='" + params[9] + "', vType='" + params[10] + "', Profession='" + params[11] + "', vmember='" + params[12] + "', user_date='" + params[13] + "' where epic='" + params[0] + "'";
        String res="";
        DBQuery db = new DBQuery(activity);
        db.open();
        db.close();
        res=db.update(qry);
        return(res);
    }


}
