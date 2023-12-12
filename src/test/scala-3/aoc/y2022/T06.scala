package aoc.y2022

import aoc.TestingUtil

class T06 extends TestingUtil:
  private val input =
    """mjqjpqmgbljsphdztnvjfqwrcgsmlb
      |bvwbjplbgvbhsrlpgdmjqwftvncz
      |nppdvjthqldpwncqszvftbrmjlhg
      |nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
      |zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
      |""".toLines

  "The solution" should "be correct for sample group 1" in:
    D06.part1(input) shouldEqual "7 5 6 10 11"

  it should "be correct for sample group 2" in:
    D06.part2(input) shouldEqual "19 23 23 29 26"

  it should "solve part 1" in:
    D06.part1(mainInput) shouldEqual "1262"
    
  it should "solve part 2" in:
    D06.part2(mainInput) shouldEqual "3444"
end T06
