package cn.lt.gant.common.anotation;

import cn.lt.gant.common.enums.datasource.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源类型
 *
 * @author lt
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataSourceType {

    /**
     * 目标数据源
     *
     * @return
     */
    DataSourceEnum target() default DataSourceEnum.MAIN;

}
