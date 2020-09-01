package controller.main;

import model.data.Storage;
import model.loader.Loader;

public class Main {

    private static Storage storage = new Storage();
    private static Loader loader = new Loader(storage);

    public static Storage getStorage() {
        return storage;
    }

    public static Loader getLoader() {
        return loader;
    }

    public static void main(String[] args) {

        GreenSkiesApplication.main(args);
    }
}
