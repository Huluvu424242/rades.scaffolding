package com.github.funthomas424242.rades.scaffolding.readme;

import com.github.funthomas424242.rades.scaffolding.services.Github;
import com.github.funthomas424242.rades.scaffolding.services.TravisCI;

public @interface Ribons {

    boolean github() default false;

    boolean travis() default false;

    boolean bintray() default false;

    boolean waffle() default false;

    boolean codacy() default false;

    boolean codecov() default false;

}
