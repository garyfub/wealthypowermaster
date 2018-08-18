package com.zcxn.wp.cloud.common.web;

import com.zcxn.wp.cloud.common.result.RestResponse;
import com.zcxn.wp.cloud.common.service.BaseService;
import com.zcxn.wp.cloud.common.vo.CurrentUser;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * RestBase
 * Created by Athlon on 2018/4/12.
 */
public class RestBaseController<Base extends BaseService,T,ID extends Serializable> {
  @Autowired
  protected HttpServletRequest request;

  @Autowired
  protected Base baseService;
  //用于模拟登录
  @Value( "${login.account:admin}" )
  private String loginAccount;
  @Value( "${login.name:admin}" )
  private String loginName;
  @Value( "${login.groupId:10040}" )
  private String loginGroupId;
  @Value( "${login.groupName:黑龙江}" )
  private String loginGroupName;


  @RequestMapping(value = "/add",method = RequestMethod.POST)
  @ApiOperation(value = "保存业务对象")
  public  RestResponse<T> add(@RequestBody T entity)
  {
     baseService.save( entity );
     return new RestResponse<T>().success(entity);
  }
  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  @ApiOperation(value = "获取业务对象")
  public RestResponse<T> get(@PathVariable ID id)
  {
     return new RestResponse().success( baseService.findOne( id ) );
  }

  @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
  @ApiOperation(value = "删除业务对象")
  public RestResponse<T> remove(@PathVariable ID id)
  {
      baseService.delete( id );
      return new RestResponse<T>().success();
  }

  @RequestMapping(value = "/update",method = RequestMethod.POST)
  @ApiOperation(value = "修改业务对象")
  public RestResponse<T> update(@RequestBody  T entity)
  {
      baseService.save( entity );
      return new RestResponse<T>().success();
  }

  @RequestMapping(value = "/list",method = RequestMethod.GET)
  @ApiOperation(value = "获取列表")
  public RestResponse<List<T>> list()
  {
    return new RestResponse<List<T>>().success( (List<T>) baseService.findAll() );
  }




  /**
   * 使用redis作用tokenStore时可以获取到CrrentUser，其他方式只能获取用户名
   * @return
   */
  final protected CurrentUser getCurrentUser() {

    CurrentUser currentUser=null;
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if(principal != null )
      {
        if(principal instanceof  CurrentUser)
          return (CurrentUser)principal;
        else if(principal instanceof  String)
        {
          currentUser = new CurrentUser( (String) principal,"123456",Collections.emptyList() );
          return currentUser;
        }
        else
          throw new RuntimeException("登录已失效,请重新登陆！");
      }
      else
        throw new RuntimeException("登录已失效,请重新登陆！");
    }
    catch (Exception e)
    {
      // e.printStackTrace();
      currentUser =new CurrentUser( (String) loginName,"123456",Collections.emptyList() );
      currentUser.setAccount( loginAccount );
      currentUser.setGroupId( loginGroupId );
      currentUser.setGroupName( loginGroupName );
    }
    return currentUser;
  }





}
