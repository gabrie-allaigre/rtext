package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

import java.util.HashSet;
import java.util.Set;

public class SetTypeAdapter<E> implements IRtextTypeAdapter<Set<E>> {

    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new SetTypeAdapterFactory();

    private final static String DEFAULT_SEPARATOR = ",";

    private final Rtext rtext;
    private final TypeToken<E> typeToken;
    private final String separator;

    public SetTypeAdapter(Rtext rtext, TypeToken<E> typeToken, String separator) {
        super();

        this.rtext = rtext;
        this.typeToken = typeToken;
        this.separator = separator;
    }

    @Override
    public Set<E> toDst(String s) {
        if (s == null) {
            return new HashSet<>();
        }

        IRtextTypeAdapter<E> componentTypeAdapter = rtext.getTypeAdapter(typeToken);

        String[] ss = s.split(separator);
        Set<E> cs = new HashSet<>(ss.length);
        for (int i = 0; i < ss.length; i++) {
            cs.add(componentTypeAdapter.toDst(ss[i]));
        }
        return cs;
    }

    public static class SetTypeAdapterFactory implements IRtextTypeAdapterFactory {

        private final String separator;

        public SetTypeAdapterFactory() {
            this(DEFAULT_SEPARATOR);
        }

        public SetTypeAdapterFactory(String separator) {
            super();

            this.separator = separator;
        }

        @Override
        public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
            if (!TypeToken.of(Set.class).isSubtypeOf(typeToken.getRawType())) {
                return null;
            }
            TypeToken<?> subTypeToken = typeToken.resolveType(Set.class.getTypeParameters()[0]);
            return (IRtextTypeAdapter<T>) new SetTypeAdapter(rtext, subTypeToken, separator);
        }
    }
}
