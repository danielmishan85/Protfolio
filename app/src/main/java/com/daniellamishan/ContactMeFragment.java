package com.daniellamishan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;

import com.daniellamishan.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactMeFragment extends Fragment {

    TextInputEditText email;
    TextInputEditText name;
    TextInputEditText message;
    Button send;
    ImageButton github;
    ImageButton linkedin;
    ExecutorService es;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_me, container, false);
        email = v.findViewById(R.id.contact_me_email_input_ed);
        name = v.findViewById(R.id.contact_me_name_input_ed);
        message = v.findViewById(R.id.contact_me_message_input_ed);
        send = v.findViewById(R.id.contact_me_send_btn);
        github = v.findViewById(R.id.contact_me_github_btn);
        linkedin = v.findViewById(R.id.contact_me_linkedin_btn);

        es = Executors.newSingleThreadExecutor();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getContext().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                            sendSMS();

                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }

            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.linkedin.com/in/daniella-mishan/";
                openWebPage(url);
                Log.d("ImageButton", "Clicked");
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/danielmishan85";
                openWebPage(url);
                Log.d("ImageButton", "Clicked");
            }
        });

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendSMS();
            } else {
                Toast.makeText(getContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSMS() {
        String myPhoneNumber = "0545720377";
        String SMS = "name: " + name.getText().toString().trim() + "\n"
                + "email: " + email.getText().toString().trim() + "\n"
                + message.getText().toString().trim() + "\n"
                + "SENT FROM MY PROTFOLIO";
        Log.d("tag", SMS);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(myPhoneNumber, null, SMS, null, null);
        Log.d("tag", "succeed");
    }

    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}