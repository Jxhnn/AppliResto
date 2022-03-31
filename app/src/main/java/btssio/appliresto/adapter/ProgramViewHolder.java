package btssio.appliresto.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import btssio.appliresto.R;

public class ProgramViewHolder {

    ImageView itemImage;
    TextView itemName;

    public ProgramViewHolder(View v) {
        itemImage = v.findViewById(R.id.imageView1);
        itemName = v.findViewById(R.id.textView1);
    }
}
