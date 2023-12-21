package aoc.y2023

import aoc.TestingUtil

class T21 extends TestingUtil:
  private val input =
    """...........
      |.....###.#.
      |.###.##..#.
      |..#.#...#..
      |....#.#....
      |.##..S####.
      |.##..#...#.
      |.......##..
      |.##.#.####.
      |.##..##.##.
      |...........
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D21.part1(input, 6) shouldEqual 16

  it should "be correct for sample 2/1" in:
    D21.part2naive(input, 6) shouldEqual 16

  it should "be correct for sample 2/2" in:
    D21.part2naive(input, 10) shouldEqual 50

  it should "be correct for sample 2/3" in:
    D21.part2naive(input, 50) shouldEqual 1594

  it should "be correct for sample 2/4" in:
    D21.part2naive(input, 100) shouldEqual 6536

//  it should "be correct for sample 2/5" in:
//    D21.part2naive(input, 500) shouldEqual 167004
//
//  it should "be correct for sample 2/6" in:
//    D21.part2naive(input, 1000) shouldEqual 668697
//
//  it should "be correct for sample 2/7" in:
//    D21.part2naive(input, 5000) shouldEqual 16733044

  it should "solve part 1" in:
    D21.part1(mainInput) shouldEqual "3814"

  it should "solve part 2" in:
    D21.part2(mainInput) shouldEqual "632257949158206"
end T21
