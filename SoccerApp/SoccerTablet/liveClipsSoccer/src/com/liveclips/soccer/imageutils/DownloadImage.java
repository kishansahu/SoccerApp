/**
 * 
 */
package com.liveclips.soccer.imageutils;

import java.io.InputStream;
import java.util.concurrent.Callable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author mohitkumar
 *
 */
public class DownloadImage implements Callable<Boolean> {
	
	String imageUrl;
    ImageView bmImage;

    public DownloadImage(ImageView bmImage, String imageUrl) {
        this.bmImage = bmImage;
        this.imageUrl = imageUrl;
    }

    /*protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }*/

    protected Boolean onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        return true;
    }

	@Override
	public Boolean call() throws Exception {
		String urldisplay = imageUrl;
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            //e.printStackTrace();
        }
        
        return onPostExecute(mIcon11);
	}

}
