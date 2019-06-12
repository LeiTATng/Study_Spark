import org.apache.spark.{SparkConf, SparkContext}

object FilterDemo {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("SortBy")
    val sc = new SparkContext(conf)
    val names = sc.parallelize(Array("xiaoli","laoli","laowang","xiaocang"))



  }

}
