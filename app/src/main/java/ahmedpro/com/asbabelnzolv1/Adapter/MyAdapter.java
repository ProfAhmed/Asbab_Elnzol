package ahmedpro.com.asbabelnzolv1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import ahmedpro.com.asbabelnzolv1.Model.ArrayListFragment;
import ahmedpro.com.asbabelnzolv1.Model.Data;

/**
 * Created by hp on 14/06/2017.
 */

public class MyAdapter extends FragmentStatePagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return Data.getList().size();
    }

    @Override
    public Fragment getItem(int position) {
        return ArrayListFragment.newInstance(Data.getContentList().get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Data.getList().get(position);
    }

    // This is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_UNCHANGED;
    }

    public int getPosition(String title) {
        return Data.getList().indexOf(title);
    }
}
