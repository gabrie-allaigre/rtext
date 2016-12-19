package com.talanlabs.rtext.configuration;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.typeadapters.TypeAdaptersHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RtextConfigurationBuilder {

    private RtextConfigurationImpl rtextConfiguration;

    private RtextConfigurationBuilder() {
        super();

        this.rtextConfiguration = new RtextConfigurationImpl();
    }

    public static RtextConfigurationBuilder newBuilder() {
        return new RtextConfigurationBuilder();
    }

    @SuppressWarnings("unchecked")
    public RtextConfigurationBuilder registerTypeAdapter(Type type, IRtextTypeAdapter<?> typeAdapter) {
        rtextConfiguration.typeAdapterFactories.add((IRtextTypeAdapterFactory) TypeAdaptersHelper.newTypeFactory(TypeToken.of(String.class), TypeToken.of(type), (IRtextTypeAdapter) typeAdapter));
        return this;
    }

    @SuppressWarnings("unchecked")
    public RtextConfigurationBuilder registerTypeHierarchyAdapter(Class<?> type, IRtextTypeAdapter<?> typeAdapter) {
        rtextConfiguration.typeAdapterFactories.add((IRtextTypeAdapterFactory) TypeAdaptersHelper.newTypeHierarchyFactory(String.class, type, (IRtextTypeAdapter) typeAdapter));
        return this;
    }

    public RtextConfigurationBuilder registerTypeAdapterFactory(IRtextTypeAdapterFactory... typeAdapterFactories) {
        rtextConfiguration.typeAdapterFactories.addAll(Arrays.asList(typeAdapterFactories));
        return this;
    }

    public IRtextConfiguration build() {
        return rtextConfiguration;
    }

    private static class RtextConfigurationImpl implements IRtextConfiguration {

        private final List<IRtextTypeAdapterFactory> typeAdapterFactories = new ArrayList<>();

        private RtextConfigurationImpl() {
            super();
        }

        @Override
        public List<IRtextTypeAdapterFactory> getTypeAdapterFactories() {
            return ImmutableList.copyOf(typeAdapterFactories);
        }
    }
}