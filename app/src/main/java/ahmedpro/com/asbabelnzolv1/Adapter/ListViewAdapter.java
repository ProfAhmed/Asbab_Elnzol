package ahmedpro.com.asbabelnzolv1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ahmedpro.com.asbabelnzolv1.R;
import ahmedpro.com.asbabelnzolv1.ViewPagerActivity;

/**
 * Created by hp on 13/06/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<String> surahs = null;
    private ArrayList<String> arraylist;

    public ListViewAdapter(Context context, List<String> worldpopulationlist) {
        mContext = context;
        this.surahs = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<String>();
        this.arraylist.addAll(worldpopulationlist);
    }


    public class ViewHolder {
        TextView surah_name;
    }

    @Override
    public int getCount() {
        return surahs.size();
    }

    @Override
    public String getItem(int position) {
        return surahs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_item_list, null);
            // Locate the TextViews in listview_item.xml
            holder.surah_name = (TextView) view.findViewById(R.id.surahnameTV);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.surah_name.setText(surahs.get(position));


        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, ViewPagerActivity.class);
                // Pass all data rank
                intent.putExtra("pos", getItem(position));
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        surahs.clear();
        if (charText.length() == 0) {
            surahs.addAll(arraylist);
        } else {
            for (String wp : arraylist) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    surahs.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
