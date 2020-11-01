package com.cxytiandi.kitty.samples.async.service;

import com.cxytiandi.kitty.async.UserQuery;
import com.cxytiandi.kitty.samples.async.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * 模拟查询，耗时1s
     * @param id
     * @return
     */
    public UserResponse getUser(String id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (id.equals("1")) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId("1");
            userResponse.setNickname("yinjihuan1");
            return userResponse;
        }
        if (id.equals("2")) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId("2");
            userResponse.setNickname("yinjihuan2");
            return userResponse;
        }
        if (id.equals("3")) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId("3");
            userResponse.setNickname("yinjihuan3");
            return userResponse;
        }
        return null;
    }

    public UserResponse getUser(UserQuery q) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId("3");
        userResponse.setNickname("yinjihuan3");
        return userResponse;
    }
}
