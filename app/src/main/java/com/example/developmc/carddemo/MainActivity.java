package com.example.developmc.carddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

//参考 http://blog.csdn.net/zxt0601/article/details/53730908
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Integer> datas;
    private CardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        //初始化配置
        CardConfig.initConfig(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //设置LayoutManager
        OverLayCardLayoutManager layoutManager = new OverLayCardLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
        //设置adapter
        adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);
    }
    private void initData(){
        datas = new ArrayList<>();
        for(int i=0;i<10;i++){
            datas.add(R.mipmap.ic_launcher);
        }
    }

    public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{
        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_card,parent,false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CardViewHolder holder, int position) {
            holder.iv_icon.setImageResource(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class CardViewHolder extends RecyclerView.ViewHolder{
            ImageView iv_icon;
            public CardViewHolder(View itemView) {
                super(itemView);
                iv_icon = (ImageView)itemView.findViewById(R.id.iv_icon);
            }
        }
    }
}
