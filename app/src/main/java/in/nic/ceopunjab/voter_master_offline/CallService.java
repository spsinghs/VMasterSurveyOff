package in.nic.ceopunjab.voter_master_offline;

/**
 * Created by admin on 05/10/2017.
 * Login service
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

class CallService extends AsyncTask<String,String,String>
{


    private Activity activity;
    private ProgressDialog progressDoalog ;
    private String result;


    CallService(Activity a)
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
    private void setData(String r)
    {
        this.result=r;
    }

    public String getData() {

        return result;
    }

    @Override
    protected String doInBackground(String... params) {
    /*    String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String OPERATION_NAME = "login";
        } catch (Exception e) { Log.d ( "exp ctch: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString());}*/

        String qry="SELECT COUNT(*) FROM [tblUsersMaster] where UserId= '" + params[0].toUpperCase() + "' and NewPassword='" + params[1] + "' and userPosition='ADMIN'";
        DBQuery db = new DBQuery(activity);
        db.open();
        return db.getCategories(qry);

    }
}
