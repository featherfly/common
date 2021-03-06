
package cn.featherfly.common.db.migration;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.db.JdbcTestBase;
import cn.featherfly.common.db.mapping.ObjectToDbMappingFactory;
import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.db.mapping.pojo.Entity;
import cn.featherfly.common.db.mapping.pojo.User;
import cn.featherfly.common.db.mapping.pojo.UserRole;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.DatabaseMetadataManager;
import cn.featherfly.common.repository.mapping.ClassMapping;

/**
 * <p>
 * VersionManagerTest
 * </p>
 *
 * @author zhongj
 */
public class VersionManagerTest extends JdbcTestBase {

    private Migrator migrator;

    private ObjectToDbMappingFactory factory;

    private SqlTypeMappingManager sqlTypeMappingManager;

    private VersionManager versionManager;

    @BeforeClass
    public void init() {
        sqlTypeMappingManager = new SqlTypeMappingManager();
        DatabaseMetadata metadata = DatabaseMetadataManager.getDefaultManager().create(dataSource);
        factory = new ObjectToDbMappingFactory(metadata, dialect, sqlTypeMappingManager);
        migrator = new Migrator(dataSource, dialect, sqlTypeMappingManager, true);
        factory.setCheckMapping(false);
        sqlTypeMappingManager.setEnumWithOrdinal(true);
        versionManager = new VersionManager(new File("./output/versions"), "migrator", migrator);
    }

    @Test
    void crateInitSqlFile() {
        File f = versionManager.createInitSqlFile("1.0.0", classMappings());
        System.out.println(f.getPath());
    }

    @Test
    void crateUpdateSqlFile() {
        File f = versionManager.createUpdateSqlFile("1.0.1", classMappings());
        System.out.println(f.getPath());
    }

    @Test
    void crateNext() {
        File f = versionManager.next(classMappings());
        System.out.println(f.getPath());
    }

    @Test
    void crateNext2() {
        File f = versionManager.next(classMappings(), new Version(1, 1, 0));
        System.out.println(f.getPath());
    }

    private Set<ClassMapping<?>> classMappings() {
        Set<ClassMapping<?>> set = new HashSet<>();
        set.add(factory.getClassMapping(Entity.class));
        set.add(factory.getClassMapping(User.class));
        set.add(factory.getClassMapping(UserRole.class));
        return set;
    }
}
