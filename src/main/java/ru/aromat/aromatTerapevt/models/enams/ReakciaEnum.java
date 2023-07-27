package ru.aromat.aromatTerapevt.models.enams;

public enum ReakciaEnum {
    PLUS_PLUS("++"),
    PLUS("+ "),
    ZERO("+-"),
    MINUS("- "),
    MINUS_MINUS("--");

    private final String reakciaName;

    ReakciaEnum(String reakciaName) {
        this.reakciaName = reakciaName;
    }

    public String getReakciaName() {
        return reakciaName;
    }
}
