package com.xu.server.base.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 16:34
 */

public enum GenderEnum implements BaseEnum<GenderEnum, Integer> {
    /**
     * 性别
     */
    MALE(1, "男") , FEMALE(0, "女"),
    ;
    final Integer gender;
    @JsonValue
    final String genderName;

    GenderEnum(Integer gender, String genderName) {
        this.gender = gender;
        this.genderName = genderName;
    }

    @Override
    public Integer getValue() {
        return gender;
    }

    @Override
    public String getName() {
        return genderName;
    }
}
