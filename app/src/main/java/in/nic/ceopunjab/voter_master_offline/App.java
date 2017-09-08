package in.nic.ceopunjab.voter_master_offline;

import android.app.Application;


public class App extends Application {
//http://stackoverflow.com/questions/21818905/get-application-context-from-non-activity-singleton-class

	public static String package_name;
    
    @Override
    public void onCreate() {
        super.onCreate();
        package_name = getPackageName();
    }
}
