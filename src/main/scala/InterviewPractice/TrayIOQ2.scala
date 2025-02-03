///*
//
//Question 2-
//
//* Your job here is to complete the implementation of the getTotalScore function returning either the sum of the user's scores (convert the number to a string using toString) or the string "ERROR".
//* The problem itself is fairly simple, focus on clarity and cleanliness as if you were going to ship this to production and maintain it for years to come.
//* Some boilerplate code is offered, assume you can't change and must use this existing code.
//* If a user or user score is missing the output should be an "ERROR".
//
//Note:
//* Feel free to copy the code to your IDE and copy the final result back here.
//* If you're running out of time but haven't finished, add notes/pseudo code telling us what you wanted to do.
//* If you're taking our Scala test then the code is going to run on scala 2.12, otherwise ignore this point.
//* You can write to stdout for debugging purposes.
//* To use the text-input file you should add lines that look like [1,3].
//
// */
//
//package InterviewPractice
//
//object TrayIOQ2 extends App {
//  println(Solutionn.solution(Array(1, 3)))
//}
//
//import scala.util.{Failure, Success, Try}
//
//object Solutionn {
//
//
//  def addTwoUserScores(firstUser: Try[Int], secondUser: Try[Int]): Try[Int] = {
//    for {
//      firstScore <- firstUser
//      secondScore <- secondUser
//    } yield firstScore + secondScore
//  }
//
//  /**
//   * This is the function that needs to be implemented
//   * Don't change its signature
//   */
//  def getTotalScore(usersIds: Seq[Int]): String = {
//    UserRepository
//      .getUsers(usersIds)
//      .flatMap((users: Seq[User]) =>
//        users.map(user => ScoreRepository.getUserScore(user))
//          .foldLeft(Success(0): Try[Int])((total: Try[Int], eachScore: Try[Int]) => addTwoUserScores(total, eachScore)))
//      .fold(_ => "ERROR", _.toString)
//  }
//
//
//  // Alternate way -2
//
//  def getTotalScore(usersIds: Seq[Int]): String = {
//    val usersT: Try[Seq[User]] = UserRepository.getUsers(usersIds)
//
//    usersT match {
//      case Failure(_) => "ERROR"
//      case Success(users: Seq[User]) =>
//        users.map(user => ScoreRepository.getUserScore(user))
//          .foldLeft(Success(0): Try[Int])((total, eachScore) => addTwoUserScores(total, eachScore))
//          .fold(_ => "ERROR", _.toString)
//    }
//  }
//
//
//
//  def getTotalScore(usersIds: Seq[Int]): String = {
//
//    def addTwoUserScores(firstUser: Try[Int], secondUser: Try[Int]): Try[Int] = {
//      for {
//        firstScore <- firstUser
//        secondScore <- secondUser
//      } yield firstScore + secondScore
//    }
//
//    val usersT: Try[Seq[User]] = UserRepository.getUsers(usersIds)
//    val scoresT: Try[Seq[Try[Int]]] = usersT.map((seqOfUser: Seq[User]) => seqOfUser.map(user => ScoreRepository.getUserScore(user)))
//    val resultT: Try[Int] = scoresT.flatMap((seqOfScore: Seq[Try[Int]]) => seqOfScore.foldLeft(Success(0): Try[Int])((total, eachScore) => addTwoUserScores(total, eachScore))) //for flatMap, T: Seq[Try[Int]], Try[U]: Try[Int]
//    resultT match {
//      case Failure(exception) => "ERROR"
//      case Success(value) => value.toString
//    }
//
//
//  }
//
//  /*
//       _____       _
//      (_____)     (_)_
//         _   ____  _| |_
//        | | |  _ \| |  _)
//       _| |_| | | | | |__
//      (_____)_| |_|_|\___)
//
//   */
//
//
//  /**
//   * Do not edit this function
//   * It's required by the platform
//   * You can assume it is capable of handling correctly all the input
//   */
//  def solution(input: Array[Int]): String = {
//    getTotalScore(input.map(_.toInt))
//  }
//
//}
//
///*
//        _
//       | |
//        \ \   ____ ___   ____ ____  ___
//         \ \ / ___) _ \ / ___) _  )/___)
//     _____) | (__| |_| | |  ( (/ /|___ |
//    (______/ \____)___/|_|   \____|___/
//
// */
//
///**
// * Do not edit this class
// * Ignore its implementation and treat it as a black box
// * (check its signatures and docs)
// */
//object ScoreRepository {
//  val scores: Map[User, Try[Int]] = Map(
//    User(1) -> Success(23),
//    User(2) -> Failure(new Exception("Something went wrong")),
//    User(3) -> Success(19)
//  )
//
//  def getUserScore(user: User): Try[Int] = {
//    Try(scores.apply(user)).flatten
//  }
//
//}
//
///*
//     _     _
//    | |   | |
//    | |   | | ___  ____  ____ ___
//    | |   | |/___)/ _  )/ ___)___)
//    | |___| |___ ( (/ /| |  |___ |
//     \______(___/ \____)_|  (___/
//
// */
//
///**
// * Do not edit this class
// * Ignore its implementation and treat it as a black box
// * (check its signatures and docs)
// */
//final case class User(id: Int)
//
///**
// * Do not edit this class
// * Ignore its implementation and treat it as a black box
// * (check its signatures and docs)
// */
//object UserRepository {
//
//  val users: Map[Int, User] = Map(
//    1 -> User(1),
//    2 -> User(2),
//    3 -> User(3)
//  )
//
//  def getUsers(ids: Seq[Int]): Try[Seq[User]] = {
//    Try(ids.map(id => users.apply(id)))
//  }
//
//}
//
///*
//doubts-
//1. on line 94     getTotalScore(input.map(_.toInt)) , input is of type Array[Int], and in   def getTotalScore(usersIds: Seq[Int]), usersIds: Seq[Int], as Array, List, Vector are subtype of Seq so we
//can send them when Seq is expected ie subtype can be sent when supertype is expected, reverse isn't true.
//2. line 156 , How is Map called like this "users.apply(id))" -- as Map is a type that has apply method available, so this is how you extract value by giving key
//3.  User(1) --means a object of class User is constructed.
//4. by-name parameter is function zero,
//    Try(ids.map(id => users.apply(id))) is     Try.apply(ids.map(id => users.apply(id)))
//    ids.map(id => users.apply(id)) is r: => T which will be executed inside Success(r)
//
//
// */
//
