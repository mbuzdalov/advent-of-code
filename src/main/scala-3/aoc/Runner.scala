package aoc

import java.io.{BufferedReader, File, FileReader}
import java.util.StringTokenizer

import scala.annotation.tailrec
import scala.util.Using

abstract class Runner:
  def part1(input: IndexedSeq[String]): String = throw UnsupportedOperationException("Solution not yet implemented")
  def part2(input: IndexedSeq[String]): String = throw UnsupportedOperationException("Solution not yet implemented")

  @tailrec
  private def readUntilNull(br: BufferedReader, dest: String => Unit): Unit =
    val line = br.readLine()
    if line != null then
      dest(line)
      readUntilNull(br, dest)

  private def runWithFile(br: BufferedReader): Unit =
    val builder = IndexedSeq.newBuilder[String]
    readUntilNull(br, builder += _)
    val input = builder.result()

    try println(part1(input)) catch
      case _: UnsupportedOperationException =>
        println("Part 1 not yet implemented")

    try println(part2(input)) catch
      case _: UnsupportedOperationException =>
        println("Part 2 not yet implemented")

  def main(args: Array[String]): Unit =
    val myClass = getClass.getName
    val tokens = StringTokenizer(myClass, ".$")
    assert(tokens.nextToken() == "aoc")
    val year, day = tokens.nextToken()
    assert(year(0) == 'y')
    val file = File(s"inputs/${year.substring(1)}/$day.in")
    if file.exists() then
      Using.resource(BufferedReader(FileReader(file)))(runWithFile)
    else
      sys.error("The input file does not exist")
  end main
end Runner
