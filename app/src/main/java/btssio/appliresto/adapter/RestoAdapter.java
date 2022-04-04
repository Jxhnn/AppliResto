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
import btssio.appliresto.utils.WebUtils;


public class RestoAdapter extends ArrayAdapter implements View.OnClickListener {
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
        // final RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(convertView);
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



        if (!likeButton.hasOnClickListeners()) {
            Log.d("debug", "onclick created");
            likeButton.setOnClickListener(this);
        }

        imageFlag.setClipToOutline(true);

        return  row;
    }

    @Override
    public void onClick(View view) {

        View item = (View) view.getParent();
        int pos = listView.getPositionForView(item);
        // long id = listView.getItemIdAtPosition(pos);
        view.setTag(pos);

        Log.d("pos: ", "" + idRestaurants[pos]);
        ImageView image = (ImageView) listView.findViewWithTag(pos);
        image.setImageResource(R.mipmap.liked);

        Log.d("id: ", "" + view.getId());

    }
}
