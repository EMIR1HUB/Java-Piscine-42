package edu.school21.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException{
    @Override
    public String toString() {
        return ": No such sub entity\n";
    }
}
