/*
 * Copyright 2015-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.cxx;

import com.google.common.collect.ImmutableList;

import java.nio.file.Path;

public enum CxxCompilationDatabaseFormat {

  CLANG {
    @Override
    public CxxCompilationDatabaseEntry createEntry(
        Path root,
        Path basePath,
        String file,
        ImmutableList<String> args) {
      return ClangCxxCompilationDatabaseEntry.of(root.toString(), file, args);
    }
  },
  NUCLIDE {
    @Override
    public CxxCompilationDatabaseEntry createEntry(
        Path root,
        Path basePath,
        String file,
        ImmutableList<String> args) {
      return NuclideCompatibleCxxCompilationDatabaseEntry.of(
          root.resolve(basePath).toString(),
          file,
          args);
    }
  };

  public abstract CxxCompilationDatabaseEntry createEntry(
      Path root,
      Path basePath,
      String file,
      ImmutableList<String> args);
}
