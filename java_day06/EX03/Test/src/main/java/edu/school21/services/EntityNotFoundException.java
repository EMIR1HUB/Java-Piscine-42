package edu.school21.services;

public class EntityNotFoundException extends RuntimeException{
    EntityNotFoundException(){
        super("It's login wrong!!!");
    }
}
