package tutorial.lorence.dummyjsonandroid.view.activities.home.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeActivity;
import tutorial.lorence.dummyjsonandroid.view.activities.home.adapter.UserAdapter;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class FragmentRecycler extends Fragment {

    private List<User> mGrouUsers;
    private HomeActivity mHomeActivity;
    private UserAdapter mUserAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    @SuppressLint("ValidFragment")
    public FragmentRecycler(Context context, HomeActivity homeActivity, UserAdapter userAdapter) {
        this.mContext = context;
        this.mHomeActivity = homeActivity;
        this.mUserAdapter = userAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mGrouUsers = mHomeActivity.getGroupUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mUserAdapter.updateData(mGrouUsers);
        mRecyclerView.setAdapter(mUserAdapter);
        return rootView;
    }
}