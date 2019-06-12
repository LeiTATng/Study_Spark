package test

import scala.util.Random

object ContextTest {
  def main(args: Array[String]): Unit = {
    val random = new Random()
    val i = math.abs(random.nextLong) % 10.0
    val a = 100 % 2.0
    println(a)
    println(i)
  }
}
