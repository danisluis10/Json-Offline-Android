package tutorial.lorence.dummyjsonandroid.view.activities.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.formats.NativeAd;

import java.util.List;

import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;
import tutorial.lorence.dummyjsonandroid.view.activities.home.adapter.viewholder.CustomHolder;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ViewType mViewType;
    private final Context mContext;
    private List<User> mListObject;

    /**
     * For this example app, the recyclerViewItems list contains only
     * {@link android.view.MenuItem} and {@link NativeAd} types.
     */
    public UserAdapter(Context context, ViewType viewType, List<User> list) {
        this.mContext = context;
        this.mListObject = list;
        this.mViewType = viewType;
    }

    @Override
    public int getItemCount() {
        return mListObject.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == mViewType.USER_VIEW_TYPE) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.item_viewholder_user, viewGroup, false);
            return new CustomHolder(itemView);
        }
        return  null;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType.USER_VIEW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == mViewType.USER_VIEW_TYPE) {
            CustomHolder holder = (CustomHolder) viewHolder;
            User item = mListObject.get(position);
            holder.tvUserName.setText(item.getUsername());
        }
    }

    public void updateData(List<User> users) {
        mListObject = users;
        notifyDataSetChanged();
    }
}
