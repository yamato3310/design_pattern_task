
object Main extends App {
  val koujyou1 = new TvKoujyou
  val koujyou2 = new RadioKoujyou

  val array: List[Seihin] = List(koujyou1.create(), koujyou2.create(), koujyou1.create())
  array.foreach(_.seihinPrint())
}

trait Koujyou {
  def create(): Seihin = {
    val seihin = factoryMethod()
    seihin
  }

  def factoryMethod(): Seihin
}

trait Seihin {
  def seihinPrint(): Unit
}

class TvKoujyou extends Koujyou {
  override def factoryMethod(): Seihin = new Television

}

class RadioKoujyou extends Koujyou {
  override def factoryMethod(): Seihin = new Radio

}

class Television extends Seihin {
  private var tvSerialNumber: Int = Counter.getTvNumber()
  private var date: String = Date.today

  def numberring(): Unit = {
    this.tvSerialNumber = Counter.getTvNumber()
  }

  def setData(date: String) = {
    this.date = date
  }

  override def seihinPrint(): Unit = {
    println("このテレビに関する情報")
    println(s"製造番号: ${tvSerialNumber}")
    println(s"製造年月日: ${date}")
  }
}

class Radio extends Seihin {
  private var radioSerialNumber = Counter.getRadioNumber()

  def numberring(): Unit = {
    this.radioSerialNumber = Counter.getRadioNumber()
  }

  override def seihinPrint(): Unit = {
    println("このラジオに関する情報")
    println(s"製造番号: ${radioSerialNumber}")
  }
}

object Date {
  def today = "2020/01/20"
}

object Counter {
  private var tvNum = 100
  private var radioNum = 76

  def getTvNumber(): Int = {
    tvNum += 1
    tvNum
  }

  def getRadioNumber(): Int = {
    radioNum += 1
    radioNum
  }
}