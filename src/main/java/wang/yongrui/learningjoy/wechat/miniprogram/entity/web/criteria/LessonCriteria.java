/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria;

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
public class LessonCriteria {

	private Long courseId;

	private Long teacherId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar endDate;

}
