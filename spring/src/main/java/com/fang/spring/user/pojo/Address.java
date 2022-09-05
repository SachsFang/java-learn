package com.fang.spring.user.pojo;

/**
 * @author shaobin
 * @date 2022/9/4 15:44
 */
public class Address {
    private String city;
    private String detail;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
