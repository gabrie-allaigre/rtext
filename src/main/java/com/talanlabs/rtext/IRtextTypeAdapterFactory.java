package com.talanlabs.rtext;

import com.google.common.reflect.TypeToken;

public interface IRtextTypeAdapterFactory extends com.talanlabs.typeadapters.ITypeAdapterFactory<Rtext> {

    <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken);

    @Override
    default <Src, Dst> com.talanlabs.typeadapters.ITypeAdapter<Src, Dst> create(Rtext parent, TypeToken<Src> srcTypeToken, TypeToken<Dst> dstTypeToken) {
        return (com.talanlabs.typeadapters.ITypeAdapter<Src, Dst>) this.create(parent, dstTypeToken);
    }
}
