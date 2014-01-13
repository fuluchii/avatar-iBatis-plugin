package me.fuluchii.ibatis.avatar.model;

import com.intellij.psi.xml.XmlAttribute;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author fuluchii.zhao
 */
public interface Result extends DomElement {

    @NotNull
    @Attribute("colomn")
    public GenericAttributeValue<XmlAttribute> addColomn();

    @NotNull
    @Attribute("property")
    public GenericAttributeValue<XmlAttribute> addProperty();



}
