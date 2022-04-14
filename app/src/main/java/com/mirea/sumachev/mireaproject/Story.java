package com.mirea.sumachev.mireaproject;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Story {

    private String name;
    private String text;

    public Story(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
