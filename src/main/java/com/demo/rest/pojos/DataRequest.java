package com.demo.rest.pojos;

public class DataRequest {
    private String[] data;

    public DataRequest(){ }

    public DataRequest(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
    // returns a comma separated string, use to write to the file
    @Override
    public String toString(){
        return System.currentTimeMillis() + "," + String.join(",", this.data);
    }
}
