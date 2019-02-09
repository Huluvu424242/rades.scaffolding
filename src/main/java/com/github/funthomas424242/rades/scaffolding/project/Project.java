package com.github.funthomas424242.rades.scaffolding.project;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import java.lang.annotation.*;

@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.SOURCE)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@Documented
public @interface Project {

    GroupId groupId;
    ArtifactId artifactId;
    Version version;
}
