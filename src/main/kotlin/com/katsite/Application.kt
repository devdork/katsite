package com.katsite

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.css.Color
import kotlinx.css.body
import kotlinx.css.em
import kotlinx.css.p
import kotlinx.html.*

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module() {
    routing {
        get("/") {
            call.respondHtml {
                body {
                    header {
                        h1 { +"kat is kyute!" }
                    }
                    main {
                        section {

                        }
                    }
                }
            }
        }

        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.red
                }
                p {
                    fontSize = 2.em
                }
                rule("p.myclass") {
                    color = Color.blue
                }
            }
        }
    }
}