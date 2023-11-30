package com.spencer.ItemApp.models;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MyData {
    private ArrayList<String> values;
    public ArrayList<String> getValues() {
        return this.values;
    }
}
