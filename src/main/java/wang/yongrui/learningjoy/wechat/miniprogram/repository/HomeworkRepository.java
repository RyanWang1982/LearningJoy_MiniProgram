/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.HomeworkEntity;

/**
 * @author Wang Yongrui
 *
 */
@Repository
public interface HomeworkRepository
		extends JpaRepository<HomeworkEntity, Long>, JpaSpecificationExecutor<HomeworkEntity> {

}
