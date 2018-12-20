package com.example.normaoni.recyclerhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.normaoni.recyclerhelper.adt.ItemAdt;
import com.example.normaoni.recyclerhelper.data.ItemDTO;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.adt_size)
    TextView adt_size;
    @BindView(R.id.array_size)
    TextView array_size;

    private int totalSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ArrayList<ItemDTO> dtos = new ArrayList<>(Arrays.asList(
                new ItemDTO(1,"A"),
                new ItemDTO(2,"B"),
                new ItemDTO(3,"C"),
                new ItemDTO(4,"D"),
                new ItemDTO(5,"E"),
                new ItemDTO(6,"F"),
                new ItemDTO(7,"G"),
                new ItemDTO(8,"H"),
                new ItemDTO(9,"I"),
                new ItemDTO(10,"J"),
                new ItemDTO(11,"K"),
                new ItemDTO(12,"L"),
                new ItemDTO(13,"M"),
                new ItemDTO(14,"N"),
                new ItemDTO(15,"O"),
                new ItemDTO(16,"P"),
                new ItemDTO(17,"Q"),
                new ItemDTO(18,"R"),
                new ItemDTO(19,"S"),
                new ItemDTO(20,"T"),
                new ItemDTO(21,"U"),
                new ItemDTO(22,"V"),
                new ItemDTO(23,"W"),
                new ItemDTO(24,"X"),
                new ItemDTO(25,"Y"),
                new ItemDTO(26,"Z"),
                new ItemDTO(27,"0"),
                new ItemDTO(28,"1"),
                new ItemDTO(29,"2"),
                new ItemDTO(30,"3")
        ));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemAdt(adt_size, dtos, new ItemAdt.OnRemoveListener() {
            @Override
            public void onRemoved() {
                array_size.setText("Arrays: "+ --totalSize);
            }
        }));
        array_size.setText("Arrays: "+(totalSize = dtos.size()));
    }

    @OnClick(R.id.add)
    public void onClickAdd(){
        ((ItemAdt)recyclerView.getAdapter()).getDtos().add(new ItemDTO(-1,"NewData"));
        recyclerView.getAdapter().notifyItemInserted(recyclerView.getAdapter().getItemCount());
        array_size.setText("Arrays: "+ ++totalSize);
    }
}
