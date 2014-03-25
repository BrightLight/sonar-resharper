/*
 * SonarQube ReSharper Plugin
 * Copyright (C) 2014 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.resharper;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;

import java.io.File;

public class FileProvider {

  private final Project project;
  private final SensorContext context;

  public FileProvider(Project project, SensorContext context) {
    this.project = project;
    this.context = context;
  }

  public File fileInSolution(File solutionFile, String filePath) {
    return new File(solutionFile.getParentFile(), filePath.replace('\\', '/'));
  }

  public org.sonar.api.resources.File fromIOFile(File file) {
    // Workaround SonarQube < 4.2, the context should not be required
    return context.getResource(org.sonar.api.resources.File.fromIOFile(file, project));
  }

}
