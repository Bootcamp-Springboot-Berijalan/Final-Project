package com.example.ProjectSpringboot.config

import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.util.JwtGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthInterceptor(): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("token")
        if(token == null){
            val body:ResMessageDto<String> = ResMessageDto(
                status = 401,
                message = "You don't have permission",
                data = null
            )
            internalServerError(body, response)
            return false
        }
        try {
            JwtGenerator().decodeJwt(token).get("sub")
        }catch (e: ExpiredJwtException){
            e.printStackTrace()
            val body: ResMessageDto<String> = ResMessageDto(401, "Invalid token", null)
            internalServerError(body, response)
            return false
        }
        return super.preHandle(request, response, handler)
    }

    private fun internalServerError(
        body: ResMessageDto<String>,
        res: HttpServletResponse
    ): HttpServletResponse{
        res.status = HttpStatus.FORBIDDEN.value()
        res.contentType = "application/json"
        res.writer.write(convertObjectToJson(body))
        return res
    }

    fun convertObjectToJson(dto: ResMessageDto<String>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}