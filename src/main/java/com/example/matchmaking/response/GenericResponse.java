package com.example.matchmaking.response;


import com.example.matchmaking.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)

public class GenericResponse {
    String detail;
    Status status = Status.SUCCESS;
    String error;
}