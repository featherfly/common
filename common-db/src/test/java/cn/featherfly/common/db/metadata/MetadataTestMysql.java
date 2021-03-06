package cn.featherfly.common.db.metadata;

import org.apache.commons.dbcp.BasicDataSource;

import cn.featherfly.common.db.Table;

public class MetadataTestMysql {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        //		dataSource.setUrl("jdbc:mysql://192.168.1.99:3306/mysql");
        //        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/db_test");
        dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/db_test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        DatabaseMetadata meta = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        System.out.println(meta.getName());

        for (Table td : meta.getTables()) {
            System.out.println("\t" + td.getName());
            td.getColumns().forEach(c -> {
                System.out.println("\t\t" + c.getName() + " " + c.getTypeName() + "(" + c.getType() + ")" + " "
                        + c.getDefaultValue());
            });
        }
        for (Table td : meta.getTables()) {
            System.out.println("\t" + td.toString());
        }
    }
}