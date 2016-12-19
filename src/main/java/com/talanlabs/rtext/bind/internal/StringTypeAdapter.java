package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

public class StringTypeAdapter implements IRtextTypeAdapter<String> {

    private static final StringTypeAdapter TYPE_ADAPTER = new StringTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(String.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private StringTypeAdapter() {
        super();
    }

    @Override
    public String toDst(String s) {
        return s;
    }
}
