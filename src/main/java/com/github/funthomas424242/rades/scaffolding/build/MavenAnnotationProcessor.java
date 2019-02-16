package com.github.funthomas424242.rades.scaffolding.build;

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

import com.github.funthomas424242.rades.scaffolding.DIHelper;
import com.github.funthomas424242.rades.scaffolding.DIHelperComponent;
import com.github.funthomas424242.rades.scaffolding.DaggerDIHelperComponent;
import com.github.funthomas424242.rades.scaffolding.project.Project;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MavenAnnotationProcessor extends AbstractProcessor {


    protected DIHelper diHelper;

    private Types typeUtils;
    private Elements elementUtils;

    private Filer filer;

    private Messager messager;


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(Maven.class.getCanonicalName());
        return annotataions;
    }


    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        final DIHelperComponent diHelperComponent = DaggerDIHelperComponent.create();
        diHelper = diHelperComponent.getDIHelper();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            System.out.println("###Annotation: " + annotation.getQualifiedName());

            diHelper.createAnnotationHelper().computePackageAnnotation(roundEnv, annotation, (annotatedElement) -> {
                System.out.println("Break3");
                final Annotation projectAnnotation = annotatedElement.getAnnotation(Project.class);
                System.out.println("###projectAnno: " + projectAnnotation.getClass().getCanonicalName().toString());
                System.out.println("###groupId: " + ((Project) projectAnnotation).groupId());
                System.out.println("####artifactId: " + ((Project) projectAnnotation).artifactId());
                System.out.println("###version: " + ((Project) projectAnnotation).version());
            });

        }
        return false;
    }


}
