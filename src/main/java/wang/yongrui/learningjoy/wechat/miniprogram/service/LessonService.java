/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.LessonCriteria;

/**
 * @author Wang Yongrui
 *
 */
public interface LessonService {

	/**
	 * @param lesson
	 * @return
	 */
	public Lesson create(Lesson lesson);

	/**
	 * @param id
	 * @return
	 */
	public Lesson retrieve(Long id);

	/**
	 * @param lessonCriteria
	 * @param pageable
	 * @return
	 */
	public Page<Lesson> retrievePagination(LessonCriteria lessonCriteria, Pageable pageable);

	/**
	 * @param lesson
	 * @return
	 */
	public Lesson patchUpdate(Lesson lesson);

	/**
	 * @param lesson
	 * @return
	 */
	public Lesson putUpdate(Lesson lesson);

}
