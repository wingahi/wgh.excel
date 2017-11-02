/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Administrator
 */
public enum EnumTest {
    MALE("男"),
    FEMALE("女");
    private String description;

    private EnumTest(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
