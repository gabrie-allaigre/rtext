package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;
import org.apache.commons.lang3.math.NumberUtils;

public class DoubleTypeAdapter implements IRtextTypeAdapter<Double> {

    public static final DoubleTypeAdapter TYPE_ADAPTER = new DoubleTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(Double.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private DoubleTypeAdapter() {
        super();
    }

    @Override
    public Double toDst(String s) {
        if (s == null) {
            return null;
        }
        if (!NumberUtils.isNumber(s)) {
            throw new NumberFormatException();
        }
        return NumberUtils.createDouble(s);
    }
}
