package accounts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class AccountsServer

fun main(args: Array<String>) {
    runApplication<AccountsServer>(*args)
}

// Coment para hacer push origin
