package com.talanlabs.rtext.bind.internal;

import com.google.common.base.Splitter;
import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;

public class ArrayTypeAdapter<E> implements IRtextTypeAdapter<E[]> {

    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new ArrayTypeAdapterFactory();

    private final static String DEFAULT_SEPARATOR = ",";

    private final Rtext rtext;
    private final TypeToken<E> typeToken;
    private final String separator;

    public ArrayTypeAdapter(Rtext rtext, TypeToken<E> typeToken, String separator) {
        super();

        this.rtext = rtext;
        this.typeToken = typeToken;
        this.separator = separator;
    }

    @Override
    public E[] toDst(String s) {
        if (StringUtils.isEmpty(s)) {
            return (E[]) Array.newInstance(typeToken.getRawType(), 0);
        }

        IRtextTypeAdapter<E> componentTypeAdapter = rtext.getTypeAdapter(typeToken);
        return Splitter.on(separator).omitEmptyStrings().splitToList(s).stream().map(componentTypeAdapter::toDst).toArray(i -> (E[]) Array.newInstance(typeToken.getRawType(), i));
    }

    public static class ArrayTypeAdapterFactory implements IRtextTypeAdapterFactory {

        private final String separator;

        public ArrayTypeAdapterFactory() {
            this(DEFAULT_SEPARATOR);
        }

        public ArrayTypeAdapterFactory(String separator) {
            super();

            this.separator = separator;
        }

        @Override
        public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
            if (!typeToken.isArray()) {
                return null;
            }
            return (IRtextTypeAdapter<T>) new ArrayTypeAdapter(rtext, typeToken.getComponentType(), separator);
        }
    }
}
