package TourOfScala

/*

//https://tourofscala.com/scala/random
Scala Random

A random number generator will generate a number that cannot be predicted.

Although, it is impossible to generate true random number on a computer.

Please take a look at line 3 with the import statement. This tells the compiler that, to be able to compile this code, it will need to fetch this other component. In most Scala code you are going to see in your professional life, there will be import statements at the beginning of the files. But, do not worry, those are usually added automatically by the development environment (IDE) so you don't have to care for it quite yet.

You recognize the new from the SKB about class. It tells us that scala.util.Random is a class that needs to be instantiated before able to use it.

Now we have a random generator, what can we do with it? You can generate a lot of different types, here we are only focusing on Int to simplify things but you can take a look at scala.util.Random documentation to see what else is available.

Try running the code several times, do you see that the number generated are different each time?

However, the number generated by the generator started with a seed always generate the same series of number. This is because there are no true random in a computer. A random generator is a function that given a number generate a new number. The starting number is the seed. If you are playing procedurally generated games, such as Minecraft for instance, this is what the seed is for, it initializes the random generator.

One interesting part of this SKB is the randomInt method. Did you figure out what was the missing part ? If not, here is the solution:

rand.nextInt(max - min) + min
The first part ( rand.nextInt(max - min) ) will return an Integer between 0 and max - min but we want something between min and max. We need to add min. That way, we generate a number between 0 + min and max - min + min, which resolve to min to max.

There is a little brain candy at the end of the code, did you notice it ? for. This is called a for-comprehension. We are going to go more into details about it in up-coming SKBs.

An other brain candy is the range. In Scala, you can describe a range of number in different ways:

0 to 2 will generate the numbers 0, 1, 2
0 until 2 which will generate the numbers 0, 1
0 until 10 by 3 which will generate the numbers 0, 3, 6, 9
 */
object ScalaRandom extends App {

  import scala.util.Random

  {
    // Create a random generator
    val rand: Random = new Random()

    println("Random Int:")
    println(rand.nextInt())

    // Try different values
    val maxRand = 5000
    println(s"Random Int lower than $maxRand:")
    println(rand.nextInt(maxRand))
  }

  // Utilities to generate "random" numbers
  object RandomUtils {
    // the seed
    val seed = 0
    // initialize the random generator
    private val rand = new Random(seed)

    //example- to find a random number b/w 4 and 10 we find the random no. b/w 0 and (10-4) ie 0 and 6 and add 4 to it to essentially shift the number to right ie b/w 4 and 10.
    def randomInt(min: Int, max: Int): Int = {
      rand.nextInt(max - min) + min
    }
  }

  val minRand = 10
  val maxRand = 20
  println(
    s"Random number between $minRand and $maxRand with seed ${RandomUtils.seed}:"
  )
  println(RandomUtils.randomInt(minRand, maxRand))

  val output = RandomUtils.randomInt(13, 200)
  assert(output == 41, output)

  for {
    min <- 0 to 1000
    max <- 0 to 10
    if min < max
  } {
    val randomNumber = RandomUtils.randomInt(min, max)
    assert(randomNumber >= min)
    assert(randomNumber < max)
  }

  println("Congratulations ! Keep moving forward.")


}
