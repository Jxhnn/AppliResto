package btssio.appliresto.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import btssio.appliresto.R;
import btssio.appliresto.activity.CritiquesResto;
import btssio.appliresto.activity.MainMenu;
import btssio.appliresto.activity.showRestaurant;
import btssio.appliresto.holder.CommentViewHolder;
import btssio.appliresto.modele.CritiqueDAO;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.User;
import btssio.appliresto.utils.IntentStorage;


public class CommentAdapter extends ArrayAdapter implements View.OnClickListener {
    private String[] userNames;
    private String[] descComments;
    private String[] emails;
    private Activity context;
    private User loggedUser;
    private ListView listView;
    private Resto thisResto;


    public CommentAdapter(Activity context, String[] userNames, String[] descComments, String[] emails,ListView listView, User loggedUser, Resto thisResto) {
        super(context, R.layout.row_item2, userNames);
        this.context = context;
        this.userNames = userNames;
        this.descComments = descComments;
        this.loggedUser = loggedUser;
        this.listView = listView;
        this.emails = emails;
        this.thisResto = thisResto;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommentViewHolder holder = null;
        View row=convertView;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView==null) {
            row = inflater.inflate(R.layout.row_item2, null, true);
            holder = new CommentViewHolder(row);
            row.setTag(holder);

            holder.userName = (TextView) row.findViewById(R.id.userNameComment);
            holder.descComment = (TextView) row.findViewById(R.id.descCommentComment);
            holder.deleteComment = (Button) row.findViewById(R.id.deleteComment);

        } else {
            holder = (CommentViewHolder) row.getTag();
        }

        holder.userName.setText(userNames[position] + " à dit :");
        holder.descComment.setText(descComments[position]);

        if (userNames[position].equals(loggedUser.getPseudoU())) {
            holder.userName.setTextColor(Color.rgb(47, 126, 51));
            holder.descComment.setTextColor(Color.rgb(47, 126, 51));

            holder.deleteComment.setVisibility(View.VISIBLE);


        } else {
            holder.userName.setTextColor(Color.rgb(0, 0, 0));
            holder.descComment.setTextColor(Color.rgb(0, 0, 0));
            if (!loggedUser.isAdmin()) holder.deleteComment.setVisibility(View.INVISIBLE);
        }


        if (!holder.deleteComment.hasOnClickListeners()) {
            holder.deleteComment.setOnClickListener(this);
        }

        notifyDataSetChanged();
        return row;
    }

    @Override
    public void onClick(View view) {

        View item = (View) view.getParent();
        int pos = listView.getPositionForView(item);
        CritiqueDAO commentManager = new CritiqueDAO(listView.getContext());

        commentManager.supprimerCrit(thisResto.getIdR(), emails[pos]);
        Toast.makeText(listView.getContext(), "Commentaire supprimé ! ", Toast.LENGTH_SHORT).show();

        restartActivity();
    }

    public void restartActivity() {
        Intent intent = context.getIntent();
        IntentStorage.add(intent, "LoggedUser", loggedUser);
        IntentStorage.add(intent, "thisResto", thisResto);
        context.finish();
        context.startActivity(intent);
    }
}
