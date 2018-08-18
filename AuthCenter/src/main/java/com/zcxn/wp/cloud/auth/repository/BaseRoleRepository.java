package com.zcxn.wp.cloud.auth.repository;

import com.zcxn.wp.cloud.auth.entity.BaseRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Athlon on 2018/3/16.
 */
@Repository
public interface BaseRoleRepository extends JpaRepository<BaseRole,String> {

  @Query(value = "select role.* from iease_base_role_t role,iease_base_userrole_r ur "
      + "where ur.role_id=role.role_id and ur.account=?1",nativeQuery = true)
  List<BaseRole> getBaseRoleByUserId(String userId);

}
