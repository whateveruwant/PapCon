package com.papcon.papcon;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkrdb on 2017-07-09.
 */

public class RegisterRequest extends StringRequest{

    final static private String URL = "http://dbals1630.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userType, String userID, String userPassword, String userName, String userSex, int userAge, String userAddress, String userInfo, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userType",userType);
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userSex",userSex);
        parameters.put("userAge", userAge + ""); // int형을 문자열로 변환하기 위해 + "" 해줌
        parameters.put("userAddress",userAddress);
        parameters.put("userInfo",userInfo);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
