package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;


/**
 * Created by admin on 05/18/2017.
 */
// CAlled of UserActivity SuperActivity

class CallUser extends AsyncTask<String,String,String>
{

    private ProgressDialog progressDoalog;
    private String res="";
    private Activity activity;

    CallUser(Activity a)
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
        progressDoalog.dismiss();

    }


    @Override
    protected String doInBackground(String... params) {
   /*     String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String OPERATION_NAME = "Register";
        String SOAP_ACTION = "http://tempuri.org/Register";
        String SOAP_ADDRESS = new GlobalData().getSOAP(); //"http://117.239.84.132/web/Service1.asmx";
       // String SOAP_ADDRESS = "http://220.158.164.252/app/Service1.asmx";
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        try {
            PropertyInfo pi0 = new PropertyInfo();
            pi0.setName("chk");
            pi0.setValue(params[0]);
            pi0.setType(String.class);

            PropertyInfo pi = new PropertyInfo();
            pi.setName("uname");
            pi.setValue(params[1]);
            pi.setType(String.class);

            PropertyInfo pi1 = new PropertyInfo();
            pi1.setName("uid");
            pi1.setValue(params[2]);
            pi1.setType(String.class);

            PropertyInfo pi2 = new PropertyInfo();
            pi2.setName("upas");
            pi2.setValue(params[3]);
            pi2.setType(String.class);

            PropertyInfo p3 = new PropertyInfo();
            p3.setName("add");
            p3.setValue(params[4]);
            p3.setType(String.class);


            PropertyInfo p4 = new PropertyInfo();
            p4.setName("epic");
            p4.setValue(params[5]);
            p4.setType(String.class);

            PropertyInfo p5 = new PropertyInfo();
            p5.setName("dob");
            p5.setValue(params[6]);
            p5.setType(String.class);

            request.addProperty(pi0);
            request.addProperty(pi);
            request.addProperty(pi1);
            request.addProperty(pi2);
            request.addProperty(p3);
            request.addProperty(p4);
            request.addProperty(p5);

            //  httpTransport.setTimeout(25000);
            httpTransport.call(SOAP_ACTION, envelope);
            // SoapObject obj1 = (SoapObject) envelope.getResponse();
            SoapObject  obj1 =  (SoapObject)envelope.bodyIn;
            res = obj1.getProperty(0).toString();

        }
        catch (Exception e) { Log.d ( "EXP Search: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString()); }*/
        return (res);
    }


}