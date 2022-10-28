package edu.school21.spring.service.repositories;

public class IllegalException extends RuntimeException {
    IllegalException(){
        super("Don't found data!");
    }
}
