import org.apache.spark.sql.SparkSession

object HiveDemo {

  def main(args: Array[String]): Unit = {
  System.setProperty("HADOOP_USER_NAME","atguigu")
    val spark = SparkSession
          .builder()
          .master("local[*]")
          .appName("Test")
          .enableHiveSupport()
          .config("spark.sql.warehouse.dir", "hdfs://hadoop102:9000/user/hive/warehouse")
          .getOrCreate()
    import  spark.implicits._
    import spark.sql

    sql("show databases").show
    sql("create database HiveDemo").show

    spark.stop()
  }

}
