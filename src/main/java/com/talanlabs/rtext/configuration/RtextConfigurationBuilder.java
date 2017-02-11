package com.talanlabs.rtext.configuration;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.talanlabs.rtext.IRtextTypeAdapter;
import com.talanlabs.rtext.IRtextTypeAdapterFactory;
import com.talanlabs.rtext.Rtext;
import com.talanlabs.typeadapters.ITypeAdapter;
import com.talanlabs.typeadapters.ITypeAdapterFactory;
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

    public RtextConfigurationBuilder registerTypeAdapter(Type type, IRtextTypeAdapter<?> typeAdapter) {
        rtextConfiguration.typeAdapterFactories
                .add(new RtextTypeAdapterFactoryAdapter(TypeAdaptersHelper.newTypeFactory(TypeToken.of(String.class), TypeToken.of(type), (IRtextTypeAdapter) typeAdapter)));
        return this;
    }

    public RtextConfigurationBuilder registerTypeHierarchyAdapter(Class<?> type, IRtextTypeAdapter<?> typeAdapter) {
        rtextConfiguration.typeAdapterFactories.add(new RtextTypeAdapterFactoryAdapter(TypeAdaptersHelper.newTypeHierarchyFactory(String.class, type, (IRtextTypeAdapter) typeAdapter)));
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

    private static class RtextTypeAdapterFactoryAdapter implements IRtextTypeAdapterFactory {

        private ITypeAdapterFactory<Rtext> typeAdapterFactory;

        public RtextTypeAdapterFactoryAdapter(ITypeAdapterFactory<Rtext> typeAdapterFactory) {
            super();

            this.typeAdapterFactory = typeAdapterFactory;
        }

        @Override
        public <T> IRtextTypeAdapter<T> create(Rtext rtext, TypeToken<T> typeToken) {
            ITypeAdapter<String, T> typeAdapter = typeAdapterFactory.create(rtext, TypeToken.of(String.class), typeToken);
            return typeAdapter != null ? new RtextTypeAdapter<T>(typeAdapter) : null;
        }
    }

    private static class RtextTypeAdapter<T> implements IRtextTypeAdapter<T> {

        private ITypeAdapter<String, T> typeAdapter;

        public RtextTypeAdapter(ITypeAdapter<String, T> typeAdapter) {
            this.typeAdapter = typeAdapter;
        }

        @Override
        public T toDst(String s) {
            return typeAdapter.toDst(s);
        }
    }
}