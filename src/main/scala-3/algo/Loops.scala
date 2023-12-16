package algo

object Loops:
  inline def foreach(from: Int, until: Int)(inline fun: Int => Any): Unit =
    var i = from
    while i < until do
      fun(i)
      i += 1

  inline def forBits(mask: Int)(inline fun: Int => Any): Unit =
    var m = mask
    var c = 0
    while m != 0 do
      c += Integer.numberOfTrailingZeros(m)
      fun(c)
      c += 1
      m = mask >>> c

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
