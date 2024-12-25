package com.example.matchmaking.exception;

import com.example.matchmaking.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExceptionHandler {

    private String errorMsg;
    private HttpStatus httpStatus;
    Status status = Status.SUCCESS;
}
