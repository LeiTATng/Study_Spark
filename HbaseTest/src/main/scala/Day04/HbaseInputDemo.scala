package Day04

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object HbaseInputDemo {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Parctice").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104")
    hbaseConf.set(TableInputFormat.INPUT_TABLE,"student")

    val rdd = sc.newAPIHadoopRDD(

      hbaseConf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )
    val rdd2: RDD[String] = rdd.map {
      case (_, result) => Bytes.toString(result.getRow)
    }
    rdd2.collect.foreach(println)
    sc.stop()

  }

}
