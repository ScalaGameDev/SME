package tutorial

import com.jme3.app.SimpleApplication
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.scene.{Geometry,Node}
import com.jme3.scene.Spatial
import com.jme3.scene.shape.Box

import com.jme3.syntax._

/** Sample 2 - How to use nodes as handles to manipulate objects in the scene.
 * You can rotate, translate, and scale objects by manipulating their parent nodes.
 * The Root Node is special: Only what is attached to the Root Node appears in the scene. */
class HelloNode extends SimpleApplication {
 
  override def simpleInitApp: Unit = {
    /** create a blue box at coordinates (1,-1,1) */
    val box1 = Box(1,1,1)
    val blue = Geometry("Box", box1)
    blue.setLocalTranslation(1, -1, 1)
    val mat1 = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    mat1.setColor("Color", ColorRGBA.Blue)
    blue.setMaterial(mat1)

    /** create a red box straight above the blue one at (1,3,1) */
    val box2 = Box(1,1,1)
    val red = Geometry("Box", box2)
    red.setLocalTranslation(1, 3, 1)
    val mat2 = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    mat2.setColor("Color", ColorRGBA.Red)
    red.setMaterial(mat2)

    /** Create a pivot node at (0,0,0) and attach it to the root node */
    val pivot = Node("pivot")
    discard[Int]{ rootNode.attachChild(pivot)} // put this node in the scene

    /** Attach the two boxes to the *pivot* node. */
    discard[Int]{ pivot.attachChild(blue)}
    discard[Int]{ pivot.attachChild(red)}
    /** Rotate the pivot node: Note that both boxes have rotated! */
    discard[Spatial]{ pivot.rotate(.4f,.4f,0f)}
  }

}

object HelloNode {
  def main(args:Array[String]): Unit = {

    import java.util.logging.{Logger,Level}
    Logger.getLogger("").setLevel(Level.WARNING)

    val app = new HelloNode
    app.start()
  }
}
