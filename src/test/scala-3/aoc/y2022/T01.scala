package aoc.y2022

class T01 extends TestingUtil(D01):
  override def answer1: String = "24000"
  override def input1: String =
    """1000
      |2000
      |3000
      |
      |4000
      |
      |5000
      |6000
      |
      |7000
      |8000
      |9000
      |
      |10000
      |""".stripMargin

  override def answer2: String = "45000"
  override def input2: String = input1
end T01
