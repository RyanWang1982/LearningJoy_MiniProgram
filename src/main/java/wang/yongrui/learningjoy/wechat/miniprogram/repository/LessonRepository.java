/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity;

/**
 * @author Wang Yongrui
 *
 */
@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long>, JpaSpecificationExecutor<LessonEntity> {

}
