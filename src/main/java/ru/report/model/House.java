package ru.report.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class House implements Serializable {

    private Long id;
    private Integer unom;
    private String mr;
    private String address;
    private BigDecimal sqrOForm;
    private String vidPrava;
    private String nameSubject;

    public House() {
    }

    public House(Integer unom, String mr, String address) {
        this.unom = unom;
        this.mr = mr;
        this.address = address;
    }

    public House(String address, BigDecimal sqrOForm, String vidPrava) {
        this.address = address;
        this.sqrOForm = sqrOForm;
        this.vidPrava = vidPrava;
    }

    public House(Integer unom, String address, BigDecimal sqrOForm, String vidPrava) {
        this.unom = unom;
        this.address = address;
        this.sqrOForm = sqrOForm;
        this.vidPrava = vidPrava;
    }

    public House(Long id, Integer unom, String mr, String address) {
        this.id = id;
        this.unom = unom;
        this.mr = mr;
        this.address = address;
    }

    public House(Long id, Integer unom, String mr, String address, BigDecimal sqrOForm, String vidPrava, String nameSubject) {
        this.id = id;
        this.unom = unom;
        this.mr = mr;
        this.address = address;
        this.sqrOForm = sqrOForm;
        this.vidPrava = vidPrava;
        this.nameSubject = nameSubject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUnom() {
        return unom;
    }

    public void setUnom(Integer unom) {
        this.unom = unom;
    }

    public String getMr() {
        return mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSqrOForm() {
        return sqrOForm;
    }

    public void setSqrOForm(BigDecimal sqrOForm) {
        this.sqrOForm = sqrOForm;
    }

    public String getVidPrava() {
        return vidPrava;
    }

    public void setVidPrava(String vidPrava) {
        this.vidPrava = vidPrava;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", unom=" + unom +
                ", mr='" + mr + '\'' +
                ", address='" + address + '\'' +
                ", sqrOForm=" + sqrOForm +
                ", vidPrava='" + vidPrava + '\'' +
                ", nameSubject='" + nameSubject + '\'' +
                '}';
    }
}
