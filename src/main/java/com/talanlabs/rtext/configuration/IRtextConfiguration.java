package com.talanlabs.rtext.configuration;

import com.talanlabs.rtext.IRtextTypeAdapterFactory;

import java.util.List;

public interface IRtextConfiguration {

    /**
     * @return Adapter factories
     */
    List<IRtextTypeAdapterFactory> getTypeAdapterFactories();

}
