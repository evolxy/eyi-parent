package com.xu.server.email.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/5/26 17:01
 */

@Data
@AllArgsConstructor
public class EmailInfo {
	private String to;
	private String subject;
}
