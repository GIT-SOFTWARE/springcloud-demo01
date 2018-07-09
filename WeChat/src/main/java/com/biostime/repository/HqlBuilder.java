package com.biostime.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @title hql语句构建器
 * @description 
 * @author 尹泉
 * @date 2009-5-18
 */
@SuppressWarnings("unchecked")
public class HqlBuilder {
	
    /**
     * JPQL语句
     */
    private StringBuffer hql;

    /**
     * JPQL查询参数名称
     */
    private List<String> names;

    /**
     * JPQL查询参数值
     */
    private List values;

    /**
     * 语句中是否已经出现where词组
     */
    private boolean whereOccurred;

    public HqlBuilder() {
        names = new ArrayList<String>();
        values = new ArrayList();
        hql = new StringBuffer(100);
        whereOccurred = false;
    }

    /**
     * 加查询HQL条件
     *
     * @param hql hql查询语句
     * @return
     */
    public HqlBuilder addHql(String hql) {
        this.hql.append(hql).append(" ");
        return this;
    }

    /**
     * 加子查询HQL条件
     *
     * @param sub where或and开头的子条件语句（where或and省略）
     * @return
     */
    public HqlBuilder addSubHql(String sub) {
        if (whereOccurred) {
            sub = "and " + sub;
        } else {
            sub = "where " + sub;
            whereOccurred = true;
        }
        hql.append(sub).append(" ");
        return this;
    }

    /**
     * 加HQL查询参数
     *
     * @param value 参数值
     * @return
     */
    public HqlBuilder addParam(Object value) {
        values.add(value);
        return this;
    }

    /**
     * 加HQL查询参数
     *
     * @param name  参数名称
     * @param value 参数值
     * @return
     */
    public HqlBuilder addParam(String name, Object value) {
        names.add(name);
        values.add(value);
        return this;
    }

    public List<String> getNames() {
        return names;
    }

    public List getValues() {
        return values;
    }

    public String[] getNameArray() {
        return names.toArray(new String[names.size()]);
    }

    public Object[] getValueArray() {
        return values.toArray();
    }

    public String getQueryString() {
        return hql.toString();
    }

}
