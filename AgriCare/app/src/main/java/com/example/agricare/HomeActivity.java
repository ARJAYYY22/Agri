package com.example.agricare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {

    CardView cardViewCrop, cardViewLiveStock, cardViewFish;
    SearchView searchView;
    ListView listView;
    String [] names = {"Palay", "Tubo", "Mais", "Manok", "Baboy", "Baka", "Kambing", "Tilapia", "Hito"};
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        cardViewCrop = findViewById(R.id.cardViewCrop);
        cardViewLiveStock = findViewById(R.id.cardViewLiveStock);
        cardViewFish = findViewById(R.id.cardViewFish);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);

        listView.setVisibility(View.GONE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("Object", parent.getItemAtPosition(position).toString());

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listView.setVisibility(View.VISIBLE);
                HomeActivity.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty())
                {
                    listView.setVisibility(View.GONE);
                }
                else
                {
                    listView.setVisibility(View.VISIBLE);
                    HomeActivity.this.arrayAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        cardViewCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HomeActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.crop_selection_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.BOTTOM;

                window.setAttributes(wlp);

                LinearLayout linearLayoutPalay, linearLayoutTubo, linearLayoutMais;

                linearLayoutPalay = dialog.findViewById(R.id.linearLayoutPalay);
                linearLayoutTubo = dialog.findViewById(R.id.linearLayoutTubo);
                linearLayoutMais = dialog.findViewById(R.id.linearLayoutMais);

                linearLayoutPalay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Palay");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
                linearLayoutTubo.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Tubo");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
                linearLayoutMais.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Mais");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        });

        cardViewLiveStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HomeActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.live_stock_selection_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.BOTTOM;

                window.setAttributes(wlp);

                LinearLayout linearLayoutManok, linearLayoutBaboy, linearLayoutBaka, linearLayoutKambing;

                linearLayoutManok = dialog.findViewById(R.id.linearLayoutManok);
                linearLayoutBaboy = dialog.findViewById(R.id.linearLayoutBaboy);
                linearLayoutBaka = dialog.findViewById(R.id.linearLayoutBaka);
                linearLayoutKambing = dialog.findViewById(R.id.linearLayoutKambing);

                linearLayoutManok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Manok");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
                linearLayoutBaboy.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Baboy");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
                linearLayoutBaka.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Baka");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
                linearLayoutKambing.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Kambing");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        });

        cardViewFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HomeActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.fish_selection_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.BOTTOM;

                window.setAttributes(wlp);

                LinearLayout linearLayoutTilapia, linearLayoutHito;

                linearLayoutTilapia = dialog.findViewById(R.id.linearLayoutTilapia);
                linearLayoutHito = dialog.findViewById(R.id.linearLayoutHito);

                linearLayoutTilapia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Tilapia");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
                linearLayoutHito.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Object", "Hito");

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        });
    }
}