package com.daniellamishan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daniellamishan.R;
import com.google.android.material.textfield.TextInputEditText;

public class AboutMeFragment extends Fragment {

    Button downloadMyCV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about_me, container, false);
        
        downloadMyCV = v.findViewById(R.id.about_me_btn);
        
        downloadMyCV.setOnClickListener(v1 -> {
            String url = "https://docdro.id/5jsIZFp";
            openWebPage(url);
            Log.d("ImageButton", "Clicked");
        });

        return v;
    }

    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}