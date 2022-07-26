package com.xu.server.base.pojo.bo;

import com.xu.server.base.enums.GenderEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/28 17:03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUserBo implements Serializable {
    private Long id;
    private String username;
    private String nickname;
    private GenderEnum gender;
    private String  email;
    private boolean locked;
    private boolean expire;
}
