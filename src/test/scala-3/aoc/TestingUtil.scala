package aoc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait TestingUtil(runner: Runner) extends AnyFlatSpec with Matchers:
  extension (input: String) def toLines: Seq[String] = input.split('\n').toIndexedSeq

  def input1: String
  def answer1: String
  def input2: String
  def answer2: String

  "Part 1 sample" should "be computed correctly" in:
    runner.part1(input1.toLines) shouldEqual answer1

  "Part 2 sample" should "be computed correctly" in:
    runner.part2(input2.toLines) shouldEqual answer2

end TestingUtil
