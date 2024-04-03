package com.example.ProjectSpringboot.exception

import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.techno.springbootdasar.exception.DataHasSpacesException
import com.techno.springbootdasar.exception.DataNotFoundException
import com.techno.springbootdasar.exception.DataNotUniqueException
import com.techno.springbootdasar.exception.InvalidTokenException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {
    @ExceptionHandler (MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = mapOf<String, Any>(
            "status" to "F",
            "error" to "field",
            "message" to errors
        )
        return ResponseEntity.badRequest().body(result)
    }
    @ExceptionHandler(DataNotFoundException::class)
    fun handleDataNotFound(exception: RuntimeException): ResponseEntity<ResMessageDto<String>>{
        exception.printStackTrace()
        return ResponseEntity.badRequest().body(ResMessageDto(
            status = 400, message = exception.message.toString()
        ))
    }
    @ExceptionHandler(DataNotUniqueException::class)
    fun handleDataNotUnique(exception: DataNotUniqueException): ResponseEntity<ResMessageDto<String>>{
        exception.printStackTrace()
        return ResponseEntity.badRequest().body(
            ResMessageDto(
            status = 400, message = exception.message.toString()
            )
        )
    }
    @ExceptionHandler(DataHasSpacesException::class)
    fun handleDataHasSpaces(exception: DataHasSpacesException): ResponseEntity<ResMessageDto<String>>{
        exception.printStackTrace()
        return ResponseEntity.badRequest().body(ResMessageDto(
            status = 400, message = exception.message.toString()
        ))
    }
    @ExceptionHandler(InvalidTokenException::class)
    fun handleInvalidToken(exception: InvalidTokenException): ResponseEntity<ResMessageDto<String>>{
        exception.printStackTrace()
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResMessageDto(
            status = 401, message = exception.message.toString()
        ))
    }
}