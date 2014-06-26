/**
 * Created by ian on 6/5/2014.
 */

package opengltests2;

import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.GL;
import javax.media.opengl.DebugGL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.Texture;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JOGLQuad implements GLEventListener {

    private long time = System.currentTimeMillis();
    private Texture mTest;

    private static vnGLCanvas main_canvas;
    private static Animator main_animator;
    private static Frame main_frame;

    public static void main(String[] args){

        final Dimension CONFIG_DIMENSIONS = new Dimension();
        CONFIG_DIMENSIONS.height = 576;
        CONFIG_DIMENSIONS.width = 1024;

        main_canvas = new vnGLCanvas();
        main_animator = new Animator(main_canvas);
        main_frame = new Frame("VN 01");

        main_canvas.addGLEventListener(new JOGLQuad());

        main_frame.add(main_canvas);

        main_frame.setSize(CONFIG_DIMENSIONS);

        main_frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                main_animator.stop();
                main_frame.dispose();
                System.exit(0);
            }
        });

        main_frame.setVisible(true);

        main_animator.setUpdateFPSFrames(20, null);
        main_animator.start();

        main_canvas.requestFocus();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

        final GL2 gl2 = glAutoDrawable.getGL().getGL2();
        glAutoDrawable.setGL(new DebugGL2(glAutoDrawable.getGL().getGL2()));

        gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl2.glEnable(GL.GL_TEXTURE_2D);
//		gl2.glClearDepthf(1.0f);
//        gl2.glShadeModel(GLLightingFunc.GL_FLAT);
//        gl2.glDisable(GL.GL_DEPTH_TEST);
//        gl2.glEnable(GL.GL_DEPTH_TEST);
//        gl2.glDepthFunc(GL.GL_LEQUAL);
//        gl2.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

        final GL2 gl2 = glAutoDrawable.getGL().getGL2();

        gl2.glClear(GL.GL_COLOR_BUFFER_BIT);
//        gl2.glClear(GL.GL_DEPTH_BUFFER_BIT);
//        gl2.glEnable(GL.GL_TEXTURE_2D);
//        gl2.glLoadIdentity();

        if (mTest == null) {
            try {
                mTest = TextureIO.newTexture(this.getClass().getResource("/res/images/1024-576.jpg"), true, TextureIO.JPG);
                mTest.enable(gl2);
                mTest.bind(gl2);
                System.out.println("Bound a texture");
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }

//        gl2.glBindTexture(GL.GL_TEXTURE_2D, texture[0]);
//        gl2.glTranslatef(0.0f, 0.0f, -0.5f);

        gl2.glBegin(GL2.GL_TRIANGLES);

            gl2.glTexCoord2f(0.0f, 1.0f); gl2.glVertex3f(-1.0f, 1.0f, 0.0f); // Top Left
            gl2.glTexCoord2f(1.0f, 1.0f); gl2.glVertex3f(1.0f, 1.0f, 0.0f); // Top Right
            gl2.glTexCoord2f(0.0f, 0.0f); gl2.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left

            gl2.glTexCoord2f(0.0f, 0.0f); gl2.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
            gl2.glTexCoord2f(1.0f, 1.0f); gl2.glVertex3f(1.0f, 1.0f, 0.0f); // Top Right
            gl2.glTexCoord2f(1.0f, 0.0f); gl2.glVertex3f(1.0f, -1.0f, 0.0f); // Bottom Right

        gl2.glEnd();

//        gl2.glDisable(GL.GL_TEXTURE_2D);

        if (System.currentTimeMillis() - time >= 3000) {
            System.out.println(main_animator.getLastFPS());
            time = System.currentTimeMillis();
        }

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {

        final GL2 gl2 = glAutoDrawable.getGL().getGL2();
//        System.out.println(gl2.glGetString(GL.GL_VERSION));
		
		gl2.glViewport(0, 0, width, height);
		
        gl2.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl2.glLoadIdentity();

        //gl2.glOrthof(0.0f, (float)width, 0, (float)height, -1.0f, 1.0f);
//        final float aspect = (float) width / (float) height;
//        final float fh = 1.0f;
//	    final float fw = 1.0f;
//        gl2.glFrustumf(-fw, fw, -fh, fh, 1.0f, 2.0f);
        gl2.glOrthof(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
		gl2.glDisable(GL.GL_DEPTH_TEST);
        gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl2.glLoadIdentity();

//        gl2.glViewport(0, 0, width, height);

    }
}
