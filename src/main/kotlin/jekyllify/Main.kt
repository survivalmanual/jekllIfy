package jekyllify

import java.io.File

fun main(args: Array<String>) {

    val outPath = "/home/ligi/git/survivalmanual.github.io"
    File("/home/ligi/git/wikis/SurvivalManual.wiki/").listFiles().filter { it.name != ".git" }.forEach {
        println("procesing ${it.name}")

        val target = File(outPath, it.name)
        it.copyTo(target, overwrite = true)

        if (target.name.endsWith(".md")) {
            val old = target.readText()

            val titleAndParam = if (it.name == "Home.md") {
                "layout: index\npermalink: /\n"
            } else {
                val perma = "/" + it.name.replace(".md", "")
                "layout: default\npermalink: $perma\n"
            }
            target.writeText("---\ntitle: " + it.name + "\n$titleAndParam---\n$old")
        }

    }

    File(outPath, "Home.md").renameTo(File(outPath, "index.md"))
}