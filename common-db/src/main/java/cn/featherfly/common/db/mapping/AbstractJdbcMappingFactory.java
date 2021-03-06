
package cn.featherfly.common.db.mapping;

import java.math.BigDecimal;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.jpa.ColumnDefault;
import cn.featherfly.common.db.jpa.Comment;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.ClassNameJpaConversion;
import cn.featherfly.common.repository.mapping.ClassNameUnderlineConversion;
import cn.featherfly.common.repository.mapping.PropertyMapping;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;
import cn.featherfly.common.repository.mapping.PropertyNameJpaConversion;
import cn.featherfly.common.repository.mapping.PropertyNameUnderlineConversion;

/**
 * <p>
 * AbstractMappingFactory
 * </p>
 * .
 *
 * @author zhongj
 */
public abstract class AbstractJdbcMappingFactory implements JdbcMappingFactory {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The mapped types. */
    protected final Map<Class<?>, ClassMapping<?>> mappedTypes = new HashMap<>();

    /** The metadata. */
    protected DatabaseMetadata metadata;

    /** The dialect. */
    protected Dialect dialect;

    /** The class name conversions. */
    protected List<ClassNameConversion> classNameConversions = new ArrayList<>();

    /** The property name conversions. */
    protected List<PropertyNameConversion> propertyNameConversions = new ArrayList<>();

    /** The sql type mapping manager. */
    protected SqlTypeMappingManager sqlTypeMappingManager;

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata the metadata
     * @param dialect  the dialect
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect) {
        this(metadata, dialect, null);
    }

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata              the metadata
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        this(metadata, dialect, sqlTypeMappingManager, null, null);
    }

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata                the metadata
     * @param dialect                 the dialect
     * @param classNameConversions    the class name conversions
     * @param propertyNameConversions the property name conversions
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        this(metadata, dialect, new SqlTypeMappingManager(), classNameConversions, propertyNameConversions);
    }

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param sqlTypeMappingManager   the sql type mapping manager
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
            List<PropertyNameConversion> propertyNameConversions) {
        super();
        this.metadata = metadata;
        this.dialect = dialect;

        if (Lang.isEmpty(classNameConversions)) {
            this.classNameConversions.add(new ClassNameJpaConversion());
            this.classNameConversions.add(new ClassNameUnderlineConversion());
        } else {
            this.classNameConversions.addAll(classNameConversions);
        }

        if (Lang.isEmpty(propertyNameConversions)) {
            this.propertyNameConversions.add(new PropertyNameJpaConversion());
            this.propertyNameConversions.add(new PropertyNameUnderlineConversion());
        } else {
            this.propertyNameConversions.addAll(propertyNameConversions);
        }
        if (sqlTypeMappingManager == null) {
            this.sqlTypeMappingManager = new SqlTypeMappingManager();
        } else {
            this.sqlTypeMappingManager = sqlTypeMappingManager;
        }
    }

    /**
     * Sets the column mapping.
     *
     * @param mapping      the mapping
     * @param beanProperty the bean property
     */
    protected void setColumnMapping(PropertyMapping mapping, BeanProperty<?> beanProperty) {
        boolean isPk = beanProperty.hasAnnotation(Id.class);
        Column column = beanProperty.getAnnotation(Column.class);
        if (column != null) {
            mapping.setSize(
                    ClassUtils.isParent(Number.class, beanProperty.getType()) ? column.precision() : column.length());
            mapping.setDecimalDigits(column.scale());
            mapping.setInsertable(column.insertable());
            mapping.setUpdatable(column.updatable());
            mapping.setUnique(column.unique());
            mapping.setNullable(column.nullable());
        } else {
            // @Column length的默认值
            if (beanProperty.getType() == String.class) {
                mapping.setSize(255);
            } else if (beanProperty.getType() == Double.class || beanProperty.getType() == Double.TYPE
                    || beanProperty.getType() == Float.class || beanProperty.getType() == Float.TYPE
                    || beanProperty.getType() == BigDecimal.class) {
                mapping.setSize(12);
                mapping.setDecimalDigits(2);
            }
        }
        if (mapping.getSize() == 0) {
            SQLType sqlType = sqlTypeMappingManager.getSqlType(beanProperty.getType());
            mapping.setSize(dialect.getDefaultSize(sqlType));
        }
        ColumnDefault columnDefault = beanProperty.getAnnotation(ColumnDefault.class);
        if (columnDefault != null) {
            mapping.setDefaultValue(columnDefault.value());
        }
        Comment comment = beanProperty.getAnnotation(Comment.class);
        if (comment != null) {
            mapping.setRemark(comment.value());
        }
        if (beanProperty.getType().isEnum()) {
            if (!sqlTypeMappingManager.isEnumWithOrdinal()) {
                mapping.setSize(dialect.getDefaultSize(sqlTypeMappingManager.getEnumOrdinalType()));
            }
        }

        if (isPk) {
            mapping.setNullable(false);
            GeneratedValue generatedValue = beanProperty.getAnnotation(GeneratedValue.class);
            if (generatedValue != null) {
                if (generatedValue.strategy() == GenerationType.IDENTITY
                        || generatedValue.strategy() == GenerationType.AUTO) {
                    mapping.setAutoincrement(true);
                } else {
                    // TODO 其他实现，后续慢慢添加-_-
                    throw new JdbcMappingException("只实现了IDENTITY, AUTO时使用数据库的自增长的策略");
                }
            } else {
                mapping.setAutoincrement(true);
            }
        }
    }

    /**
     * Checks if is transient.
     *
     * @param beanProperty the bean property
     * @param logInfo      the log info
     * @return true, if is transient
     */
    protected boolean isTransient(BeanProperty<?> beanProperty, StringBuilder logInfo) {
        boolean result = beanProperty.hasAnnotation(java.beans.Transient.class)
                || beanProperty.hasAnnotation(javax.persistence.Transient.class);
        if (result && logger.isDebugEnabled()) {
            logInfo.append(String.format("%s###\t%s is annotated with @Transient, ignore",
                    SystemPropertyUtils.getLineSeparator(), beanProperty.getName()));
        }
        return result;
    }

    /**
     * 返回dialect.
     *
     * @return dialect
     */
    @Override
    public Dialect getDialect() {
        return dialect;
    }

    /**
     * 返回sqlTypeMappingManager.
     *
     * @return sqlTypeMappingManager
     */
    @Override
    public SqlTypeMappingManager getSqlTypeMappingManager() {
        return sqlTypeMappingManager;
    }

    /**
     * 返回classNameConversions.
     *
     * @return classNameConversions
     */
    public List<ClassNameConversion> getClassNameConversions() {
        return classNameConversions;
    }

    /**
     * 设置classNameConversions.
     *
     * @param classNameConversions classNameConversions
     */
    public void setClassNameConversions(List<ClassNameConversion> classNameConversions) {
        this.classNameConversions = classNameConversions;
    }

    /**
     * 返回propertyNameConversions.
     *
     * @return propertyNameConversions
     */
    public List<PropertyNameConversion> getPropertyNameConversions() {
        return propertyNameConversions;
    }

    /**
     * 设置propertyNameConversions.
     *
     * @param propertyNameConversions propertyNameConversions
     */
    public void setPropertyNameConversions(List<PropertyNameConversion> propertyNameConversions) {
        this.propertyNameConversions = propertyNameConversions;
    }

    /**
     * 返回metadata.
     *
     * @return metadata
     */
    @Override
    public DatabaseMetadata getMetadata() {
        return metadata;
    }
}
