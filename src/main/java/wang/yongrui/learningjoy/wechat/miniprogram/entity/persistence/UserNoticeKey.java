/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
public class UserNoticeKey implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long user;

	private Long notice;

}
