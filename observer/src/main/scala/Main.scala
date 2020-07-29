object Main extends App {
  val cuckooClock = new CuckooClock()
  val subject = new Subject(cuckooClock)
  subject.notification()
}

trait TimeAdmin {
  def update(subject: Subject): Unit
}

class CuckooClock extends TimeAdmin {
  def update(subject: Subject): Unit = {
    val hour = subject.getHour()
    (1 to hour).foreach(_ => println("ぽっぽー！"))
  }
}

class SubtractionDisplay extends TimeAdmin {
  private var countDownValue = 10000

  override def update(subject: Subject): Unit = {
    countDownValue = countDownValue - 1
    println(countDownValue)
  }
}

class Subject(val observer: TimeAdmin) {
  def getHour(): Int = 10
  def notification(): Unit = observer.update(this)
}