package org.maxim.crud.repository.gson;

import java.util.List;
import java.util.function.Function;

public class IdGenerator {
    public static <T> Long generatedId(List<T> generic, Function<T, Long> id) {
            return generic
                    .stream()
                    .mapToLong(id::apply)
                    .max().orElse(0) + 1;

        }
    }




