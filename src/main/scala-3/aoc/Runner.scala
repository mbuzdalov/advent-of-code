package aoc

abstract class Runner:
  def part1(input: IndexedSeq[String]): String
  def part2(input: IndexedSeq[String]): String


  def main(args: Array[String]): Unit =
    val input = TestReader.read(getClass)

    try println(part1(input)) catch
      case _: NotImplementedError =>
        println("Part 1 not yet implemented")
    
    try println(part2(input)) catch
      case _: NotImplementedError =>
        println("Part 2 not yet implemented")
end Runner
