package com.github.funthomas424242.rades.scaffolding;

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

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Set;
import java.util.function.Consumer;

//@Singleton
public class AnnotationHelper {

    // Quelle: https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
    // Innere private Klasse, die erst beim Zugriff durch die umgebende Klasse initialisiert wird
    private static final class InstanceHolder {
        // Die Initialisierung von Klassenvariablen geschieht nur einmal
        // und wird vom ClassLoader implizit synchronisiert
        static final AnnotationHelper INSTANCE = new AnnotationHelper();
    }

    // Verhindere die Erzeugung des Objektes über andere Methoden
    private AnnotationHelper() {
    }

    // Eine nicht synchronisierte Zugriffsmethode auf Klassenebene.
    public static AnnotationHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public boolean computePackageAnnotation(RoundEnvironment roundEnv, TypeElement annotation,
                                            Consumer<Element> onSuccess, ErrorCallback onError) {
        boolean noErrorOccured = true;

        final Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
        for (final Element annotatedElement : annotatedElements) {
            if (annotatedElement.getKind() == ElementKind.PACKAGE) {
                onSuccess.accept(annotatedElement);
            } else {
                onError.addError(annotation, "Nur für package nutzbar.", annotatedElement);
                noErrorOccured = false;
            }
        }
        return noErrorOccured;
    }

}
