package data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.danieltilley.lastfmtoptracks.ActivityTrackDetails;
import com.example.danieltilley.lastfmtoptracks.AppController;
import com.example.danieltilley.lastfmtoptracks.R;

import java.util.ArrayList;

import model.Song;

/**
 * Created by danieltilley on 19/01/2017.
 */

public class CustomListviewAdapter extends ArrayAdapter<Song>{

    private LayoutInflater inflater;
    private ArrayList<Song> data;
    private Activity mContext;
    private int layoutResourceId;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public CustomListviewAdapter(Activity context, int resource, ArrayList<Song> objs) {
        super(context, resource, objs);
        data = objs;
        mContext = context;
        layoutResourceId = resource;
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getPosition(Song item) {
        return super.getPosition(item);
    }

    @Override
    public Song getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row = convertView;
        ViewHolder viewHolder = null;

        if ( row == null) {

            inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(layoutResourceId, parent, false);

            viewHolder = new ViewHolder();


            //Get references to our views
            viewHolder.artistImage = (NetworkImageView)row.findViewById(R.id.trackImageMain);
            viewHolder.trackName = (TextView) row.findViewById(R.id.trackNameMain);
            viewHolder.artistName = (TextView) row.findViewById(R.id.artistNameMain);
            viewHolder.trackRank = (TextView) row.findViewById(R.id.rankMain);

            row.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder) row.getTag();
        }

        viewHolder.song = data.get(position);

        //We can now display the data

        viewHolder.artistName.setText("Artist: " + viewHolder.song.getArtistName());
        viewHolder.trackName.setText("Track: " + viewHolder.song.getTackName());
        viewHolder.trackRank.setText("Rank: " + viewHolder.song.getTrackRank());
        viewHolder.artistImage.setImageUrl(viewHolder.song.getArtistImageURL(), imageLoader);

        final ViewHolder finalViewHolder = viewHolder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //go to new screen
                Intent i = new Intent(mContext, ActivityTrackDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("songObj", finalViewHolder.song);
                i.putExtras(mBundle);
                mContext.startActivity(i);
            }//end onClick
        });
        return row;
    }//end getView


    //ViewHolder Class used to display current row
    public class ViewHolder {
        Song song;
        TextView artistName;
        TextView trackName;
        TextView trackRank;
        NetworkImageView artistImage;
    }//end ViewHolder
}//endCustomListAdapter
