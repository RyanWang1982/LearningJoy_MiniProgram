/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria;

import java.io.Serializable;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
public class CourseCriteria implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long teacherId;

	private Long studentId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar endDate;

}
