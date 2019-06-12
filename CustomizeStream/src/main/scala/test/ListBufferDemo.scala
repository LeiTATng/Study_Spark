package test

object ListBufferDemo {
  def main(args: Array[String]): Unit = {


    val ints = List(2,3,4,5)

    val ints1 = 1 +: ints
    println(ints1)

    val ints2 = 1 :: ints
    println(ints2)
    println(" ==  ")
    val ints3 = ints1 ++ ints2
    println(ints3)

    val a = ints1 ::: ints2
    println(a)
    Array
  }

}
