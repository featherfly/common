
package cn.featherfly.common.structure.tree.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 树结构对象标注判断是否相同的方法，返回boolean类型
 * </p>
 * 
 * @author Zhong Ji
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Equals {
}
