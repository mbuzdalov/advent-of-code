package aoc.y2022

import scala.collection.mutable.ArrayBuffer

import aoc.Runner

object D07 extends Runner:
  private case class Dir(name: String, parent: Dir):
    val children = new ArrayBuffer[Dir]()
    private var _fileSize, _totalSize: Long = 0
    def addFileSize(size: Long): Unit = _fileSize += size
    def totalSize: Long = _totalSize

    def computeTotalSize(): Long =
      _totalSize = _fileSize + children.view.map(_.computeTotalSize()).sum
      _totalSize

    def traverse(fun: Dir => Unit): Unit =
      for child <- children do child.traverse(fun)
      fun(this)

  private def construct(input: Seq[String]): Dir =
    val root = Dir("/", null)
    var curr = root
    input foreach:
      case s"$$ cd /" => curr = root
      case s"$$ cd .." => curr = curr.parent
      case s"$$ cd $child" => curr = curr.children.find(_.name == child).get
      case s"$$ ls" =>
      case s"dir $child" => curr.children += Dir(child, curr)
      case s"$size $child" => curr.addFileSize(size.toLong)
    root

  override def part1(input: Seq[String]): String =
    val root = construct(input)
    root.computeTotalSize()
    var sumGreater = 0L
    root.traverse(d => if d.totalSize <= 100000 then sumGreater += d.totalSize)
    sumGreater.toString

  override def part2(input: Seq[String]): String =
    val root = construct(input)
    val requiredMinimumSize = 30000000 - (70000000 - root.computeTotalSize())
    var answer = Long.MaxValue
    root.traverse(d => if d.totalSize >= requiredMinimumSize then answer = math.min(answer, d.totalSize))
    answer.toString
end D07
