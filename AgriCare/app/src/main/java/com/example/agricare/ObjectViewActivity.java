package com.example.agricare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agricare.commons.FileUtils;
import com.example.agricare.commons.PreferenceHelper;
import com.example.agricare.models.Agriculture;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Objects;

public class ObjectViewActivity extends AppCompatActivity {

    public static final String AGRICULTURE = "agriculture";
    public static final String TYPE = "type";

    public enum AGRICULTURE_TYPE {
        CROPS,
        LIVESTOCK,
        FISH
    }

    LinearLayout linearLayoutBack;
    TextView tvObjectName, tvHows, tvPrevention;
    ImageView imgObject;
    CardView cardViewMagtanim, cardViewPesticide, cardViewVideo;
    private Agriculture agriculture;
    private String howsTitle, preventionTitle;
    private PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_view_activity);
        agriculture = (Agriculture) getIntent().getExtras().getSerializable(AGRICULTURE);
        AGRICULTURE_TYPE mType = (AGRICULTURE_TYPE) getIntent().getSerializableExtra(TYPE);
        linearLayoutBack = findViewById(R.id.linearLayoutBack);
        tvObjectName = findViewById(R.id.tvObjectName);
        imgObject = findViewById(R.id.imgObject);
        cardViewMagtanim = findViewById(R.id.cardViewMagtanim);
        cardViewPesticide = findViewById(R.id.cardViewPesticide);
        cardViewVideo = findViewById(R.id.cardViewVideo);
        tvHows = findViewById(R.id.tv_hows);
        tvPrevention = findViewById(R.id.tv_prevention);
        tvObjectName.setText(agriculture.getLabel());

        preferenceHelper = PreferenceHelper.getInstance(this);

        switch (Objects.requireNonNull(mType)){
            case CROPS:
                howsTitle = getString(R.string.how_to_plant);
                preventionTitle = getString(R.string.pesticides);
                tvHows.setText(howsTitle);
                tvPrevention.setText(preventionTitle);
                break;
            default:
                howsTitle = getString(R.string.how_to_care);
                preventionTitle = getString(R.string.how_to_treat);

                tvHows.setText(howsTitle);
                tvPrevention.setText(preventionTitle);
                break;
        }

        switch (agriculture.getImage()){
            case Agriculture.PALAY:
                imgObject.setImageResource(R.drawable.crops_icon);
                break;
            case Agriculture.TUBO:
                imgObject.setImageResource(R.drawable.sugar_cane_icon);
                break;
            case Agriculture.MAIS:
                imgObject.setImageResource(R.drawable.corn_icon);
                break;
            case Agriculture.CHICKEN:
                imgObject.setImageResource(R.drawable.chicken_icon);
                break;
            case Agriculture.PIG:
                imgObject.setImageResource(R.drawable.live_stock_icon);
                break;
            case Agriculture.COW:
                imgObject.setImageResource(R.drawable.cow_icon);
                break;
            case Agriculture.GOAT:
                imgObject.setImageResource(R.drawable.goat_icon);
                break;
            case Agriculture.TILAPIA:
                imgObject.setImageResource(R.drawable.tilapia_icon);
                break;
            case Agriculture.HITO:
                imgObject.setImageResource(R.drawable.catfish_icon);
                break;
        }

        cardViewVideo.setOnClickListener(v -> {
            Intent intent = new Intent(ObjectViewActivity.this, VideoViewActivity.class);
            intent.putExtra(AGRICULTURE, agriculture);
            startActivity(intent);
        });

        cardViewMagtanim.setOnClickListener(v -> {
            Dialog dialog = new Dialog(ObjectViewActivity.this);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(R.layout.how_to_plant_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();

            wlp.gravity = Gravity.BOTTOM;

            window.setAttributes(wlp);

            LinearLayout linearLayoutBack;
            PDFView pdfView;

            linearLayoutBack = dialog.findViewById(R.id.linearLayoutBack);
            pdfView = dialog.findViewById(R.id.pdfView);
            String language = "en";
            if(preferenceHelper.contains(PreferenceHelper.LANGUAGE))
                language = preferenceHelper.getString(PreferenceHelper.LANGUAGE);
            int rawId = getResources().getIdentifier(agriculture.getImage() +"_description_"+language, "raw", getPackageName());
            File file = FileUtils.getFileFromRaw(ObjectViewActivity.this, rawId, agriculture.getImage() + "_description_"+language+".pdf");
            pdfView.fromFile(file)
                    .onError(t -> {
                        Log.d("pdf_error", "ERROR: " + t.getLocalizedMessage());
                    })
                    .load();
            linearLayoutBack.setOnClickListener(v12 -> dialog.dismiss());
            dialog.show();
        });

        cardViewPesticide.setOnClickListener(v -> {
            Dialog dialog = new Dialog(ObjectViewActivity.this);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(R.layout.pesticide_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            window.setAttributes(wlp);
            LinearLayout linearLayoutBack;
            PDFView pdfView = dialog.findViewById(R.id.pdfView);
            linearLayoutBack = dialog.findViewById(R.id.linearLayoutBack);
            linearLayoutBack.setOnClickListener(v1 -> dialog.dismiss());

            String language = "en";
            if(preferenceHelper.contains(PreferenceHelper.LANGUAGE))
                language = preferenceHelper.getString(PreferenceHelper.LANGUAGE);
            int rawId = getResources().getIdentifier(agriculture.getImage() +"_pes_"+language, "raw", getPackageName());
            File file = FileUtils.getFileFromRaw(ObjectViewActivity.this, rawId, agriculture.getImage() + "_pes_"+language+".pdf");
            pdfView.fromFile(file)
                    .onError(t -> {
                        Log.d("pdf_error", "ERROR: " + t.getLocalizedMessage());
                    })
                    .load();
            dialog.show();
        });

        linearLayoutBack.setOnClickListener(v -> finish());
    }
}