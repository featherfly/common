
package cn.featherfly.common.gentool.db;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.builder.ColumnModel;
import cn.featherfly.common.db.builder.TableModel;
import cn.featherfly.common.db.metadata.SqlType;

/**
 * <p>
 * UserTable
 * </p>
 *
 * @author zhongj
 */
public class UserTable extends TableModel {

    UserTable() {
    }

    public enum UserColumns implements Column {
        id(new ColumnModel()),
        name(new ColumnModel());

        private Column column;

        private UserColumns(Column column) {
            this.column = column;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
            return column.getName();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getTypeName() {
            return column.getTypeName();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getRemark() {
            return column.getRemark();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDefaultValue() {
            return column.getDefaultValue();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SqlType getSqlType() {
            return column.getSqlType();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getType() {
            return column.getType();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getSize() {
            return column.getSize();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getColumnIndex() {
            return column.getColumnIndex();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isNullable() {
            return column.isNullable();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPrimaryKey() {
            return column.isPrimaryKey();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getDecimalDigits() {
            return column.getDecimalDigits();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAutoincrement() {
            return column.isAutoincrement();
        }

    }
}