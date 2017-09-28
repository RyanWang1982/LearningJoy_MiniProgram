/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;

/**
 * @author Wang Yongrui
 *
 */
@Repository
public interface WeChatUserRepository
		extends JpaRepository<WeChatUserEntity, Long>, JpaSpecificationExecutor<WeChatUserEntity> {

}
