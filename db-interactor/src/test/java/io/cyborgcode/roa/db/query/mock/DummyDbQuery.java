package io.cyborgcode.roa.db.query.mock;

import io.cyborgcode.roa.db.query.DbQuery;

public class DummyDbQuery implements DbQuery<TestEnum> {
   @Override
   public String query() {
      return "SELECT * FROM dummy";
   }

   @Override
   public TestEnum enumImpl() {
      return TestEnum.VALUE;
   }
}