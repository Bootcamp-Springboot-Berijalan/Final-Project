package com.example.ProjectSpringboot.util

import com.example.ProjectSpringboot.domain.dto.request.ReqLoginDto
import com.example.ProjectSpringboot.domain.dto.request.ReqUserDto
import com.example.ProjectSpringboot.domain.dto.response.ResUserDto
import com.techno.springbootdasar.exception.DataNotFoundException
import com.techno.springbootdasar.exception.InvalidTokenException
import io.jsonwebtoken.*
import java.util.*
import javax.crypto.spec.SecretKeySpec

class JwtGenerator {
    companion object{
        private const val SECRET_KEY = "YOUR_SECRET_KEY_THAT_HAS_256_LONG"
        private val instance: JwtGenerator = JwtGenerator()
    }
    fun createJwt(req: ResUserDto): String{
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMillis: Long = System.currentTimeMillis()
        val now = Date(nowMillis)

        val apiKeySecretBytes = SECRET_KEY.toByteArray()
        val signInKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder: JwtBuilder = Jwts.builder()
            .setSubject(req.email)
            .claim("username", req.username)
            .claim("type", req.type)
            .setIssuer("admin")
            .setAudience("user")
            .signWith(signInKey, signatureAlgorithm)

        val expMillis = nowMillis + 3600000L
        val exp = Date(expMillis)
        builder.setExpiration(exp)

        return builder.compact()
    }
    fun decodeJwt(jwt: String): Claims {
        try {
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.toByteArray())
                .build()
                .parseClaimsJws(jwt).body
            return claims
        }catch (e: JwtException){
            e.printStackTrace()
            throw DataNotFoundException("Invalid token asf asf")
        }
    }
}