package com.talanlabs.rtext;

import org.apache.commons.lang3.NotImplementedException;

public interface IRtextTypeAdapter<T> extends com.talanlabs.typeadapters.ITypeAdapter<String, T> {

    @Override
    default String toSrc(T t) {
        throw new NotImplementedException("toSrc is not implemented");
    }
}
