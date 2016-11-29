package jekyllify

import java.io.File

fun main(args: Array<String>) {

    val outPath = "/home/ligi/git/survivalmanual.github.io"
    File("/home/ligi/git/wikis/SurvivalManual.wiki/").listFiles().forEach {
        println("procesing ${it.name}")


        if (!it.name.equals(".git")) {
            val target = File(outPath, it.name)
            it.copyTo(target, overwrite = true)


            if (target.name.endsWith(".md")) {
                val old = target.readText()
                if (it.name=="Home.md") {
                    target.writeText("---\ntitle: "+it.name+"\nlayout: index\npermalink: /\n---\n"+old)
                } else {
                    val perma = "/" + it.name.replace(".md", "")
                    target.writeText("---\ntitle: "+it.name+"\nlayout: default\npermalink: $perma\n---\n"+old)

                }

            }
        }


    }

    File(outPath,"Home.md").renameTo(File(outPath,"index.md"))

 }