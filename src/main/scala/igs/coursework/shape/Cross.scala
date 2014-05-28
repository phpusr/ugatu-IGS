package igs.coursework.shape

import javafx.scene.paint.Color
import javafx.geometry.Point3D
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions

/**
 * @author phpusr
 *         Date: 28.05.14
 *         Time: 13:12
 */

/**
 * Крест
 */
class Cross(startPoint: Point3D, size: Double, color: Color, shade: Double) extends ComplexShape {

  init()

  def init() {

    /** Построение куба с параметрами по умолчанию */
    val newCube = (x: Double, y: Double, z: Double) =>
      new Cube(new Point3D(startPoint.getX + x, startPoint.getY + y, startPoint.getZ + z), size, color, shade)

    val topCube = newCube(size, 0, 0)
    val middleCube = newCube(size, size, 0)
    val bottomCube = newCube(size, size * 2, 0)
    val leftCube = newCube(0, size, 0)
    val rightCube = newCube(size * 2, size, 0)

    // Convert scala list => java list
    partList = ListBuffer(topCube, middleCube, bottomCube, leftCube, rightCube)
    getChildren.addAll(JavaConversions.bufferAsJavaList(partList))
  }
}
