package tcs.com.drawapp;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 1256104 on 6/3/2016.
 */
public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewAdapter> {
    Bitmap bit;
    private List<Hotel> list;

    HotelAdapter(List<Hotel> list){
        this.list = list;
    }
    public class ViewAdapter extends RecyclerView.ViewHolder{
        public TextView address, rating, price,city;
        public ImageView iv ;

        public ViewAdapter(View itemView) {
            super(itemView);
            address = (TextView)itemView.findViewById(R.id.tv_role);
            rating = (TextView)itemView.findViewById(R.id.tv_data);
            price = (TextView)itemView.findViewById(R.id.tv_place);
            city = (TextView)itemView.findViewById(R.id.tv_dob);
            iv=(ImageView)itemView.findViewById(R.id.iv_image);
        }
    }

    @Override
    public ViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);

        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.ViewAdapter holder, int position) {
//        Movie movie = moviesList.get(position);
//        holder.title.setText(movie.getTitle());
//        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
            Hotel hotel = list.get(position);
            holder.address.setText(hotel.getAddress());
            holder.rating.setText(hotel.getRating());
            holder.price.setText(hotel.getPrice());
            holder.city.setText(hotel.getCity());
            holder.iv.setImageBitmap(hotel.getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
