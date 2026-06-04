package org.example.demo.domain;

public record Box(int length, int width, int height, double weight) {
    public int getVolume() {
        return width() * height() * length();
    }
}
