package com.example.agricare.commons;

import android.content.Context;
import android.content.res.Resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static File getFileFromRaw(Context context, int resId, String fileName) {
        Resources resources = context.getResources();
        File file = null;
        try {
            // Open the audio file from the raw folder
            if(resId == 0){
                return null;
            }
            InputStream inputStream = resources.openRawResource(resId);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

            // Create a new File Object
            file = new File(context.getExternalFilesDir(null), fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
