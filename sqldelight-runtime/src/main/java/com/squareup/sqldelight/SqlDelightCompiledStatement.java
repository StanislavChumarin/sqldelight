/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.sqldelight;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;

public abstract class SqlDelightCompiledStatement implements SupportSQLiteQuery {
  public final String table;
  public final SupportSQLiteStatement program;

  protected SqlDelightCompiledStatement(String table, SupportSQLiteStatement program) {
    this.table = table;
    this.program = program;
  }

  public static abstract class Insert extends SqlDelightCompiledStatement {
    protected Insert(String table, SupportSQLiteStatement program) {
      super(table, program);
    }
  }

  public static abstract class Update extends SqlDelightCompiledStatement {
    protected Update(String table, SupportSQLiteStatement program) {
      super(table, program);
    }
  }

  public static abstract class Delete extends SqlDelightCompiledStatement {
    protected Delete(String table, SupportSQLiteStatement program) {
      super(table, program);
    }
  }
}
