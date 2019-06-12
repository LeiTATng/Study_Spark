package day07

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

object MySource{

  def apply(host: String, port: Int): MySource = new MySource(host, port)
  
}



class MySource(host:String ,port:Int) extends Receiver[String](StorageLevel.MEMORY_ONLY){
  override def onStart(): Unit = {
      new Thread("Socket"){

        override def run(): Unit = {

          receive()
        }
      }
      def receive()={
        val socket = new Socket(host,port)

        val reader = new BufferedReader(new InputStreamReader(socket.getInputStream,StandardCharsets.UTF_8))
        var line:String = null

        while (!isStopped() && line != null){

            line = reader.readLine()
            store(line)
        }
        reader.close()
        socket.close()
        restart("agin....")
      }


  }

  override def onStop(): Unit = {



  }
}
