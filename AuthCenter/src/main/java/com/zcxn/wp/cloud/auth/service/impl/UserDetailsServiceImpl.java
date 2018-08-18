package com.zcxn.wp.cloud.auth.service.impl;

import com.zcxn.wp.cloud.auth.entity.BaseRole;
import com.zcxn.wp.cloud.auth.entity.BaseUser;
import com.zcxn.wp.cloud.auth.integration.AuthenticationContext;
import com.zcxn.wp.cloud.auth.integration.AuthenticationType;
import com.zcxn.wp.cloud.auth.integration.IAuthenticator;
import com.zcxn.wp.cloud.auth.service.BaseRoleService;
import com.zcxn.wp.cloud.common.vo.CurrentUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Athlon on 2018/3/16.
 */
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

  @Autowired
  private BaseRoleService baseRoleService;
  @Autowired
  private  JdbcTemplate jdbcTemplate;
  //权限表暂时不明了
  // @Autowired
  // private PermissionService permissionService;

  private List<IAuthenticator> authenticators;

  @Autowired(required = false)
  public void setIntegrationAuthenticators(List<IAuthenticator> authenticators) {
    this.authenticators = authenticators;
  }



  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthenticationType integrationAuthentication = AuthenticationContext.get();
    //判断是否是集成登录
    if (integrationAuthentication == null) {
      integrationAuthentication = new AuthenticationType();
    }
    integrationAuthentication.setUsername(username);
    BaseUser baseUser = this.authenticate(integrationAuthentication);

    if(null == baseUser)
    {
      throw new UsernameNotFoundException("用户名或密码错误");
    }

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    boolean enabled = true; // 可用性 :true:可用 false:不可用
    boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
    boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
    boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定

    List<BaseRole> rolesList = baseRoleService.findRoleByUserAccount(username);
    for (BaseRole role:rolesList)
    {
      //角色必须是ROLE_开头，可以在数据库中设置
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
      grantedAuthorities.add(grantedAuthority);
      //这里添加角色的权限，现在先写死
      GrantedAuthority authority = new SimpleGrantedAuthority("/");
      grantedAuthorities.add(authority);
    }
    if(grantedAuthorities.isEmpty())
    {
      grantedAuthorities.add( new SimpleGrantedAuthority("/") );
    }

    CurrentUser user = new CurrentUser(baseUser.getAccount(), baseUser.getPassword(),
        enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    //拷贝其他信息
    try {
      BeanUtils.copyProperties(baseUser,user);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return user;
  }

  private BaseUser authenticate(AuthenticationType integrationAuthentication) {
    if (this.authenticators != null) {
      for (IAuthenticator authenticator : authenticators) {
        if (authenticator.support(integrationAuthentication)) {
          return authenticator.authenticate(integrationAuthentication);
        }
      }
    }
    return null;
  }
}
