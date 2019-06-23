package cn.featherfly.common.lang;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.lang.asserts.IllegalArgumentAssert;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出IllegalArgumentException异常
 * 一般用于检查传入参数是否合法
 * </p>
 *
 * @author 钟冀
 * @since 1.0
 * @version 1.0
 */
public final class AssertIllegalArgument {
    
    private static final IllegalArgumentAssert ASSERT = new IllegalArgumentAssert();

    /**
     * @param parentType
     * @param subType
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isParent(java.lang.Class, java.lang.Class)
     */
    public static void isParent(Class<?> parentType, Class<?> subType) {
        ASSERT.isParent(parentType, subType);
    }

    private AssertIllegalArgument() {
    }

    /**
     * @param object
     * @param arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotNull(java.lang.Object, java.lang.String)
     */
    public static void isNotNull(Object object, String arg) {
        ASSERT.isNotNull(object, arg);
    }

    /**
     * @param text
     * @param arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotBlank(java.lang.String, java.lang.String)
     */
    public static void isNotBlank(String text, String arg) {
        ASSERT.isNotBlank(text, arg);
    }

    /**
     * @param obj
     * @param args
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.Object, java.lang.String)
     */
    public static void isNotEmpty(Object obj, String args) {
        ASSERT.isNotEmpty(obj, args);
    }

    /**
     * @param text
     * @param arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.String, java.lang.String)
     */
    public static void isNotEmpty(String text, String arg) {
        ASSERT.isNotEmpty(text, arg);
    }

    /**
     * @param array
     * @param arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.lang.Object[], java.lang.String)
     */
    public static void isNotEmpty(Object[] array, String arg) {
        ASSERT.isNotEmpty(array, arg);
    }

    /**
     * @param collection
     * @param arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.util.Collection, java.lang.String)
     */
    public static void isNotEmpty(Collection<?> collection, String arg) {
        ASSERT.isNotEmpty(collection, arg);
    }

    /**
     * @param map
     * @param arg
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isNotEmpty(java.util.Map, java.lang.String)
     */
    public static void isNotEmpty(Map<?, ?> map, String arg) {
        ASSERT.isNotEmpty(map, arg);
    }

    /**
     * @param file
     * @param args
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isExists(java.io.File, java.lang.String)
     */
    public static void isExists(File file, String args) {
        ASSERT.isExists(file, args);
    }

    /**
     * @param clazz
     * @param obj
     * @see cn.featherfly.common.lang.asserts.LocalizedAssert#isInstanceOf(java.lang.Class, java.lang.Object)
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        ASSERT.isInstanceOf(clazz, obj);
    }
}