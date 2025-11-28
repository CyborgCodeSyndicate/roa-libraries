package ${package}.db_module.db;

import io.cyborgcode.roa.db.config.DbType;
import java.sql.Driver;

public enum Databases implements DbType<Databases> {

#if ( $dbType.equals("H2") || $dbType == ""  || $dbType == "/" )
   H2(new org.h2.Driver(), "jdbc:h2")
#end

#if ( $dbType.equals("POSTGRES") )
   POSTGRES(null, "jdbc:postgresql")
#end

#if ( $dbType.equals("MYSQL") )
   MYSQL(null, "jdbc:mysql")
#end
   ;

   private final Driver driver;
   private final String protocol;

   Databases(Driver driver, String protocol) {
      this.driver = driver;
      this.protocol = protocol;
   }

   @Override
   public Driver driver() {
      return driver;
   }

   @Override
   public String protocol() {
      return protocol;
   }

   @Override
   public Databases enumImpl() {
      return this;
   }
}
