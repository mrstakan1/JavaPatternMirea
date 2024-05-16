package ru.example.task13;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class User {

    @Value("${program.user.name}")
    private final String name;

    @Value("${program.user.last_name}")
    private final String last_name;

    @Value("${program.user.group}")
    private final String group;

    @Value("${program.user.some:def}")
    private final String some;

    @PostConstruct
    public void start() {
        log.info("name: {}, last_name: {}, group: {}, some: {}", name, last_name, group, some);
    }
}
