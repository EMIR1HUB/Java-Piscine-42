package app;

import classes.Car;
import classes.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    private static final User user = new User();
    private static final Car car = new Car();
    private static Class<?> clasInput;

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        Class<?> classUser = Class.forName("classes.User");
        Class<?> classCar = Class.forName("classes.Car");

        String justClassUserName = classUser.getSimpleName();
        String justClassCarName = classCar.getSimpleName();

        System.out.print("Classes:" + "\n- " + justClassUserName
                + "\n- " + justClassCarName + "\n---------------------\nEnter class name:\n-> ");

        String inputClass = input.nextLine();

        clasInput = Class.forName("classes." + inputClass);
        Field[] fields = clasInput.getDeclaredFields();
        Method[] methods = clasInput.getDeclaredMethods();

        System.out.println("fields:");
        for (Field field : fields) {
            String type = (field.getAnnotatedType()).toString();
            if (type.contains("java.lang.")) {
                type = type.substring(10);
            }
            System.out.println("\t" + type + " " + field.getName());
        }
        System.out.println("methods:");
        for (Method method : methods) {
            String type = (method.getAnnotatedReturnType()).toString();
            if (type.contains("java.lang.")) {
                type = type.substring(10);
            }
            String temp = Arrays.toString(method.getParameterTypes());
            temp = temp.substring(1, temp.length() - 1);
            System.out.println("\t" + type + " " + method.getName() + "(" + temp + ")");
        }

        System.out.println("---------------------\nLet’s create an object.");
        for (Field field : fields) {
            System.out.print(field.getName() + ":\n-> ");
            typeForInput(field, input);
        }
        ShowClassToString();

        System.out.print("---------------------\nEnter name of the field for changing:\n-> ");
        String nameDo = input.nextLine();
        for (Field field : fields) {
            if (nameDo.equals((field.getName()).toString())) {
                System.out.print("Enter String value:\n-> ");
                typeForInput(field, input);
            }
        }
        ShowClassToString();

        System.out.print("---------------------\nEnter name of the method for call:\n-> ");
        nameDo = input.nextLine();
        for (Method method : methods) {
            String temp = Arrays.toString(method.getParameterTypes());
            temp = temp.substring(1, temp.length() - 1);
            if (nameDo.equals(method.getName() + "(" + temp + ")")) {
                int anInt = 0;
                if (!temp.equals("")) {
                    System.out.print("Enter " + temp + " value:\n-> ");
                    anInt = input.nextInt();
                }
                System.out.println("Method returned:");
                inputMethod(method, anInt);
            }
        }
    }

    private static void ShowClassToString(){
        if(clasInput.getSimpleName().equals("User")) {
            System.out.println("Object created: " + user.toString());
        } else if (clasInput.getSimpleName().equals("Car")) {
            System.out.println("Object created: " + car.toString());
        }
    }

    private static void inputMethod(Method method, int anInt){
        switch ((method.getName()).toString()) {
            case "addMonth" -> System.out.println(user.addMonth(anInt));
            case "yearBirthday" -> System.out.println(user.yearBirthday());
            case "yearHasPassed" -> System.out.println(car.yearHasPassed());
        }
    }

    private static void typeForInput(Field field, Scanner input) {
        String type = (field.getAnnotatedType()).toString();
        if (type.contains("java.lang.")) {
            type = type.substring(10);
        }
        field.setAccessible(true);
        if (type.equals("String")) {
            String inOb = input.nextLine();
            try {
                if(clasInput.getSimpleName().equals("User")){
                    field.set(user, inOb);
                }
                if(clasInput.getSimpleName().equals("Car")){
                    field.set(car, inOb);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else if (type.equals("Integer") || type.equals("int")) {
            int inOb = input.nextInt();
            input.nextLine();
            try {
                if(clasInput.getSimpleName().equals("User")){
                    field.set(user, inOb);
                }
                if(clasInput.getSimpleName().equals("Car")){
                    field.set(car, inOb);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
