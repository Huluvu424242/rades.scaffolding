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
import com.github.funthomas424242.rades.scaffolding.DaggerDIHelperComponent;
import com.github.funthomas424242.rades.scaffolding.ErrorCallback;
import com.github.funthomas424242.rades.scaffolding.project.Project;
import io.vavr.control.Option;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
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
        diHelper = DaggerDIHelperComponent.create().getDIHelper();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean noErrorOccured = true;

        for (TypeElement annotation : annotations) {
            System.out.println("###Annotation: " + annotation.getQualifiedName());

            noErrorOccured = diHelper.createAnnotationHelper().computePackageAnnotation(roundEnv, annotation,
                (annotatedElement) -> {
                    System.out.println("Break3" + annotatedElement);
                    Option.of(annotatedElement.getAnnotation(Project.class)).onDefined((projectAnno) -> {
                        System.out.println("Projekt Annotation:" + projectAnno);
                        System.out.println("###projectAnno: " + projectAnno.getClass().getCanonicalName().toString());
                        System.out.println("###groupId: " + ((Project) projectAnno).groupId());
                        System.out.println("####artifactId: " + ((Project) projectAnno).artifactId());
                        System.out.println("###version: " + ((Project) projectAnno).version());
                    }).orElse(() -> {
                        printErrorMessage(annotatedElement, "missing @Project");
                        return null;
                    });
                }
                , getErrorCallback()
            ) && noErrorOccured;
        }
        return noErrorOccured;
    }

    protected ErrorCallback getErrorCallback() {
        return new ErrorCallback() {
            @Override
            public void addError(Element element, String message, Object... args) {
                printErrorMessage(element, message, args);
            }
        };
    }

    public void printErrorMessage(Element element, String message, Object... args) {
        messager.printMessage(
            Diagnostic.Kind.ERROR,
            String.format(message, args),
            element);
    }


}
