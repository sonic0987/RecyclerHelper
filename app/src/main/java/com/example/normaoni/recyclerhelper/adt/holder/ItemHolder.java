package com.example.normaoni.recyclerhelper.adt.holder;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.normaoni.recyclerhelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.idx)
    public TextView idx;
    @BindView(R.id.name)
    public TextView name;

    @BindView(R.id.bg)
    public View bg;
    @BindView(R.id.count)
    public TextView count;

    @BindView(R.id.content)
    public View content;

    @BindView(R.id.text)
    public EditText text;

    private SetOnTextChangeListener listener;
    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public int setBackground(int color){
        if(color>=itemView.getContext().getResources().getIntArray(R.array.colors).length)
            color = 0;
        ((GradientDrawable) bg.getBackground()).setColor(bg.getContext().getResources().getIntArray(R.array.colors)[color++]);
        return color;
    }

    public void setOnTextChange(final SetOnTextChangeListener listener){
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null)
                    listener.onTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public interface SetOnTextChangeListener{
        void onTextChanged(String text);
    }
}
