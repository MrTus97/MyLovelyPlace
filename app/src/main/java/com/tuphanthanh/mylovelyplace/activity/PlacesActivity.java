package com.tuphanthanh.mylovelyplace.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tuphanthanh.mylovelyplace.ActivityUtils;
import com.tuphanthanh.mylovelyplace.R;
import com.tuphanthanh.mylovelyplace.adapter.PlacesAdapter;
import com.tuphanthanh.mylovelyplace.data.PlaceRepo;
import com.tuphanthanh.mylovelyplace.data.model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlacesActivity extends AppCompatActivity {
    private String categoryID;

    @BindView(R.id.lvPlacesAct)
    ListView lvPlaces;
    @BindView(R.id.txtPlacesAct_NoData)
    TextView txtNoData;


    private List<Place> places = new ArrayList<>();
    private PlaceRepo placeRepo;
    private PlacesAdapter placesAdapter;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        ButterKnife.bind(this);
        init();

    }
    private void init(){
        categoryID = getIntent().getStringExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA);
        placeRepo = PlaceRepo.getInstance(this);
        placesAdapter = new PlacesAdapter(this,places);

        initProgressDialog();
        progressDialog.show();
        getPlaces();
        onPlaceClick();
    }
    private void getPlaces(){
        places = placeRepo.getPlaces(categoryID);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!places.isEmpty()){
                    txtNoData.setVisibility(View.GONE);
                }
                lvPlaces.setAdapter(placesAdapter);
                placesAdapter.updatePlaces(places);
                progressDialog.dismiss();
            }
        },2000);

    }
    private void initProgressDialog(){
        progressDialog = new ProgressDialog(PlacesActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_retrieving_data));
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void onPlaceClick(){
        lvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Place place = places.get(i);
                Intent intent = new Intent(PlacesActivity.this,DetailActivity.class);
                intent.putExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA,place.getPlaceId());
                intent.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA,place.getCategoryId());
                startActivity(intent);
            }
        });
    }



    @OnClick(R.id.fabPlacesAct_AddNewPlace)
    public void addNewPlace(View view){
        Intent addEditActInten = new Intent(PlacesActivity.this,AddEditActivity.class);
        addEditActInten.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA,categoryID);
        startActivity(addEditActInten);
    }
    @OnClick(R.id.btnPlacesAct_ShowAllOnMap)
    public void showAllOnMap(View view){
        Toast.makeText(getApplicationContext(),"Add new",Toast.LENGTH_SHORT).show();
    }
}
