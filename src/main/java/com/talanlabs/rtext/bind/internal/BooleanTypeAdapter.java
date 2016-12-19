package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;
import org.apache.commons.lang3.BooleanUtils;

public class BooleanTypeAdapter implements IRtextTypeAdapter<Boolean> {

    public static final BooleanTypeAdapter TYPE_ADAPTER = new BooleanTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(Boolean.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private BooleanTypeAdapter() {
        super();
    }

    @Override
    public Boolean toDst(String s) {
        if (s == null) {
            return null;
        }
        return BooleanUtils.toBooleanObject(s);
    }
}
