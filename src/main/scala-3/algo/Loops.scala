package algo

object Loops:
  inline def repeat(times: Int)(inline fun: => Any): Unit =
    var i = 0
    while i < times do
      fun
      i += 1
  
  inline def foreach(from: Int, until: Int)(inline fun: Int => Any): Unit =
    var i = from
    while i < until do
      fun(i)
      i += 1

  inline def findFirst[T](from: Int, until: Int)(inline fun: Int => Option[T]): Option[T] =
    var i = from + 1
    var result = fun(from)
    while result.isEmpty && i < until do
      result = fun(i)
      i += 1
    result
  
  inline def any(from: Int, until: Int)(inline predicate: Int => Boolean): Boolean =
    var result = false
    var t = from
    while !result && t < until do
      result = predicate(t)
      t += 1
    result

  inline def all(from: Int, until: Int)(inline predicate: Int => Boolean): Boolean =
    var result = true
    var t = from
    while result && t < until do
      result = predicate(t)
      t += 1
    result

  inline def forBits(mask: Int)(inline fun: Int => Any): Unit =
    var m = mask
    var c = 0
    while m != 0 do
      c += Integer.numberOfTrailingZeros(m)
      fun(c)
      c += 1
      m = mask >>> c

  inline def count(from: Int, until: Int)(inline predicate: Int => Boolean): Int =
    var result = 0
    var t = from
    while t < until do
      if predicate(t) then result += 1
      t += 1
    result  
  
  inline def mapFold[T](from: Int, until: Int)(inline fold: (T, T) => T)(inline fun: Int => T): T =
    if from == until then throw IllegalArgumentException("Empty interval") else
      var result = fun(from)
      var curr = from
      while
        curr += 1
        curr < until
      do
        val v = fun(curr)
        result = fold(result, v)
      result
end Loops
