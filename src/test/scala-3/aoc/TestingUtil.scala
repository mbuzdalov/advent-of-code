package aoc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait TestingUtil extends AnyFlatSpec with Matchers:
  extension (input: String) def toLines: IndexedSeq[String] = input.stripMargin.split('\n').toIndexedSeq
end TestingUtil
