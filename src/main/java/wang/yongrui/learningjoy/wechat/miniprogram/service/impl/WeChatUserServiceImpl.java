/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserChildEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserParentEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.UserParent;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.WeChatUserRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class WeChatUserServiceImpl implements WeChatUserService {

	@Autowired
	private WeChatUserRepository userRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * create(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser)
	 */
	@Override
	public WeChatUser create(WeChatUser user) {
		WeChatUserEntity userEntity = new WeChatUserEntity();
		BeanUtils.copyProperties(user, userEntity);

		if (CollectionUtils.isNotEmpty(user.getParentSet())) {
			Set<UserParentEntity> userParentEntitySet = new HashSet<>();
			for (UserParent userParent : user.getParentSet()) {
				WeChatUserEntity parentEntity = (null == userParent.getParent()
						|| null == userParent.getParent().getId()) ? null
								: userRepository.findOne(userParent.getParent().getId());
				if (null != parentEntity) {
					Set<UserChildEntity> userChildEntitySet = CollectionUtils.isNotEmpty(
							parentEntity.getChildEntitySet()) ? parentEntity.getChildEntitySet() : new HashSet<>();
					UserChildEntity userChildEntity = new UserChildEntity();
					userChildEntity.setChild(userEntity);
					userChildEntity.setUser(parentEntity);
					userChildEntitySet.add(userChildEntity);
					parentEntity.setChildEntitySet(userChildEntitySet);

					UserParentEntity userParentEntity = new UserParentEntity();
					userParentEntity.setUser(userEntity);
					userParentEntity.setParent(parentEntity);
					userParentEntitySet.add(userParentEntity);
				}
			}

			if (CollectionUtils.isNotEmpty(userParentEntitySet)) {
				userEntity.setParentEntitySet(userParentEntitySet);
			}
		}

		userEntity = userRepository.saveAndFlush(userEntity);
		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(WeChatUserEntity_.childEntitySet);
		includedAttributeSet.add(WeChatUserEntity_.parentEntitySet);

		return new WeChatUser(userEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * retrieveBasicInfoByWeChatUnionId(java.lang.String)
	 */
	@Override
	public WeChatUser retrieveBasicInfoByWeChatUnionId(String weChatUnionId) {
		WeChatUserEntity userEntity = userRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			return criteriaBuilder.equal(root.get(WeChatUserEntity_.weChatInfo).get(WeChatUserBasic_.unionId),
					weChatUnionId);
		});

		return new WeChatUser(userEntity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * retrieveBasicInfo(java.lang.Long)
	 */
	@Override
	public WeChatUser retrieveBasicInfo(Long id) {
		return new WeChatUser(userRepository.findOne(id));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * retrieveWithSetting(java.lang.Long)
	 */
	@Override
	public WeChatUser retrieveWithSetting(Long id) {
		WeChatUserEntity userEntity = userRepository.findOne(id);
		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(WeChatUserEntity_.userSettingEntity);
		return new WeChatUser(userEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * retrieveWithChildren(java.lang.Long)
	 */
	@Override
	public WeChatUser retrieveWithChildren(Long id) {
		WeChatUserEntity userEntity = userRepository.findOne(id);
		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(WeChatUserEntity_.childEntitySet);
		return new WeChatUser(userEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * patchUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.
	 * WeChatUser)
	 */
	@Override
	public WeChatUser patchUpdate(WeChatUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.
	 * WeChatUser)
	 */
	@Override
	public WeChatUser putUpdate(WeChatUser user) {
		// TODO Auto-generated method stub
		return null;
	}

}
