package btssio.appliresto.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import btssio.appliresto.R;
import btssio.appliresto.activity.MainMenu;
import btssio.appliresto.utils.WebUtils;


public class RestoAdapter extends ArrayAdapter implements View.OnClickListener {
    private String[] countryNames;
    private String[] capitalNames;
    private String[] imageid;
    private Activity context;
    private int[] likeButtons;

    public RestoAdapter(Activity context, String[] countryNames, String[] capitalNames, String[] imageid) {
        super(context, R.layout.row_item, countryNames);
        this.context = context;
        this.countryNames = countryNames;
        this.capitalNames = capitalNames;
        this.imageid = imageid;
        likeButtons = new int[999999];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.itemRestaurantName);
        TextView textViewCapital = (TextView) row.findViewById(R.id.itemRestaurantDesc);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);
        ImageView likeButton = (ImageView) row.findViewById(R.id.likeButton);
        //TODO : check for already liked restaurants

        textViewCountry.setText(countryNames[position]);
        textViewCapital.setText(capitalNames[position]);

        Drawable b = null;
        try {
            b = new WebUtils().execute(imageid[position]).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageFlag.setImageDrawable(b);
        likeButton.setOnClickListener(this);
        // likeButtons[likeButton.getId()] = position;
        imageFlag.setClipToOutline(true);


        return  row;
    }

    @Override
    public void onClick(View view) {
        Log.d("DEBUG BUTTON", "POSITION :" + likeButtons[view.getId()]);
    }
}
