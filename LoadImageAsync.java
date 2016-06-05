package tcs.com.drawapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by 1256104 on 6/3/2016.
 */
public class LoadImageAsync extends AsyncTask<String, Integer, Bitmap> {

    ProgressDialog mDialog;
    private Context mContext;
    private ImageView mImageView;
    Hotel hotel;

    public LoadImageAsync(Context c, ImageView iv) {
        mContext = c;
        mDialog = new ProgressDialog(mContext);
        mImageView = iv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog.setMessage("Retrieving image...");
        mDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = new MyConnection().getImageBitmap(params[0]);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap s) {

        mDialog.dismiss();
        if (s != null) {
            Hotel.setImage(s);
            
        }
    }
}
