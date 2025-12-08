package algo

import scala.annotation.tailrec

class DisjointSet(n: Int):
  private val p = Array.tabulate(n)(i => i)
  private val r = Array.fill(n)(0)
  private var nComp = n
  
  @tailrec private def fetch(v: Int): Int = if v == p(v) then v else fetch(p(v))
  @tailrec private def update(v: Int, np: Int): Int = if v == p(v) then np else
    val op = p(v)
    p(v) = np
    update(op, np)
  
  def nComponents: Int = nComp
  def get(v: Int): Int = update(v, fetch(v))

  def unite(a: Int, b: Int): Unit =
    val aa = get(a)
    val bb = get(b)
    if aa != bb then
      nComp -= 1
      if r(aa) == r(bb) then r(aa) += 1
      if r(aa) < r(bb) then p(aa) = bb
      else p(bb) = aa
    