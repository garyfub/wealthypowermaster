package com.zcxn.wp.cloud.auth.service;

import com.zcxn.wp.cloud.auth.entity.BaseUser;

/**
 * Created by Athlon on 2018/3/16.
 */
public interface BaseUserService {

       BaseUser findByUserAccount(String account);

}
