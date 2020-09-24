package org.crm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * (DicType)实体类
 *
 * @author makejava
 * @since 2020-09-03 16:46:25
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DicType implements Serializable {
    private static final long serialVersionUID = 809891093871855991L;
    /**
     * ����������������Ϊ�գ����ܺ������ġ�
     */
    private String code;

    private String name;

    private String description;

    private List<DicValue> valueList;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DicValue> getValueList() {
        return valueList;
    }

    public void setValueList(List<DicValue> valueList) {
        this.valueList = valueList;
    }

    @Override
    public String toString() {
        return "DicType{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", valueList=" + valueList +
                '}';
    }
}