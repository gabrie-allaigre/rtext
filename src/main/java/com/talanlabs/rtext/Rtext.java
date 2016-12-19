package com.talanlabs.rtext;

import com.google.common.primitives.Primitives;
import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.bind.internal.BigDecimalTypeAdapter;
import com.talanlabs.rtext.bind.internal.BigIntegerTypeAdapter;
import com.talanlabs.rtext.bind.internal.BooleanTypeAdapter;
import com.talanlabs.rtext.bind.internal.DateTypeAdapter;
import com.talanlabs.rtext.bind.internal.DoubleTypeAdapter;
import com.talanlabs.rtext.bind.internal.EnumTypeAdapter;
import com.talanlabs.rtext.bind.internal.FloatTypeAdapter;
import com.talanlabs.rtext.bind.internal.IntegerTypeAdapter;
import com.talanlabs.rtext.bind.internal.LocalDateTimeTypeAdapter;
import com.talanlabs.rtext.bind.internal.LocalDateTypeAdapter;
import com.talanlabs.rtext.bind.internal.LocalTimeTypeAdapter;
import com.talanlabs.rtext.bind.internal.LongTypeAdapter;
import com.talanlabs.rtext.bind.internal.RtextTypeAdapters;
import com.talanlabs.rtext.bind.internal.StringTypeAdapter;
import com.talanlabs.rtext.bind.internal.ToObjectTypeAdapter;
import com.talanlabs.rtext.configuration.IRtextConfiguration;
import com.talanlabs.typeadapters.TypeAdaptersDelegate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Rtext {

    private final TypeAdaptersDelegate<Rtext> typeAdaptersDelegate;

    public Rtext(IRtextConfiguration rtextConfiguration) {
        super();

        List<IRtextTypeAdapterFactory> factories = new ArrayList<>();

        factories.add(ToObjectTypeAdapter.FACTORY);

        factories.addAll(rtextConfiguration.getTypeAdapterFactories());

        factories.add(StringTypeAdapter.FACTORY);
        factories.add(RtextTypeAdapters.newFactory(int.class, Integer.class, IntegerTypeAdapter.TYPE_ADAPTER));
        factories.add(IntegerTypeAdapter.FACTORY);
        factories.add(RtextTypeAdapters.newFactory(float.class, Float.class, FloatTypeAdapter.TYPE_ADAPTER));
        factories.add(FloatTypeAdapter.FACTORY);
        factories.add(RtextTypeAdapters.newFactory(double.class, Double.class, DoubleTypeAdapter.TYPE_ADAPTER));
        factories.add(DoubleTypeAdapter.FACTORY);
        factories.add(RtextTypeAdapters.newFactory(long.class, Long.class, LongTypeAdapter.TYPE_ADAPTER));
        factories.add(LongTypeAdapter.FACTORY);
        factories.add(RtextTypeAdapters.newFactory(boolean.class, Boolean.class, BooleanTypeAdapter.TYPE_ADAPTER));
        factories.add(BooleanTypeAdapter.FACTORY);
        factories.add(BigDecimalTypeAdapter.FACTORY);
        factories.add(BigIntegerTypeAdapter.FACTORY);
        factories.add(DateTypeAdapter.FACTORY);
        factories.add(LocalDateTypeAdapter.FACTORY);
        factories.add(LocalTimeTypeAdapter.FACTORY);
        factories.add(LocalDateTimeTypeAdapter.FACTORY);
        factories.add(EnumTypeAdapter.FACTORY);
        typeAdaptersDelegate = new TypeAdaptersDelegate<>(this, factories);
    }

    public <T> T fromText(String text, Class<T> classOfT) {
        Object res = fromText(text, (Type) classOfT);
        if (classOfT.isPrimitive() && res == null) {
            throw new IllegalArgumentException("Not return primivite with null value");
        }
        return Primitives.wrap(classOfT).cast(res);
    }

    @SuppressWarnings("unchecked")
    public <T> T fromText(String text, Type typeOfT) {
        if (text == null) {
            return null;
        }
        TypeToken<T> typeToken = (TypeToken<T>) TypeToken.of(typeOfT);

        IRtextTypeAdapter<T> typeAdapter = getTypeAdapter(typeToken);
        return typeAdapter.toDst(text);
    }

    /**
     * Get type adapter for type
     *
     * @param type type
     * @return a type adapter
     */
    public <T> IRtextTypeAdapter<T> getTypeAdapter(TypeToken<T> type) {
        return (IRtextTypeAdapter<T>) typeAdaptersDelegate.getTypeAdapter(TypeToken.of(String.class), type);
    }
}
