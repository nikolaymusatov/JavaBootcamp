package edu.school21.Spring;

public class RendererErrImpl implements Renderer{
    private PreProcessor preProcessor;
    
    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    
    @Override
    public void print(String text) {
        System.err.println(this.preProcessor.process(text));
    }
}
