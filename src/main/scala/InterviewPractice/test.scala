package InterviewPractice

import java.nio.file.{Files, Path, Paths, FileVisitOption, FileVisitResult}
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import scala.collection.JavaConverters._

object FileLister {
  def listFiles(directoryPath: String): Unit = {
    val startPath = Paths.get(directoryPath)

    Files.walkFileTree(startPath, Set(FileVisitOption.FOLLOW_LINKS).asJava, Int.MaxValue, new SimpleFileVisitor[Path] {
      override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
        if (Files.isRegularFile(file)) {
          println(file.toAbsolutePath.toString)
        }
        FileVisitResult.CONTINUE
      }

      override def visitFileFailed(file: Path, exc: java.io.IOException): FileVisitResult = {
        // Handle file visit failure if needed
        FileVisitResult.CONTINUE
      }

      override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = {
        if (dir != startPath) {
          println(dir.toAbsolutePath.toString + " (directory)")
        }
        FileVisitResult.CONTINUE
      }
    })
  }

  def main(args: Array[String]): Unit = {
    val directoryPath = "/Users/kkl07/dev/ScalaLearning/src/main/scala"
    listFiles(directoryPath)
  }
}



