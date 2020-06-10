// これはシングルトンパターンではないです

/*
出力
1
1
2
1
2
 */

object SingletonTest extends App {
  val renbanA: Renban = Renban.getInstance()
  val renbanB: Renban = Renban.getInstance()

  println(renbanA.getNumber())
  println(renbanA.getNumber())
  println(renbanB.getNumber())
  println(renbanA.getNumber())
  println(renbanB.getNumber())
}

class Renban(id: Int) {
  val number: Int = id
  def getNumber(): Int = number
}

object Renban {
  var currentId = 0

  def getInstance(): Renban = {
    currentId += 1
    new Renban(currentId)
  }
}
