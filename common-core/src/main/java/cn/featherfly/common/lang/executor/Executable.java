package cn.featherfly.common.lang.executor;

/**
 * <p>
 * 可执行接口
 * </p>
 * 
 * @author zhongj
 * @since 1.6
 * @version 1.0
 */
@FunctionalInterface
public interface Executable {
    /**
     * 执行
     */
    void execute();
}
