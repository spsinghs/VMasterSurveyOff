package in.nic.ceopunjab.voter_master_offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Debug;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


class DBQuery {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBQuery(Context context) {
        dbHelper = new DBHelper(context);
    }

    public DBQuery createDatabase() throws SQLException {
        try {
            dbHelper.createDataBase();
        } catch (IOException ignored) {
        }
        return this;
    }

    public DBQuery open() throws SQLException {
        try {
            dbHelper.openDataBase();
            dbHelper.close();
            db = dbHelper.getReadableDatabase();
        } catch (SQLException ignored) {
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    //______________________________________________________________________________________________
    //__________________________________      QUERIES      ________________________________________
    //______________________________________________________________________________________________
    public String getCategories(String sql) {


        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> categoriesName = new ArrayList<>();
        ArrayList<String> categoriesCol = new ArrayList<>();
        StringBuilder str= new StringBuilder();
        try {
        if (cursor.getCount()==1) {
            cursor.moveToFirst();
                    str.append(cursor.getString(0));
        }
        else
        {
            if (cursor.moveToFirst()) {
                do {

                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        str.append(cursor.getString(i));
                        //categoriesCol.add(cursor.getString(i));
                    }
                    str.append("\n");
                    //categoriesName.add(categoriesCol.toString());
                    //categoriesCol.clear();
                } while (cursor.moveToNext());
            }
        }
       //     return categoriesName;
        } catch (Exception ignored) {
            Log.e("DBquery",ignored.toString() );
        }
        finally {
            cursor.close();
            db.close();
        }

        return str.toString();

    }

    public String getAC (String sql)
    {
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> categoriesName = new ArrayList<>();
        ArrayList<String> categoriesCol = new ArrayList<>();
        StringBuilder s1= new StringBuilder();
        try {


            s1.append("--Choose Assembly Consistency--" + "\n");

            if (cursor.moveToFirst()) {
                do {
                    s1.append(cursor.getString(0));
                    s1.append("\n");
                } while (cursor.moveToNext());
            }

        } catch (Exception ignored) {
            Log.e("DBquery",ignored.toString() );
        }
        finally {
            cursor.close();
            db.close();
        }
      return s1.toString();
    }
    public String getSpinner(String sql)
    {
//        StringBuilder s0=new StringBuilder();
//        StringBuilder s1=new StringBuilder();
//        StringBuilder s2 = new StringBuilder();
//        StringBuilder s3=new StringBuilder();
//        StringBuilder s4=new StringBuilder();
//        StringBuilder s5=new StringBuilder();

        CallSelect.s0.append("--Choose Voter Eco Status--" + "\n");
        CallSelect.s2.append("--Choose Voter Habitation--" + "\n");
        CallSelect.s3.append("--Choose Voter Religion--" + "\n");
        CallSelect.s4.append("--Choose Voter Cast--" + "\n");
        CallSelect.s5.append("--Choose Voter Type--" + "\n");
        Cursor cursor = db.rawQuery(sql, null);
    try
    {
        if (cursor.moveToFirst()) {
            do {

                    if (cursor.getString(cursor.getColumnIndex("DDType")).toLowerCase().equals("eco status")) {
                        CallSelect.s0.append(cursor.getString(cursor.getColumnIndex("DDValue")));
                        CallSelect.s0.append("\n");
                    }
                    else if(cursor.getString(cursor.getColumnIndex("DDType")).toLowerCase().equals("habitation"))
                    {
                        CallSelect.s2.append(cursor.getString(cursor.getColumnIndex("DDValue")));
                        CallSelect.s2.append("\n");
                    }
                    else if(cursor.getString(cursor.getColumnIndex("DDType")).toLowerCase().equals("voter religion"))
                    {
                        CallSelect.s3.append(cursor.getString(cursor.getColumnIndex("DDValue")));
                        CallSelect.s3.append("\n");
                    }

                    else if(cursor.getString(cursor.getColumnIndex("DDType")).toLowerCase().equals("voter cast"))
                    {
                        CallSelect.s4.append(cursor.getString(cursor.getColumnIndex("DDValue")));
                        CallSelect.s4.append("\n");
                    }
                    else if(cursor.getString(cursor.getColumnIndex("DDType")).toLowerCase().equals("voter type"))
                    {
                        CallSelect.s5.append(cursor.getString(cursor.getColumnIndex("DDValue")));
                        CallSelect.s5.append("\n");
                    }
                    //categoriesCol.add(cursor.getString(i));

            } while (cursor.moveToNext());
            CallSelect.s1.append(CallSelect.s0);
            CallSelect.s1.append(CallSelect.s2);
            CallSelect.s1.append(CallSelect.s3);
            CallSelect.s1.append(CallSelect.s4);
            CallSelect.s1.append(CallSelect.s5);

            return CallSelect.s1.toString();
        }
    }
    catch (Exception ignored) {
        Log.e("DBquery",ignored.toString() );
    }
    finally {
        cursor.close();
        db.close();
        }
       return "";
    }

    public ArrayList <ListData> getSearch(String sql)
    {
        ArrayList<ListData> CustomListView = new ArrayList<>();
        // ListData list = new ListData();

        Cursor cursor = db.rawQuery(sql, null);
        int i = 1;
        try
        {
        if (cursor.moveToFirst()) {
            do {
                ListData list = new ListData();
                list.setField1(Integer.toString(i++));
                list.setField2(cursor.getString(cursor.getColumnIndex("EPIC")));
                list.setField3(cursor.getString(cursor.getColumnIndex("VoterName")));
                list.setField4(cursor.getString(cursor.getColumnIndex("FH_Name")));
                list.setField5(cursor.getString(cursor.getColumnIndex("HOUSE_NO")));
                list.setField6(cursor.getString(cursor.getColumnIndex("RelationType")));
                list.setDob(cursor.getString(cursor.getColumnIndex("vdob")));
                list.setDom(cursor.getString(cursor.getColumnIndex("vdom")));
                list.setCast(cursor.getString(cursor.getColumnIndex("VCAST")));
                list.setMob(cursor.getString(cursor.getColumnIndex("VMOBILE")));
                list.setRelg(cursor.getString(cursor.getColumnIndex("VRel")));
                list.setHabit(cursor.getString(cursor.getColumnIndex("VHabit")));
                list.seteco(cursor.getString(cursor.getColumnIndex("VEco")));
                list.setOrg(cursor.getString(cursor.getColumnIndex("VOrg")));
                list.setRem(cursor.getString(cursor.getColumnIndex("vRem")));
                list.setvType(cursor.getString(cursor.getColumnIndex("vType")));
                list.setProf1(cursor.getString(cursor.getColumnIndex("vprof")));
                CustomListView.add(list);

            } while (cursor.moveToNext());
            }
        }
         catch (Exception ignored) {
        Log.e("DBquery",ignored.toString() );
          }
        finally {
            cursor.close();
            db.close();
        }
        return CustomListView;
    }

    public String getSearch2 (String sql)
    {

        // ListData list = new ListData();

        Cursor cursor = db.rawQuery(sql, null);
        int i=1;
        try {
            if (cursor.moveToFirst()) {
                do {
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("VoterName")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("FH_Name")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("RelationType")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("EPIC")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("vdob")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("vdom")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("VOrg")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("vRem")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("vprof")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("VMOBILE")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("VCAST")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("VRel")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("VHabit")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("vType")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("VEco")));
                    CallSerch.stringBuilder.append("\n");
                    CallSerch.stringBuilder.append(cursor.getString(cursor.getColumnIndex("HOUSE_NO")));
                    CallSerch.stringBuilder.append("\n");

                } while (cursor.moveToNext());
            }
        }catch (Exception ignored) {
            Log.e("DBquery",ignored.toString() );
        }
    finally {
        cursor.close();
            db.close();
    }
        return "";

    }

    public Bitmap getPic(String sql)
    {
       // select isnull([Photo],'') as photo FROM [ElectionSolution_40].[dbo].[tblVoterMaster] where epic= '" + epic + "'"
        Cursor cursor = db.rawQuery(sql, null);
        Bitmap bitmap=null;
        StringBuilder s1= new StringBuilder();
        try {

            if (cursor.moveToFirst()) {
                do {

                    byte[] data = cursor.getBlob(0);
                    if (data != null) {
                        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    }
                }while (cursor.moveToNext());
            }

        } catch (Exception ignored) {
            Log.e("DBquery",ignored.toString() );
        }
        finally {
            cursor.close();
            db.close();
        }
        return bitmap;

    }
    public String getFamily(String sql)
    {
        Cursor cursor = db.rawQuery(sql, null);
        try {
            CallPic.f1.append("Family Tree");
            CallPic.f1.append("\n");

            if (cursor.moveToFirst()) {
                do {
                    CallPic.f1.append(cursor.getString(0));
                    CallPic.f1.append("\n");
                } while (cursor.moveToNext());
            }

        } catch (Exception ignored) {
            Log.e("DBquery",ignored.toString() );
        }
        finally {
            cursor.close();
            db.close();
        }
        return "";
    }

    public StringBuilder getDetails()
    {
        StringBuilder s1=new StringBuilder();;
        try {

            Cursor cursor = db.rawQuery("select booth_no, count (*) 'Total Voters',count(user_date) 'Updated Voter' from tblvoterMaster group by ward_no", null);

            if (cursor.moveToFirst()) {
                do {

                    s1.append("Part No:" + cursor.getString(0)+ "\n");
                    s1.append("Total Voter:" + cursor.getString(1)+ "\n");
                    s1.append("\n");
                } while (cursor.moveToNext());
            }
           // return s1;
        }
        catch(Exception e)
        {
          Log.e("Exp:",e.toString());
        }
        return s1;
    }

    public String update(String sql)
    {

        try {
            SQLiteDatabase db2 = dbHelper.getWritableDatabase();
            db2.execSQL(sql);
             db2.close();
        }
        catch(Exception e)
        {return "0";}
        finally {
            //db2.close();
        }
        return "1";
    }

    public String ImportData(ContentValues cv)
    {
        try {
            SQLiteDatabase db2 = dbHelper.getWritableDatabase();
            db2.insert("tblVoterMaster",null,cv);
            db2.close();
            return "1";
//            db2.execSQL(sql);
        }
        catch(Exception e)
        {return "0";}
        finally {
            //db2.close();
        }

    }
    public String exportXLS()
    {

        Cursor c = db.rawQuery("select * from tblVoterMaster '", null);
        int rowcount = 0;
        int colcount = 0;
        File sdCardDir = Environment.getExternalStorageDirectory();
        String filename = "BackUp.csv";
        // the name of the file to export with
        try
        {
        File saveFile = new File(sdCardDir, filename);
        FileWriter fw = new FileWriter(saveFile);

        BufferedWriter bw = new BufferedWriter(fw);
        rowcount = c.getCount();
        colcount = c.getColumnCount();
        if (rowcount > 0) {
            c.moveToFirst();
            for (int i = 0; i < colcount; i++) {
                if (i != colcount - 1) {
                    bw.write(c.getColumnName(i) + ",");

                } else {
                    bw.write(c.getColumnName(i));
                }
            }
            bw.newLine();

            for (int i = 0; i < rowcount; i++) {
                c.moveToPosition(i);

                for (int j = 0; j < colcount; j++) {
                    if (j != colcount - 1)
                    {
                        c.getColumnName(j);
                        if (j==22 || j==38)
                        {

                            byte[] myBlobData = c.getBlob(j);
                            if (myBlobData!=null) {
                                String encodedData = Base64.encodeToString(myBlobData, Base64.DEFAULT);
                                bw.write(encodedData+",");
                                //bw.write("photo" + ",");
                            }
                            else
                                bw.write(null +",");
                        }
                        else
                        bw.write(c.getString(j) + ",");
                    }
                    else
                        bw.write(c.getString(j));
                }
                bw.newLine();
            }
            bw.flush();
        }
    }
    catch (Exception ignored) {
            Log.e("DBquery " + ignored.getStackTrace()[0].getLineNumber(),ignored.toString() );
        return "0";
        }
        finally {
            c.close();
            db.close();
        }
        return "1";



}

    public String Delete()
    {

        try {
            SQLiteDatabase db2 = dbHelper.getWritableDatabase();
            db2.execSQL("delete from tblvotermaster");
            db2.close();
            return "1";
        }
        catch(Exception e)
        {return "0";}
    }

public void Export()
{
    Cursor c;
    try {
        c = db.rawQuery("select * from tblVoterMaster", null);
        int rowcount = 0;
        int colcount = 0;
        rowcount = c.getCount();
        colcount = c.getColumnCount();

        for (int i = 0; i < rowcount; i++) {
            c.moveToPosition(i);

            for (int j = 0; j < colcount; j++) {
                StringBuilder str = new StringBuilder();
                str.append("6");
                str.append(")@(");
                if (j != colcount - 1) {
                   // c.getColumnName(j);
                    if (j == 22 || j == 37) {

                        byte[] myBlobData = c.getBlob(j);
                        if (myBlobData != null) {
                            String encodedData = Base64.encodeToString(myBlobData, Base64.DEFAULT);
                            str.append(encodedData);
                          // str.append(")@(");
                        } else
                            str.append(" ");
                       // str.append(")@(");
                    } else
                        str.append(c.getString(j));
                        str.append(")@(");

                }
                else {
                    str.append(c.getString(j));
                   // new CallExport().execute(str.toString());
                }
            }

            //bw.newLine(); Cal
        }
    }
     catch (Exception ignored)
     { Log.e("DBquery " + ignored.getStackTrace()[0].getLineNumber(),ignored.toString() ); }

    finally {
    //c.close();
    db.close();
    }

}

}
