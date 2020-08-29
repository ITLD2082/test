package com.ld.bean;

public class Province {

    private Integer id;
    private String name;
    private String remark;//B：新增了功能

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
