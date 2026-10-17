package com.example.chatmobile;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/** A tiny launcher that opens Google Chat in the user's existing Brave browser session. */
public class MainActivity extends Activity {
    private static final String CHAT_URL = "https://chat.google.com/";
    private static final String BRAVE_PACKAGE = "com.brave.browser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(dp(28), dp(28), dp(28), dp(28));
        root.setBackgroundColor(Color.rgb(248, 249, 250));

        TextView icon = new TextView(this);
        icon.setText("G");
        icon.setTextSize(42);
        icon.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        icon.setTextColor(Color.rgb(26, 115, 232));
        icon.setGravity(Gravity.CENTER);
        GradientDrawable iconBg = new GradientDrawable();
        iconBg.setColor(Color.WHITE);
        iconBg.setShape(GradientDrawable.OVAL);
        iconBg.setStroke(dp(1), Color.rgb(218, 220, 224));
        icon.setBackground(iconBg);
        root.addView(icon, new LinearLayout.LayoutParams(dp(84), dp(84)));

        TextView title = new TextView(this);
        title.setText("Chat Mobile");
        title.setTextSize(24);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(Color.rgb(32, 33, 36));
        title.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleParams.topMargin = dp(18);
        root.addView(title, titleParams);

        TextView description = new TextView(this);
        description.setText("Open Google Chat in Brave using your existing signed-in browser session.");
        description.setTextSize(16);
        description.setTextColor(Color.rgb(95, 99, 104));
        description.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams descriptionParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        descriptionParams.topMargin = dp(10);
        root.addView(description, descriptionParams);

        Button open = new Button(this);
        open.setText("Open Google Chat in Brave");
        open.setTextAllCaps(false);
        open.setTextSize(16);
        open.setTextColor(Color.WHITE);
        open.setPadding(dp(16), dp(8), dp(16), dp(8));
        GradientDrawable buttonBg = new GradientDrawable();
        buttonBg.setColor(Color.rgb(26, 115, 232));
        buttonBg.setCornerRadius(dp(14));
        open.setBackground(buttonBg);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(54));
        buttonParams.topMargin = dp(26);
        root.addView(open, buttonParams);
        open.setOnClickListener(v -> openInBrave());

        TextView note = new TextView(this);
        note.setText("This app is a shortcut. Google Chat runs in Brave, so it can use Brave's existing cookies and login. It does not embed or copy your session.");
        note.setTextSize(13);
        note.setTextColor(Color.rgb(95, 99, 104));
        note.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams noteParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        noteParams.topMargin = dp(20);
        root.addView(note, noteParams);

        setContentView(root);
        // Launch directly when the app icon is tapped; the button remains as a retry path.
        root.post(this::openInBrave);
    }

    private void openInBrave() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CHAT_URL));
        intent.setPackage(BRAVE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
            finish();
        } catch (ActivityNotFoundException e) {
            // Brave is not installed or cannot handle this URL; use the default browser instead.
            try {
                Intent fallback = new Intent(Intent.ACTION_VIEW, Uri.parse(CHAT_URL));
                startActivity(fallback);
                finish();
            } catch (ActivityNotFoundException ignored) {
                Toast.makeText(this, "Install Brave or another browser first.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
