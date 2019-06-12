package test

import scala.collection.mutable


object YieldTest {
  def main(args: Array[String]): Unit = {

    var arr: mutable.Seq[Int] = Array(1,2,3,4,5).toBuffer
    //使用yield会产生一个新的数组缓冲
    var ints = for (elem <- arr)  elem * elem

    var i: Int = 10
    var i1: Unit = i = 11

    println(i1)
  }

}
