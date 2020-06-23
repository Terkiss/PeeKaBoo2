package com.terukiss.peekaboo2.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class PeekabooAlartDialog {

    Context context;
    private String title = "default";
    ArrayList<String> selectionList = new ArrayList<>();
    ArrayList<String> selectedItem = new ArrayList<>();

    AlertDialog.Builder builder;

    public PeekabooAlartDialog(Context context) {
        this.context = context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    private void messageInput(String message)
    {
        builder = new AlertDialog.Builder(context);
        if (message.length() > 1) {
            builder.setTitle(title).setMessage(message);
        }
        builder.setTitle(title);
    }

    public void dialogCreate(String message) {
        messageInput(message);
    }

    public void positiveButtonCreate(String name, DialogInterface.OnClickListener clickListener)
    {
        builder.setPositiveButton(name, clickListener);
    }
    public void positiveButtonCreate(int id, DialogInterface.OnClickListener clickListener)
    {
        builder.setPositiveButton(id, clickListener);
    }

    public void negativeButtonCreate(String name, DialogInterface.OnClickListener clickListener)
    {
        builder.setNegativeButton(name, clickListener);
    }
    public void negativeButtonCreate(int id, DialogInterface.OnClickListener clickListener)
    {
        builder.setNegativeButton(id, clickListener);
    }

    public void neturalButtonCreate(String name, DialogInterface.OnClickListener clickListener)
    {
        builder.setNeutralButton(name, clickListener);
    }

    public void neturalButtonCreate(int id, DialogInterface.OnClickListener clickListener)
    {
        builder.setNeutralButton(id, clickListener);
    }


    public void addSingleSelectionList(String[] list, DialogInterface.OnClickListener clickListener)
    {
        selectionList.clear();
        // 배열안의 리스트를 어레이 리스트로 변환
        selectionList.addAll(Arrays.asList(list));

        builder.setItems(list, clickListener);
    }

    public void addMultipleChoiseList(String[] list, DialogInterface.OnMultiChoiceClickListener clickListener)
    {
        // 모든 리스트 초기화
        selectionList.clear();
        selectedItem.clear();

        //selectionList.addAll(Arrays.asList(list));

        selectionList.addAll(Arrays.asList(list));
        boolean[] bool = new boolean[list.length];
        Arrays.fill(bool, false);

        if(clickListener == null)
        {
            Log.d("FragmentTest", "널입니다");
        }

        builder.setMultiChoiceItems(list, bool, clickListener);

    }

    public void addSingleChoiseList(String[] list, DialogInterface.OnClickListener clickListener)
    {
        // 모든 리스트 초기화
        selectionList.clear();
        selectedItem.clear();

        selectionList.addAll(Arrays.asList(list));

        builder.setSingleChoiceItems(list, 0, clickListener);
    }


    public void customDialogView(View view)
    {
        builder.setView(view);

    }


    public void show()
    {
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void addSelectedItem(String item)
    {
        selectedItem.add(item);
    }
    public void removeSelectedItem(String item)
    {
        for(int i = 0 ; i < selectedItem.size(); i++)
        {
            String temp = selectedItem.get(i);
            if(temp.equals(item))
            {
                selectedItem.remove(i);
            }
        }
    }
    public void viewSelectedItem(JeongLog jeongLog)
    {
        for(int i = 0; i< selectedItem.size(); i++)
        {
            jeongLog.logD(i+" 번쨰 선택  "+selectedItem.get(i));
        }
    }
    public ArrayList<String> getSelectionList()
    {
        return selectionList;
    }
}
