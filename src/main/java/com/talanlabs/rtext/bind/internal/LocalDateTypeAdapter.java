package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

import java.time.LocalDate;

public class LocalDateTypeAdapter implements IRtextTypeAdapter<LocalDate> {

    public static final LocalDateTypeAdapter TYPE_ADAPTER = new LocalDateTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(LocalDate.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private LocalDateTypeAdapter() {
        super();
    }

    @Override
    public LocalDate toDst(String s) {
        if (s == null) {
            return null;
        }
        return LocalDate.parse(s);
    }
}
