package classes;

import interfacec.PreProcessor;
import interfacec.Renderer;

public class RendererStandardImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String s) {
        System.out.println(preProcessor.process(s));
    }
}
