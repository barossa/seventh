package by.bsuir.commerce.seventh.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class ControllerUtils {
    static Map<String, List<String>> getErrorsMap(BindingResult bindingResult) {
        Set<String> errorFields = bindingResult
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toSet());

        Function<String, List<String>> errors = field -> bindingResult.getFieldErrors(field)
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return errorFields
                .stream()
                .collect(Collectors.toMap(key -> key, errors));
    }
}
