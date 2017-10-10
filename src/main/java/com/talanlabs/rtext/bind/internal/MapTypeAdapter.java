package com.talanlabs.rtext.bind.internal;

import com.google.common.base.Splitter;
import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapTypeAdapter<E, F> implements IRtextTypeAdapter<Map<E, F>> {

    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new MapTypeAdapterFactory();

    private final static String DEFAULT_SEPARATOR_KEY_VALUE = "=";
    private final static String DEFAULT_SEPARATOR = ";";

    private final Rtext rtext;
    private final TypeToken<E> keyTypeToken;
    private final TypeToken<F> valueTypeToken;
    private final String separatorKeyValue;
    private final String separator;

    public MapTypeAdapter(Rtext rtext, TypeToken<E> keyTypeToken, TypeToken<F> valueTypeToken, String separatorKeyValue, String separator) {
        super();

        this.rtext = rtext;
        this.keyTypeToken = keyTypeToken;
        this.valueTypeToken = valueTypeToken;
        this.separatorKeyValue = separatorKeyValue;
        this.separator = separator;
    }

    @Override
    public Map<E, F> toDst(String s) {
        if (s == null) {
            return new HashMap<>();
        }

        IRtextTypeAdapter<E> keyTypeAdapter = rtext.getTypeAdapter(keyTypeToken);
        IRtextTypeAdapter<F> valueTypeAdapter = rtext.getTypeAdapter(valueTypeToken);

        return Splitter.on(separator).omitEmptyStrings().splitToList(s).stream().map(e -> e.split(separatorKeyValue)).collect(Collectors.toMap(e -> keyTypeAdapter.toDst(e[0]), e -> valueTypeAdapter.toDst(e[1])));
    }

    public static class MapTypeAdapterFactory implements IRtextTypeAdapterFactory {

        private final String separatorKeyValue;
        private final String separator;

        public MapTypeAdapterFactory() {
            this(DEFAULT_SEPARATOR_KEY_VALUE, DEFAULT_SEPARATOR);
        }

        public MapTypeAdapterFactory(String separatorKeyValue, String separator) {
            super();

            this.separatorKeyValue = separatorKeyValue;
            this.separator = separator;
        }

        @Override
        public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
            if (!TypeToken.of(Map.class).isSubtypeOf(typeToken.getRawType())) {
                return null;
            }
            TypeToken<?> keyTypeToken = typeToken.resolveType(Map.class.getTypeParameters()[0]);
            TypeToken<?> valueTypeToken = typeToken.resolveType(Map.class.getTypeParameters()[1]);
            return (IRtextTypeAdapter<T>) new MapTypeAdapter(rtext, keyTypeToken, valueTypeToken, separatorKeyValue, separator);
        }
    }
}
