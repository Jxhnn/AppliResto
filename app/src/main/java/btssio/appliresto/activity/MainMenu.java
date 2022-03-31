package btssio.appliresto.activity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import btssio.appliresto.R;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private ImageView accountIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getSupportActionBar().hide();

        accountIcon = findViewById(R.id.accountIcon);

        accountIcon.setOnClickListener(this);
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
