object Main extends App {
  val gameListAggregate = new GameListAggregate()
  val iterator = gameListAggregate.createIterator()
  gameListAggregate.add(Game("みんなでゴルフ", 3700))
  gameListAggregate.add(Game("ファイナルファンタジア", 7300))
  gameListAggregate.add(Game("ロケットモンスター", 5400))
  gameListAggregate.add(Game("サイコロの達人", 5200))

  while ( !iterator.isDone() ) {
    val game = iterator.getItem()
    println(game.getName)
    iterator.next()
  }

}

trait Iterator {
  def first(): Unit     // 取り出し位置を最初の要素へ変える
  def next(): Unit      // 取り出し位置を次の要素へ変える
  def isDone(): Boolean    // 取り出し位置が最後を超えたか？
  def getItem(): Game   // 現在の取り出し位置から取り出す
}

trait Aggregate {
  def createIterator(): Iterator
}

class GameListIterator(val aggregate: GameListAggregate) extends Iterator {
  var current = 0

  override def first(): Unit = {
    current = 0
  }

  override def next(): Unit = {
    current += 1
  }

  override def isDone(): Boolean = if(current >= aggregate.getNumberOfStock()) true else false

  override def getItem(): Game = aggregate.getAt(current)
}

class GameListAggregate() extends Aggregate {
  var list: Array[Game] = Array()
  var numberOfStock: Int = 0

  override def createIterator(): Iterator = new GameListIterator(this)

  def add(game: Game) = {
    list = list :+ game
    numberOfStock += 1
  }

  def getAt(number: Int): Game = list(number)

  def getNumberOfStock(): Int = numberOfStock
}

case class Game(name: String, price: Int) {
  def getName: String = name
  def getPrice: Int = price
}