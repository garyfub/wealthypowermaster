package com.zcxn.wp.cloud.auth.service.impl;

import com.zcxn.wp.cloud.auth.entity.BaseRole;
import com.zcxn.wp.cloud.auth.repository.BaseRoleRepository;
import com.zcxn.wp.cloud.auth.service.BaseRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Athlon on 2018/3/16.
 */
@Service
public class BaseRoleServiceImpl implements BaseRoleService {
  @Autowired
  private BaseRoleRepository baseRoleRepository;

  @Override
  public List<BaseRole> findRoleByUserAccount(String account) {
    return baseRoleRepository.getBaseRoleByUserId(account);
  }
}
