package aoc.y2023

import scala.annotation.tailrec

import aoc.Runner

object D07 extends Runner:
  private class HandOrdering(chars: String) extends Ordering[(String, Int, Int)]:
    override def compare(lhs: (String, Int, Int), rhs: (String, Int, Int)): Int =
      if lhs._2 != rhs._2 then lhs._2 - rhs._2 else
        @tailrec
        def compare(idx: Int): Int = if idx == lhs._1.length then 0 else
          val cc = chars.indexOf(rhs._1(idx)) - chars.indexOf(lhs._1(idx))
          if cc != 0 then cc else compare(idx + 1)
        compare(0)

  private def toCounts(hand: String): String = hand.distinct.map(c => (hand.count(c == _) + '0').toChar).sorted
  private def classify(counts: String): Int = counts match
    case "5" => 6
    case "14" => 5
    case "23" => 4
    case "113" => 3
    case "122" => 2
    case "1112" => 1
    case _ => 0

  private def handType1(hand: String): Int = classify(toCounts(hand))

  private def handType2(hand: String): Int =
    val njHand = hand.filter(_ != 'J')
    if njHand.isEmpty then classify("5") else
      val njCounts = toCounts(njHand)
      classify(njCounts.init + (njCounts.last + hand.length - njHand.length).toChar)

  private def classify(handType: String => Int)(line: String): (String, Int, Int) = line match
    case s"$hand $bid" => (hand, handType(hand), bid.toInt)

  override def part1(input: IndexedSeq[String]): String =
    val classified = input.map(classify(handType1)).sorted(using HandOrdering("AKQJT98765432"))
    classified.zipWithIndex.map(p => (p._2 + 1) * p._1._3).sum.toString

  override def part2(input: IndexedSeq[String]): String =
    val classified = input.map(classify(handType2)).sorted(using HandOrdering("AKQT98765432J"))
    classified.zipWithIndex.map(p => (p._2 + 1) * p._1._3).sum.toString
end D07
