/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wang Yongrui
 *
 */
@Embeddable
@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class WeChatUserBasic {

	@Column(unique = true)
	private String unionId;

}
