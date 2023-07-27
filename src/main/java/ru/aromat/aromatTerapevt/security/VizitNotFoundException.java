package ru.aromat.aromatTerapevt.security;

public class VizitNotFoundException extends RuntimeException {
    public VizitNotFoundException(Long id) {
        super("Could not find Vizit with id: " + id);
    }
}

