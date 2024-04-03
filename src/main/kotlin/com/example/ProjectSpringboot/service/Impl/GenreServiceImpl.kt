package com.example.ProjectSpringboot.service.Impl

import com.example.ProjectSpringboot.domain.dto.request.ReqGenreDto
import com.example.ProjectSpringboot.domain.dto.response.ResGenreDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.example.ProjectSpringboot.domain.entity.GenreEntity
import com.example.ProjectSpringboot.repository.GenreRepository
import com.example.ProjectSpringboot.service.GenreService
import com.techno.springbootdasar.exception.DataNotFoundException
import com.techno.springbootdasar.exception.DataNotUniqueException
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl(
    val repository: GenreRepository
): GenreService {
    private fun checkDupe(name: String){
        val genre = repository.findByName(name)
        if(genre != null){
            throw DataNotUniqueException("Genre already exist")
        }
    }

    override fun create(req: ReqGenreDto): ResMessageDto<ResGenreDto> {
        checkDupe(req.name)
        val input = GenreEntity(name = req.name)
        repository.save(input)
        val genre = repository.findByName(req.name)
        val res = ResGenreDto(id = genre!!.id!!, name = genre.name!!)
        return ResMessageDto(
            status = 200, message = "Genre added!",
            data = res
        )
    }

    override fun update(id: Int, req: ReqGenreDto): ResMessageDto<ResGenreDto> {
        val genre = repository.findById(id)
        if(genre.isEmpty)
            throw DataNotFoundException("Genre id not found")
        if(genre.get().name != req.name){
            checkDupe(req.name)
        }
        genre.get().name = req.name
        repository.save(genre.get())
        val reso = repository.findById(id)
        val res = ResGenreDto(id = reso.get().id!!, name = reso.get().name!!)
        return ResMessageDto(
            status = 200, message = "Genre updated!",
            data = res
        )
    }

    override fun getAll(): ResMessageDto<List<ResGenreDto>> {
        val genres = repository.findAll()
        val res = arrayListOf<ResGenreDto>()
        for(genre in genres){
            val data = ResGenreDto(id = genre!!.id!!, name = genre.name!!)
            res.add(data)
        }
        return ResMessageDto(data = res)
    }

    override fun getById(id: Int): ResMessageDto<ResGenreDto> {
        val genreExist = repository.findById(id)
        if(genreExist.isEmpty)
            throw DataNotFoundException("Genre not found")
        val res = ResGenreDto(
            id = genreExist.get().id!!,
            name = genreExist.get().name!!
        )
        return ResMessageDto(data = res)
    }
}