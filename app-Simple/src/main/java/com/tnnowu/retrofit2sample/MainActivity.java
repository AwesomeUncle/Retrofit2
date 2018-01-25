package com.tnnowu.retrofit2sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LoadingView.LoadingViewListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String API_URL = "https://api.github.com";

    private MainAdapter mAdapter;

    private LoadingView mLoadingView;

    private RecyclerView mRecyclerView;

    List<Contributor> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MainAdapter();

        mRecyclerView = (RecyclerView) findViewById(R.id.rcv);
        setupRecyclerView(mRecyclerView);

        mLoadingView = (LoadingView) findViewById(R.id.loading);
        mLoadingView.setListener(this);
        mLoadingView.showLoading();

        initData();
    }

    private void initData() {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        MainService service = retrofit.create(MainService.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = service.getCall();
        Log.d(TAG, "main: " + call);

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    mLoadingView.showContentView();
                    Log.d(TAG, "onResponse: " + "isSuccessful");
                    mList = response.body();
                    Log.d(TAG, "onResponse: " + "list" + mList);

                    if (mList != null && mList.size() > 0) {
                        mAdapter.setData(mList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                mLoadingView.showFailed();
            }
        });
    }

    @Override
    public void onFailedClickListener() {
        initData();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setCallback(new MainAdapter.MainCallback() {
            @Override
            public void onItemClick(Contributor contributor) {
                Toast.makeText(MainActivity.this, "" + contributor.getLogin(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
