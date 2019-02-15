package com.microstrategy.autovaluedemo;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class ImmutableListTypeAdapterFactory implements TypeAdapterFactory {


    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        //https://stackoverflow.com/a/42543623/4068957
        //https://stackoverflow.com/a/13624060/4068957
        if (!ImmutableList.class.isAssignableFrom(type.getRawType())) {
            return null;
        }
        final TypeToken<T> mapType = (TypeToken<T>) TypeToken.getParameterized(List.class,
                ((ParameterizedType) type.getType()).getActualTypeArguments());
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, mapType);
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                return (T) ImmutableList.copyOf((List) delegate.read(in));
            }
        };
    }
}