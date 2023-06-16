package com.example.springserve;

import lombok.Data;

@Data
public class Response {
    private int status;
    private String msg;
    private Object Data;

    public Response() {}

    public Response(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        Data = data;
    }

}
