package com.zcxn.wp.cloud.common.service.impl;

import com.zcxn.wp.cloud.common.result.Pagination;
import com.zcxn.wp.cloud.common.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * CRUD基本实现的
 * Created by Athlon on 2018/4/12.
 */
public class BaseServiceImpl<R extends CrudRepository<T,Serializable>,T > implements BaseService<T,Serializable> {


  @Autowired
  private R repository;

  @PersistenceContext
  protected EntityManager em;

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  @Value( "${spring.datasource.driver-class-name:'mysql'}" )
  private String jdbcType;

  @Override
  public T save(T entity) {
    // return null;
    return (T) repository.save( entity );
  }

  @Override
  public <S extends T> Iterable<S> save(Iterable<S> entities) {
    return repository.saveAll(entities);
    //return repository.save( entities );
  }

  @Override
  public T findOne(Serializable id) {

    return (T) repository.findById(id).orElse(null);
  }

  @Override
  public Iterable<T> findAll() {
    return repository.findAll();
  }

  @Override
  public Iterable<T> findAll(Iterable<Serializable> ids) {
    return repository.findAllById(ids);
  }

  @Override
  public void delete(Serializable id) {
    repository.deleteById(id);
  }

  @Override
  public void delete(T entity) {
    repository.delete( entity );
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }

  @Override
  public Pagination<?> queryForPagination(String sql, Object[] args, int pageNum,
      int pageSize,RowMapper<?> mapper) {

    if(pageNum<=0)
    {
      pageNum = 1;
    }
    //构建总数SQL
    String countSql = buildPaginationCountSql( sql );

    Integer total = jdbcTemplate.queryForObject( countSql,args,Integer.class);

    String pageSql = buildPaginationSql( sql,pageNum,pageSize );

    List  list = jdbcTemplate.query( pageSql,args,mapper );

    Pagination<?> pagination = new Pagination<>( pageNum,pageSize,total );

    pagination.setItems( list );

    return pagination;
  }
  public Pagination<Map<String,Object>> queryForPagination(String sql, Object[] args, int pageNum,
      int pageSize)
  {
      if(pageNum<=0)
      {
        pageNum = 1;
      }
      //构建总数SQL
      String countSql = buildPaginationCountSql( sql );

      Integer total = jdbcTemplate.queryForObject( countSql,args,Integer.class);

      String pageSql = buildPaginationSql( sql,pageNum,pageSize );

    List<Map<String,Object>> list =  jdbcTemplate.queryForList( pageSql,args);

    Pagination<Map<String,Object>> pagination = new Pagination<>( pageNum,pageSize,total );

    pagination.setItems( list );

    return pagination;
  }

  protected String buildPaginationSql(String sql,int start, int pageSize)
  {
      StringBuilder pageSql = new StringBuilder( sql );

      if(StringUtils.containsIgnoreCase( jdbcType,"mysql" ))
      {
        pageSql.append(" limit ").append((start - 1)*pageSize).append(',').append(pageSize);
        return  pageSql.toString();
      }
      else
      {
        StringBuilder sbSql = new StringBuilder(sql.length() + 90);

        sbSql.append("SELECT * FROM ( SELECT ROWNUM RN, T.* FROM ( ").append(sql)
            .append(" ) T WHERE ROWNUM <= ").append(start * pageSize).append(" ) WHERE RN  >= ")
            .append(((start-1)*pageSize+1));
        return sbSql.toString();
      }
  }

  protected  String buildPaginationCountSql(String sql) {

     StringBuilder countSql = new StringBuilder(sql.toUpperCase());

    if(StringUtils.containsIgnoreCase( jdbcType,"mysql" ))
       countSql.insert(0, "SELECT COUNT(1) FROM (").append(" ) as T");
    else
      countSql.insert(0, "SELECT COUNT(1) FROM (").append(" ) T");


    return countSql.toString();
  }
}
