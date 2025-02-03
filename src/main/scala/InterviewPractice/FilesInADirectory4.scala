package InterviewPractice

import os._

object FilesInADirectory4 {
  def listFiles(directoryPath: String): Unit = {
    val path = Path(directoryPath)

    if (os.exists(path) && os.isDir(path)) {
      os.walk(path).filter(os.isFile).foreach { filePath =>
        println(filePath.toString())
      }
    } else {
      println("The specified path is not a valid directory.")
    }

  }

  def main(args: Array[String]): Unit = {
    val directoryPath = "/Users/kkl07/dev/ScalaLearning/src/main/scala"
    listFiles(directoryPath)
  }
}


//object FileLister {
//  def listFiles(directoryPath: String): Unit = {
//    val path = os.Path(directoryPath)
//
//    if (os.exists(path) && os.isDir(path)) {
//      os.walk(path).filter(os.isFile).foreach { filePath =>
//        println(os.rel / filePath)
//      }
//    } else {
//      println("The specified path is not a valid directory.")
//    }
//  }





