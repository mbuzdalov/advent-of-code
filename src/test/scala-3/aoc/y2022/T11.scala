package aoc.y2022

import aoc.TestingUtil

class T11 extends TestingUtil:
  private val input =
    """Monkey 0:
      |  Starting items: 79, 98
      |  Operation: new = old * 19
      |  Test: divisible by 23
      |    If true: throw to monkey 2
      |    If false: throw to monkey 3
      |
      |Monkey 1:
      |  Starting items: 54, 65, 75, 74
      |  Operation: new = old + 6
      |  Test: divisible by 19
      |    If true: throw to monkey 2
      |    If false: throw to monkey 0
      |
      |Monkey 2:
      |  Starting items: 79, 60, 97
      |  Operation: new = old * old
      |  Test: divisible by 13
      |    If true: throw to monkey 1
      |    If false: throw to monkey 3
      |
      |Monkey 3:
      |  Starting items: 74
      |  Operation: new = old + 3
      |  Test: divisible by 17
      |    If true: throw to monkey 0
      |    If false: throw to monkey 1
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D11.part1(input) shouldEqual "10605"

  it should "be correct for sample 2" in:
    D11.part2(input) shouldEqual "2713310158"

  it should "solve part 1" in:
    D11.part1(mainInput) shouldEqual "113220"
    
  it should "solve part 2" in:
    D11.part2(mainInput) shouldEqual "30599555965"
end T11
