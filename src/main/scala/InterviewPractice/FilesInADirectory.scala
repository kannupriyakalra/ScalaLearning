package InterviewPractice

// Write a function that would print all the file names in a directory and all of the sub directories of that directory.

//object FilesInADirectory extends App{
//
//  //It returns list of file names in a directory and all its sub directories.
//  def filesInADirectory(directoryPath: String): List[String] = {
//
//
//  }
//
//  filesInADirectory("/Users/kkl07/dev/ScalaLearning/src/main/scala")
//}

/*

Pseudo code-

 1. get list of files present in that directory.
 2. get list of sub directories.
 3. for each sub directory, get list of files from that sub directory. --recursive step
 4. combine all the list of files from each sub directory into one list.
 5. return this list + list from step 1.


https://www.baeldung.com/scala/list-files
https://docs.scala-lang.org/toolkit/os-intro.html
 */


