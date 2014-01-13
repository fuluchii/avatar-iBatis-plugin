package me.fuluchii.ibatis.avatar.model;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;

/**
 * @author fuluchii.zhao
 */
public interface SqlMap extends DomElement {

    @SubTagList("resultMap")
    public ResultMap getResultMap();



}
