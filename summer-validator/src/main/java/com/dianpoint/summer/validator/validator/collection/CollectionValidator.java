package com.dianpoint.summer.validator.validator.collection;

import com.dianpoint.summer.validator.ValidationRule;
import com.dianpoint.summer.validator.validator.Validator;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/24 14:59
 */

public interface CollectionValidator<E, C extends Collection<E>> extends Validator<C> {


    /**
     * 集合校验接口 用于对集合及其元素进行校验
     *
     * @param rule 元素校验器规则
     * @return 集合类型必须是Collection<E> 的子类
     */
    CollectionValidator<E, C> elementRule(ValidationRule<E> rule);


    /**
     * 为集合中每个元素添加基于predicate的校验规则
     *
     * @param condition    校验条件
     * @param errorMessage 校验失败时的错误提示信息
     * @return 集合校验器实例 支持链式调用
     */
    CollectionValidator<E, C> elementRule(Predicate<E> condition, String errorMessage);

    /**
     * 为集合中每个元素添加一个校验器
     *
     * @param validator 元素校验器
     * @return 当前校验器实例 支持链式调用
     */
    CollectionValidator<E, C> elementValidator(Validator<E> validator);

}
