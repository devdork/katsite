package com.katsite

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*
import kotlin.random.Random

val MEMES = listOf(
    "please do not adjust your television set, everything will be fine",
    "who stole the spirit level?",
    "umm, nyah~!",
    "mew mew :3",
    "i don't think therefore i'm not",
    "'poggers' is forbidden, blame nikky"
)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
fun Application.module() {
    routing {
        get("/") {
            val requestMeme = MEMES.random()
            call.respondHtml {
                head {
                    link {
                        rel="stylesheet"
                        type="text/css"
                        href="./styles.css"
                    }
                    title {
                        +"kat - $requestMeme"
                    }
                }
                body {
                    header {
                        h1 { +"kat" }
                        p { +requestMeme }
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
                }
                rule("main") {
                    padding(1.em)
                }
                header {
                    padding(0.5.em)
                    textAlign = TextAlign.center
                    color = Color("#fff")
                    backgroundColor = Color("#141414")
                }
            }
            rotations[ip] = rotations.getValue(ip) % 360 + 1
        }
    }
}