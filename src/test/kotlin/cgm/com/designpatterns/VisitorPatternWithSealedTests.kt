package cgm.com.designpatterns

import org.junit.jupiter.api.Test


class VisitorPatternTests1 {
    @Test
    internal fun name() {
        val internationalSearch = MyInternationalSearch()
        val article = internationalSearch.search("banana")

        val useArticle = MyUserArticle()
        useArticle.printName(article)

        val useArticle2 = MyUserArticle2(0)
        useArticle2.sumCode(article)
    }
}

class MyUserArticle2(private val initialValue: Int) {
    fun sumCode(article: MyArticle): Int {
        return when(article) {
            is MyArticle.MyItaArticle -> initialValue + article.minsan.toInt()
            is MyArticle.MyDeuArticle -> initialValue + article.code
            is MyArticle.MyEspArticle -> TODO()
        }
    }
}

class MyUserArticle {
    fun printName(article: MyArticle) {
        when(article) {
            is MyArticle.MyItaArticle -> println(article.description)
            is MyArticle.MyDeuArticle -> println(article.name)
            is MyArticle.MyEspArticle -> TODO()
        }.exhaustive
    }
}

val Any.exhaustive: Unit get() = Unit

class MyInternationalSearch(){
    fun search(articleName : String): MyArticle= TODO("search")
}

sealed class MyArticle {
    data class MyItaArticle(val minsan: String, val description: String,val ean: Int) : MyArticle()
    data class MyDeuArticle(val code: Int, val name: String,val pzn: Double): MyArticle()
    data class MyEspArticle(val code: List<String>): MyArticle()
}
