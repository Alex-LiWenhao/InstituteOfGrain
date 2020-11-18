package com.alex.servicebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AlexException
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/10 23:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlexException extends RuntimeException {
    private Integer code;
    private String message;
    @Override
    public String toString() {
        return "AlexException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}
