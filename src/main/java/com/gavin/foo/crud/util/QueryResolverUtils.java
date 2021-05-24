package com.gavin.foo.crud.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gavin.foo.crud.base.annatation.Query;

import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;



/**
 * Utils - 查询解析
 *
 * @author lilh
 * @date 2017/7/5 14:33
 */
public final class QueryResolverUtils {

    private static Map<Class<?>, Field[]> clazzFieldMap = new ConcurrentHashMap<>();

    public static <T> Wrapper<T> parse(Object param) {
        Wrapper<T> wrapper = new EntityWrapper<>();
        return parse(param, wrapper);
    }


    public static <T> Wrapper<T> parse(Object param, Class<T> clazz) {
        return parse(param);
    }


    public static <T> Wrapper<T> parse(Object obj, Wrapper<T> wrapper) {
        if (Objects.isNull(obj)) {
            return wrapper;
        }
        Field[] fields = getDeclaredFields(obj.getClass());
        Arrays.stream(fields).forEach(field -> {
            Object value = getFieldValue(field, obj);
            if (checkValue(value)) {
                Query query = field.getAnnotation(Query.class);
                if (Objects.nonNull(query)) {
                    String fieldName;
                    String queryField = query.field(); //指定数据库查询字段
                    if ("".equals(queryField)) {
                        //驼峰转下划线
                        fieldName = field.getName()
                                .replaceAll("(.)(\\p{Upper})", "$1_$2")
                                .toLowerCase();
                    } else {
                        fieldName = queryField;
                    }

                    wrap(query, wrapper, fieldName, value);
                }
            }
        });

        return wrapper;
    }

    private static boolean checkValue(Object value) {
        if (Objects.isNull(value)) {
            return false;
        }

        if (value instanceof String
                && "".equals(value)) {
            return false;
        }

        return true;
    }

    private static <T> void wrap(Query query, Wrapper<T> wrapper, String field, Object value) {

        Query.Operator operator = query.operator();
        if (operator == Query.Operator.eq) {
            wrapper.eq(field, value);
        } else if (operator == Query.Operator.gt) {
            wrapper.gt(field, value);
        } else if (operator == Query.Operator.lt) {
            wrapper.lt(field, value);
        } else if (operator == Query.Operator.ge) {
            wrapper.ge(field, value);
        } else if (operator == Query.Operator.le) {
            wrapper.le(field, value);
        } else if (operator == Query.Operator.ne) {
            wrapper.ne(field, value);
        } else if (operator == Query.Operator.like) {
            if (value instanceof String) {
                if (Objects.nonNull(value)) {
                    value = ((String) value).replaceAll("%", "/%");
                }
                wrapper.like(field, (String) value);
            }
        } else if (operator == Query.Operator.isNull) {
            if (Objects.nonNull(value)) {
                if (Objects.equals(query.condition(), value.toString())) {
                    wrapper.isNull(field);
                } else {
                    if (query.mutex()) {
                        wrapper.isNotNull(field);
                    }
                }
            }
        } else if (operator == Query.Operator.isNotNull) {
            if (Objects.nonNull(value)) {
                if (Objects.equals(query.condition(), value.toString())) {
                    wrapper.isNotNull(field);
                } else {
                    if (query.mutex()) {
                        wrapper.isNull(field);
                    }
                }
            }
        } else if (operator == Query.Operator.in) {
            if (value instanceof Collection && CollectionUtils.isNotEmpty((Collection) value)) {
                wrapper.in(field, (Collection<?>) value);
            }
        } else if (operator == Query.Operator.notIn) {
            if (value instanceof Collection && CollectionUtils.isNotEmpty((Collection) value)) {
                wrapper.notIn(field, (Collection<?>) value);
            }
        }
    }

    private static Object getFieldValue(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    private static Field[] getDeclaredFields(Class<?> cls) {
        Field[] fields = clazzFieldMap.get(cls);
        if (Objects.isNull(fields)) {
            fields = cls.getDeclaredFields();
            clazzFieldMap.put(cls, fields);
        }
        return fields;
    }
}
