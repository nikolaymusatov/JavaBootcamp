package edu.school21.Spring;

public class PrinterWithPrefixImpl implements Printer{
    private Renderer renderer;
    private String prefix;
    
    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "";
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    @Override
    public void print(String text) {
        this.renderer.print(this.prefix + " " + text);
    }
}
