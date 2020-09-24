package org.crm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * (DicValue)实体类
 *
 * @author makejava
 * @since 2020-09-04 19:10:07
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DicValue implements Serializable {
    private static final long serialVersionUID = 861932858942846291L;
    /**
     * ����������UUID
     */
    private String id;
    /**
     * ����Ϊ�գ�����Ҫ��ͬһ���ֵ��������ֵ�ֵ�����ظ�������Ψһ�ԡ�
     */
    private String value;
    /**
     * ����Ϊ��
     */
    private String text;
    /**
     * ����Ϊ�գ�����Ϊ�յ�ʱ��Ҫ�������������
     */
    private String orderno;
    /**
     * ���
     */
    private String typecode;

    private DicType dicType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public DicType getDicType() {
        return dicType;
    }

    public void setDicType(DicType dicType) {
        this.dicType = dicType;
    }

    @Override
    public String toString() {
        return "DicValue{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", text='" + text + '\'' +
                ", orderno='" + orderno + '\'' +
                ", typecode='" + typecode + '\'' +
                ", dicType=" + dicType +
                '}';
    }
}