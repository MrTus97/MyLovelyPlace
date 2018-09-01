package com.tuphanthanh.mylovelyplace.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tuphanthanh.mylovelyplace.ActivityUtils;
import com.tuphanthanh.mylovelyplace.R;
import com.tuphanthanh.mylovelyplace.data.PlaceRepo;
import com.tuphanthanh.mylovelyplace.data.model.Place;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.imgDetailAct_PlacePicture)
    ImageView imgPlacePicture;
    @BindView(R.id.edtDetaiActl_PlaceName)
    EditText edtPlaceName;
    @BindView(R.id.edtDetailAct_Address)
    EditText edtPlaceAddress;
    @BindView(R.id.edtDetailAct_Description)
    EditText edtPlaceDescription;

    private String placeId;
    private String categoryId;

    private PlaceRepo placeRepo;
    private Place place;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        placeId = getIntent().getStringExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA);
        categoryId = getIntent().getStringExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA);
        placeRepo = PlaceRepo.getInstance(this);
        Log.d("Test",placeId + " " + categoryId);
        initProgressDialog();
        setPlace();

    }
    private void setPlace(){
        place = addTestData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (place.getPlaceImage() != null){
                    Bitmap placeBitmap = BitmapFactory.decodeByteArray(place.getPlaceImage(),0,place.getPlaceImage().length);
                    imgPlacePicture.setImageBitmap(placeBitmap);
                }
                edtPlaceName.setText(place.getPlaceName());
                edtPlaceAddress.setText(place.getPlaceAddress());
                edtPlaceDescription.setText(place.getPlaceDecription());
            }
        },4000);
        progressDialog.dismiss();

    }
    private void initProgressDialog(){
        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.text_retrieving_data));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
    private Place addTestData(){
        return new Place.Builder()
                .setPlaceId(UUID.randomUUID().toString())
                .setCategoryId(categoryId)
                .setPlaceImage(null)
                .setPlaceName("ABCD")
                .setPlaceAddress("ABCD")
                .setPlaceDecription("ABCD")
                .setPlaceLat(0)
                .setPlaceLong(0)
                .build();

    }
    @OnClick(R.id.imgDetailAct_EditPicture)
    public void editPlace(View view){

        Intent intent = new Intent(DetailActivity.this,AddEditActivity.class);
        intent.putExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA,place.getPlaceId());
        intent.putExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA,place.getCategoryId());
        startActivity(intent);
    }
    @OnClick(R.id.imgDetailAct_DeletePicture)
    public void deletePlace(View view){
        AlertDialog.Builder alertDialog =  new AlertDialog.Builder(this);
        alertDialog.setTitle(getResources().getString(R.string.text_warning));
        alertDialog.setMessage(
                getResources().getString(R.string.warning_do_you_want_to_delete)
                        +"'"+ place.getPlaceName()+"' ?"
        );
        alertDialog.setPositiveButton(getResources().getString(R.string.text_positive),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_LONG).show();
                placeRepo.delete(placeId);
            }

        });
        alertDialog.setNegativeButton(getResources().getString(R.string.text_negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.imgDetailAct_Redirect)
    public void redirectPlace(View view){

    }
}
