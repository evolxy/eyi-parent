package com.xu.server.base.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 16:34
 */

public enum GenderEnum {
    MALE(true, "男") , FEMALE(false, "女"),
    ;
    final boolean gender;
    @JsonValue
    final String genderName;

    GenderEnum(boolean gender, String genderName) {
        this.gender = gender;
        this.genderName = genderName;
    }
}
