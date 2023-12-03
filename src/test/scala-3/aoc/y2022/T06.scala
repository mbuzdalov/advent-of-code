package aoc.y2022

import aoc.TestingUtil

class T06 extends TestingUtil(D06):
  override def answer1: String = "7 5 6 10 11"
  override def input1: String =
    """mjqjpqmgbljsphdztnvjfqwrcgsmlb
      |bvwbjplbgvbhsrlpgdmjqwftvncz
      |nppdvjthqldpwncqszvftbrmjlhg
      |nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
      |zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
      |""".stripMargin

  override def answer2: String = "19 23 23 29 26"
  override def input2: String = input1
end T06
