/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.NoticeCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#create(
	 * wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice)
	 */
	@Override
	public Notice create(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#
	 * retrieve(java.lang.Long)
	 */
	@Override
	public Notice retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#
	 * retrieveAllByCriteria(wang.yongrui.learningjoy.wechat.miniprogram.entity.
	 * web.criteria.NoticeCriteria)
	 */
	@Override
	public Set<Notice> retrieveAllByCriteria(NoticeCriteria noticeCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#
	 * patchUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.
	 * Notice)
	 */
	@Override
	public Notice patchUpdate(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice)
	 */
	@Override
	public Notice putUpdate(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}

}
