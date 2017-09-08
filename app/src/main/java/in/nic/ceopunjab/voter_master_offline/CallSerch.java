package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 05/12/2017.
 * Search voter
 */

class CallSerch extends AsyncTask<String,String,String> {

    private Activity activity;
    private ProgressDialog progressDoalog ;
    private String result;
    private  ArrayList <ListData> CustomListView= new ArrayList<>();
    private  customTableAdapter Tadapter;
    public static StringBuilder stringBuilder;
    private String chk;

    CallSerch(Activity a)
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
       // lv.setAdapter(null);
    }

    @Override
    protected void onPostExecute(String s) {
       if (activity.equals( new VoterUpdateActivity())) return;
        if (chk.equals("1")) {
            Tadapter = new customTableAdapter(activity, CustomListView);
            ListView lv = (ListView) this.activity.findViewById(R.id.Listv);
            lv.setAdapter(Tadapter);
        }
         progressDoalog.dismiss();
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            String ac=params[0];
            String epic=params[4];
            String Name= params[1];
            String Fname=params[2];
            String hno=params[3];
            chk=params[5];
            String qry="";
/*        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String OPERATION_NAME = "Search";
        String SOAP_ACTION = "http://tempuri.org/Search";
        String SOAP_ADDRESS = new GlobalData().getSOAP(); //"http://117.239.84.132/web/Service1.asmx";
       // String SOAP_ADDRESS = "http://220.158.164.252/app/Service1.asmx";
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        String res="";
        try {
            PropertyInfo pi = new PropertyInfo();
            pi.setName("ac");
            pi.setValue(params[0]);
            pi.setType(String.class);
            String ac=params[0];
            String epic=params[4];
            String Name= params[1];
            String Fname=params[2];
            String hno=params[3];

            String chk=params[5];
            String qry;

            PropertyInfo pi1 = new PropertyInfo();
            pi1.setName("Name");
            pi1.setValue(params[1]);
            pi1.setType(String.class);

            PropertyInfo pi2 = new PropertyInfo();
            pi2.setName("Fname");
            pi2.setValue(params[2]);
            pi2.setType(String.class);

            PropertyInfo pi3 = new PropertyInfo();
            pi3.setName("hno");
            pi3.setValue(params[3]);
            pi3.setType(String.class);

            PropertyInfo pi4 = new PropertyInfo();
            pi4.setName("epic");
            pi4.setValue(params[4]);
            pi4.setType(String.class);

            PropertyInfo pi5 = new PropertyInfo();
            pi5.setName("chk");
            pi5.setValue(params[5]);
            pi5.setType(String.class);
            Check=params[5];

            request.addProperty(pi);
            request.addProperty(pi1);
            request.addProperty(pi2);
            request.addProperty(pi3);
            request.addProperty(pi4);
            request.addProperty(pi5);

          //  httpTransport.setTimeout(25000);
            httpTransport.call(SOAP_ACTION, envelope);
            SoapObject obj1 = (SoapObject) envelope.getResponse();

            SoapObject obj11 =(SoapObject) obj1.getProperty(1);
            SoapObject obj2 =(SoapObject) obj11.getProperty(0);

            SoapObject obj3 ;*/

            if (chk.equals("1"))
            {
                if (!ac.equals(""))
                {
                    if (epic.equals("") && Name.equals("") && Fname.equals("") && hno.equals(""))
                        qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'') as HOUSE_NO,ifnull([RelationType],'') as RelationType,ifnull([vdob],'')as vdob,ifnull([vdom],'') as vdom,ifnull([VCAST],'') as VCAST,ifnull([VMOBILE],'') as VMOBILE,ifnull([vReligion],'') as VRel,ifnull([vHabitation],'') as VHabit,ifnull([vEco],'') as VEco,ifnull([vOrigin],'') as VOrg,ifnull([vRemark],'') as vRem ,ifnull([vType],'') as vType ,ifnull([Profession],'') as vprof from [tblVoterMaster] where ac_no= '" + ac + "'";
                    else if (epic.equals(""))
                    {
                        if (! Name.equals(""))
                        {
                            if (Fname.equals(""))
                                qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'') as HOUSE_NO,ifnull([RelationType],'') as RelationType,ifnull([vdob],'')as vdob,ifnull([vdom],'') as vdom,ifnull([VCAST],'') as VCAST,ifnull([VMOBILE],'') as VMOBILE,ifnull([vReligion],'') as VRel,ifnull([vHabitation],'') as VHabit,ifnull([vEco],'') as VEco,ifnull([vOrigin],'') as VOrg,ifnull([vRemark],'') as vRem ,ifnull([vType],'') as vType  ,ifnull([Profession],'') as vprof FROM  [tblVoterMaster] where AC_NO= '" + ac + "' and VoterName LIKE'" + Name + "%'";
                            else
                                qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'') as HOUSE_NO,ifnull([RelationType],'') as RelationType,ifnull([vdob],'')as vdob,ifnull([vdom],'') as vdom,ifnull([VCAST],'') as VCAST,ifnull([VMOBILE],'') as VMOBILE,ifnull([vReligion],'') as VRel,ifnull([vHabitation],'') as VHabit,ifnull([vEco],'') as VEco,ifnull([vOrigin],'') as VOrg,ifnull([vRemark],'') as vRem ,ifnull([vType],'') as vType  ,ifnull([Profession],'') as vprof FROM  [tblVoterMaster] where AC_NO= '" + ac + "' and VoterName LIKE'" + Name + "%' and FH_Name LIKE'" + Fname + "%'";
                        }
                        else if (!Fname.equals(""))
                        {
                            qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'') as HOUSE_NO,ifnull([RelationType],'') as RelationType,ifnull([vdob],'')as vdob,ifnull([vdom],'') as vdom,ifnull([VCAST],'') as VCAST,ifnull([VMOBILE],'') as VMOBILE,ifnull([vReligion],'') as VRel,ifnull([vHabitation],'') as VHabit,ifnull([vEco],'') as VEco,ifnull([vOrigin],'') as VOrg,ifnull([vRemark],'') as vRem ,ifnull([vType],'') as vType ,ifnull([Profession],'') as vprof  FROM  [tblVoterMaster] where AC_NO= '" + ac + "' and FH_Name LIKE '" + Fname + "%'";
                        }
                        else if (!hno.equals(""))
                        {
                            qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'') as HOUSE_NO,ifnull([RelationType],'') as RelationType,ifnull([vdob],'')as vdob,ifnull([vdom],'') as vdom,ifnull([VCAST],'') as VCAST,ifnull([VMOBILE],'') as VMOBILE,ifnull([vReligion],'') as VRel,ifnull([vHabitation],'') as VHabit,ifnull([vEco],'') as VEco,ifnull([vOrigin],'') as VOrg,ifnull([vRemark],'') as vRem,ifnull([vType],'') as vType  ,ifnull([Profession],'') as vprof FROM  [tblVoterMaster] where AC_NO= '" + ac + "' and House_no='" + hno + "'";
                        }
                    }

                    else if (!epic.equals(""))
                        qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'') as HOUSE_NO,ifnull([RelationType],'') as RelationType,ifnull([vdob],'')as vdob,ifnull([vdom],'') as vdom,ifnull([VCAST],'') as VCAST,ifnull([VMOBILE],'') as VMOBILE,ifnull([vReligion],'') as VRel,ifnull([vHabitation],'') as VHabit,ifnull([vEco],'') as VEco,ifnull([vOrigin],'') as VOrg,ifnull([vRemark],'') as vRem,ifnull([vType],'') as vType ,ifnull([Profession],'') as vprof  FROM  [tblVoterMaster] where EPIC= '" + epic + "' and ac_no='" + ac + "'";

                }
            }
            else if (chk.equals("2"))
            {
                 stringBuilder=new StringBuilder();
                qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'') as HOUSE_NO,ifnull([RelationType],'') as RelationType,ifnull([vdob],'')as vdob,ifnull([vdom],'') as vdom,ifnull([VCAST],'') as VCAST,ifnull([VMOBILE],'') as VMOBILE,ifnull([vReligion],'') as VRel,ifnull([vHabitation],'') as VHabit,ifnull([vEco],'') as VEco,ifnull([vOrigin],'') as VOrg,ifnull([vRemark],'') as vRem,ifnull([vType],'') as vType ,ifnull([Profession],'') as vprof  FROM  [tblVoterMaster] where EPIC= '" + epic + "'";
            }

            else
            {
                qry = "SELECT [AC_NO],[EPIC],[VoterName],[FH_Name],ifnull([HOUSE_NO],'0') as HOUSE_NO,[DOB] ,ifnull([VCast],'-') as Vcast,ifnull([VMobile],'-') as VMobile,ifnull([VDom],' ') as VDom FROM [tblVoterMaster] where EPIC= '" + epic + "'";
                //[AC_NO],[Ward_NO],[Booth_NO],[SECTION_NO],[VoterNo],[EPIC],[VoterName],[FH_Name],[HOUSE_NO],[GENDER],[AGE],[DOB] ,[VCast] ,[VMobile],[VType],[Profession],[PerResi] ,[IsWorker],[IsFamilyHead],[RefBy],[Remarks] ,[Photo],[HNo_New],[GaliBlock_New],[SecVillCol_New],[OtherInformation_New] ,[AreaID],[VoterName_H],[FH_Name_H],[House_No_H],[RelationType],[Status_Type],[PrintYN],[Order_By],[vdob],vdom],[vphoto]
            }

            DBQuery db = new DBQuery(activity);
           // List<String> spn1= db.getCategories(qry);
            db.open();
            if(chk.equals("1"))
                CustomListView=db.getSearch(qry);
            else if(chk.equals("2"))
                db.getSearch2(qry);
            db.close();

            // adapter.setCategory(db.getCategories("NRX2705290"));

        } catch (Exception e) { Log.d ( "EXP Search: ",e.getStackTrace()[0].getLineNumber() +" "  + e.toString()); }


        return null;
    }
}
