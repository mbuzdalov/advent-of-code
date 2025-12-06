package aoc

import java.io._
import java.util._

import scala.annotation.tailrec
import scala.util.Using

object TestReader:
  @tailrec
  private def readUntilNull(br: BufferedReader, dest: String => Unit): Unit =
    val line = br.readLine()
    if line != null then
      dest(line)
      readUntilNull(br, dest)

  private def readFromFile(br: BufferedReader): IndexedSeq[String] =
    val builder = IndexedSeq.newBuilder[String]
    readUntilNull(br, builder += _)
    builder.result()

  def read(clazz: Class[_], name: String): IndexedSeq[String] =
    val tokens = StringTokenizer(clazz.getName, ".$")
    assert(tokens.nextToken() == "aoc")
    val year, day = tokens.nextToken()
    assert(year(0) == 'y')
    val file = File(s"inputs/${year.tail}/${day.tail}/$name")
    if file.exists() then
      Using.resource(BufferedReader(FileReader(file)))(readFromFile)
    else
      sys.error("The input file does not exist")
end TestReader
