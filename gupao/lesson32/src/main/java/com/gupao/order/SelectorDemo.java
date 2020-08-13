package com.gupao.order;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class SelectorDemo implements ImportSelector {
    /**
     * Select and return the names of which class(es) should be imported based on
     * the {@link AnnotationMetadata} of the importing @{@link Configuration} class.
     *
     * @param importingClassMetadata
     * @return the class names, or an empty array if none
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {



        return new String[0];
    }
}
