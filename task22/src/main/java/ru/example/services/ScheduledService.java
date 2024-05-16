package ru.example.services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tuganov.TaskMXBean;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class ScheduledService implements TaskMXBean {
    private final ServiceDB service;

    @PostConstruct
    private void init() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.example:type=ScheduledService");
        if (!mbs.isRegistered(name)) {
            mbs.registerMBean(this, name);
        } else {
            log.info("MBean with name {} is already registered.", name);
        }
    }


    @Scheduled(fixedRateString = "PT30M")
    public void remadeFiles() throws IOException {
        Files.walk(Path.of("src/main/resources/entities")).filter(Files::isRegularFile).forEach(p -> {
            try {
                Files.delete(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        FileWriter studentsFile = new FileWriter("src/main/resources/entities/students.txt");
        studentsFile.write(service.getStudents().toString());

        FileWriter subjectsFile = new FileWriter("src/main/resources/entities/subjects.txt");
        subjectsFile.write(service.getSubjects().toString());

        studentsFile.close();
        subjectsFile.close();
    }

    @Override
    public void runRemadeFiles() throws IOException {
        remadeFiles();
    }
}
