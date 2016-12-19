package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

import java.time.LocalTime;

public class LocalTimeTypeAdapter implements IRtextTypeAdapter<LocalTime> {

    public static final LocalTimeTypeAdapter TYPE_ADAPTER = new LocalTimeTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(LocalTime.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private LocalTimeTypeAdapter() {
        super();
    }

    @Override
    public LocalTime toDst(String s) {
        if (s == null) {
            return null;
        }
        return LocalTime.parse(s);
    }
}
