package com.example.normaoni.recyclerhelper.data;

public class ItemDTO {
    public int idx;
    public String name;
    public String memo;

    public int loadCnt;
    public int viewColorCnt;
    public ItemDTO(int idx, String name) {
        this.idx = idx;
        this.name = name;
        this.loadCnt = 0;
        this.viewColorCnt = 0;
    }
}
