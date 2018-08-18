package com.zcxn.wp.cloud.auth.service;

import com.zcxn.wp.cloud.auth.entity.BaseRole;
import java.util.List;

/**
 * Created by Athlon on 2018/3/16.
 */
public interface BaseRoleService {

    List<BaseRole> findRoleByUserAccount(String account);

}
