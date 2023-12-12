package aoc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait TestingUtil extends AnyFlatSpec with Matchers:
  extension (input: String) def toLines: IndexedSeq[String] = input.stripMargin.split('\n').toIndexedSeq

  private lazy val input = TestReader.read(getClass)

  def main1(runner: Runner, answer: String): Unit =
    "Puzzle part 1" should "pass" in:
      runner.part1(input) shouldEqual answer

  def main2(runner: Runner, answer: String): Unit =
    "Puzzle part 2" should "pass" in:
      runner.part2(input) shouldEqual answer
end TestingUtil
