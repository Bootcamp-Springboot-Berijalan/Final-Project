package com.example.ProjectSpringboot.service.Impl

import com.example.ProjectSpringboot.domain.dto.request.ReqLoginDto
import com.example.ProjectSpringboot.domain.dto.request.ReqUserDto
import com.example.ProjectSpringboot.domain.dto.response.ResLoginDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.example.ProjectSpringboot.repository.UserRepository
import com.example.ProjectSpringboot.service.LoginService
import com.example.ProjectSpringboot.util.JwtGenerator
import com.techno.springbootdasar.exception.DataNotFoundException
import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(
    private val userRepository: UserRepository
): LoginService {
    override fun login(req: ReqLoginDto): ResLoginDto {
        val email: String
        val token: String
        val userExist = userRepository.findByEmail(req.email) ?: throw DataNotFoundException("Username does not exist")
        val argon2 = Argon2Factory.create()
        val matchPassword = argon2.verify(userExist.password, req.password.toCharArray())
        if(!matchPassword){
            throw DataNotFoundException("Invalid password")
        }
        val userData = ResUserDto(
            id = userExist.id!!,
            name = userExist.name!!,
            username = userExist.username!!,
            email = userExist.email!!,
            type = userExist.type!!
        )
        email = userExist.email!!
        token = JwtGenerator().createJwt(userData)
        return ResLoginDto(email = email, token = token)
    }
}