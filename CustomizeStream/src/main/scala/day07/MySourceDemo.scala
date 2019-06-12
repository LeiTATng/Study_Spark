package day07

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object MySourceDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(5))
    val lines = ssc.receiverStream[String](MySource("192.168.9.102", 9999))

    val words = lines.flatMap(_.split("\\W+"))

    val count = words.map((_, 1)).reduceByKey(_+_)


    count.print

    ssc.start()
    ssc.awaitTermination()
    ssc.stop(false)

  }

}
