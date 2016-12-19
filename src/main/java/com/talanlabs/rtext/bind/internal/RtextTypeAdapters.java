package com.talanlabs.rtext.bind.internal;

import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;

public class RtextTypeAdapters {

    private RtextTypeAdapters() {
        super();
    }

    public static <TT> IRtextTypeAdapterFactory newFactory(final TypeToken<TT> type, final IRtextTypeAdapter<TT> typeAdapter) {
        return new IRtextTypeAdapterFactory() {
            @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
            @Override
            public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
                return typeToken.equals(type) ? (IRtextTypeAdapter<T>) typeAdapter : null;
            }
        };
    }

    public static <TT> IRtextTypeAdapterFactory newFactory(final Class<TT> type, final IRtextTypeAdapter<TT> typeAdapter) {
        return new IRtextTypeAdapterFactory() {
            @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
            @Override
            public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
                return typeToken.getRawType() == type ? (IRtextTypeAdapter<T>) typeAdapter : null;
            }

            @Override
            public String toString() {
                return "Factory[type=" + type.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> IRtextTypeAdapterFactory newFactory(final Class<TT> unboxed, final Class<TT> boxed, final IRtextTypeAdapter<? super TT> typeAdapter) {
        return new IRtextTypeAdapterFactory() {
            @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
            @Override
            public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == unboxed || rawType == boxed) ? (IRtextTypeAdapter<T>) typeAdapter : null;
            }

            @Override
            public String toString() {
                return "Factory[type=" + boxed.getName() + "+" + unboxed.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> IRtextTypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> base, final Class<? extends TT> sub, final IRtextTypeAdapter<? super TT> typeAdapter) {
        return new IRtextTypeAdapterFactory() {
            @SuppressWarnings("unchecked") // we use a runtime check to make sure the 'T's equal
            @Override
            public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == base || rawType == sub) ? (IRtextTypeAdapter<T>) typeAdapter : null;
            }

            @Override
            public String toString() {
                return "Factory[type=" + base.getName() + "+" + sub.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
