package com.example.agricare;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import com.example.agricare.commons.JsonParser;
import com.example.agricare.commons.PreferenceHelper;
import com.example.agricare.models.Agriculture;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    CardView cardViewCrop, cardViewLiveStock, cardViewFish;
    SearchView searchView;
    ListView listView;
    String [] names;
    ArrayAdapter<String> arrayAdapter;
    private String language = "en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        if(PreferenceHelper.getInstance(this).contains(PreferenceHelper.LANGUAGE))
            language = PreferenceHelper.getInstance(this).getString(PreferenceHelper.LANGUAGE);

        cardViewCrop = findViewById(R.id.cardViewCrop);
        cardViewLiveStock = findViewById(R.id.cardViewLiveStock);
        cardViewFish = findViewById(R.id.cardViewFish);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

        names = new String[] {
                getString(R.string.rice),
                getString(R.string.sugar_cane),
                getString(R.string.corn),
                getString(R.string.chicken),
                getString(R.string.pig),
                getString(R.string.cow),
                getString(R.string.goat),
                getString(R.string.tilapia),
                getString(R.string.hito)};
        List<Agriculture> cropList = new ArrayList<>(new JsonParser().getCrops(this, language));
        List<Agriculture> liveStockList = new ArrayList<>(new JsonParser().getLiveStock(this, language));
        List<Agriculture> fishList = new ArrayList<>(new JsonParser().getFish(this, language));
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);

        listView.setVisibility(View.GONE);

        ImageView ivSetting = findViewById(R.id.iv_setting);
        ivSetting.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            listView.setVisibility(View.GONE);
            searchView.setQuery("", false);
            String selectedItem = listView.getItemAtPosition(position).toString().toLowerCase();
            Log.d("selected_item", selectedItem);
            for(int i = 0; i< cropList.size(); i++){
                if(cropList.get(i).getLabel().toLowerCase().equals(selectedItem)){
                    Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable(ObjectViewActivity.AGRICULTURE, cropList.get(i));
                    intent.putExtras(b);
                    intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.CROPS);
                    startActivity(intent);
                    return;
                }
            }
            for(int i = 0; i< liveStockList.size(); i++){
                if(liveStockList.get(i).getLabel().toLowerCase().equals(selectedItem)){
                    Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable(ObjectViewActivity.AGRICULTURE, liveStockList.get(i));
                    intent.putExtras(b);
                    intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.LIVESTOCK);
                    startActivity(intent);
                    return;
                }
            }
            for(int i = 0; i< fishList.size(); i++){
                if(fishList.get(i).getLabel().toLowerCase().equals(selectedItem)){
                    Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable(ObjectViewActivity.AGRICULTURE, fishList.get(i));
                    intent.putExtras(b);
                    intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.FISH);
                    startActivity(intent);
                    return;
                }
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

        cardViewCrop.setOnClickListener(v -> {
            Dialog dialog = new Dialog(HomeActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(R.layout.crop_selection_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();

            wlp.gravity = Gravity.BOTTOM;

            window.setAttributes(wlp);

            LinearLayout linearLayoutPalay, linearLayoutTubo, linearLayoutMais;

            linearLayoutPalay = dialog.findViewById(R.id.linearLayoutPalay);
            linearLayoutTubo = dialog.findViewById(R.id.linearLayoutTubo);
            linearLayoutMais = dialog.findViewById(R.id.linearLayoutMais);

            linearLayoutPalay.setOnClickListener(v1 -> {
                Agriculture palay = cropList.get(0);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, palay);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.CROPS);
                startActivity(intent);
            });
            linearLayoutTubo.setOnClickListener(v12 -> {
                Agriculture tubo = cropList.get(1);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, tubo);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.CROPS);
                startActivity(intent);
            });
            linearLayoutMais.setOnClickListener(v13 -> {
                Agriculture mais = cropList.get(2);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, mais);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.CROPS);
                startActivity(intent);
            });

            dialog.show();
        });

        cardViewLiveStock.setOnClickListener(v -> {
            Dialog dialog = new Dialog(HomeActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(R.layout.live_stock_selection_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
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

            linearLayoutManok.setOnClickListener(v15 -> {
                Agriculture manok = liveStockList.get(0);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, manok);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.LIVESTOCK);
                startActivity(intent);
            });
            linearLayoutBaboy.setOnClickListener(v14 -> {
                Agriculture baboy = liveStockList.get(1);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, baboy);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.LIVESTOCK);
                startActivity(intent);
            });
            linearLayoutBaka.setOnClickListener(v16 -> {
                Agriculture baka = liveStockList.get(2);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, baka);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.LIVESTOCK);
                startActivity(intent);
            });
            linearLayoutKambing.setOnClickListener(v17 -> {
                Agriculture kambing = liveStockList.get(3);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, kambing);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.LIVESTOCK);
                startActivity(intent);
            });

            dialog.show();
        });

        cardViewFish.setOnClickListener(v -> {
            Dialog dialog = new Dialog(HomeActivity.this);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(R.layout.fish_selection_layout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();

            wlp.gravity = Gravity.BOTTOM;

            window.setAttributes(wlp);

            LinearLayout linearLayoutTilapia, linearLayoutHito;

            linearLayoutTilapia = dialog.findViewById(R.id.linearLayoutTilapia);
            linearLayoutHito = dialog.findViewById(R.id.linearLayoutHito);
            linearLayoutTilapia.setOnClickListener(v18 -> {
                Agriculture tilapia = fishList.get(0);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, tilapia);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.FISH);
                startActivity(intent);
            });
            linearLayoutHito.setOnClickListener(v19 -> {
                Agriculture hito = fishList.get(1);
                Intent intent= new Intent(HomeActivity.this, ObjectViewActivity.class);
                Bundle b = new Bundle();
                b.putSerializable(ObjectViewActivity.AGRICULTURE, hito);
                intent.putExtras(b);
                intent.putExtra(ObjectViewActivity.TYPE, ObjectViewActivity.AGRICULTURE_TYPE.FISH);
                startActivity(intent);
            });

            dialog.show();
        });
    }
}