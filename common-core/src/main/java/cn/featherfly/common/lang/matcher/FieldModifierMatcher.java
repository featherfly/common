
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Field;

import cn.featherfly.common.lang.reflect.Modifier;

/**
 * <p>
 * 匹配Field Modifier的实现
 * </p>
 *
 * @author zhongj
 */
public class FieldModifierMatcher extends MemberModifierMatcher<Field>
    implements FieldMatcher{
    
    /**
     */
    public FieldModifierMatcher() {
    }
    
    /**
     * @param modifier modifier
     */
    public FieldModifierMatcher(Modifier modifier) {        
        super(modifier);
    }
}
