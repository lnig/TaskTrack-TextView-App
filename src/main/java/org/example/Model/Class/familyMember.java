package org.example.Model.Class;

public class familyMember {
    private String name;

    public familyMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "familyMember{" +
                "name='" + name + '\'' +
                '}';
    }
}
