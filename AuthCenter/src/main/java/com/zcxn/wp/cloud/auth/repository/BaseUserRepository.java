package com.zcxn.wp.cloud.auth.repository;

import com.zcxn.wp.cloud.auth.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Athlon on 2018/3/16.
 */
@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser,String> {

}



