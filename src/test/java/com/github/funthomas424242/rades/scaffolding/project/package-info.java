/*-
 * #%L
 * RADES Scaffolding Projekt
 * %%
 * Copyright (C) 2018 - 2019 PIUG
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
@Project(projectRootDir = "../../../../../../../..",
    groupId = "com.github.funthomas424242.rades",
    artifactId = "scaffolding",
    version = "1.0.0-SNAPSHOT")
@Maven
@Readme(ribons = @Ribons(travis = true, github = true)
    , content = {@Paragraph("text"), @Paragraph("Blase.")}
)
@Github(user = "FunThomas424242", repository = "rades.scaffolding")
@Bintray(user = "funthomas424242", repository = "funthomas424242-libs", packageName = "rades.scaffolding")
package com.github.funthomas424242.rades.scaffolding.project;


import com.github.funthomas424242.rades.scaffolding.build.Maven;
import com.github.funthomas424242.rades.scaffolding.readme.Paragraph;
import com.github.funthomas424242.rades.scaffolding.readme.Readme;
import com.github.funthomas424242.rades.scaffolding.readme.Ribons;
import com.github.funthomas424242.rades.scaffolding.services.Bintray;
import com.github.funthomas424242.rades.scaffolding.services.Github;
