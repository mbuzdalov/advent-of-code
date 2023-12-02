package aoc.y2023

import java.util.StringTokenizer

import aoc.Runner

object D02 extends Runner:
  case class Sample(red: Int, green: Int, blue: Int):
    def dominated(other: Sample): Boolean =
      red <= other.red && green <= other.green && blue <= other.blue
    def max(other: Sample): Sample =
      Sample(math.max(red, other.red), math.max(green, other.green), math.max(blue, other.blue))
    def power: Int = red * green * blue
  case class Game(id: Int, samples: Seq[Sample])

  private def parseSample(text: String): Sample =
    val tok = new StringTokenizer(text, " ,")
    var red, green, blue = 0
    while tok.hasMoreTokens do
      val num = tok.nextToken().toInt
      tok.nextToken() match
        case "blue" => blue += num
        case "red" => red += num
        case "green" => green += num
    Sample(red, green, blue)

  private def parse(line: String): Game =
    val tok1 = new StringTokenizer(line, ":;")
    val header = new StringTokenizer(tok1.nextToken(), " ")
    header.nextToken()
    val id = header.nextToken().toInt
    val samples = IndexedSeq.fill(tok1.countTokens())(parseSample(tok1.nextToken()))
    Game(id, samples)

  override def part1(input: Seq[String]): String =
    val limit = Sample(12, 13, 14)
    input.map(parse).filter(_.samples.forall(_.dominated(limit))).map(_.id).sum.toString

  override def part2(input: Seq[String]): String =
    input.map(parse).map(_.samples.reduce(_ max _).power).sum.toString
end D02