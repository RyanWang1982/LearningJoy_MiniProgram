/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import static org.springframework.beans.BeanUtils.*;
import static wang.yongrui.learningjoy.wechat.miniprogram.util.PatchBeanUtils.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.NoticeEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.NoticeEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserNoticeEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserNoticeEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.UserNotice;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.NoticeCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.NoticeRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.WeChatUserRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private WeChatUserRepository userRepository;

	@Autowired
	private NoticeRepository noticeRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#create(
	 * wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice)
	 */
	@Override
	public Notice create(Notice notice) {
		NoticeEntity noticeEntity = new NoticeEntity();
		copyProperties(notice, noticeEntity);

		WeChatUserEntity senderEntity = userRepository.findOne(notice.getSender().getId());
		noticeEntity.setSenderEntity(senderEntity);

		Set<UserNotice> recipientNoticeSet = notice.getRecipientNoticeSet();
		List<Long> recipientIdList = new ArrayList<>();
		for (UserNotice eachUserNotice : recipientNoticeSet) {
			recipientIdList.add(eachUserNotice.getUser().getId());
		}
		List<WeChatUserEntity> recipientNoticeEntityList = userRepository.findAll(recipientIdList);
		Set<UserNoticeEntity> recipientNoticeEntitySet = new LinkedHashSet<>();
		for (WeChatUserEntity eachWeChatUserEntity : recipientNoticeEntityList) {
			UserNoticeEntity userNoticeEntity = new UserNoticeEntity();
			userNoticeEntity.setUser(eachWeChatUserEntity);
			recipientNoticeEntitySet.add(userNoticeEntity);
		}
		noticeEntity.setRecipientNoticeEntitySet(recipientNoticeEntitySet);

		noticeEntity = noticeRepository.saveAndFlush(noticeEntity);
		entityManager.refresh(noticeEntity);

		return retrieve(noticeEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#
	 * retrieve(java.lang.Long)
	 */
	@Override
	public Notice retrieve(Long id) {
		return new Notice(noticeRepository.findOne(id));
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
		List<NoticeEntity> allNoticeEntityList = noticeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);

			Join<NoticeEntity, WeChatUserEntity> senderJoin = root.join(NoticeEntity_.senderEntity);
			Join<UserNoticeEntity, WeChatUserEntity> recipientNoticeJoin = root
					.join(NoticeEntity_.recipientNoticeEntitySet).join(UserNoticeEntity_.user);

			Predicate restrictions = criteriaBuilder.conjunction();
			if (null != noticeCriteria.getSenderId()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.equal(senderJoin.get(WeChatUserEntity_.id), noticeCriteria.getSenderId()));
			}
			if (null != noticeCriteria.getRecipientId()) {
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
						.equal(recipientNoticeJoin.get(WeChatUserEntity_.id), noticeCriteria.getRecipientId()));
			}
			if (null != noticeCriteria.getStartDate()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.greaterThan(root.get(NoticeEntity_.deadline), noticeCriteria.getStartDate()));
			}
			if (null != noticeCriteria.getEndDate()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.lessThan(root.get(NoticeEntity_.deadline), noticeCriteria.getEndDate()));
			}
			return restrictions;
		});

		Set<Notice> allNoticeSet = new LinkedHashSet<>();
		for (NoticeEntity eachNoticeEntity : allNoticeEntityList) {
			allNoticeSet.add(new Notice(eachNoticeEntity));
		}

		return allNoticeSet;
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
		NoticeEntity noticeEntity = noticeRepository.findOne(notice.getId());
		updateProperties(notice, noticeEntity, false);
		noticeEntity = noticeRepository.saveAndFlush(noticeEntity);
		return new Notice(noticeEntity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice)
	 */
	@Override
	public Notice putUpdate(Notice notice) {
		NoticeEntity noticeEntity = noticeRepository.findOne(notice.getId());
		updateProperties(notice, noticeEntity, true);
		noticeEntity = noticeRepository.saveAndFlush(noticeEntity);
		return new Notice(noticeEntity);
	}

}
