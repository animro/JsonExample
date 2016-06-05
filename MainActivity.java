package tcs.com.drawapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnNetworkDataReceivedListner{

    TextView tdata,trole,tdob,tplace;
    ImageView timage;

    private List<Hotel> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private HotelAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tdata = (TextView)findViewById(R.id.tv_data);
        trole = (TextView)findViewById(R.id.tv_role);
        tdob = (TextView)findViewById(R.id.tv_dob);
        tplace =(TextView)findViewById(R.id.tv_place);

        timage = (ImageView)findViewById(R.id.iv_image);

        new NetworkCallAsync(this,this).execute("http://172.26.49.129:8080/HotelExamples.json");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new HotelAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void dataReceived(String data) {
        try {
            Bitmap bitmap;

            LoadImageAsync loadIamge;
            JSONObject json = new JSONObject(data);
            JSONObject query = json.getJSONObject("HotelListResponse");
          //  JSONObject results = query.getJSONObject("numberOfRoomsRequested");
            JSONArray playerArray = json.optJSONArray("HotelList");

           /* trole.setText(query.getString("numberOfRoomsRequested") + ", " + query.getString("priceRange"));*/

           // JSONObject playerDetails = player.getJSONObject("HotelList");
            for(int i=0; i < 8; i++) {
                JSONObject jsonObject = playerArray.getJSONObject(i);
                int id = Integer.parseInt(jsonObject.optString("id").toString());
                loadIamge = new LoadImageAsync(this,timage);
                loadIamge.execute("http://172.26.49.129:8080/hotel.jpg");
                Hotel dataset ;

                bitmap = Hotel.getImage();
                dataset= new Hotel(bitmap,(query.getString("numberOfRoomsRequested") + ", " + query.getString("priceRange")),jsonObject.getString("address").toString(), jsonObject.getString("HotelName").toString(),jsonObject.getString("phoneNumber").toString(),jsonObject.getString("city"));

                list.add(dataset);

                mAdapter.notifyDataSetChanged();
                /*tdata.setText(jsonObject.getString("address").toString() + " " + jsonObject.getString("HotelName").toString());
                tdob.setText(jsonObject.getString("phoneNumber").toString());

               // JSONObject placeOfBirth = jsonObject.getJSONObject("roomType");
                tplace.setText(jsonObject.getString("city"));*/
            }
            //  new LoadImageAsync(this,timage).execute(playerDetails.getString("PlayerThumbImgName"));
        } catch (JSONException e) {
            Log.d("JSON ERROR",e.toString());
            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }







    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
