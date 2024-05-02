package com.example.agricare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ObjectViewActivity extends AppCompatActivity {

    LinearLayout linearLayoutBack;
    TextView tvObjectName;
    ImageView imgObject;
    String Object;
    CardView cardViewMagtanim, cardViewPesticide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_view_activity);

        Object = getIntent().getExtras().getString("Object");

        linearLayoutBack = findViewById(R.id.linearLayoutBack);
        tvObjectName = findViewById(R.id.tvObjectName);
        imgObject = findViewById(R.id.imgObject);
        cardViewMagtanim = findViewById(R.id.cardViewMagtanim);
        cardViewPesticide = findViewById(R.id.cardViewPesticide);

        tvObjectName.setText(Object);

        //IMAGES
        {
            if(Object.equals("Palay"))
            {
                imgObject.setImageResource(R.drawable.crops_icon);
            }

            if(Object.equals("Tubo"))
            {
                imgObject.setImageResource(R.drawable.sugar_cane_icon);
            }

            if(Object.equals("Mais"))
            {
                imgObject.setImageResource(R.drawable.corn_icon);
            }

            if(Object.equals("Tilapia"))
            {
                imgObject.setImageResource(R.drawable.tilapia_icon);
            }

            if(Object.equals("Hito"))
            {
                imgObject.setImageResource(R.drawable.catfish_icon);
            }

            if(Object.equals("Manok"))
            {
                imgObject.setImageResource(R.drawable.chicken_icon);
            }

            if(Object.equals("Baboy"))
            {
                imgObject.setImageResource(R.drawable.live_stock_icon);
            }

            if(Object.equals("Baka"))
            {
                imgObject.setImageResource(R.drawable.cow_icon);
            }

            if(Object.equals("Kambing"))
            {
                imgObject.setImageResource(R.drawable.goat_icon);
            }
        }

        cardViewMagtanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ObjectViewActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.how_to_plant_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.BOTTOM;

                window.setAttributes(wlp);

                LinearLayout linearLayoutBack;
                TextView tvDescription;

                linearLayoutBack = dialog.findViewById(R.id.linearLayoutBack);
                tvDescription = dialog.findViewById(R.id.tvDescription);

                //DESCRIPTION
                {
                    if(Object.equals("Palay"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Tubo"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Mais"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Tilapia"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Hito"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Manok"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Baboy"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Baka"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Kambing"))
                    {
                        tvDescription.setText("");
                    }
                }

                linearLayoutBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        cardViewPesticide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ObjectViewActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.pesticide_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.BOTTOM;

                window.setAttributes(wlp);

                LinearLayout linearLayoutBack;
                TextView tvDescription;

                linearLayoutBack = dialog.findViewById(R.id.linearLayoutBack);
                tvDescription = dialog.findViewById(R.id.tvDescription);

                //DESCRIPTION
                {
                    if(Object.equals("Palay"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Tubo"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Mais"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Tilapia"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Hito"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Manok"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Baboy"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Baka"))
                    {
                        tvDescription.setText("");
                    }

                    if(Object.equals("Kambing"))
                    {
                        tvDescription.setText("");
                    }
                }

                linearLayoutBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        linearLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}