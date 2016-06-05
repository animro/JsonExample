package tcs.com.drawapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by 1256104 on 6/3/2016.
 */
public class NetworkCallAsync extends AsyncTask<String,Integer,String>{

    ProgressDialog mProgressDialog;
    Context mContext;
    OnNetworkDataReceivedListner mCallback;
    NetworkCallAsync(Context context,OnNetworkDataReceivedListner call){
        mCallback = call;
        mContext = context;
        mProgressDialog = new ProgressDialog(mContext);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setMessage("Retrieving data");
        mProgressDialog.show();

    }
    @Override
    protected String doInBackground(String... params) {
        String data = new MyConnection().getData(params[0]);
        return data;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressDialog.setProgress(values[0]);

    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressDialog.dismiss();
        mCallback.dataReceived(s);
    }
}
