package com.mobile.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // adapter에 들어갈 list 입니다.
    private ArrayList<com.example.recycler_test.DetailData> listData = new ArrayList<>();
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
    private final int TYPE_FOOTER = 2;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_detail_header, parent, false);
            holder = new com.example.recycler_test.HeaderViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_detail_footer, parent, false);
            holder = new com.example.recycler_test.FooterViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_datail_item, parent, false);
            holder = new ItemViewHolder(view);
        }
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof com.example.recycler_test.HeaderViewHolder) {
            com.example.recycler_test.HeaderViewHolder headerViewHolder = (com.example.recycler_test.HeaderViewHolder) holder;
        } else if (holder instanceof com.example.recycler_test.FooterViewHolder) {
            com.example.recycler_test.FooterViewHolder footerViewHolder = (com.example.recycler_test.FooterViewHolder) holder;
        } else {
            // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.onBind(listData.get(position - 1), position);
        }
    }
    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size() + 2;
    }
    void addItem(com.example.recycler_test.DetailData detailData) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(detailData);
    }
    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        //private Button button;
        ItemViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.search_list_item_1);
            textView2 = itemView.findViewById(R.id.search_list_item_text);
        }
        void onBind(com.example.recycler_test.DetailData detailData, int position) {
            textView1.setText(detailData.getTitle());
            textView2.setText(detailData.getContent());
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else if (position == listData.size() + 1)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }
}