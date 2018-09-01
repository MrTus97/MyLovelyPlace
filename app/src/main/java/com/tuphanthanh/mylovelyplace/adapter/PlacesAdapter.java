package com.tuphanthanh.mylovelyplace.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuphanthanh.mylovelyplace.R;
import com.tuphanthanh.mylovelyplace.data.model.Place;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlacesAdapter extends BaseAdapter {
    Context context;
    private List<Place> places;

    public PlacesAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
        setPlaces(places);
    }

    private void setPlaces(List<Place> places){
        this.places = places;
    }
    public void updatePlaces(List<Place> places){
        this.places = places;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int i) {
        return places.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PlaceViewHolder viewHolder = new PlaceViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view ==null){
            view = layoutInflater.inflate(R.layout.item_place,viewGroup,false);
            ButterKnife.bind(viewHolder,view);
            view.setTag(viewHolder);
        }
        viewHolder = (PlaceViewHolder) view.getTag();
        Place place = (Place) getItem(i);

        if(place.getPlaceImage() !=null){
            Bitmap placeBitmap = BitmapFactory.decodeByteArray(place.getPlaceImage(),0,place.getPlaceImage().length);
            viewHolder.imgPlace.setImageBitmap(placeBitmap);
        }

        viewHolder.txtPlaceName.setText(place.getPlaceName());
        viewHolder.txtPlaceDescription.setText(place.getPlaceDecription());
        viewHolder.txtPlaceAddress.setText(place.getPlaceAddress());
        return view;
    }
    class PlaceViewHolder{
        @BindView(R.id.imgItemPlace_Picture)
        ImageView imgPlace;
        @BindView(R.id.txtItemPlace_PlaceName)
        TextView txtPlaceName;
        @BindView(R.id.txtItemPlace_PlaceAddress)
        TextView txtPlaceAddress;
        @BindView(R.id.txtItemPlace_Description)
        TextView txtPlaceDescription;
    }
}
