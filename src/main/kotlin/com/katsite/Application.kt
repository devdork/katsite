package com.katsite

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import kotlin.random.Random

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    link {
                        rel="stylesheet"
                        type="text/css"
                        href="./styles.css"
                    }
                }
                body {
                    header {
                        h1 { +"kat" }
                        p { +"who stole the spirit level?" }
                    }
                    main {
                        section {
                            h2 { +"other places i am on the web" }
                            ul {
                                li {
                                    +"twitter - "
                                    a("https://twitter.com/floofykate") {+"@floofykate" }
                                }
                                li {
                                    +"fediverse - "
                                    a("https://queer.af/@kat") { +"@kat@queer.af" }
                                }
                                li {
                                    +"github - "
                                    a("https://github.com/devdork") { +"@devdork" }
                                }
                                li {
                                    +"gitlab - "
                                    a("https://gitlab.com/floofykate") { +"@floofykate" }
                                }
                            }
                        }
                    }
                }
            }
        }
        val rotations = mutableMapOf<String, Int>().withDefault { k -> Random.nextInt(20) }

        get("/styles.css") {
            val ip = call.request.origin.host
            call.respondCss {
                html {
                    backgroundColor = Color("#ddd")
                }
                body {
                    boxShadow(Color("#999"), 20.px, 20.px, 20.px, 20.px)
                    backgroundColor = Color("#fff")
                    fontFamily = "sans-serif"
                    margin(8.em, LinearDimension.auto)
                    width = 60.pct
                    height = 100.pct
                    transform.rotate(rotations.getValue(ip).deg)
//                    padding(1.em)
                }
                rule("main") {
                    padding(1.em)
                }
                header {
//                    margin(0.px, -999.px)
                    padding(0.5.em)
                    //width = 100.pct
                    textAlign = TextAlign.center
                    color = Color("#fff")
                    backgroundColor = Color("#141414")
                }
                p {
//                    fontSize = 2.em
                }
                rule("p.myclass") {
                    color = Color.blue
                }
            }
            rotations[ip] = rotations.getValue(ip) % 360 + 1
        }
    }
}