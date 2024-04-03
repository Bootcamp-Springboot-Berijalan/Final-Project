package com.example.ProjectSpringboot.service.Impl

import com.example.ProjectSpringboot.domain.dto.request.ReqUserDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.example.ProjectSpringboot.domain.entity.UserEntity
import com.example.ProjectSpringboot.repository.TypeRepository
import com.example.ProjectSpringboot.repository.UserRepository
import com.example.ProjectSpringboot.service.UserService
import com.techno.springbootdasar.exception.DataNotFoundException
import com.techno.springbootdasar.exception.DataNotUniqueException
import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val repository: UserRepository,
    val typeRepository: TypeRepository
): UserService {
    private fun checkDupeUsr(username: String,){
        val usedUsername = repository.findByUsername(username)
        if(usedUsername != null){
            throw DataNotUniqueException("Username already used")
        }
    }
    private fun checkDupeEml(email: String,){
        val usedEmail = repository.findByEmail(email)
        if(usedEmail != null){
            throw DataNotUniqueException("Email already used")
        }
    }
    private fun resUser(username: String): ResUserDto{
        val userRes = repository.findByUsername(username)
        var type: String = ""
        if(userRes!!.type == "T001")
            type = "Free"
        if(userRes.type == "T002")
            type = "Premium"
        val res = ResUserDto(
            id = userRes.id!!, name = userRes.name!!,
            username = userRes.username!!, email = userRes.email!!,
            type = type
        )
        return res
    }
    private fun nullType(type: String): String{
        return if (type == ""){
            "Free"
        }else{
            type
        }
    }
    private fun typeAssign(type: String): String{
        val typeFound = typeRepository.findByDescription(nullType(type)) ?: throw DataNotFoundException("Type not found")
        return typeFound.typeId!!
    }
    private fun isExist(id: Int): Optional<UserEntity> {
        val user = repository.findById(id)
        if(user.isEmpty)
            throw DataNotFoundException("User id not found")
        return user
    }

    override fun create(req: ReqUserDto): ResMessageDto<ResUserDto> {
        checkDupeUsr(req.username)
        checkDupeEml(req.email)
        val password: Argon2 = Argon2Factory.create()
        val input = UserEntity(
            name = req.name, username = req.username,
            email = req.email, password = password.hash(10, 65536 , 1, req.password.toCharArray()),
            type = typeAssign(req.type)
        )
        repository.save(input)
        return ResMessageDto(
            status = 200, message = "User added!",
            data = resUser(req.username)
        )
    }

    override fun update(id: Int, req: ReqUserDto): ResMessageDto<ResUserDto> {
        val user = isExist(id)
        if(user.get().username != req.username)
            checkDupeUsr(req.username)
        if(user.get().email != req.email)
            checkDupeEml(req.email)
        val password: Argon2 = Argon2Factory.create()
        user.get().name = req.name
        user.get().username = req.username
        user.get().email = req.email
        user.get().password = password.hash(10, 65536 , 1, req.password.toCharArray())
        user.get().type = typeAssign(req.type)
        repository.save(user.get())
        return ResMessageDto(
            status = 200, message = "User added!",
            data = resUser(req.username)
        )
    }

    override fun getAll(): ResMessageDto<List<ResUserDto>> {
        val users = repository.findAll()
        val res = arrayListOf<ResUserDto>()
        for(user in users){
            val data = resUser(user.username!!)
            res.add(data)
        }
        return ResMessageDto(data = res)
    }

    override fun getById(id: Int): ResMessageDto<ResUserDto> {
        val user = isExist(id)
        val res = resUser(user.get().username!!)
        return ResMessageDto(data = res)
    }
}