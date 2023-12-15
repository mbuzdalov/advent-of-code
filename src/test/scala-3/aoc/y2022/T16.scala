package aoc.y2022

import aoc.TestingUtil

class T16 extends TestingUtil:
  private val input =
    """Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
      |Valve BB has flow rate=13; tunnels lead to valves CC, AA
      |Valve CC has flow rate=2; tunnels lead to valves DD, BB
      |Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
      |Valve EE has flow rate=3; tunnels lead to valves FF, DD
      |Valve FF has flow rate=0; tunnels lead to valves EE, GG
      |Valve GG has flow rate=0; tunnels lead to valves FF, HH
      |Valve HH has flow rate=22; tunnel leads to valve GG
      |Valve II has flow rate=0; tunnels lead to valves AA, JJ
      |Valve JJ has flow rate=21; tunnel leads to valve II
      |""".stripMargin.toLines

  "The solution" should "be correct for sample 1" in:
    D16.part1(input) shouldEqual "1651"

  it should "be correct for sample 2" in:
    D16.part2(input) shouldEqual "1707"

  it should "solve part 1" in:
    D16.part1(mainInput) shouldEqual "1595"

  it should "solve part 2" in:
    D16.part2(mainInput) shouldEqual "2189"
end T16
