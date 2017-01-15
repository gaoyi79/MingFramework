package cn.org.mingframework.dao.interfaces;

public interface EntityService<T> {
	T save(T entity);//保存单个实体
    Iterable<T> save(Iterable<? extends T> entities);//保存集合
    T findOne(Long id);//根据id查找实体
    boolean exists(Long id);//根据id判断实体是否存在
    Iterable<T> findAll();//查询所有实体,不用或慎用!
    long count();//查询实体数量
    void delete(Long id);//根据Id删除实体
    void delete(T entity);//删除一个实体
    void delete(Iterable<? extends T> entities);//删除一个实体的集合
    void deleteAll();//删除所有实体,不用或慎用!
}
