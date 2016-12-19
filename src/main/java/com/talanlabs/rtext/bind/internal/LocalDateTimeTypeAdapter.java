package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

import java.time.LocalDateTime;

public class LocalDateTimeTypeAdapter implements IRtextTypeAdapter<LocalDateTime> {

    public static final LocalDateTimeTypeAdapter TYPE_ADAPTER = new LocalDateTimeTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(LocalDateTime.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private LocalDateTimeTypeAdapter() {
        super();
    }

    @Override
    public LocalDateTime toDst(String s) {
        if (s == null) {
            return null;
        }
        return LocalDateTime.parse(s);
    }
}
