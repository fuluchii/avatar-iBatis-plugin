package me.fuluchii.ibatis.avatar.model;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author fuluchii.zhao
 */
public interface ResultMap extends DomElement {

    @NotNull
    @Attribute("class")
    public GenericAttributeValue<Attribute> getClassName();

    @NotNull
    @SubTagList("result")
    public List<Result> getResult();

    @SubTagList("result")
    public Result addResult();

}
