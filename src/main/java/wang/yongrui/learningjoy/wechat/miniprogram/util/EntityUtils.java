/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.util;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author Wang Yongrui
 *
 */
public class EntityUtils {

	/**
	 * @param entitySet
	 * @param objectClass
	 * @return
	 */
	public static <E, O> Set<O> getObjectSetFromEntitySet(Set<E> entitySet, Class<O> objectClass) {
		Set<O> objSet = null;
		if (CollectionUtils.isNotEmpty(entitySet)) {
			objSet = new LinkedHashSet<>();
			for (E entity : entitySet) {
				try {
					O obj = objectClass.newInstance();
					BeanUtils.copyProperties(entity, obj);
					objSet.add(obj);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return objSet;
	}
}
