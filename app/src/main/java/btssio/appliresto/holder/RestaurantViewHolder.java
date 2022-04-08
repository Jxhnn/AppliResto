package btssio.appliresto.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import btssio.appliresto.R;
import btssio.appliresto.modele.Aimer;
import btssio.appliresto.modele.AimerDAO;
import btssio.appliresto.modele.User;

public class RestaurantViewHolder implements View.OnClickListener {
    public TextView textViewCountry;
    public TextView textViewCapital;
    public ImageView imageFlag;
    public ImageView likeButton;
    private ListView listView;
    private int[] idRestaurants;
    public static User loggedUser;

    public RestaurantViewHolder(View v, ListView listView, int[] idRestaurants) {
        this.idRestaurants = idRestaurants;
        this.listView = listView;
    }

    @Override
    public void onClick(View view) {
        View item = (View) view.getParent();
        int pos = listView.getPositionForView(item);
        ImageView image = (ImageView) view;

        AimerDAO likeManager = new AimerDAO(image.getContext());
        Log.d("RETRIEVING: ", "email: " + loggedUser.getMailU()
                + " and idRestaurant: " + idRestaurants[pos]);
        Aimer liked = likeManager.getAimer(loggedUser.getMailU(), idRestaurants[pos]);
        Log.d("LIKED: ", "RETURNING email: " + liked.getAime());
        if (liked.getAime() == false) {
            liked.setAime(true);
            likeManager.updateLike(liked);
            Log.d("LIKED: ", "UPDATED liked = : " + liked.getAime());
            likeButton.setImageResource(R.mipmap.liked);
        } else {
            liked.setAime(false);
            likeManager.updateLike(liked);
            Log.d("LIKED: ", "UPDATED liked = : " + liked.getAime());
            likeButton.setImageResource(R.mipmap.unliked);
        }



        // likeButton.setImageResource(R.mipmap.liked);


    }

    public void setLikeListener() {
        likeButton.setOnClickListener(this);
    }
}
