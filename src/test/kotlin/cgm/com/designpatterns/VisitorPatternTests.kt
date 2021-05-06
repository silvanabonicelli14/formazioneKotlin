package cgm.com.designpatterns

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class VisitorPatternTests {
    @Test
    internal fun name() {
        val internationalSearch = InternationalSearch()
        val article = internationalSearch.search("banana")

        val useArticle = UserArticle()
        useArticle.printName(article) shouldBe "descr"

        val useArticle2 = UserArticle2(0)
        useArticle2.sumCode(article) shouldBe 22223
    }
}

class UserArticle {
//    fun printName(article: Article) {
//        return when(article) {
//            is ItaArticle -> println(article.description)
//            is DeuArticle -> println(article.name)
//            else -> throw Exception("unknown")
//        }
//    }

    fun printName(article: Article) {
        article.readBy(object: ArticleReader{
            override fun readIta(article: ItaArticle) {
                println(article.description)
            }

            override fun readDeu(article: DeuArticle) {
                println(article.name)
            }

            override fun readEsp(article: EspArticle) {
                println(article.code.forEach{"${println(it)}\n"})
            }

        })
    }
}

class UserArticle2(private val initialValue: Int) {
//    fun sumCode(article: Article): Int {
//        return when(article) {
//            is ItaArticle -> initialValue + article.minsan.toInt()
//            is DeuArticle -> initialValue + article.code
//            else -> throw Exception("unknown")
//        }
//    }

    fun sumCode(article: Article): Int {
        var sum = initialValue

        article.readBy(object: ArticleReader{
            override fun readIta(article: ItaArticle) {
                sum + article.ean
            }

            override fun readDeu(article: DeuArticle) {
                TODO("Not yet implemented")
            }

            override fun readEsp(article: EspArticle) {
                TODO("Not yet implemented")
            }

        })
        return sum
    }
}

class InternationalSearch(){
    fun search(articleName : String)= ItaArticle("123","descr",22222)
}

interface Article{
    fun readBy(reader: ArticleReader)
}

interface ArticleReader {
    fun readIta(article: ItaArticle): Unit
    fun readDeu(article: DeuArticle): Unit
    fun readEsp(article: EspArticle): Unit
}

data class ItaArticle(val minsan: String, val description: String,val ean: Int) : Article {
    override fun readBy(reader: ArticleReader) {
        reader.readIta(this)
    }
}

data class DeuArticle(val code: Int, val name: String,val pzn: Double): Article {
    override fun readBy(reader: ArticleReader) {
        reader.readDeu(this)
    }
}

data class EspArticle(val code: List<String>): Article {
    override fun readBy(reader: ArticleReader) {
        TODO("Not yet implemented")
    }
}