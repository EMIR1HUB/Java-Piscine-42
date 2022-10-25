package app;

import classes.PreProcessorToUpperImpl;
import classes.PrinterWithDateTimeImpl;
import classes.PrinterWithPrefixImpl;
import classes.RendererErrImpl;
import interfacec.PreProcessor;
import interfacec.Renderer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        //Определяем контекст файл в котором содержатся прописанные нами бины
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        //Получем бины, которые были определены в файле context.xml
        PrinterWithPrefixImpl printerWithPrefix = context.getBean("printerWithPrefixOutToUpper", PrinterWithPrefixImpl.class);
        printerWithPrefix.print("Hello!");

        printerWithPrefix = context.getBean("printerWithPrefixOutToLower", PrinterWithPrefixImpl.class);
        printerWithPrefix.setPrefix("Male:");
        printerWithPrefix.print("Mike");

        PrinterWithDateTimeImpl printerWithDateTime = context.getBean("printerWithDateTimeErrToUpper", PrinterWithDateTimeImpl.class);
        printerWithDateTime.print("I went to home");

        context.close();    //Контекст всегда должен закрываться
    }
}
