package com.tuphanthanh.mylovelyplace.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tuphanthanh.mylovelyplace.ActivityUtils;
import com.tuphanthanh.mylovelyplace.R;
import com.tuphanthanh.mylovelyplace.data.PlaceRepo;
import com.tuphanthanh.mylovelyplace.data.model.Place;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEditActivity extends AppCompatActivity {
    @BindView(R.id.imgAddEditAct_PlacePicture)
    ImageView imgPlacePicture;
    @BindView(R.id.edtAddEditAct_PlaceName)
    EditText edtPlaceName;
    @BindView(R.id.edtAddEditAct_Description)
    EditText edtPlaceDescription;
    @BindView(R.id.edtAddEditAct_Address)
    EditText edtPlaceAddress;

    private String placeId;
    private String categoryId;
    private PlaceRepo placeRepo;
    private boolean hasImage = false;
    private boolean allowSave = false;

    private static final int IMAGE_CAPTURE_REQUEST_CODE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_CAPTURE_REQUEST_CODE && resultCode == RESULT_OK){
            if (data ==null){
                //Thêm mới
                if (placeId == null){
                    hasImage = false;
                    allowSave = false;
                }else{
                    //cập nhật
                    hasImage = true;
                }

            }else{
                hasImage = true;
                allowSave = true;
                Bitmap placeImage = (Bitmap) data.getExtras().get("data");
                imgPlacePicture.setImageBitmap(placeImage);
            }
        }
    }

    private void init() {
        placeId = getIntent().getStringExtra(ActivityUtils.PLACE_KEY_PUT_EXTRA);
        categoryId = getIntent().getStringExtra(ActivityUtils.CATEGORY_KEY_PUT_EXTRA);
        placeRepo = PlaceRepo.getInstance(this);

        if(placeId != null){
            hasImage = true;
        }
    }

    @OnClick(R.id.btnAddEditAct_Save)
    public void savePlace(View view){
        String placeName = edtPlaceName.getText().toString();
        String placeAddress = edtPlaceAddress.getText().toString();
        String placeDescription = edtPlaceDescription.getText().toString();


        if (Place.validateInput(placeName,placeAddress,placeDescription,categoryId)){
            allowSave = true;
        }else {
            Toast.makeText(getApplicationContext(),"Please fill in place's infomation",Toast.LENGTH_SHORT).show();
            allowSave = false;
        }
        if (allowSave){
            //Thêm mới
            if (hasImage )
            if (hasImage &&placeId ==null){
                Toast.makeText(getApplicationContext(),"Add new",Toast.LENGTH_SHORT).show();
            }
            //Cập nhật
            if (placeId!=null){
                Toast.makeText(getApplicationContext(),"Edit",Toast.LENGTH_SHORT).show();
            }
        }

    }
    @OnClick(R.id.imgAddEditAct_PlacePicture)
    public void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_REQUEST_CODE);
    }
}
