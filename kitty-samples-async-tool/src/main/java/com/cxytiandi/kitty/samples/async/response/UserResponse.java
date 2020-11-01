package com.cxytiandi.kitty.samples.async.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 昵称
     */
    private String nickname;

}