package com.sample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightCompiledStatement;
import com.squareup.sqldelight.SqlDelightStatement;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.Collections;

public interface TestModel {
  String TABLE_NAME = "foo";

  String _ID = "_id";

  String BAR = "bar";

  String BAZ = "baz";

  String CREATE_TABLE = ""
      + "CREATE TABLE foo (\n"
      + "  _id INTEGER NOT NULL PRIMARY KEY,\n"
      + "  bar INTEGER NOT NULL,\n"
      + "  baz INTEGER NOT NULL\n"
      + ")";

  long _id();

  boolean bar();

  long baz();

  interface Creator<T extends TestModel> {
    T create(long _id, boolean bar, long baz);
  }

  final class Mapper<T extends TestModel> implements RowMapper<T> {
    private final Factory<T> testModelFactory;

    public Mapper(Factory<T> testModelFactory) {
      this.testModelFactory = testModelFactory;
    }

    @Override
    public T map(@NonNull Cursor cursor) {
      return testModelFactory.creator.create(
          cursor.getLong(0),
          cursor.getInt(1) == 1,
          cursor.getLong(2)
      );
    }
  }

  final class Factory<T extends TestModel> {
    public final Creator<T> creator;

    public Factory(Creator<T> creator) {
      this.creator = creator;
    }

    /**
     * @deprecated Use {@link Some_update}
     */
    @Deprecated
    public SqlDelightStatement some_update(boolean bar, long baz) {
      StringBuilder query = new StringBuilder();
      query.append("UPDATE foo\n"
              + "SET bar = ");
      query.append(bar ? 1 : 0);
      query.append("\n"
              + "WHERE baz = ");
      query.append(baz);
      return new SqlDelightStatement(query.toString(), new String[0], Collections.<String>singleton("foo"));
    }
  }

  final class Some_update extends SqlDelightCompiledStatement.Update {
    public Some_update(SQLiteDatabase database) {
      super("foo", database.compileStatement(""
              + "UPDATE foo\n"
              + "SET bar = ?2\n"
              + "WHERE baz = ?1"));
    }

    public void bind(long baz, boolean bar) {
      program.bindLong(1, baz);
      program.bindLong(2, bar ? 1 : 0);
    }
  }
}
