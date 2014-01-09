package me.fuluchii.ibatis.avatar.model;

import com.intellij.util.containers.HashMap;

import java.util.Map;

/**
 * @author fuluchii.zhao
 */
public class TableModel {

    private String className;

    private String beanName;

    private String dtoName;

    private String sqlmapName;

    private String daoName;

    private String basePackage;

    private Map<String,String> filedMap;

    private String TableNSName;

    public String getBeanName() {
        return className+"Bean";
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getDtoName() {
        return className+"Dto";
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }

    public String getSqlmapName() {
        return className;
    }

    public void setSqlmapName(String sqlmapName) {
        this.sqlmapName = sqlmapName;
    }

    public String getDaoName() {
        return className+"Dao";
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, String> getFiledMap() {
        return filedMap;
    }

    public void setFiledMap(Map<String, String> filedMap) {
        this.filedMap = filedMap;
    }

    public String getTableNSName() {
        return TableNSName;
    }

    public void setTableNSName(String tableNSName) {
        TableNSName = tableNSName;
    }

    public TableModel(String name, String basePackage,String tablejson){
        this.setClassName(name);
        this.setBasePackage(basePackage);
        String[] tableFields = tablejson.split("\\|");
        this.filedMap = new HashMap<String, String>();
        for (String tableField : tableFields) {
            String[] filedStrings = tableField.split(",");
            filedMap.put(filedStrings[0],filedStrings[1]);
        }
    }

}
