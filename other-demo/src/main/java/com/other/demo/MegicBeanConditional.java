package com.other.demo;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MegicBeanConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();


        return false;
    }
}
