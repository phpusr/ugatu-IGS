package igs.lab

import javafx.scene.Group
import javafx.scene.transform.Rotate
import javafx.scene.paint.Color
import javafx.scene.shape.{Rectangle, RectangleBuilder}
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions
import scala.util.Random

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 16:20
 */

/**
 * Куб
 */
class Cube(size: Double, color: Color, shade: Double) extends Group {
  val rx = new Rotate(0,Rotate.X_AXIS)
  val ry = new Rotate(0,Rotate.Y_AXIS)
  val rz = new Rotate(0,Rotate.Z_AXIS)

  // Кол-во сторон
  val sidesNumber = 6

  // Цвета сторон объекта
  val colors = if (color != null) {
    for (i <- 1 to sidesNumber)
      yield color.deriveColor(0.0, 1.0, 1 - ((sidesNumber - i).toDouble / 10) * shade, 1.0)
  } else {
    //List(Color.ORANGE, Color.FUCHSIA, Color.GREEN, Color.RED, Color.BLUE, Color.GOLD)
    def randomColorNumber = () => Random.nextInt(256)
    for(i <-1 to sidesNumber)
      yield Color.rgb(randomColorNumber(), randomColorNumber(), randomColorNumber())
  }

  // back face
  var index = 0
  val r1 = RectangleBuilder.create()
  r1.width(size)
  r1.height(size)
  r1.fill(colors(index))
  r1.translateX(-0.5*size)
  r1.translateY(-0.5*size)
  r1.translateZ(0.5*size)

  // bottom face
  index += 1
  val r2 = RectangleBuilder.create()
  r2.width(size)
  r2.height(size)
  r2.fill(colors(index))
  r2.translateX(-0.5*size)
  r2.translateY(0)
  r2.rotationAxis(Rotate.X_AXIS)
  r2.rotate(90)

  // right face
  index += 1
  val r3 = RectangleBuilder.create()
  r3.width(size)
  r3.height(size)
  r3.fill(colors(index))
  r3.translateX(-1*size)
  r3.translateY(-0.5*size)
  r3.rotationAxis(Rotate.Y_AXIS)
  r3.rotate(90)

  // left face
  index += 1
  val r4 = RectangleBuilder.create()
  r4.width(size)
  r4.height(size)
  r4.fill(colors(index))
  r4.translateX(0)
  r4.translateY(-0.5*size)
  r4.rotationAxis(Rotate.Y_AXIS)
  r4.rotate(90)

  // top face
  index += 1
  val r5 = RectangleBuilder.create()
  r5.width(size)
  r5.height(size)
  r5.fill(colors(index))
  r5.translateX(-0.5*size)
  r5.translateY(-1*size)
  r5.rotationAxis(Rotate.X_AXIS)
  r5.rotate(90)

  // top face
  index += 1
  val r6 = RectangleBuilder.create()
  r6.width(size)
  r6.height(size)
  r6.fill(colors(index))
  r6.translateX(-0.5*size)
  r6.translateY(-0.5*size)
  r6.translateZ(-0.5*size)

  // Convert scala list => java list
  val rectangleBuilders = ListBuffer(r1, r2, r3, r4, r5, r6).map(_.build())
  val rectagles: java.util.List[Rectangle] = JavaConversions.bufferAsJavaList(rectangleBuilders)

  getTransforms.addAll(rz, ry, rx)
  getChildren.addAll(rectagles)

}
