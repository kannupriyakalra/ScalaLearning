package TourOfScala

/*
https://tourofscala.com/scala/flatmap

Scala flatMap
flatMap  is map followed by flatten. Try to replace the flatMap in the code above by map and add flatten after it.
 case usage here is called pattern matching
 */
object ScalaflatMap extends App {

  // Learn more about Scala on https://leobenkel.com

  val opt: Option[Int] = Some(1)

  val outOpt: Option[Int] = opt.flatMap {
    case n if n > 3 => Some(4)
    case 1 => Some(3)
    case _ => None
  }

  val outOpt1: Option[Int] = opt.flatMap { x =>
    x match {
      case n if n > 3 => Some(4)
      case 1 => Some(3)
      case _ => None
    }
  }

  val outOpt2: Option[Int] = opt.map {
    case n if n > 3 => Some(4)
    case 1 => Some(3)
    case _ => None
  }.flatten


  assert(outOpt.contains(3))
  //  assert(outOpt == Some(3))

  val l: List[Int] = 3 :: 3 :: Nil

  val outList: List[Int] = l.flatMap {
    case n if n == 2 => List(1, 2, 3)
    case n if n == 3 => n :: n :: Nil
    case n if n < 5 => n :: Nil
    case _ => Nil
  }

  assert(outList.length == 4)


  println("Congratulations ! Go beyond.")


}
