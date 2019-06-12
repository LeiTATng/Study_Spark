package day06

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamWordCount {
  def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
      val ssc = new StreamingContext(conf,Seconds(5))

      val lines = ssc.socketTextStream("192.168.9.102",9999)
    
      val words = lines.flatMap(_.split("\\W+"))
      val wordOne = words.map((_,1)).reduceByKey(_+_)
      println("aaa")
      wordOne.print

      ssc.start()


      ssc.awaitTermination()

  }

}
