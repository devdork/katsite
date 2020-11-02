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
    "'poggers' is forbidden, blame nikky",
    "this website is as straight as i am"
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

        fun Int.toHSL(saturation: Int = 100, lightness: Int = 50) = hsl(this % 360, saturation, lightness)

        val rotations = mutableMapOf<String, Int>().withDefault { k -> Random.nextInt(20) }
        get("/styles.css") {
            val ip = call.request.origin.host
            val background = Random.nextInt(360)
            val bodyBackground = Random.nextInt(360)
            val headerBackground = Random.nextInt(360)
            val foreground = (bodyBackground + 180 % 360)
            val headerForeground = (headerBackground + 180)
            val boredRadius = Random.nextInt(0,5).em

            call.respondCss {
                html {
                    backgroundColor = background.toHSL(saturation = Random.nextInt(50,100))
                }
                body {
                    boxShadow(
                        hsl(Random.nextInt(360), Random.nextInt(50,100), 50),
                        Random.nextInt(20, 200).px,
                        Random.nextInt(20, 200).px,
                        Random.nextInt(20, 200).px,
                        Random.nextInt(20, 200).px
                    )
                    backgroundColor = bodyBackground.toHSL(saturation = Random.nextInt(50,100))
                    fontFamily = "sans-serif"
                    margin(Random.nextInt(4,12).em, LinearDimension.auto)
                    width = 60.pct
                    minWidth = 768.px
                    height = 100.pct
                    color = foreground.toHSL(saturation = Random.nextInt(50,100))
                    transform.rotate(rotations.getValue(ip).deg)
                    borderRadius = boredRadius
                    overflow = Overflow.hidden
                }
                rule("main") {
                    padding(1.em)
                }
                header {
                    padding(0.5.em)
                    textAlign = TextAlign.center
                    borderTopLeftRadius = boredRadius
                    borderTopRightRadius = boredRadius
                    color = headerForeground.toHSL(saturation = Random.nextInt(50,100))
                    backgroundColor = headerBackground.toHSL(saturation = Random.nextInt(50,100))
                }
            }
            rotations[ip] = rotations.getValue(ip) % 360 + Random.nextInt(5, 10)
        }
    }
}