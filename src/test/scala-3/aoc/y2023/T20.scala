package aoc.y2023

import aoc.TestingUtil

class T20 extends TestingUtil:
  private val input =
    """broadcaster -> a, b, c
      |%a -> b
      |%b -> c
      |%c -> inv
      |&inv -> a
      |""".toLines

  private val input2 = """broadcaster -> a
                         |%a -> inv, con
                         |&inv -> b
                         |%b -> con
                         |&con -> output
                         |""".toLines

  "The solution" should "be correct for sample 1/1" in:
    D20.part1(input) shouldEqual "32000000"

  it should "be correct for sample 1/2" in :
    D20.part1(input2) shouldEqual "11687500"

  it should "solve part 1" in:
    D20.part1(mainInput) shouldEqual "818649769"

  it should "solve part 2" in:
    D20.part2(mainInput) shouldEqual "246313604784977"
end T20
