package service;

import java.util.List;

/**
 * @author BDsnake
 * @since 2023/3/28 21:33
 */
public interface Service {
    /**
     * 持久化
     *
     * @param model
     */
    void save(T model);

    /**
     * 批量持久化
     *
     * @param models
     */
    void save(List<T> models);

    /**
     * 通过主鍵刪除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 批量刪除 eg：ids -> “1,2,3,4”
     *
     * @param ids
     */
    void deleteByIds(String ids);

    /**
     * 更新
     *
     * @param model
     */
    void update(T model);

    /**
     * 通过ID查找
     *
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     *
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     * @throws ServiceException
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException, ServiceException;


    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     *
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     *
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> findAll();
}
