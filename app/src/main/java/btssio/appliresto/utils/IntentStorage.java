package btssio.appliresto.utils;

import android.content.Intent;
import java.util.HashMap;
import java.util.Vector;

/**
 * @Author: Jxhnn / Namirasoft
 * @Date: 08/12/2021
 * How to use : https://stackoverflow.com/a/62406575
 */
public class IntentStorage {
    // Number
    private static int Number = 1;

    public static int NextNumber() {
        return Number++;
    }

    //
    private static String _Intent_Identifier = "_Intent_Identifier";
    private static HashMap<Integer, Vector<Integer>> DeleteList = new HashMap<Integer, Vector<Integer>>();
    private static HashMap<Integer, HashMap<String, Object>> ObjectList = new HashMap<Integer, HashMap<String, Object>>();

    public static int GetIntent_Identifier(Intent I) {
        int Intent_Identifier = I.getIntExtra(_Intent_Identifier, 0);
        if (Intent_Identifier == 0)
            I.putExtra(_Intent_Identifier, Intent_Identifier = NextNumber());
        return Intent_Identifier;
    }

    public static void add(Intent I, String Name, Object O) {
        int Intent_Identifier = GetIntent_Identifier(I);
        synchronized (ObjectList) {
            if (!ObjectList.containsKey(Intent_Identifier))
                ObjectList.put(Intent_Identifier, new HashMap<String, Object>());
            ObjectList.get(Intent_Identifier).put(Name, O);
        }
    }

    public static <T> T get(Intent I, String Name) {
        int Intent_Identifier = GetIntent_Identifier(I);
        synchronized (DeleteList) {
            DeleteList.remove(Intent_Identifier);
        }
        return (T) ObjectList.get(Intent_Identifier).get(Name);
    }

    public static void remove(final Intent I) {
        final int Intent_Identifier = GetIntent_Identifier(I);
        final int ThreadID = NextNumber();
        synchronized (DeleteList) {
            if (!DeleteList.containsKey(Intent_Identifier))
                DeleteList.put(Intent_Identifier, new Vector<Integer>());
            DeleteList.get(Intent_Identifier).add(ThreadID);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                }
                synchronized (DeleteList) {
                    if (DeleteList.containsKey(Intent_Identifier))
                        if (DeleteList.get(Intent_Identifier).contains(ThreadID))
                            synchronized (ObjectList) {
                                ObjectList.remove(Intent_Identifier);
                            }
                }
            }
        }).start();
    }
}