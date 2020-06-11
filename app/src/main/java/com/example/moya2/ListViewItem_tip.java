package com.example.moya2;

public class ListViewItem_tip {
    private String category_str;
    public ListViewItem_tip(String category){
        this.category_str = category;
    }
    public void setCategory(String category){category_str = category;}
    public String getCategory(){return this.category_str;}
}
