package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

public class EnumTypeAdapter<T extends Enum<T>> implements IRtextTypeAdapter<T> {

    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        @SuppressWarnings("unchecked")
        @Override
        public <E> IRtextTypeAdapter<E> create(Rtext rtext, TypeToken<E> typeToken) {
            Class<? super E> rawType = typeToken.getRawType();
            if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                return null;
            }
            if (!rawType.isEnum()) {
                rawType = rawType.getSuperclass();
            }
            return new EnumTypeAdapter(rawType);
        }
    };

    private final Class<T> classOfT;

    private EnumTypeAdapter(Class<T> classOfT) {
        super();

        this.classOfT = classOfT;
    }

    @Override
    public T toDst(String s) {
        return s == null ? null : Enum.valueOf(classOfT, s);
    }
}
