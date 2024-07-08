package com.example.batch7.ch8.service.oauth;

import com.example.batch7.ch8.dto.req.LoginModel;
import com.example.batch7.ch8.dto.req.RegisterModel;

import java.util.Map;

public interface UserService {
    Map registerManual(RegisterModel objModel) ;

    Map registerByGoogle(RegisterModel objModel) ;

    Map login(LoginModel objModel);
}
