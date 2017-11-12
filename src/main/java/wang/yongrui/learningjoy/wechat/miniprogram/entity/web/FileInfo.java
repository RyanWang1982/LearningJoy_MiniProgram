/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.FileInfoBasic;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class FileInfo extends FileInfoBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
