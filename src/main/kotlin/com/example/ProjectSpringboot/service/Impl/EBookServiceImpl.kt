package com.example.ProjectSpringboot.service.Impl

import com.example.ProjectSpringboot.domain.dto.request.ReqEBookDto
import com.example.ProjectSpringboot.domain.dto.response.ResEBookDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.entity.EBookEntity
import com.example.ProjectSpringboot.repository.EBookRepository
import com.example.ProjectSpringboot.repository.GenreRepository
import com.example.ProjectSpringboot.repository.TypeRepository
import com.example.ProjectSpringboot.service.EBookService
import com.example.ProjectSpringboot.service.GenreService
import com.example.ProjectSpringboot.util.JwtGenerator
import com.techno.springbootdasar.exception.DataNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class EBookServiceImpl(
    private val repository: EBookRepository,
    val genreRepository: GenreRepository,
    val typeRepository: TypeRepository,
    val genreService: GenreService
): EBookService {
    private fun resBook(name: String): ResEBookDto {
        val bookRes = repository.findByName(name)
        val genres: MutableList<String> = mutableListOf()
        for(item in bookRes!!.genre!!){
            val temp = genreService.getById(item).data
            temp?.name.let{genres.add(it!!)}
        }
        var type: String = ""
        if(bookRes.type == "T001")
            type = "Free"
        if(bookRes.type == "T002")
            type = "Premium"
        val res = ResEBookDto(
            id = bookRes.id!!,
            name = bookRes.name!!,
            author = bookRes.author!!,
            type = type,
            genre = genres
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
//    private fun genreAssign(id: Int): String{
//        val genreFound = genreRepository.findById(id)
//        return genreFound.get().name!!
//    }

    override fun create(req: ReqEBookDto): ResMessageDto<ResEBookDto> {
        val input = EBookEntity(
            name = req.name, author = req.author,
            type = typeAssign(req.type),
            genre = req.genre,
            dtAdded = LocalDateTime.now(ZoneId.of("Asia/Jakarta")),
            dtUpdated = LocalDateTime.now(ZoneId.of("Asia/Jakarta"))
        )
        repository.save(input)
        return ResMessageDto(
            status = 200, message = "E-Book added!",
            data = resBook(req.name)
        )
    }

    override fun update(id: Int, req: ReqEBookDto): ResMessageDto<ResEBookDto> {
        val ebook = repository.findById(id)
        if(ebook.isEmpty)
            throw DataNotFoundException("E-Book not found")
        ebook.get().name = req.name
        ebook.get().author = req.author
        ebook.get().type = typeAssign(req.type)
        ebook.get().genre = req.genre
        ebook.get().dtUpdated = LocalDateTime.now(ZoneId.of("Asia/Jakarta"))
        repository.save(ebook.get())
        return ResMessageDto(
            status = 200, message = "E-Book updated!",
            data = resBook(req.name)
        )
    }

    override fun getAll(token: String): ResMessageDto<List<ResEBookDto>> {
        try {
            // Extract user type from token
            val userType = JwtGenerator().decodeJwt(token).get("type").toString()
            // Retrieve all books
            val books = repository.findAll()
            val res = mutableListOf<ResEBookDto>()
            for (book in books) {
                val data = resBook(book.name!!)
                // Filter based on user type
                if (userType == "T001") {
                    if (book.type.toString() == "T001")
                        res.add(data)
                } else {
                    res.add(data)
                }
            }
            return ResMessageDto(data = res)
        } catch (e: Exception) {
            // Log the exception for debugging
            e.printStackTrace()
            // Return an error response with a more specific message
            return ResMessageDto(status = 500, message = "Internal Server Error: ${e.message}", data = null)
        }
    }

    override fun searchBook(input: String): ResMessageDto<List<ResEBookDto>> {
        val genre = genreRepository.findByName(input)
        val books = mutableListOf<ResEBookDto>()
        println(genre!!.id)
        val searchData = repository.search(input, genre.id)
        for(book in searchData){
            val bookData = resBook(book.name!!)
            books.add(bookData)
        }
        return ResMessageDto(data = books)
    }
}