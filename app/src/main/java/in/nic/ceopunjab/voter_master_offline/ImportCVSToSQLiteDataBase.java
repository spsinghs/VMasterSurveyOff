package in.nic.ceopunjab.voter_master_offline;

import java.io.File;
import java.io.FileReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVReader;

public class ImportCVSToSQLiteDataBase extends AsyncTask<String, String, String> {

    Activity activity;
    Context context;
    File file=null;
    private ProgressDialog dialog;

    public ImportCVSToSQLiteDataBase(Context context, Activity activity,File file) {
        this.context=context;
        this.activity=activity;
        this.file=file;
    }

    @Override
    protected void onPreExecute()
    {
        dialog=new ProgressDialog(context);
        dialog.setTitle("Importing Data into SecureIt DataBase");
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        String data="";
        Log.d(getClass().getName(), file.toString());

        try{
            CSVReader reader = new CSVReader(new FileReader(file));
            String [] nextLine;
            String str1 = "INSERT INTO tablusers40  values(";
            //here I am just displaying the CSV file contents, and you can store your file content into db from while loop...

            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                String accId=nextLine[0];
                String acc_name=nextLine[1];
                data=data+"AccId:"+accId  +"  Account_name:"+acc_name+"\n";

                StringBuilder sb = new StringBuilder(str1);
                //String[] str = nextLine..split(",");
                sb.append("'" + nextLine[0] + "',");
                sb.append(nextLine[1] + "',");
                sb.append(nextLine[2] + "',");
                sb.append(nextLine[3] +  "',");
                sb.append(nextLine[4] +  "',");
                sb.append(nextLine[5] +  "',");
                sb.append(nextLine[6] +  "',");
                sb.append(nextLine[7] +  "',");
                sb.append(nextLine[8] +  "',");
                sb.append(nextLine[9] +  "',");
                sb.append(nextLine[10] +  "',");
                sb.append(nextLine[11] +  "',");
                sb.append(nextLine[12] +  "',");
                sb.append(nextLine[13] +  "',");
                sb.append(nextLine[14] +  "',");
                sb.append(nextLine[15] +  "',");
                sb.append(nextLine[16] +  "',");
                sb.append(nextLine[17] +  "',");
                sb.append(nextLine[18] +  "',");
                sb.append(nextLine[19] +  "',");
                sb.append(nextLine[21] +  "',");
                sb.append(nextLine[22] +  "',");
                sb.append(nextLine[23] +  "',");
                sb.append(nextLine[24] +  "',");
                sb.append(nextLine[25] +  "',");
                sb.append(nextLine[26] +  "',");
                sb.append(nextLine[27] +  "',");
                sb.append(nextLine[28] +  "',");
                sb.append(nextLine[29] +  "',");
                sb.append(nextLine[30] +  "',");
                sb.append(nextLine[31] +  "',");
                sb.append(nextLine[32] +  "',");
                sb.append(nextLine[33] +  "',");
                sb.append(nextLine[34] +  "',");
                sb.append(nextLine[35] +  "',");
                sb.append(nextLine[36] +  "',");
                sb.append(nextLine[37] +  "',");
                sb.append(nextLine[38] +  "',");
                sb.append(nextLine[39] +  "',");
                sb.append(nextLine[40] +  "',");
                sb.append(nextLine[41] +  "',");
                sb.append(nextLine[42] +  "',");
                sb.append(nextLine[43] +  "',");
                sb.append(nextLine[44] +  "',");
                sb.append(nextLine[45] +  "'");
                sb.append("\");\"");
               // Log.d("Insert=" , sb.toString());

                //db.execSQL(sb.toString());

            }
            return data;

        } catch (Exception e) {
            Log.e("Error", "Error for importing file");
        }
        return data="";

    }

    protected void onPostExecute(String data)
    {

        if (dialog.isShowing())
        {
            dialog.dismiss();
        }

        if (data.length()!=0)
        {
            Toast.makeText(context, "File is built Successfully!"+"\n"+data, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "File fail to build", Toast.LENGTH_SHORT).show();
        }
    }


}
