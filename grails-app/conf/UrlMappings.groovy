class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "/right.gsp"(view: "/main/right")
        "/welcome.gsp"(view: "/main/welcome")
        "/left.gsp"(view: "/main/left")

        "403"(view: "/errors/forbidden")
        "404"(view: "/errors/notFound")
        "500"(view: "/errors/serverError")
        "503"(view: "/errors/serverError")
    }
}
