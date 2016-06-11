package be.witspirit.testfactory.support;

import be.witspirit.testfactory.support.valueproviders.ValueProviders;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Tries to automatically populate a class based on reflection
 */
public class ReflectiveTestFactory<T> implements TestFactory<T> {
    private Map<Class<?>, Supplier<?>> typeSuppliers = new HashMap<>();

    private Class<T> targetType;

    public ReflectiveTestFactory(Class<T> targetType) {
        this.targetType = targetType;

        setupDefaultSuppliers();
    }

    private void setupDefaultSuppliers() {
        typeSuppliers.put(String.class, ValueProviders.randomString(10,20));
        typeSuppliers.put(Integer.class, ValueProviders.intWithin(0,100));
    }

    public <V> ReflectiveTestFactory<T> provide(Class<V> type, Supplier<V> supplier) {
        typeSuppliers.put(type, supplier);
        return this;
    }

    @Override
    public T get() {
        try {
            T obj = targetType.newInstance(); // Implies default constructor availability
            for (Method method : targetType.getMethods()) {
                if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                    // Let us start with the simple case
                    method.invoke(obj, getValue(method));
                }
            }

            return obj;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Reflection problem", e);
        }
    }

    private Object getValue(Method method) {
        Class<?> parameterType = method.getParameterTypes()[0];

        // Do we already know how to provide such a type ?
        Supplier<?> valueSupplier = typeSuppliers.get(parameterType);
        if (valueSupplier != null) {
            // We do ! Go ahead an supply a value
            return valueSupplier.get();
        }

        // We don't... Perhaps we can automatically reflect our way to provide it
        Supplier<?> automaticTypeProvider = new ReflectiveTestFactory<>(parameterType);
        typeSuppliers.put(parameterType, automaticTypeProvider);
        return getValue(method);
    }
}
