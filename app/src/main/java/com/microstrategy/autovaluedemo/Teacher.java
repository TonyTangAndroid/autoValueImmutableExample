package com.microstrategy.autovaluedemo;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Teacher {

    public abstract String name();

    public abstract ImmutableList<String> list();

    public abstract ImmutableMap<String, Integer> map();

    public static Builder builder() {
        return new AutoValue_Teacher.Builder();
    }

    static TypeAdapter<Teacher> typeAdapter(Gson gson) {
        return new AutoValue_Teacher.GsonTypeAdapter(gson).setDefaultName("tony");
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Builder list(ImmutableList<String> name);

        public abstract Builder map(ImmutableMap<String, Integer> map);

        public abstract Teacher build();
    }
}
