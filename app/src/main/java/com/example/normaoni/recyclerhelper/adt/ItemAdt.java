package com.example.normaoni.recyclerhelper.adt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.normaoni.recyclerhelper.R;
import com.example.normaoni.recyclerhelper.adt.holder.ItemHolder;
import com.example.normaoni.recyclerhelper.data.ItemDTO;

import java.util.ArrayList;

public class ItemAdt extends RecyclerView.Adapter<ItemHolder> {

    private ArrayList<ItemDTO> dtos;
    private TextView adt_size;

    private OnRemoveListener listener;
    public ItemAdt(TextView adt_size,ArrayList<ItemDTO> dtos,OnRemoveListener listener) {
        this.adt_size = adt_size;
        this.dtos = dtos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        v = View.inflate(parent.getContext(), R.layout.view_item, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int i) {
        holder.idx.setText(dtos.get(i).idx+" : "+i);
        holder.name.setText(dtos.get(i).name);
        holder.text.setText(dtos.get(i).memo);

        dtos.get(i).viewColorCnt = holder.setBackground(dtos.get(holder.getAdapterPosition()).viewColorCnt);
        holder.count.setText(String.format("%s",dtos.get(holder.getAdapterPosition()).loadCnt++));

        holder.setOnTextChange(new ItemHolder.SetOnTextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                dtos.get(holder.getAdapterPosition()).memo = text;
            }
        });
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), holder.getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
            }
        });
        holder.content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(holder.itemView.getContext(), holder.getAdapterPosition()+" DELETING..", Toast.LENGTH_SHORT).show();
                rolloverRange(i,holder);
                listener.onRemoved();
                return true;
            }
        });
    }
    private void rolloverRange(int i,ItemHolder holder){
        try {
            dtos.remove(i);
            notifyItemRemoved(holder.getAdapterPosition());
            notifyItemRangeChanged(holder.getAdapterPosition()+1,getItemCount());
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            rolloverRange(i-1,holder);
        }
    }

    @Override
    public int getItemCount() {
        adt_size.setText(dtos.size()+"");
        return dtos.size();
    }

    @Override
    public long getItemId(int i) {
        return dtos.indexOf(i);
    }

    public ArrayList<ItemDTO> getDtos() {
        return dtos;
    }
    public interface OnRemoveListener{
        void onRemoved();
    }
}
