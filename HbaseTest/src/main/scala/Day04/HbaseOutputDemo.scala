package Day04

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object HbaseOutputDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Practice").setMaster("local[*]")

    val sc = new SparkContext(conf)

    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set("hbase.zookeeper.quorum", "192.168.9.102,192.168.9.103,192.168.9.104")

    hbaseConf.set(TableOutputFormat.OUTPUT_TABLE,"student")

    val job = Job.getInstance(hbaseConf)
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    job.setOutputValueClass(classOf[Put])

    val initialRDD: RDD[(String, String, String)] = sc.parallelize(List(("100", "apple", "11"), ("200", "banana", "12"), ("300", "pear", "13")))

    val hbaseRDD = initialRDD.map(x => {

      val put = new Put(Bytes.toBytes(x._1))
      put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(x._2))
      put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("weight"), Bytes.toBytes(x._3))
      (new ImmutableBytesWritable(), put)

    })
    hbaseRDD.saveAsNewAPIHadoopDataset(job.getConfiguration)


  }
}
