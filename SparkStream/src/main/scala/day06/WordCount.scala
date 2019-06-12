package day06

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable


object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val ssc = new StreamingContext(conf,Seconds(5))
    val sc = ssc.sparkContext

    val queue = mutable.Queue[RDD[Int]]()
    val rddDS = ssc.queueStream(queue,true)

    rddDS.reduce(_+_).print

    ssc.start

    for (elem <- 1 to 5){

      queue += sc.parallelize(1 to 100)
      Thread.sleep(2000)

    }
    ssc.awaitTermination()

  }

}
