package com.lerson.clonegram.dto;

public class HandlerDTO {

    private String msg;
    private Long time;

    public HandlerDTO() {}

    public HandlerDTO(String msg, Long time) {
        this.msg = msg;
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
