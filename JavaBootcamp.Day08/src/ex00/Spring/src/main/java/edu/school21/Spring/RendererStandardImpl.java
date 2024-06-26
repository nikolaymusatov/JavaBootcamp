package edu.school21.Spring;

public class RendererStandardImpl implements Renderer{
    private PreProcessor preProcessor;
    
    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    
    @Override
    public void print(String text) {
        System.out.println(this.preProcessor.process(text));
    }
}
