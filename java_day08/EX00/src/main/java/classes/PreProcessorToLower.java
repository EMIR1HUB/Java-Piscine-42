package classes;

import interfacec.PreProcessor;

public class PreProcessorToLower implements PreProcessor {
    @Override
    public String process(String message) {
        return message.toLowerCase();
    }
}
