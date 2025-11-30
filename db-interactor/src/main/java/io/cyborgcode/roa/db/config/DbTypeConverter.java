package io.cyborgcode.roa.db.config;

import io.cyborgcode.utilities.reflections.ReflectionUtil;
import java.lang.reflect.Method;
import org.aeonbits.owner.Converter;

import static io.cyborgcode.roa.db.config.DbConfigHolder.getDbConfig;

/**
 * OWNER converter for {@link DbType}.  Uses the singleton {@link DbConfig}
 * loaded by {@link DbConfigHolder#getDbConfig()} to discover via
 * {@link ReflectionUtil} exactly one enum implementing {@link DbType}
 * in your project package, then converts the raw property string into
 * that enum constant.
 *
 * <p>If zero or more than one {@code enum} implementing {@link DbType}
 * is found, this converter will fail fast with an {@link IllegalStateException}.</p>
 *
 * @author Cyborg Code Syndicate 💍👨💻
 */
public class DbTypeConverter implements Converter<DbType<?>> {

   /**
    * Convert a {@code String} property into a {@link DbType} enum instance.
    *
    * @param method the {@link Method} annotated with the {@code @Key} for which this conversion applies
    * @param input  the raw String value from configuration (e.g. "POSTGRES")
    * @return the matching {@link DbType} enum constant
    */
   @Override
   public DbType<?> convert(Method method, String input) {
      try {
         return ReflectionUtil.findEnumImplementationsOfInterface(DbType.class, input, getDbConfig().projectPackages());
      } catch (Exception e) {
         if (e.getMessage().contains("more than one enum")) {
            return ReflectionUtil.findEnumImplementationsOfInterface(DbType.class, input,
               getDbConfig().projectPackages()[0]);
         } else {
            throw e;
         }
      }
   }
}