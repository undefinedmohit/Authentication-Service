package com.authentication.utility;


import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIResponse {
    @Nullable
    public Object response;

    public HttpStatus status;
    public String message;
}
