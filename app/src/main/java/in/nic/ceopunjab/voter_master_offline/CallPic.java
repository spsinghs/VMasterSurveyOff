package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 05/19/2017.
 */

public class CallPic   extends AsyncTask<String,String,String> {


    String Result="";
    private Activity activity;
    private ProgressDialog progressDoalog ;
    public static StringBuilder f1;
    Bitmap decodedByte;
    CallPic(Activity a)
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
        ImageView iv = (ImageView) activity.findViewById(R.id.list_image);
//        if (!Result.equals("anyType{}"))
//        {
//            byte[] decodedString = Base64.decode(Result, Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            iv.setImageBitmap(decodedByte);
//        }


        Spinner fm= (Spinner) activity.findViewById(R.id.spnFamily);
        String[] f2= f1.toString().split("\n");

        List<String> slist0 = new ArrayList<String>(Arrays.asList(f2));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item,slist0);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fm.setAdapter(adapter);

    }
    @Override
    protected String doInBackground(String... params) {
        String qry1 = "";
        DBQuery db = new DBQuery(activity);
        db.open();
        f1 = new StringBuilder();
            qry1 = "select ifnull([Photo],'') as photo FROM  [tblVoterMaster] where epic= '" + params[0]+ "'";
        decodedByte=db.getPic(qry1);

        db.open();
        qry1 = "select VoterName || '-' || EPIC AS Family  from [tblVoterMaster] where AC_NO=(select ac_no from [tblVoterMaster] where EPIC ='" + params[0] + "') and Ward_NO =(select Ward_NO from [tblVoterMaster] where EPIC = '" + params[0] + "') and Booth_NO=(select Booth_NO from [tblVoterMaster] where EPIC ='" + params[0] + "') and SECTION_NO=(select SECTION_NO from [tblVoterMaster] where EPIC ='" + params[0] + "' ) and HOUSE_NO=(select HOUSE_NO from [tblVoterMaster] where EPIC ='" + params[0] + "')";
        Result=db.getFamily(qry1);

        return("1");
    }


}
