package com.goldwin.launcher;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TARGET_PACKAGE = "org.chromium.webapk.a99635ed460bff022_v2";
    private static final String MENU_PASSWORD = "710710";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btnMyApp = findViewById(R.id.btnMyApp);
        View btnSettings = findViewById(R.id.btnSettings);
        View btnMenu = findViewById(R.id.btnMenu);

        btnMyApp.setOnClickListener(v -> openMyApp());
        btnSettings.setOnClickListener(v -> openWifiSettings());
        btnMenu.setOnClickListener(this::showMenu);

        try {
            ImageView icon = findViewById(R.id.imgGoldWinIcon);
            icon.setImageDrawable(getPackageManager().getApplicationIcon(TARGET_PACKAGE));
        } catch (Exception e) {
            // Keep default icon if GoldWin app isn't found
        }
    }

    private void showMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.launcher_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_full_settings) {
                askPasswordThen(this::openFullSettings);
                return true;
            } else if (id == R.id.menu_change_home) {
                askPasswordThen(this::openChangeHomeApp);
                return true;
            }
            return false;
        });
        popup.show();
    }

    private void askPasswordThen(Runnable action) {
        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setHint("Enter password");

        new AlertDialog.Builder(this)
                .setTitle("Password Required")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String entered = input.getText().toString();
                    if (entered.equals(MENU_PASSWORD)) {
                        action.run();
                    } else {
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void openFullSettings() {
        try {
            startActivity(new Intent(Settings.ACTION_SETTINGS));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Settings not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void openChangeHomeApp() {
        try {
            startActivity(new Intent(Settings.ACTION_HOME_SETTINGS));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Home app settings not available", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onBackPressed() {
        // intentionally empty
    }
}
