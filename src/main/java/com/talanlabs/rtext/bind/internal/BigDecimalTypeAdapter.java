package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

public class BigDecimalTypeAdapter implements IRtextTypeAdapter<BigDecimal> {

    public static final BigDecimalTypeAdapter TYPE_ADAPTER = new BigDecimalTypeAdapter();
    @SuppressWarnings("unchecked")
    public static final IRtextTypeAdapterFactory FACTORY = new IRtextTypeAdapterFactory() {
        public <T2> IRtextTypeAdapter<T2> create(Rtext rtext, TypeToken<T2> typeToken) {
            if (!TypeToken.of(BigDecimal.class).isSubtypeOf(typeToken)) {
                return null;
            }
            return (IRtextTypeAdapter<T2>) TYPE_ADAPTER;
        }
    };

    private BigDecimalTypeAdapter() {
        super();
    }

    @Override
    public BigDecimal toDst(String s) {
        if (s == null) {
            return null;
        }
        if (!NumberUtils.isNumber(s)) {
            throw new NumberFormatException();
        }
        return NumberUtils.createBigDecimal(s);
    }
}
