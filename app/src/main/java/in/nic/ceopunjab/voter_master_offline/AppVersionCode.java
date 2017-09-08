package in.nic.ceopunjab.voter_master_offline;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by admin on 06/13/2017.
 */

public class AppVersionCode {
    public static int getApkVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return PDefaultValue.VERSION_CODE;
    }
}
