
package testlambda;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import cn.featherfly.common.exception.ReflectException;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.function.SerializableConsumer;
import cn.featherfly.common.lang.function.SerializableSupplier;

/**
 * <p>
 * LambdaUtils
 * </p>
 *
 * @author zhongj
 */
public class Lambdas {

    private static final Map<SerializedLambda, SerializedLambdaInfo> CACHE_LAMBDA_INFO = new ConcurrentHashMap<>(8);

    // private static final Map<SerializedLambda, String> CACHE_FIELD_NAME = new
    // ConcurrentHashMap<>(8);

    /**
     * The Class SerializedLambdaInfo.
     */
    public static class SerializedLambdaInfo {

        private String methodName;

        private String methodDeclaredClassName;

        private String methodInstanceClassName;

        private String propertyName;

        private Method method;

        private SerializedLambda serializedLambda;

        /**
         * 返回propertyName.
         *
         * @return propertyName
         */
        public String getPropertyName() {
            return propertyName;
        }

        /**
         * 返回methodName.
         *
         * @return methodName
         */
        public String getMethodName() {
            return methodName;
        }

        /**
         * 返回methodDeclaredClassName.
         *
         * @return methodDeclaredClassName
         */
        public String getMethodDeclaredClassName() {
            return methodDeclaredClassName;
        }

        /**
         * 返回methodInstanceClassName.
         *
         * @return methodInstanceClassName
         */
        public String getMethodInstanceClassName() {
            return methodInstanceClassName;
        }

        /**
         * 返回serializedLambda.
         *
         * @return serializedLambda
         */
        public SerializedLambda getSerializedLambda() {
            return serializedLambda;
        }

        /**
         * 返回method.
         *
         * @return method
         */
        public Method getMethod() {
            return method;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "SerializedLambdaInfo [methodName=" + methodName + ", methodDeclaredClassName="
                    + methodDeclaredClassName + ", methodInstanceClassName=" + methodInstanceClassName
                    + ", propertyName=" + propertyName + ", method=" + method + ", serializedLambda=" + serializedLambda
                    + "]";
        }

    }

    /**
     * The Class SerializableConsumerLambdaInfo.
     */
    public static class SerializableConsumerLambdaInfo<T> implements Consumer<T> {

        private Consumer<T> consumer;

        private Object instance;

        private SerializedLambdaInfo serializedLambdaInfo;

        /**
         * Instantiates a new serializable consumer lambda info.
         *
         * @param serializedLambdaInfo the serialized lambda info
         * @param instance             the instance
         * @param consumer             the consumer
         */
        public SerializableConsumerLambdaInfo(SerializedLambdaInfo serializedLambdaInfo, Object instance,
                Consumer<T> consumer) {
            super();
            this.instance = instance;
            this.serializedLambdaInfo = serializedLambdaInfo;
            this.consumer = consumer;
        }

        /**
         * 返回instance.
         *
         * @return instance
         */
        public Object getInstance() {
            return instance;
        }

        /**
         * 返回serializedLambdaInfo.
         *
         * @return serializedLambdaInfo
         */
        public SerializedLambdaInfo getSerializedLambdaInfo() {
            return serializedLambdaInfo;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void accept(T t) {
            consumer.accept(t);
        }
    }

    /**
     * The Class SerializableSupplierLambdaInfo.
     *
     * @param <T> the generic type
     */
    public static class SerializableSupplierLambdaInfo<T> implements Supplier<T> {

        private T value;

        private SerializedLambdaInfo serializedLambdaInfo;

        private Supplier<T> supplier;
        
        private boolean init;

        /**
         */
        private SerializableSupplierLambdaInfo(SerializedLambdaInfo serializedLambdaInfo, Supplier<T> supplier) {
            this.serializedLambdaInfo = serializedLambdaInfo;
            this.supplier = supplier;
        }

        /**
         * 返回value.
         *
         * @return value
         */
        public T getValue() {
            if (!init) {
                value = get();
                init = true;
            }
            return value;
        }

        /**
         * 返回serializedLambdaInfo.
         *
         * @return serializedLambdaInfo
         */
        public SerializedLambdaInfo getSerializedLambdaInfo() {
            return serializedLambdaInfo;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "SerializableSupplierLambdaInfo [value=" + value + ", serializedLambdaInfo=" + serializedLambdaInfo
                    + "]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T get() {
            return supplier.get();
        }
    }

    /**
     * Gets the lambda info.
     *
     * @param lambda the lambda
     * @return the lambda info
     */
    public static SerializedLambdaInfo getLambdaInfo(Serializable lambda) {
        SerializedLambda serializedLambda = getSerializedLambda(lambda);
        SerializedLambdaInfo info = CACHE_LAMBDA_INFO.get(serializedLambda);
        if (null != info) {
            return info;
        }
        SerializedLambdaInfo info2 = new SerializedLambdaInfo();
        info2.serializedLambda = serializedLambda;
        info2.methodDeclaredClassName = serializedLambda.getImplClass().replaceAll("/", ".");
        info2.methodName = serializedLambda.getImplMethodName();
        info2.method = ClassUtils.findMethod(ClassUtils.forName(info2.methodDeclaredClassName),
                o -> serializedLambda.getCapturedArgCount() == o.getParameterCount()
                        && o.getName().equals(info2.methodName));
        info2.propertyName = methodToPropertyName(info2.methodName);
        if (lambda instanceof Function || lambda instanceof BiConsumer || lambda instanceof BiFunction) {
            info2.methodInstanceClassName = org.apache.commons.lang3.StringUtils
                    .substringBefore(serializedLambda.getInstantiatedMethodType(), ";").substring(2)
                    .replaceAll("/", ".");
        } else if (lambda instanceof Supplier) {
            Class<?> obj = serializedLambda.getCapturedArg(0).getClass();
            info2.methodInstanceClassName = obj.getName();
        } else if (lambda instanceof Consumer) {
            info2.methodInstanceClassName = serializedLambda.getCapturedArg(0).getClass().getName();
        } else {
            throw new UnsupportedException("unsupported for " + lambda.getClass().getName());
        }
        CACHE_LAMBDA_INFO.put(serializedLambda, info2);
        return info2;
    }

    /**
     * Gets the serializable supplier lambda info.
     *
     * @param <T>    the generic type
     * @param lambda the lambda
     * @return the serializable supplier lambda info
     */
    public static <
            T> SerializableSupplierLambdaInfo<T> getSerializableSupplierLambdaInfo(SerializableSupplier<T> lambda) {
        SerializedLambdaInfo info = getLambdaInfo(lambda);
        //        Object value = BeanUtils.getProperty(info.getSerializedLambda().getCapturedArg(0), info.getPropertyName());
        return new SerializableSupplierLambdaInfo<>(info, lambda);
    }

    /**
     * Gets the serializable supplier lambda info.
     *
     * @param <T>    the generic type
     * @param lambda the lambda
     * @return the serializable supplier lambda info
     */
    public static <
            T> SerializableConsumerLambdaInfo<T> getSerializableConsumerLambdaInfo(SerializableConsumer<T> lambda) {
        SerializedLambdaInfo info = getLambdaInfo(lambda);
        return new SerializableConsumerLambdaInfo<>(info, info.getSerializedLambda().getCapturedArg(0), lambda);
    }

    /**
     * Gets the serialized lambda.
     *
     * @param lambda the lambda
     * @return the serialized lambda
     */
    public static SerializedLambda getSerializedLambda(Serializable lambda) {
        return computeSerializedLambda(lambda);
    }

    /**
     * Gets the lambda method.
     *
     * @param lambda the lambda
     * @return the lambda method
     */
    public static Method getLambdaMethod(SerializedLambda lambda) {
        String className = lambda.getImplClass().replaceAll("/", ".");
        String methodName = lambda.getImplMethodName();
        Method method = ClassUtils.findMethod(ClassUtils.forName(className),
                o -> lambda.getCapturedArgCount() == o.getParameterCount() && o.getName().equals(methodName));
        return method;
    }

    /**
     * Gets the lambda method.
     *
     * @param lambda the lambda
     * @return the lambda method
     */
    public static Method getLambdaMethod(Serializable lambda) {
        return getLambdaMethod(getSerializedLambda(lambda));
    }

    /**
     * Gets the lambda method name.
     *
     * @param lambda the lambda
     * @return the lambda method name
     */
    public static String getLambdaMethodName(Serializable lambda) {
        return getLambdaMethodName(computeSerializedLambda(lambda));
    }

    /**
     * Gets the lambda method name.
     *
     * @param lambda the lambda
     * @return the lambda method name
     */
    public static String getLambdaMethodName(SerializedLambda lambda) {
        return lambda.getImplMethodName();
    }

    /**
     * Gets the lambda property name.
     *
     * @param lambda the lambda
     * @return the lambda property name
     */
    public static String getLambdaPropertyName(Serializable lambda) {
        return getLambdaPropertyName(computeSerializedLambda(lambda));
    }

    /**
     * Gets the lambda property name.
     *
     * @param lambda the lambda
     * @return the lambda property name
     */
    public static String getLambdaPropertyName(SerializedLambda lambda) {
        return methodToPropertyName(getLambdaMethodName(lambda));
    }

    private static SerializedLambda computeSerializedLambda(Serializable lambda) {
        try {
            Class<?> cl = lambda.getClass();
            Method m = cl.getDeclaredMethod("writeReplace");
            m.setAccessible(true);
            Object replacement = m.invoke(lambda);
            if (!(replacement instanceof SerializedLambda)) {
                return null; // custom interface implementation
            }
            return (SerializedLambda) replacement;
        } catch (Exception e) {
            throw new ReflectException("get SerializedLambda fail", e);
        }
    }

    private static String methodToPropertyName(String methodName) {
        String name = null;
        if (methodName.startsWith("get")) {
            name = methodName.substring(3);
        } else if (methodName.startsWith("is")) {
            name = methodName.substring(2);
        } else {
            name = methodName;
        }
        if (name != null) {
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        return name;
    }
}
