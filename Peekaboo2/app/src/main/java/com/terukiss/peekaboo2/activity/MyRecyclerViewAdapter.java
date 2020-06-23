package com.terukiss.peekaboo2.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.terukiss.peekaboo2.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{

    ArrayList<ServerListItem> items;

    public MyRecyclerViewAdapter(ArrayList<ServerListItem> items)
    {
        this.items = items;
    }


    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycle_item, parent, false);

        ViewHolder vh = new ViewHolder(view);


        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 데이터 바인딩

        ServerListItem Si = items.get(position);

        holder.title.setText( Si.getTitle() );
        holder.text.setText( Si.getText() );
        holder.subtext.setText( Si.getSubtext() );
        holder.time.setText("");


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolder  extends RecyclerView.ViewHolder
    {
        public TextView title;
        public TextView text;
        public TextView subtext;
        public TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recycleItem_Title);
            text = itemView.findViewById(R.id.recycleItem_Text);
            subtext = itemView.findViewById(R.id.recycleItem_Subtext);
            time = itemView.findViewById(R.id.recycleItem_Time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("FragmentTest", v.toString()+"d");
                }
            });
        }
    }


}
