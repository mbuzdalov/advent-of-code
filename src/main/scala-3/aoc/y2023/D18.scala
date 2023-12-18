package aoc.y2023

import aoc.Runner

object D18 extends Runner:
  private case class Vector(row: Int, col: Int)

  private def parse1(line: String): Vector = line match
    case s"$dir $lenS (#$code)" =>
      val len = lenS.toInt
      dir match
        case "D" => Vector(+len, 0)
        case "U" => Vector(-len, 0)
        case "R" => Vector(0, +len)
        case "L" => Vector(0, -len)

  private def parse2(line: String): Vector = line match
    case s"$dir $lenS (#$code)" =>
      val len = Integer.parseInt(code.dropRight(1), 16)
      code.last match
        case '1' => Vector(+len, 0)
        case '3' => Vector(-len, 0)
        case '0' => Vector(0, +len)
        case '2' => Vector(0, -len)

  private def solve(input: IndexedSeq[Vector]): Long =
    var row, col, sum1, sum2 = 0L
    for Vector(dr, dc) <- input do
      sum1 += math.abs(dr) + math.abs(dc)
      sum2 += row * (col + dc) - col * (row + dr)
      row += dr
      col += dc
    1 + (sum1 + math.abs(sum2)) / 2

  override def part1(input: IndexedSeq[String]): String = solve(input.map(parse1)).toString
  override def part2(input: IndexedSeq[String]): String = solve(input.map(parse2)).toString
end D18
