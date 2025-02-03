package CodingInterview.Revolut

/*

RevolutR1
4 50- 5 35 45min

Input:
https://www.revolut.com/rewards-personalised-cashback-and-discounts/

Expected output:
https://www.rev.me/<url identifier>

For a given URL, generate a short URL and retrieve the original by the generated one. Remember only up to 100 URLs.

We are building a package which will used by a customer as a library.
 */
//object RevolutR1 {
//
//}
//


import scala.collection.mutable.HashMap

object URLShortener {
  private val baseUrl = "https://short.url/"
  private val urlMap = new HashMap[String, String]
  private var counter = 0

  def shorten(originalUrl: String): String = {
    if (urlMap.size >= 100) {
      throw new IllegalStateException("Maximum URL limit reached")
    }

    if (urlMap.contains(originalUrl)) {
      urlMap(originalUrl)
    } else {
      val shortUrl = baseUrl + counter.toString
      urlMap.put(originalUrl, shortUrl)
      urlMap.put(shortUrl, originalUrl)
      counter += 1
      shortUrl
    }
  }

  def retrieve(shortUrl: String): Option[String] = {
    urlMap.get(shortUrl)
  }
}

object Main extends App {
  // Example usage
  val originalUrl = "https://www.example.com/page?id=123"
  val shortUrl = URLShortener.shorten(originalUrl)
  println(s"Short URL: $shortUrl")

  val retrievedUrl = URLShortener.retrieve(shortUrl)
  retrievedUrl match {
    case Some(url) => println(s"Retrieved URL: $url")
    case None => println("URL not found")
  }
}



