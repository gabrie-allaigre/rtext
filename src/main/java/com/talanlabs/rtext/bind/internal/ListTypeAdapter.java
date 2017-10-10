package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

import java.util.ArrayList;
import java.util.List;

public class ListTypeAdapter<E> implements IRtextTypeAdapter<List<E>> {

    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new ListTypeAdapterFactory();

    private final static String DEFAULT_SEPARATOR = ",";

    private final Rtext rtext;
    private final TypeToken<E> typeToken;
    private final String separator;

    public ListTypeAdapter(Rtext rtext, TypeToken<E> typeToken, String separator) {
        super();

        this.rtext = rtext;
        this.typeToken = typeToken;
        this.separator = separator;
    }

    @Override
    public List<E> toDst(String s) {
        if (s == null) {
            return new ArrayList<>();
        }

        IRtextTypeAdapter<E> componentTypeAdapter = rtext.getTypeAdapter(typeToken);

        String[] ss = s.split(separator);
        List<E> cs = new ArrayList<>(ss.length);
        for (int i = 0; i < ss.length; i++) {
            cs.add(componentTypeAdapter.toDst(ss[i]));
        }
        return cs;
    }

    public static class ListTypeAdapterFactory implements IRtextTypeAdapterFactory {

        private final String separator;

        public ListTypeAdapterFactory() {
            this(DEFAULT_SEPARATOR);
        }

        public ListTypeAdapterFactory(String separator) {
            super();

            this.separator = separator;
        }

        @Override
        public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
            if (!TypeToken.of(List.class).isSubtypeOf(typeToken.getRawType())) {
                return null;
            }
            TypeToken<?> subTypeToken = typeToken.resolveType(List.class.getTypeParameters()[0]);
            return (IRtextTypeAdapter<T>) new ListTypeAdapter(rtext, subTypeToken, separator);
        }
    }
}
