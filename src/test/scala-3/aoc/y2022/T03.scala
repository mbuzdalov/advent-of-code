package aoc.y2022

import aoc.TestingUtil

class T03 extends TestingUtil(D03):
  override def answer1: String = "157"
  override def input1: String =
    """vJrwpWtwJgWrhcsFMMfFFhFp
      |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
      |PmmdzqPrVvPwwTWBwg
      |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
      |ttgJtRGJQctTZtZT
      |CrZsJsPPZsGzwwsLwLmpwMDw
      |""".stripMargin

  override def answer2: String = "70"
  override def input2: String = input1
end T03
