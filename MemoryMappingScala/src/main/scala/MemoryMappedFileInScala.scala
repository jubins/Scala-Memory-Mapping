import java.io.RandomAccessFile
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

import scala.language.postfixOps


object MemoryMappedFileInScala {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val startTime = System.currentTimeMillis
    val memoryMappedFile = new RandomAccessFile("largeFile.txt", "rw")

    //Mapping a file into memory
    //10 MB
    val count = 10485760
    var out: MappedByteBuffer = memoryMappedFile.getChannel.map(FileChannel.MapMode.READ_WRITE, 0, count)
    val src = new Array[Byte](count)

    //Writing into Memory Mapped File
    while (out.capacity() > count) {
      out.put('A'.toByte)
    }
    println("Writing to Memory Mapped File is completed")

    //reading from memory file in Java
    //var i = 0
    for (i <- 0 until count) {
      out.get(i).toChar
    }
    println("Reading from Memory Mapped File is completed")
    println("Time elapsed: %.5f seconds." format((System.currentTimeMillis - startTime)/1000F))
  }
}

