import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun main(){
    val passwordEncoder = BCryptPasswordEncoder()
    val hash = passwordEncoder.encode("Shubham@1234")
    println(hash)
}
