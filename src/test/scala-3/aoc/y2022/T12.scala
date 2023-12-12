package aoc.y2022

import aoc.TestingUtil

class T12 extends TestingUtil:
  private val input =
    """Sabqponm
      |abcryxxl
      |accszExk
      |acctuvwj
      |abdefghi
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D12.part1(input) shouldEqual "31"

  it should "be correct for sample 2" in:
    D12.part2(input) shouldEqual "29"

  it should "solve part 1" in:
    D12.part1(mainInput) shouldEqual "383"
  
  it should "solve part 2" in:
    D12.part2(mainInput) shouldEqual "377"
end T12
