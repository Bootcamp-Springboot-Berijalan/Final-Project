package com.example.ProjectSpringboot.service.Impl

import com.example.ProjectSpringboot.domain.dto.request.ReqTypeDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResTypeDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.example.ProjectSpringboot.domain.entity.TypeEntity
import com.example.ProjectSpringboot.repository.TypeRepository
import com.example.ProjectSpringboot.service.TypeService
import org.springframework.stereotype.Service

@Service
class TypeServiceImpl(
    private val typeRepository: TypeRepository
): TypeService {
    private fun resType(typeId: String): ResTypeDto{
        val userRes = typeRepository.findByTypeId(typeId)
        val res = ResTypeDto(
            typeId = userRes!!.typeId!!,
            description = userRes.description!!
        )
        return res
    }

    override fun addRow(): ResMessageDto<List<ResTypeDto>> {
        val input1 = TypeEntity(
            typeId = "T001", description = "Free"
        )
        val input2 = TypeEntity(
            typeId = "T002", description = "Premium"
        )
        if(typeRepository.findAll().isNotEmpty()){
            throw ArrayIndexOutOfBoundsException("Types are already added")
        }
        typeRepository.saveAll(listOf(input1, input2))
        val types = typeRepository.findAll().toList()
        val res = arrayListOf<ResTypeDto>()
        for (type in types){
            val data = resType(type.typeId!!)
            res.add(data)
        }
        return ResMessageDto(
            status = 200, message = "Types added",
            data = res
        )
    }

    override fun getAll(): ResMessageDto<List<ResTypeDto>> {
        val types = typeRepository.findAll()
        val res = arrayListOf<ResTypeDto>()
        for(type in types){
            val data = resType(type.typeId!!)
            res.add(data)
        }
        return ResMessageDto(data = res)
    }

    override fun getById(id: String): ResMessageDto<ResTypeDto> {
        val type = typeRepository.findByTypeId(id)
        val res = resType(type!!.typeId!!)
        return ResMessageDto(data = res)
    }
}