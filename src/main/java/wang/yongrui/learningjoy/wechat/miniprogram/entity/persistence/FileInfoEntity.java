/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.FileInfoBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "FILE")
@Getter
@Setter
public class FileInfoEntity extends FileInfoBasic {

}
