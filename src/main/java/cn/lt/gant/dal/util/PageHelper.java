package cn.lt.gant.dal.util;

import cn.lt.gant.common.enums.datasource.DataSourceEnum;
import com.github.pagehelper.SqlUtil;
import com.google.common.collect.Maps;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;
import java.util.Properties;

/**
 * 自定义分页组件，数据库方言自动根据数据源类型动态选择
 *
 * @author lt
 * @version 1.0.0
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageHelper extends com.github.pagehelper.PageHelper {

    /**
     * 更具数据源类型的不同动态选择不同的
     */
    private Map<DataSourceEnum, SqlUtil> sqlUtilMap = Maps.newConcurrentMap();

    /**
     * Mybatis拦截器方法
     *
     * @param invocation 拦截器入参
     * @return 返回执行结果
     * @throws Throwable 抛出异常
     */
    public Object intercept(final Invocation invocation) throws Throwable {
        final DataSourceEnum dsEnum = MultipleDataSource.getDataSourceKey();
        return sqlUtilMap.getOrDefault(dsEnum, new SqlUtil(dsEnum.getValue().name())).processPage(invocation);
    }

    /**
     * 设置属性值
     *
     * @param p 属性值
     */
    public void setProperties(Properties p) {
        // Nothing
    }
}
