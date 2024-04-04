package com.example.ProjectSpringboot.service.Impl

import com.example.ProjectSpringboot.domain.dto.request.ReqFavoriteDto
import com.example.ProjectSpringboot.domain.dto.response.ResFavoriteDto
import com.example.ProjectSpringboot.domain.dto.response.ResMessageDto
import com.example.ProjectSpringboot.domain.entity.EBookEntity
import com.example.ProjectSpringboot.domain.entity.FavoriteEntity
import com.example.ProjectSpringboot.repository.EBookRepository
import com.example.ProjectSpringboot.repository.FavoriteRepository
import com.example.ProjectSpringboot.repository.UserRepository
import com.example.ProjectSpringboot.service.FavoriteService
import com.example.ProjectSpringboot.util.JwtGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class FavoriteServiceImpl(
    private val favoriteRepository: FavoriteRepository,
    private val eBookRepository: EBookRepository,
    private val userRepository: UserRepository
): FavoriteService {
    override fun create(token: String, req: ReqFavoriteDto): ResMessageDto<String> {
        val input = FavoriteEntity(
            bookId = req.bookId,
            userAdded = JwtGenerator().decodeJwt(token).get("username").toString(),
            dtAdded = LocalDateTime.now(ZoneId.of("Asia/Jakarta")),
            dtUpdated = LocalDateTime.now(ZoneId.of("Asia/Jakarta")),
        )
        favoriteRepository.save(input)
        return ResMessageDto(
            status = 200, message = "Favorite added",
        )
    }

    override fun get(token: String): ResMessageDto<List<ResFavoriteDto>> {
        val username = JwtGenerator().decodeJwt(token).get("username").toString()
        val user = userRepository.findByUsername(username)
        val favs = favoriteRepository.findAllByUserAdded(username)
        val res = arrayListOf<ResFavoriteDto>()
        for(fav in favs){
            val data = ResFavoriteDto(
                id = fav.id!!,
                bookId = fav.bookId!!,
                user = fav.userAdded!!
            )
            if(Objects.equals(user!!.type, "T001")  ){
                if(Objects.equals(eBookRepository.findById(fav.bookId!!).get().type,"T001" )){
                    res.add(data)
                }
            }else{
                res.add(data)
            }
        }
        return ResMessageDto(data = res)
    }
}