package com.tuphanthanh.mylovelyplace.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tuphanthanh.mylovelyplace.ActivityUtils;
import com.tuphanthanh.mylovelyplace.R;
import com.tuphanthanh.mylovelyplace.data.PlaceRepo;
import com.tuphanthanh.mylovelyplace.data.model.Category;
import com.tuphanthanh.mylovelyplace.data.model.Place;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoriesActivity extends AppCompatActivity {

    @BindView(R.id.txtCategoriesAct_Restaurant)
    TextView txtRestaurant;
    @BindView(R.id.txtCategoriesAct_Cinema)
    TextView txtCinema;
    @BindView(R.id.txtCategoriesAct_ATM)
    TextView txtAtm;
    @BindView(R.id.txtCategoriesAct_Fashion)
    TextView txtFashion;

    private PlaceRepo placeRepo;
    private List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        placeRepo = PlaceRepo.getInstance(this);
        categories = placeRepo.getCategory();
        Log.d("Test",String.valueOf(categories.size()));
    }
    private void setCategories(){
        txtRestaurant.setText(categories.get(0).getCategoryName());
        txtCinema.setText(categories.get(1).getCategoryName());
        txtFashion.setText(categories.get(2).getCategoryName());
        txtAtm.setText(categories.get(3).getCategoryName());
    }

    private void startPlacesAct(String categoryID){
        Intent intent = new Intent(CategoriesActivity.this, PlacesActivity.class);
        intent.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA,categoryID);
        startActivity(intent);
    }

    @OnClick(R.id.layoutCategoriesAct_Restaurant)
    public void clickOnRestaurant(View v){
        String categoryId = categories.get(0).getCategoryId();
        startPlacesAct(categoryId);
    }
    @OnClick(R.id.layoutCategoriesAct_Cinema)
    public void clickOnCinema(View v){
        String categoryId = categories.get(1).getCategoryId();
        startPlacesAct(categoryId);
    }
    @OnClick(R.id.layoutCategoriesAct_Fashion)
    public void clickOnFashion(View v){
        String categoryId = categories.get(2).getCategoryId();
        startPlacesAct(categoryId);
    }
    @OnClick(R.id.layoutCategoriesAct_ATM)
    public void clickOnAtm(View v){
        String categoryId = categories.get(3).getCategoryId();
        startPlacesAct(categoryId);
    }

}
