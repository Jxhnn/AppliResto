package btssio.appliresto.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import btssio.appliresto.R;
import btssio.appliresto.activity.MainMenu;
import btssio.appliresto.holder.RestaurantViewHolder;
import btssio.appliresto.modele.Aimer;
import btssio.appliresto.modele.AimerDAO;
import btssio.appliresto.utils.WebUtils;


public class RestoAdapter extends ArrayAdapter {
    private String[] countryNames;
    private String[] capitalNames;
    private String[] imageid;
    private int[] idRestaurants;
    private Activity context;
    private HashMap<Integer, Integer> likeButtons;
    private ListView listView;


    public RestoAdapter(Activity context, String[] countryNames, String[] capitalNames, String[] imageid, int[] idRestaurants, ListView listView) {


        super(context, R.layout.row_item, countryNames);
        this.context = context;
        this.countryNames = countryNames;
        this.capitalNames = capitalNames;
        this.imageid = imageid;
        this.idRestaurants = idRestaurants;
        this.listView = listView;
        likeButtons = new HashMap<Integer, Integer>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RestaurantViewHolder holder = null;
        View row=convertView;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView==null) {
            row = inflater.inflate(R.layout.row_item, null, true);
            holder = new RestaurantViewHolder(row, listView, idRestaurants);
            row.setTag(holder);

            holder.textViewCountry = (TextView) row.findViewById(R.id.itemRestaurantName);
            holder.textViewCapital = (TextView) row.findViewById(R.id.itemRestaurantDesc);
            holder.imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);
            holder.likeButton = (ImageView) row.findViewById(R.id.likeButton);


        } else {
            holder = (RestaurantViewHolder) row.getTag();
        }



        //TODO : check for already liked restaurants

        AimerDAO likeManager = new AimerDAO(row.getContext());
        Aimer isLiked = likeManager.getAimer(RestaurantViewHolder.loggedUser.getMailU(), idRestaurants[position]);


        if (isLiked != null && isLiked.getAime() == true) {
            holder.likeButton.setImageResource(R.mipmap.liked);
        } else if (isLiked != null) {
            holder.likeButton.setImageResource(R.mipmap.unliked);
        }


        if (holder.textViewCountry != null) {
            holder.textViewCountry.setText(countryNames[position]);
            holder.textViewCapital.setText(capitalNames[position]);
        }

        Drawable b = null;
        try {
            b = new WebUtils().execute(imageid[position]).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (holder.imageFlag != null) {
            holder.imageFlag.setImageDrawable(b);
        }
        holder.likeButton.setClipToOutline(true);


        if (holder.likeButton != null && !holder.likeButton.hasOnClickListeners()) {
            Log.d("debug", "onclick created");
            holder.setLikeListener();
        }

        if (holder.imageFlag != null) {
            holder.imageFlag.setClipToOutline(true);
        }

        notifyDataSetChanged();
        return  row;
    }
}
