package com.zcxn.wp.cloud.auth.service.impl;

import com.zcxn.wp.cloud.auth.entity.BaseUser;
import com.zcxn.wp.cloud.auth.repository.BaseUserRepository;
import com.zcxn.wp.cloud.auth.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Athlon on 2018/3/16.
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {
  @Autowired
  private BaseUserRepository baseUserRepository;

  @Override
  public BaseUser findByUserAccount(String account) {
      return baseUserRepository.findById(account).get();
  }
}
