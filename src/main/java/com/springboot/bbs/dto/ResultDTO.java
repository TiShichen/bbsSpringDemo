package com.springboot.bbs.dto;

import com.springboot.bbs.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * @Author ShichenLi
 * @Date 8/23/2019 16:37
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static Object errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("Request succeeded, but DON'T TRY TO NUKE OUR SERVER, WE ARE WATCHING YOU.");
        return resultDTO;
    }
}
