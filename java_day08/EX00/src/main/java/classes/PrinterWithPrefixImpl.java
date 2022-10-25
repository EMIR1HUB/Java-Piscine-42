package classes;

import interfacec.Printer;
import interfacec.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private final Renderer renderer;
    private String prefix = "PREFIX";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.render(prefix + " " + message);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
