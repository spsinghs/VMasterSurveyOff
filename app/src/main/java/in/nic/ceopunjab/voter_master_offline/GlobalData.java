package in.nic.ceopunjab.voter_master_offline;

import android.app.Application;

/**
 * Created by admin on 05/25/2017.
 */


public class GlobalData extends  Application {

    private String Name;
   // private static final String SOAP_ADDRESS = "http://10.10.89.240/web/Service1.asmx";

    //http://117.239.84.132/web/Service1.asmx";
    //private static final String SOAP_ADDRESS = "http://127.0.0.1/web/Service1.asmx";
   private static final String SOAP_ADDRESS = "http://220.158.164.252/app/Service1.asmx";

    public String getName() {

        return Name;
    }
    public String getSOAP() {

        return SOAP_ADDRESS;
    }

    public void setName(String aName) {

        Name = aName;

    }

}