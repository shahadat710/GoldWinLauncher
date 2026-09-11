package com.goldwin.launcher;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // =====================================================================
    // IMPORTANT: Replace this with the EXACT package name of the GoldWin
    // app installed on your M1K device. To find it:
    //   Settings > Apps > GoldWin > (package name shown in App info,
    //   or under "Storage" details)
    // The value below is a placeholder based on an earlier screenshot.
    // =====================================================================
    private static final String TARGET_PACKAGE = "org.chromium.webapk.a99635ed460bff022_v2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMyApp = findViewById(R.id.btnMyApp);
        Button btnSettings = findViewById(R.id.btnSettings);

        btnMyApp.setOnClickListener(v -> openMyApp());
        btnSettings.setOnClickListener(v -> openWifiSettings());
    }

    private void openMyApp() {
        PackageManager pm = getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(TARGET_PACKAGE);
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(launchIntent);
        } else {
            Toast.makeText(this,
                    "GoldWin app not found. Check TARGET_PACKAGE value.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void openWifiSettings() {
        try {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Wi-Fi settings not available", Toast.LENGTH_SHORT).show();
        }
    }

    // Pressing Back on THIS launcher screen does nothing (there's nowhere
    // "back" to go from Home). This does NOT block Back inside GoldWin
    // itself -- inside GoldWin, Back behaves normally; once GoldWin's own
    // screen stack is exhausted, Android naturally returns to this Home
    // screen. No Kiosk/Lock Task Mode is used anywhere, so background
    // services (including the Print Spooler) are never restricted.
    @Override
    public void onBackPressed() {
        // intentionally empty
    }
}
