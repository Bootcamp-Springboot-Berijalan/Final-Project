package com.example.ProjectSpringboot

import com.example.ProjectSpringboot.service.TypeService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class ProjectSpringbootApplication

fun main(args: Array<String>) {
	runApplication<ProjectSpringbootApplication>(*args)
}
