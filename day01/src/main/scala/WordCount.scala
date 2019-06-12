import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount")
    //先船舰SparkCOntext对象
    val sc = new SparkContext(conf)
    //通过sc来得到Rdd
    val linesRdd: RDD[String] = sc.textFile("hdfs://192.168.9.102:9000/input")
    //对Rdd做各种转化
    val wordRdd = linesRdd.flatMap(_.split("\\W+"))
    val wordCountRD = wordRdd.map((_,1)).reduceByKey(_+_)

    //对RDD做行动
      val result = wordCountRD.collect()
//    println(result.mkString(","))
    //关闭sc
    sc.stop()
  }

}
