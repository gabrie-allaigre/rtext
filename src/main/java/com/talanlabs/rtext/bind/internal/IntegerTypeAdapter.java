package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;
import org.apache.commons.lang3.math.NumberUtils;

public class IntegerTypeAdapter implements IRtextTypeAdapter<Integer> {

    public static final IntegerTypeAdapter TYPE_ADAPTER = new IntegerTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(Integer.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private IntegerTypeAdapter() {
        super();
    }

    @Override
    public Integer toDst(String s) {
        if (s == null) {
            return null;
        }
        if (!NumberUtils.isNumber(s)) {
            throw new NumberFormatException();
        }
        return NumberUtils.createInteger(s);
    }
}
