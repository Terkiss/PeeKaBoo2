package com.terukiss.peekaboo2.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.RoomDataModel;

import java.util.ArrayList;

public class RoomListRecyclerViewAdapter extends RecyclerView.Adapter<RoomListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> item ;
    public RoomListRecyclerViewAdapter(Context context, ArrayList<String> item)
    {
        this.context = context;
        this.item = item;
    }
    public void setItem(ArrayList<String> items)
    {
        this.item = items;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycle_item_01, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 데이터 바인딩
        holder.room.setText(item.get(position));
        holder.sender.setText("보낸사람 미구현");
        holder.contents.setText("컨텐츠 내용 미구현");
        holder.time.setText("현재 시간 미구현");
    }

    @Override
    public int getItemCount()
    {
        return item.size();
    }


    // 바인딩 객체 생성
    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView room;
        public TextView sender;
        public TextView contents;
        public TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            room = itemView.findViewById(R.id.recycleItem_RoomName);
            sender = itemView.findViewById(R.id.recycleItem_Sender);
            contents = itemView.findViewById(R.id.recycleItem_Text);
            time = itemView.findViewById(R.id.recycleItem_Time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 방 엑티비티 실행
                }
            });
        }
    }
}
