package edu.upc.dsa.util;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class ObjectHelper {

    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;

    }

    public static Object getter(Object object, String property) {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(property, object.getClass());
            return pd.getReadMethod().invoke(object);
        }
        catch (IntrospectionException | InvocationTargetException | IllegalAccessException | IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void setter(Object object, String property, Object value) {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(property, object.getClass());
            pd.getWriteMethod().invoke(object, value);
        }
        catch (IntrospectionException | InvocationTargetException | IllegalAccessException | IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }
}
