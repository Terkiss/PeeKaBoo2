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
import com.terukiss.peekaboo2.helper.ConnectionInfo;
import com.terukiss.peekaboo2.helper.JeongLog;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{

    ArrayList<ServerListItem> items;
    Context context;
    public MyRecyclerViewAdapter(ArrayList<ServerListItem> items, Context context)
    {
        this.items = items;
        this.context = context;
    }
    public void setItem(ArrayList<ServerListItem> items)
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
        return items.size();
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

                    Log.d("FragmentTest", "title = "+title.getText());
                    Log.d("FragmentTest", "text = "+text.getText());
                    Log.d("FragmentTest", "subtext = "+subtext.getText());

                    String temp = title.getText().toString();

                    int index = temp.indexOf(": ");

                    // 주소 부분
                    ConnectionInfo.ServerHostName = temp.substring(index+2);

                    // 포트 부분
                    temp = text.getText().toString();
                    index = temp.indexOf(": ");
                    ConnectionInfo.ServerPort =temp.substring(index+2);

                    // 서버 애칭 부분
                    temp = subtext.getText().toString();
                    index = temp.indexOf(": ");
                    ConnectionInfo.ServerNick =temp.substring(index+2);

                    Log.d("FragmentTest", "ServerHostName :"+ConnectionInfo.ServerHostName);
                    Log.d("FragmentTest", "ServerPort :"+ConnectionInfo.ServerPort);
                    Log.d("FragmentTest", "ServerNick :"+ConnectionInfo.ServerNick);

                    Toast.makeText(context,ConnectionInfo.ServerHostName+" 서버로 연결이 재설정 되었습니다", Toast.LENGTH_LONG ).show();

//                    ConnectionInfo.ServerHostName = title.getText().toString();
//
//                    ConnectionInfo.ServerPort = text.getText().toString();
//
//                    ConnectionInfo.ServerNick = subtext.getText().toString();
                }
            });
        }
    }


}
