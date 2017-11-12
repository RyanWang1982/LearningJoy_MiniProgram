/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import java.util.Set;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.StudentPerformance;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.StudentPerformanceCriteria;

/**
 * @author Wang Yongrui
 *
 */
public interface StudentPerformanceService {

	/**
	 * @param studentPerformance
	 * @return
	 */
	public StudentPerformance create(StudentPerformance studentPerformance);

	/**
	 * @param id
	 * @return
	 */
	public StudentPerformance retrieve(Long id);

	/**
	 * @param studentPerformanceCriteria
	 * @return
	 */
	public Set<StudentPerformance> retrieveAllByCriteria(StudentPerformanceCriteria studentPerformanceCriteria);

	/**
	 * @param studentPerformance
	 * @return
	 */
	public StudentPerformance patchUpdate(StudentPerformance studentPerformance);

	/**
	 * @param studentPerformance
	 * @return
	 */
	public StudentPerformance putUpdate(StudentPerformance studentPerformance);

}
