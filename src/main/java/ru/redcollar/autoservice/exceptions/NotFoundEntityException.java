package ru.redcollar.autoservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String name, Long id) {
        super("Ошибка, " + name + " с id:" + id + " не найден");
    }

}