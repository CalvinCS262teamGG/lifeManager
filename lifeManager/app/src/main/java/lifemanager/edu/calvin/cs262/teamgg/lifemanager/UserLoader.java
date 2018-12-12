package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

import android.content.Context;
import android.net.NetworkRequest;
import android.support.v4.content.AsyncTaskLoader;

public class UserLoader extends AsyncTaskLoader<String> {

    private String userQuery;

    public UserLoader(Context context, String query) {
        super(context);
        userQuery = query;
    }

    @Override
    public String loadInBackground() { return NetworkUtils.getUserInfo(userQuery); }

    @Override
    protected void onStartLoading() {forceLoad();}



}

