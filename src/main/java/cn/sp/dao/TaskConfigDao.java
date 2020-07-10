package cn.sp.dao;

import cn.sp.entity.TaskConfig;
import org.apache.ibatis.annotations.Param;

/**
 * @author 2YSP
 * @date 2020/7/10 19:54
 */
public interface TaskConfigDao {

    TaskConfig queryById(@Param("id") Integer id);
}
