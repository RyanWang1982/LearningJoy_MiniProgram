/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import java.util.Set;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Homework;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.HomeworkCriteria;

/**
 * @author Wang Yongrui
 *
 */
public interface HomeworkService {

	/**
	 * @param homework
	 * @return
	 */
	public Homework create(Homework homework);

	/**
	 * @param id
	 * @return
	 */
	public Homework retrieve(Long id);

	/**
	 * @param homeworkCriteria
	 * @return
	 */
	public Set<Homework> retrieveAllByCriteria(HomeworkCriteria homeworkCriteria);

	/**
	 * @param homework
	 * @return
	 */
	public Homework patchUpdate(Homework homework);

	/**
	 * @param homework
	 * @return
	 */
	public Homework putUpdate(Homework homework);

}
