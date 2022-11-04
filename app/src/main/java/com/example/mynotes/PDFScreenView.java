package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URLEncoder;

public class PDFScreenView extends AppCompatActivity {

    WebView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfscreen_view);

        pdfView = (WebView) findViewById(R.id.pdfView);
        pdfView.getSettings().setJavaScriptEnabled(true);
        pdfView.getSettings().setDomStorageEnabled(true);

        Intent intent = getIntent();
        String fileName = getIntent().getStringExtra("pdfName");
        String pdfUrl = getIntent().getStringExtra("pdfUrl");

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(fileName);
        pd.setMessage("Opening...");

        pdfView.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String url = "";
        try{
            url = URLEncoder.encode(pdfUrl, "UTF-8");
        }
        catch (Exception ignored){

        }

        pdfView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+url);
    }
}