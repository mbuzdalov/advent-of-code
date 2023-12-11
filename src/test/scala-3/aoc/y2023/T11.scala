package aoc.y2023

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class T11 extends AnyFlatSpec with Matchers:
  private val input: IndexedSeq[String] =
    """...#......
      |.......#..
      |#.........
      |..........
      |......#...
      |.#........
      |.........#
      |..........
      |.......#..
      |#...#.....
      |""".stripMargin.split('\n').toIndexedSeq

  "The solution" should "work with expansion rate 2" in:
    D11.solve(input, 2) shouldEqual "374"
  it should "work with expansion rate 10" in:
    D11.solve(input, 10) shouldEqual "1030"
  it should "work with expansion rate 100" in:
    D11.solve(input, 100) shouldEqual "8410"
end T11
