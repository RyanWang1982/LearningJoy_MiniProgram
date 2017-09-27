/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;

/**
 * @author Wang Yongrui
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>, JpaSpecificationExecutor<CourseEntity> {

}
