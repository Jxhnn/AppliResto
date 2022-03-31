package btssio.appliresto.activity;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;

import btssio.appliresto.R;
import btssio.appliresto.adapter.RestoAdapter;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.RestoDAO;
import btssio.appliresto.utils.WebUtils;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private ImageView accountIcon;
    private ListView restaurantsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


        accountIcon = findViewById(R.id.accountIcon);
        // restaurantsList = findViewById(R.id.restaurantsList);

        accountIcon.setOnClickListener(this);


        RestoDAO restoManager = new RestoDAO(this);
        ArrayList<Resto> restorants = new ArrayList<Resto>();
        restorants = restoManager.getRestos();

        String[] nomRestaurants = new String[restorants.size()];
        Drawable[] imageRestaurants = new Drawable[restorants.size()];

        for (int i = 0; i <= restorants.size() - 1; i++) {
            nomRestaurants[i] = restorants.get(i).getNomR();
            imageRestaurants[i] = WebUtils.LoadImageFromWebOperations(restorants.get(i).getPhotoPrincipal());
        }

        // https://stackoverflow.com/a/8353790
        // https://java2blog.com/android-custom-listview-with-images-text-example/

    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main_option_menu, popup.getMenu());
        popup.show();

    }

    @Override
    public void onClick(View view) {

        if (view == accountIcon) {
            showPopup(view);
        }
    }
}
