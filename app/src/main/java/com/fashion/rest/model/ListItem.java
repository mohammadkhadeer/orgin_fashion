package com.fashion.rest.model;

import java.util.ArrayList;

public class ListItem {
    String listType;

    public ArrayList<Integer> CPArrayListT1 = new ArrayList<>();
    public ArrayList<Integer> CPArrayListT2 = new ArrayList<>();
    public ArrayList<Integer> CPArrayListT3 = new ArrayList<>();
    public ArrayList<Integer> CPArrayListT4 = new ArrayList<>();

    public ArrayList<ArrayList<Deal>> ItemT1ArrayListT1 = new ArrayList<>();
    public ArrayList<ArrayList<Deal>> ItemT2ArrayListT2 = new ArrayList<>();
    public ArrayList<ArrayList<Deal>> ItemT3ArrayListT3 = new ArrayList<>();
    public ArrayList<ArrayList<Deal>> ItemT4ArrayListT4 = new ArrayList<>();

    public ListItem(String listType, ArrayList<Integer> CPArrayListT1, ArrayList<Integer> CPArrayListT2, ArrayList<Integer> CPArrayListT3, ArrayList<Integer> CPArrayListT4, ArrayList<ArrayList<Deal>> itemT1ArrayListT1, ArrayList<ArrayList<Deal>> itemT2ArrayListT2, ArrayList<ArrayList<Deal>> itemT3ArrayListT3, ArrayList<ArrayList<Deal>> itemT4ArrayListT4) {
        this.listType = listType;
        this.CPArrayListT1 = CPArrayListT1;
        this.CPArrayListT2 = CPArrayListT2;
        this.CPArrayListT3 = CPArrayListT3;
        this.CPArrayListT4 = CPArrayListT4;
        ItemT1ArrayListT1 = itemT1ArrayListT1;
        ItemT2ArrayListT2 = itemT2ArrayListT2;
        ItemT3ArrayListT3 = itemT3ArrayListT3;
        ItemT4ArrayListT4 = itemT4ArrayListT4;
    }

    public ListItem() {
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public ArrayList<Integer> getCPArrayListT1() {
        return CPArrayListT1;
    }

    public void setCPArrayListT1(ArrayList<Integer> CPArrayListT1) {
        this.CPArrayListT1 = CPArrayListT1;
    }

    public ArrayList<Integer> getCPArrayListT2() {
        return CPArrayListT2;
    }

    public void setCPArrayListT2(ArrayList<Integer> CPArrayListT2) {
        this.CPArrayListT2 = CPArrayListT2;
    }

    public ArrayList<Integer> getCPArrayListT3() {
        return CPArrayListT3;
    }

    public void setCPArrayListT3(ArrayList<Integer> CPArrayListT3) {
        this.CPArrayListT3 = CPArrayListT3;
    }

    public ArrayList<Integer> getCPArrayListT4() {
        return CPArrayListT4;
    }

    public void setCPArrayListT4(ArrayList<Integer> CPArrayListT4) {
        this.CPArrayListT4 = CPArrayListT4;
    }

    public ArrayList<ArrayList<Deal>> getItemT1ArrayListT1() {
        return ItemT1ArrayListT1;
    }

    public void setItemT1ArrayListT1(ArrayList<ArrayList<Deal>> itemT1ArrayListT1) {
        ItemT1ArrayListT1 = itemT1ArrayListT1;
    }

    public ArrayList<ArrayList<Deal>> getItemT2ArrayListT2() {
        return ItemT2ArrayListT2;
    }

    public void setItemT2ArrayListT2(ArrayList<ArrayList<Deal>> itemT2ArrayListT2) {
        ItemT2ArrayListT2 = itemT2ArrayListT2;
    }

    public ArrayList<ArrayList<Deal>> getItemT3ArrayListT3() {
        return ItemT3ArrayListT3;
    }

    public void setItemT3ArrayListT3(ArrayList<ArrayList<Deal>> itemT3ArrayListT3) {
        ItemT3ArrayListT3 = itemT3ArrayListT3;
    }

    public ArrayList<ArrayList<Deal>> getItemT4ArrayListT4() {
        return ItemT4ArrayListT4;
    }

    public void setItemT4ArrayListT4(ArrayList<ArrayList<Deal>> itemT4ArrayListT4) {
        ItemT4ArrayListT4 = itemT4ArrayListT4;
    }
}
