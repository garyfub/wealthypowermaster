package com.zcxn.wp.cloud.common.service;


import com.zcxn.wp.cloud.common.result.Pagination;
import java.io.Serializable;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Athlon on 2018/4/12.
 */
public interface BaseService<T,ID extends Serializable> {

  /**
   * 查询
   *
   * @param entity
   * @return
   */
  T save(T entity);

  /**
   * 保存对象
   *
   * @param entities
   * @return
   */
  <S extends T> Iterable<S> save(Iterable<S> entities);

  /**
   * 通过Id查询
   *
   * @param id
   * @return
   */
  T findOne(ID id);

  /**
   * 查询所有列表
   * @return
   */
  Iterable<T> findAll();

  /**
   * 根据ID列表查询所有
   * @param ids
   * @return
   */
  Iterable<T> findAll(Iterable<ID> ids);

  /**
   * 删除对象通过ID
   * @param id
   */
  void delete(ID id);

  /**
   * 删除对象
   * @param entity
   */
  void delete(T entity);

  /**
   * 删除所有对象
   */
  void deleteAll();

  Pagination<?> queryForPagination(String sql, Object[] args, int pageNum,
      int pageSize,RowMapper<?> mapper);

  public Pagination<Map<String,Object>> queryForPagination(String sql, Object[] args, int pageNum,
      int pageSize);
}
