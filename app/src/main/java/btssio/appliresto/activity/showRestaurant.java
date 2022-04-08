package btssio.appliresto.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import btssio.appliresto.R;
import btssio.appliresto.adapter.CommentAdapter;
import btssio.appliresto.modele.Critique;
import btssio.appliresto.modele.CritiqueDAO;
import btssio.appliresto.modele.Resto;
import btssio.appliresto.modele.User;
import btssio.appliresto.modele.UserDAO;
import btssio.appliresto.utils.IntentStorage;
import btssio.appliresto.utils.WebUtils;

public class showRestaurant extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private TextView titreResto, descResto, globalNote, commentNumber, scheduleRestaurant, addressResto;
    private ImageView imageViewResto, globalNoteIcon, backButtonShow, accountIcon;
    private Button addComment;
    private Resto thisResto;
    private User loggedUser;
    private ListView commentList;
    private String[] userNames;
    private String[] descComments;
    private int avgNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_restaurant);
        getSupportActionBar().hide();

        titreResto = findViewById(R.id.titreResto);
        descResto = findViewById(R.id.descResto);
        imageViewResto = findViewById(R.id.imageViewResto);
        commentList = findViewById(R.id.commentList);
        globalNoteIcon = findViewById(R.id.globalNoteIcon);
        globalNote = findViewById(R.id.globalNote);
        commentNumber = findViewById(R.id.commentNumber);
        backButtonShow = findViewById(R.id.backButtonShow);
        scheduleRestaurant = findViewById(R.id.scheduleResto);
        addressResto = findViewById(R.id.addressResto);
        accountIcon = findViewById(R.id.accountIcon);
        addComment = findViewById(R.id.addComment);

        globalNoteIcon.getLayoutParams().height = 50;
        globalNoteIcon.getLayoutParams().width = 50;

        backButtonShow.setOnClickListener(this);
        accountIcon.setOnClickListener(this);
        addComment.setOnClickListener(this);

        thisResto = IntentStorage.get(getIntent(), "thisResto");
        loggedUser = IntentStorage.get(getIntent(), "loggedUser");

        titreResto.setText(thisResto.getNomR());
        descResto.setText(thisResto.getDescR());

        commentList.setDivider(new ColorDrawable(0xD3D3D3FF));
        commentList.setDividerHeight(3);

        Drawable b = null;
        try {
            b = new WebUtils().execute(thisResto.getPhotoPrincipal()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        imageViewResto.setImageDrawable(b);
        imageViewResto.setClipToOutline(true);
        imageViewResto.setOutlineProvider(ViewOutlineProvider.BACKGROUND);

        CritiqueDAO commentManager = new CritiqueDAO(this);
        UserDAO userManager = new UserDAO(this);
        ArrayList<Critique> allComments = commentManager.getCommentsOfRestaurant(thisResto.getIdR());

        userNames = new String[allComments.size()];
        descComments = new String[allComments.size()];

        for (int i = 0; i <= allComments.size() - 1; i++) {
            String userName = userManager.getUsernameFromEmail(allComments.get(i).getMailU());
            userNames[i] = userName;
            descComments[i] = allComments.get(i).getCommentaire();
            avgNote += allComments.get(i).getNote();
        }

        if (allComments.size() > 0) avgNote /= allComments.size();
        else avgNote = 0;

        commentNumber.setText(" (" + allComments.size() + " notes)");
        globalNote.setText("" + avgNote);
        scheduleRestaurant.setText(thisResto.getHorairesR());
        addressResto.setText(thisResto.getNumAdrR() + " "
                + thisResto.getVoieAdrR() + ", "
                + thisResto.getCpR() + " "
                + thisResto.getVilleR());


        if (descComments != null) {
            CommentAdapter adapter = new CommentAdapter(this, userNames, descComments, commentList);
            commentList.setAdapter(adapter);
        }



    }

    @Override
    protected void onDestroy() {
        IntentStorage.remove(getIntent());
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view  == backButtonShow) {
            Intent mainMenu = new Intent(showRestaurant.this, MainMenu.class);
            IntentStorage.add(mainMenu, "LoggedUser", loggedUser);
            startActivity(mainMenu);
        } else if (view == accountIcon) {
            btssio.appliresto.utils.PopupMenu.showPopup(view, this);
        } else if (view == addComment) {
            Intent addCommentActivity = new Intent(showRestaurant.this, CritiquesResto.class);
            IntentStorage.add(addCommentActivity, "LoggedUser", loggedUser);
            IntentStorage.add(addCommentActivity, "thisResto", thisResto);
            startActivity(addCommentActivity);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
