package net.kibotu.sandbox.nsa;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Enables and displays all possible android permissions.
 *
 * @see http://developer.android.com/reference/android/Manifest.permission.html
 */
public class NSA extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((ListView) findViewById(R.id.list)).setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, getPermissions()));
    }
    public ArrayList<String> getPermissions() {
        Context context = this;
        PackageManager pm = context.getPackageManager();
        ArrayList<String> permissions = new ArrayList<String>();
        List<PermissionGroupInfo> lstGroups = pm.getAllPermissionGroups(0);
        for (PermissionGroupInfo pgi : lstGroups) {
            permissions.add(pgi.name);
            try {
                List<PermissionInfo> lstPermissions = pm.queryPermissionsByGroup(pgi.name, 0);
                for (PermissionInfo pi : lstPermissions) {
                    permissions.add(pi.name);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return permissions;
    }
}
