/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
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
		userEntity = userRepository.saveAndFlush(userEntity);

		return new WeChatUser(userEntity);
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
		Set<Attribute<?, ?>> includAttributeSet = new HashSet<>();
		includAttributeSet.add(WeChatUserEntity_.userSettingEntity);
		return new WeChatUser(userEntity, includAttributeSet);
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
		Set<Attribute<?, ?>> includAttributeSet = new HashSet<>();
		includAttributeSet.add(WeChatUserEntity_.childEntitySet);
		return new WeChatUser(userEntity, includAttributeSet);
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
