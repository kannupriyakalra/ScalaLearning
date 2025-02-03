
/*
Question 1-

* Refactor the code of the ConnectorExecutor class to be more readable and flexible for future changes (expect hundreds of new connectors will be added).
* Also check if there are error cases that are not covered by the existing code and fix them: the expected behaviour is for the code to return the connector output or the string "ERROR" if
something goes wrong (it should never throw exception).
* Even though the error results will always be represented by a simple "ERROR" string feel free to model the different cases according to what you would do in production code.
* The problem itself is fairly simple, focus on clarity and cleanliness as if you were going to ship this to production and maintain it for years to come (remember, hundreds of connectors)
* Please DO NOT EDIT any code outside the ConnectorExecutor class, but feel free to add as many new "things" as needed
Note:
* Feel free to copy the code to your IDE and copy the final result back here.
* If you're running out of time but haven't finished, add notes/pseudo code telling us what you wanted to do
* If you're taking our Scala test then the code is going to run on scala 2.12, otherwise ignore this point
* You can write to stdout for debugging purposes
* To use the text-input file you should add lines that look like
"Slack","SendChannelMessage",["channel:#random","message:check out tray.io!"]
* Keep in mind that you need to click the "submit task" button for each task separately.

 */

package InterviewPractice

import InterviewPractice.ConnectorExecutor.Email

object TrayIOQ1 extends App {

  println(Solution.solution("Slack", "SendChannelMessage", Array("channel:#random", "message:check out tray.io!")))//o/p- SLACK CHANNEL MESSAGE OUTPUT
  println(Solution.solution("Slack", "SendPrivateMessage", Array("recipient:#john", "message:ok")))//o/p-SLACK PRIVATE MESSAGE OUTPUT
  println(Solution.solution("Email", "SendEmail", Array("to:#human", "cc:#manager", "bcc:#supervisor", "body:good work")))//o/p-ERROR

  /**
   * Good code tips:
   * 1. Use string constant, val slack = "Slack", to avoid spelling mistake, to reuse it at multiple places
   * 2. Don't use if else, usually
   * 3. We created a connector trait as part of refactoring to make code more readable and flexible for future changes like handling addition of hundreds of new incoming connectors.
   * Otherwise, there will be loads of if-else statements to be handled. To add a new connector, we can write another implementation of connector trait and this won't require changing the remaining code.
   */
}

/**
 * This is the object that needs to be refactored
 * Feel free to add new functions, classes or whatever else you need.
 */
object ConnectorExecutor {
//  val emailClient = new EmailClient
//  val slackClient = new SlackClient
//  // val otherClient = new OtherClient
//  // ...

  sealed trait Connector {
    type Op
    def connectorName: String
    def parse(name: String): Option[Op]

    def execute(operation: Op, params: Map[String, String]): Option[String]

    def parseOperationThenExecute(operation: String, params: Map[String, String]): Option[String] =
      parse(operation).flatMap(op => execute(op, params))
  }

  private case object Slack extends Connector {

    override type Op = Operation

    override def connectorName: String = this.toString
    override def parse(name: String): Option[Operation] = name match {
      case "SendChannelMessage" => Some(SendChannelMessage)
      case "SendPrivateMessage" => Some(SendPrivateMessage)
      case _ => None
    }

    override def execute(operation: Operation, params: Map[String, String]): Option[String] = operation.perform(params)

    private lazy val slackClient = new SlackClient // allocate memory only when used for the first time
    sealed trait Operation {
      def perform(params: Map[String, String]): Option[String]
    }

    private case object SendChannelMessage extends Operation {
      private val Channel = "channel"
      private val Message = "message"
      override def perform(params: Map[String, String]): Option[String] = for {
        channel <- params.get(Channel)
        message <- params.get(Message)
        conf <- Option(ConfigurationRepository.findConnectorConfiguration(connectorName))
      } yield slackClient.sendChannelMessage(channel, message, conf)
    }

    private case object SendPrivateMessage extends Operation {
      private val Recipient = "recipient"
      private val Message = "message"
      override def perform(params: Map[String, String]): Option[String] = for {
        channel <- params.get(Recipient)
        message <- params.get(Message)
        conf <- Option(ConfigurationRepository.findConnectorConfiguration(connectorName))
      } yield slackClient.sendPrivateMessage(channel, message, conf)
    }
  }

  private case object Email extends Connector {

    override type Op = Operation

    override def connectorName: String = this.toString

    override def parse(name: String): Option[Operation] = name match {
      case "SendEmail" => Some(SendEmail)
      case _ => None
    }
    override def execute(operation: Operation, params: Map[String, String]): Option[String] = operation.perform(params)

    private lazy val emailClient = new EmailClient
    sealed trait Operation {
      def perform(params: Map[String, String]): Option[String]
    }

    private case object SendEmail extends Operation {

      private val To = "to"
      private val Cc = "cc"
      private val Bcc = "bcc"
      private val Body = "body"
      override def perform(params: Map[String, String]): Option[String] = for {
        to <- params.get(To)
        cc <- params.get(Cc)
        bcc <- params.get(Bcc)
        body <- params.get(Body)
        conf <- Option(ConfigurationRepository.findConnectorConfiguration(connectorName))
      } yield emailClient.sendEmail(to, cc, bcc, body, conf)
    }
  }

  private object Connector {
    def apply(name: String): Option[Connector] = name match {
      case "Slack" => Some(Slack)
      case "Email" => Some(Email)
      case _ => None
    }
  }

  /**
   * Don't change the signature of this function
   */
  def executeConnector(connector: String, operation: String, params: Map[String, String]): String = {
    Connector(connector).flatMap(_.parseOperationThenExecute(operation, params)).getOrElse("ERROR")
  }

  //  def executeConnector(connector: String, operation: String, params: Map[String, String]): String = {
  //    if (connector == "Slack") {
  //      if (operation == "SendChannelMessage") {
  //        val channel = params("channel") //Map throws exception if key is not found, we need to fix this as in FP we don't throw exception, they are part of the type.
  //        val message = params("message") //handle the exception thrown by Map here as well.
  //        val conf = ConfigurationRepository.findConnectorConfiguration("Slack") //can return null if the configuration is missing
  //        slackClient.sendChannelMessage(channel, message, conf) //do not send any null parameter as it will then throw NullPointerException.
  //      } else if (operation == "SendPrivateMessage") {
  //        val recipient = params("recipient")
  //        val message = params("message")
  //        val conf = ConfigurationRepository.findConnectorConfiguration("Slack")
  //        slackClient.sendPrivateMessage(recipient, message, conf)
  //      } else {
  //        "ERROR"
  //      }
  //    } else if (connector == "Email") {
  //      if (operation == "SendEmail") {
  //        val to = params("to")
  //        val cc = params("cc")
  //        val bcc = params("bcc")
  //        val body = params("body")
  //        val conf = ConfigurationRepository.findConnectorConfiguration("Email")
  //        emailClient.sendEmail(to, cc, bcc, body, conf)
  //      } else {
  //        "ERROR"
  //      }
  //
  //    } else {
  //      "ERROR"
  //    }
  //  }

}



/*
     _____       _
    (_____)     (_)_
       _   ____  _| |_
      | | |  _ \| |  _)
     _| |_| | | | | |__
    (_____)_| |_|_|\___)

 */


object Solution {

  /**
   * Do not edit this function
   * It's required by the platform
   */
  def solution(connector: String, operation: String, params: Array[String]): String = {
    ConnectorExecutor.executeConnector(connector, operation, parseParams(params))
  }

  /**
   * Do not edit this function
   * You can assume this parsing is capable of handling correctly all the input
   */
  def parseParams(params: Array[String]): Map[String, String] = params.toList.map { param: String =>
    param.split(":").toList match {
      case key :: value :: Nil => (key, value) //converts List[String] to List[Tuple2[String, String]]
      case _ => throw new Exception("Don't worry, there are not test cases passing through here")
    }
  }.toMap //converts List[Tuple2[String, String]] to Map[String, String]

}

/*
      ______             ___
     / _____)           / __)
    | /      ___  ____ | |__
    | |     / _ \|  _ \|  __)
    | \____| |_| | | | | |
     \______)___/|_| |_|_|

 */

/**
 * Do not edit this class
 * Ignore its implementation and treat it as a black box
 * (check its signatures and docs)
 */
object ConfigurationRepository {

  /**
   * @return the configuration for the specified connector or null if the configuration is missing
   */
  def findConnectorConfiguration(name: String): String = {
    if (name == "Slack") {
      "SLACK CONF"
    } else {
      null
    }
  }


}

/*

          ______ _ _
         / _____) (_)            _
        | /     | |_  ____ ____ | |_   ___
        | |     | | |/ _  )  _ \|  _) /___)
        | \_____| | ( (/ /| | | | |__|___ |
         \______)_|_|\____)_| |_|\___|___/


 */

/**
 * Do not edit this class
 * Ignore its implementation and treat it as a black box
 * (check its signatures and docs)
 */
class EmailClient {

  /**
   * @throws NullPointerException if any parameter is null
   */
  def sendEmail(to: String, cc: String, bcc: String, body: String, configuration: String) = "EMAIL OUTPUT"
}

/**
 * Do not edit this class
 * Ignore its implementation and treat it as a black box
 * (check its signatures and docs)
 */
class SlackClient {
  /**
   * @throws NullPointerException if any parameter is null
   */
  def sendChannelMessage(channel: String, message: String, configuration: String) = "SLACK CHANNEL MESSAGE OUTPUT"

  /**
   * @throws NullPointerException if any parameter is null
   */
  def sendPrivateMessage(recipient: String, message: String, configuration: String) = "SLACK PRIVATE MESSAGE OUTPUT"
}

/*
As discussed on the call just now I hope the feedback I gave you via zoom will help with any future interviews. Please also find some notes which is basically what we discussed.
Could have addressed scalability problem in the first task slightly more
Some duplication of code, such as the generic "Op" in the first task
There could of been more detailed error modelling and handling, though correctness is good within the task
Could also solve scaling problem better, no error modelling, second solution could be more readable like how the first task was
 I think the second exercise is still a bit more nested than other candidates have produced, but correctness is 100% and first solution is very readable.
 */
