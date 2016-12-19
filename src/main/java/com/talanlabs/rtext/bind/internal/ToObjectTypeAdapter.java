package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

public class ToObjectTypeAdapter implements IRtextTypeAdapter<Object> {

    private static final IRtextTypeAdapter<Object> TP_OBJECT_TYPE_ADAPTER = new ToObjectTypeAdapter();

    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {

        @Override
        @SuppressWarnings("unchecked")
        public <U2> IRtextTypeAdapter<U2> create(Rtext rtext, TypeToken<U2> dstTypeToken) {
            if (dstTypeToken.getRawType() != Object.class) {
                return null;
            }
            return (IRtextTypeAdapter<U2>) TP_OBJECT_TYPE_ADAPTER;
        }
    };

    @Override
    public Object toDst(String s) {
        return s;
    }
}
