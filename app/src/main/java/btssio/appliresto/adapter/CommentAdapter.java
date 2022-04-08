package btssio.appliresto.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import btssio.appliresto.R;
import btssio.appliresto.holder.CommentViewHolder;
import btssio.appliresto.holder.RestaurantViewHolder;
import btssio.appliresto.modele.Aimer;
import btssio.appliresto.modele.AimerDAO;
import btssio.appliresto.utils.WebUtils;


public class CommentAdapter extends ArrayAdapter {
    private String[] userNames;
    private String[] descComments;
    private Activity context;


    public CommentAdapter(Activity context, String[] userNames, String[] descComments, ListView listView) {
        super(context, R.layout.row_item2, userNames);
        this.context = context;
        this.userNames = userNames;
        this.descComments = descComments;
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


        } else {
            holder = (CommentViewHolder) row.getTag();
        }

        holder.userName.setText(userNames[position] + " à dit :");
        holder.descComment.setText(descComments[position]);

        notifyDataSetChanged();
        return row;
    }
}
