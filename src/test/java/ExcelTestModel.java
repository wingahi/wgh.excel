/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ExcelTestModel implements  Serializable{
    private String name;
    private Integer age;
    private Date date;
    private EnumTest state;
    private Double amount;
    private Long tttt;
    private Boolean black;
    private long count;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    public EnumTest getState() {
        return state;
    }

    public void setState(EnumTest state) {
        this.state = state;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean isBlack() {
        return black;
    }

    public void setBlack(Boolean black) {
        this.black = black;
    }

    public Long getTttt() {
        return tttt;
    }

    public void setTttt(Long tttt) {
        this.tttt = tttt;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ExcelTestModel{" + "name=" + name + ", age=" + age + ", date=" + date + ", state=" + state + ", amount=" + amount + ", tttt=" + tttt + ", black=" + black + ", count=" + count + '}';
    }

    
    
    
}
