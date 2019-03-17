package com.arman97h.swiperefresh_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.arman97h.gearpulltorefresh.refreshview.GearRefreshLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpList();
        hideRefreshLayout();
    }

    private void setUpList() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }

        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private void hideRefreshLayout() {
        final GearRefreshLayout recyclerRefreshLayout = findViewById(R.id.refresh_layout);
        recyclerRefreshLayout.setOnRefreshListener(new GearRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
    }
}
