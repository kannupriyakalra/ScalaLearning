package TourOfScala

/*
https://tourofscala.com/scala/regex

Scala Regex- This SKB is about Regex, which stand for Regular Expression.

I will not explain the details about Regex in this article but feel free to look at those resources to learn more about it:

To learn: github.com/ziishaned/learn-regex https://github.com/ziishaned/learn-regex
To learn and practice: Regex One https://regexone.com/
To practice: Regex Crossword https://regexcrossword.com/
The first example is a simple case to check for a match. However the compilation of the regex into an actionable test can be costly. It is recommended to compile the regex once before using it several times. This is when the second example come into play.

The second example is more complex and involved. We describe a structure to hold the regex and build a case class from it. We can then build shortcut methods to make the interaction with the regex more intuitive.

The third example is leveraging pattern matching. You can see each group match in the regex ( ... ) is corresponding to a value in the pattern matching.
 */
object ScalaRegex extends App {

  import scala.util.matching.Regex
  import java.util.regex.{Matcher, Pattern}
  import scala.util.Try

  // simple and quick use case
  val testPhoneNumber: String = "123-456-7890"
  val isPhoneNumber = "[0-9]{3}-[0-9]{3}-[0-9]{4}".r.matches(testPhoneNumber)
  println(s"is phone number: $isPhoneNumber")

  // longer, more efficient
  object FindEmail {
    private val regex: Regex = new Regex("([a-z]+)@([a-z]+)\\.([a-z]+)")
    private val pattern: Pattern = regex.pattern

    def apply(input: String): RegexFind = RegexFind(pattern.matcher(input))

    case class RegexFind(private val m: Matcher) {
      private lazy val find: Boolean = m.find()
      private lazy val groupCount: Int = m.groupCount()

      private lazy val matches: List[String] = (for {
        n <- 1 to groupCount
        group = Try(m.group(n))
        if group.isSuccess
      } yield group.get).toList

      override lazy val toString: String = s"match: $find, matches: $matches"
    }
  }

  val testEmail: String = "kannupriyakalra@gmail.com"
  val matches: FindEmail.RegexFind = FindEmail(testEmail)
  println(s"Matches: $matches")

  // with pattern matching
  val testAddress: String = "123 oaklands blvd."
  val isAddress: Regex = "([0-9]+) ([a-z]+) (st|blvd)\\.".r
  testAddress match {//here we are matching a string against a regex using unapply(this matches only if string is conforming to regex)
    case "abcd" => println("abcd")
    case "123 oaklands blvd." => println("Hi")
    case isAddress(number, streetName, streetType) =>
      println(s"streetName: $streetName $streetType , at: $number")
      assert(number.toInt == 123, number)
  }

  println(
    "Congratulations ! 'The most effective way to do it, is to do it.' - Amelia Earhart"
  )


}
