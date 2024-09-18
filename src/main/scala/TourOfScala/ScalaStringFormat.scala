package TourOfScala

/*
https://tourofscala.com/scala/string-format

Let's see an example on how some Java features have been integrated in Scala. Specially about String formatting at the moment.

This SKB is about formatting numbers.

In the first example, we are seeing how to add leading zeros in front of a number. That can be useful when building some UI or dashboard so the alignment is fix no matter how big the number is.
 Pretty useful and easy to use !

Then formatting Double, how to truncate the decimals as well as adding leading zeros. Play with it to get a good intuition about it.

And finally, getting closer to Java with Locale which allow you to display a number the right way based on the location. Locale can also be used to format dates and currencies ! Feel free to search
 for more information online and try to implement the code in here.
 */
object ScalaStringFormat extends App{

  // simple leading zeros
  val i: Int = 5
  val outputLeadingZeros: String = f"$i%04d" // %04d means it will take 4 slots and fill them with 0 if there is no value
  println(outputLeadingZeros) // o/p- 0005
  assert(outputLeadingZeros == "0005")

  // customize leading zeros
  val length: Int = 7
  // try with 0, 1, 2, ' '
  val c: Char = '0'
  println(s"%${c}${length}d".format(i)) // o/p- 0000005 , this is %07d

  // truncated decimals and leading zeros
  val infiniteDouble: Double = 10 / 3.0 //we wrote 3.0 instead of 3 to tell compiler denominator is a double as Int/Double = Double
  println(f"$infiniteDouble%09.4f") // o/p- 0003.3333

  // dynamic truncated decimals and leading zeros
  val totalCharacterNumber: Int = 7
  val decimalQuantity: Int = 3
  assert(totalCharacterNumber > decimalQuantity)
  val outputTruncDecimalAndLeadZero = s"%0${totalCharacterNumber}.${decimalQuantity}f".format(infiniteDouble) //%07.3f ie dynamically filling values in line 34
  println(outputTruncDecimalAndLeadZero) // o/p- 003.333
  assert(outputTruncDecimalAndLeadZero == "003.333")

  // using locale to format things

  import java.util.Locale
  import java.text.NumberFormat

  val bigNumber: Long = 123345567
  val formatNumberFR: NumberFormat = NumberFormat.getIntegerInstance(Locale.FRANCE)
  println(formatNumberFR.format(bigNumber)) //o/p- 123 345 567
  val formatNumberUS: NumberFormat = NumberFormat.getIntegerInstance(Locale.US)
  println(formatNumberUS.format(bigNumber)) //o/p- 123,345,567

  println(
    "Congratulations ! 'With the new day comes new strength and new thoughts.' - Eleanor Roosevelt"
  )


}
