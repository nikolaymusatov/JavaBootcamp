package edu.school21.Spring;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer{
    private Renderer renderer;
    
    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }
    
    @Override
    public void print(String text) {
        this.renderer.print(LocalDateTime.now() + " " + text);
    }
}
