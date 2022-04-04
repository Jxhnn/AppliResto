package btssio.appliresto.utils;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WebUtils extends AsyncTask<String, Void, Drawable> {


    @Override
    protected Drawable doInBackground(String... urls) {

        final String url = urls[0];

        // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        // StrictMode.setThreadPolicy(policy);
        InputStream is = null;
        try {
            is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, ".");
            is.close();
            return d;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
