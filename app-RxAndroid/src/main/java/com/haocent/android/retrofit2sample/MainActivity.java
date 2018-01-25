package com.haocent.android.retrofit2sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private GitHubService mService;

    private MainAdapter mAdapter;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();

        initView();

        getServiceData();
    }

    private void initService() {
        mService = new RetrofitService().getService();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rcv_main);
        mProgressBar = findViewById(R.id.progressbar);

        mAdapter = new MainAdapter(this);

        mProgressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    private void getServiceData() {
        Observable<List<GitHubBean>> observable = mService.getContributors();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<GitHubBean> gitHubBeans) {
                        Log.d(TAG, "onNext: ");

                        mAdapter.setDataList(gitHubBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");

                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }
}
