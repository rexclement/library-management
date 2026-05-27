package com.example.LibraryManagement.utils;

public class Response {

    private boolean status;
    private String remarks;
    private Object data;

    public static Response getInstance(){
        return new Response();
    }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}