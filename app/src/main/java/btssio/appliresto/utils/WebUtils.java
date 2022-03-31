package btssio.appliresto.utils;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

public class WebUtils {

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "./");
            return d;
        } catch (Exception e) {
            System.out.println("Exc="+e);
            return null;
        }
    }
}
