package edu.school21.Spring;

public class PreProcessorToUpperImpl implements PreProcessor{
    @Override
    public String process(String text) {
        return text.toUpperCase();
    }
}
