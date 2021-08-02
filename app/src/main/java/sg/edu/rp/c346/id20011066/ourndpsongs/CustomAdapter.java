package sg.edu.rp.c346.id20011066.ourndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {


    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvName = rowView.findViewById(R.id.textViewName);
        ImageView ivNew = rowView.findViewById(R.id.imageView);
        RatingBar rb = rowView.findViewById(R.id.rbStars);

        // Obtain the Android Version information based on the position
        Song currentVersion = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentVersion.getSingers());
        tvYear.setText(Integer.toString(currentVersion.getYears()));
        tvTitle.setText(currentVersion.getTitle());
        rb.setRating(currentVersion.getStars());

        if(currentVersion.getYears() >= 2019) {
            ivNew.setImageResource(R.drawable.newsong);
        } else {
            ivNew.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }
}
