package btssio.appliresto.utils;

import android.content.Context;
import android.view.MenuInflater;
import android.view.View;

import btssio.appliresto.R;

public class PopupMenu {

    public static void showPopup(View v, Context context, boolean admin) {
        androidx.appcompat.widget.PopupMenu popup = new androidx.appcompat.widget.PopupMenu(context, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main_option_menu, popup.getMenu());

        popup.getMenu().findItem(R.id.secondItem).setVisible(admin);

        popup.show();
        popup.setOnMenuItemClickListener((androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener) context);

    }
}
