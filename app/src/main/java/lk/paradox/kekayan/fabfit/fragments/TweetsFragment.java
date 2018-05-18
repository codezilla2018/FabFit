package lk.paradox.kekayan.fabfit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;

import java.util.Locale;

import lk.paradox.kekayan.fabfit.R;


public class TweetsFragment extends Fragment {
    //used twitter kit for android to retrive tweets
    //from their rest api | search function
    private Context context;
    private RecyclerView searchTimelineRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TweetTimelineRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tweets, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterConfig config = new TwitterConfig.Builder(context)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString
                        //passing Twitter API Key and Secret
                                (R.string.com_twitter_sdk_android_CONSUMER_KEY),
                        getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpSwipeRefreshLayout(view);
        setUpRecyclerView(view);
        searchtweets();
    }

    public void searchtweets() {
        SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#fitnesstips")//the search query for Tweets here hashtag for fitness
                .languageCode(Locale.ENGLISH.getLanguage())//set the language code
                .maxItemsPerRequest(50)//Max number of items to return per request
                .build();
        //creating adapter for RecyclerView
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(context)
                .setTimeline(searchTimeline)//set created timeline
                //.setTimelineFilter(timelineFilter) //set timeline filter if any required eg:porn
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();

        //finally seting created adapter to recycler view
        searchTimelineRecyclerView.setAdapter(adapter);
        //Log.d("tweets",searchTimeline.toString());

    }

    private void setUpRecyclerView(View view) {
        searchTimelineRecyclerView = view.findViewById(R.id.search_timeline_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        searchTimelineRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setUpSwipeRefreshLayout(View view) {

        //finding the id of swipe refresh layout
        swipeRefreshLayout = view.findViewById(R.id.search_swipe_refresh_layout);

        //implement refresh listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //return if adapter is null
                if (adapter == null)
                    return;

                //make set refreshing true
                swipeRefreshLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        //on success response make refreshing false
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Tweets refreshed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Toast or some other action
                        Toast.makeText(getContext(), "Failed to refresh tweets.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
