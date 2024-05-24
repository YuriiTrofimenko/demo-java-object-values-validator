package org.tyaa.demo.java.validators;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Validators {
    public static void validateObjectContent(Object object, Map<String, Object> samples, LinkedHashMap<String, String> errors) {
        Class<?> clazz = object.getClass();
        samples.forEach((sampleFieldName, sampleFieldValue) -> {
            try {
                Field field = clazz.getDeclaredField(sampleFieldName);
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    if (value == null) {
                        if (sampleFieldValue != null) {
                            errors.put(sampleFieldName, String.format("wrong value: null instead of %s", sampleFieldValue));
                        }
                    } else {
                        final String valueTypeName = value.getClass().getSimpleName();
                        final String sampleFieldValueTypeName = sampleFieldValue.getClass().getSimpleName();
                        if (!valueTypeName.equals(sampleFieldValueTypeName)) {
                            errors.put(sampleFieldName, String.format("wrong value type: %s instead of %s", valueTypeName, sampleFieldValueTypeName));
                        } else {
                            switch (valueTypeName) {
                                case "String" -> {
                                    if (!value.equals(sampleFieldValue)) {
                                        errors.put(sampleFieldName, String.format("wrong value: %s instead of %s", value, sampleFieldValue));
                                    }
                                }
                                case "Boolean" -> {
                                    if (!((Boolean) value).equals((Boolean) sampleFieldValue)) {
                                        errors.put(sampleFieldName, String.format("wrong value: %s instead of %s", value, sampleFieldValue));
                                    }
                                }
                                case "Integer" -> {
                                    if (!((Integer) value).equals((Integer) sampleFieldValue)) {
                                        errors.put(sampleFieldName, String.format("wrong value: %s instead of %s", value, sampleFieldValue));
                                    }
                                }
                                case "Double" -> {
                                    if (!((Double) value).equals((Double) sampleFieldValue)) {
                                        errors.put(sampleFieldName, String.format("wrong value: %s instead of %s", value, sampleFieldValue));
                                    }
                                }
                                default -> errors.put(sampleFieldName, "unsupported type");
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchFieldException e) {
                errors.put(sampleFieldName, "not exists");
            }
        });
    }
}
