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

public class CommentViewHolder {
    public TextView userName;
    public TextView descComment;
    public static User loggedUser;

    public CommentViewHolder(View v) {

    }
}
