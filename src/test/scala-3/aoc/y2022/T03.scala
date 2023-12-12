package aoc.y2022

import aoc.TestingUtil

class T03 extends TestingUtil:
  private val input =
    """vJrwpWtwJgWrhcsFMMfFFhFp
      |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
      |PmmdzqPrVvPwwTWBwg
      |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
      |ttgJtRGJQctTZtZT
      |CrZsJsPPZsGzwwsLwLmpwMDw
      |""".toLines

  "The solution" should "be correct for sample 1" in:
    D03.part1(input) shouldEqual "157"

  it should "be correct for sample 2" in:
    D03.part2(input) shouldEqual "70"
end T03
