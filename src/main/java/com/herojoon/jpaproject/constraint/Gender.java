package com.herojoon.jpaproject.constraint;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Gender implements BaseEnumCode<String>{
    NULL("빈값", ""),
    WOMAN("여자", "W"),
    MAN("남자", "M");

    private final String desc;

    private final String value;

    Gender(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    private static final Map<String, Gender> descriptions =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Gender::getValue, Function.identity())));

    public static Gender findOf(String findValue) {
        return Optional.ofNullable(descriptions.get(findValue)).orElse(NULL);
    }
}
