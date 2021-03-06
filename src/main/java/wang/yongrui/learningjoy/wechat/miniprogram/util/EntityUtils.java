/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.util;

import java.lang.reflect.InvocationTargetException;
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
	 * @param sourseSet
	 * @param targetClass
	 * @return
	 */
	public static <S, T> Set<T> getTargetSetFromSourceSet(Set<S> sourseSet, Class<T> targetClass) {
		Set<T> targetSet = null;
		if (CollectionUtils.isNotEmpty(sourseSet)) {
			targetSet = new LinkedHashSet<>();
			for (S source : sourseSet) {
				try {
					T target = targetClass.newInstance();
					BeanUtils.copyProperties(source, target);
					targetSet.add(target);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return targetSet;
	}

	/**
	 * @param entitySet
	 * @param entityClass
	 * @param objectClass
	 * @return
	 */
	public static <E, O> Set<O> getObjectSetFromEntitySetWithHeritage(Set<E> entitySet, Class<E> entityClass,
			Class<O> objectClass) {
		Set<O> objSet = null;
		if (CollectionUtils.isNotEmpty(entitySet)) {
			objSet = new LinkedHashSet<>();
			for (E entity : entitySet) {
				try {
					O obj = objectClass.getConstructor(entityClass).newInstance(entity);
					BeanUtils.copyProperties(entity, obj);
					objSet.add(obj);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}

		return objSet;
	}

}
