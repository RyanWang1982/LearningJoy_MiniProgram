/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import java.util.Set;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.NoticeCriteria;

/**
 * @author Wang Yongrui
 *
 */
public interface NoticeService {

	/**
	 * @param notice
	 * @return
	 */
	public Notice create(Notice notice);

	/**
	 * @param id
	 * @return
	 */
	public Notice retrieve(Long id);

	/**
	 * @param noticeCriteria
	 * @return
	 */
	public Set<Notice> retrieveAllByCriteria(NoticeCriteria noticeCriteria);

	/**
	 * @param notice
	 * @return
	 */
	public Notice patchUpdate(Notice notice);

	/**
	 * @param notice
	 * @return
	 */
	public Notice putUpdate(Notice notice);

}
