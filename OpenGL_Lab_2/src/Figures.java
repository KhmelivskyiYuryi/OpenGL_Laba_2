import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.glu.GLU;


public class Figures {

    protected static void setup( GL2 gl2, int width, int height ) {
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // Початок системи координат в центрі вікна
        GLU glu = new GLU();
        glu.gluOrtho2D( -width, width, -height, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );
    }



    protected static int Sierpinski(GL2 gl2, int count, int x1, int y1, int x2, int y2, int x3, int y3){


        // Вихід з рекурсії
        if(count==0)
            return 0;

        //Малювання ліній
        gl2.glBegin(GL2ES3.GL_LINES);
        gl2.glVertex2d(x1,y1);
        gl2.glVertex2d(x2,y2);
        gl2.glEnd();

        gl2.glBegin(GL2ES3.GL_LINES);
        gl2.glVertex2d(x2,y2);
        gl2.glVertex2d(x3,y3);
        gl2.glEnd();

        gl2.glBegin(GL2ES3.GL_LINES);
        gl2.glVertex2d(x3,y3);
        gl2.glVertex2d(x1,y1);
        gl2.glEnd();

        // Середини сторін
        int xa,xb,xc,ya,yb,yc;
        xa = (x1 + x2)/2;
        ya = (y1 + y2)/2;
        xb = (x1 + x3)/2;
        yb = (y1 + y3)/2;
        xc = (x3 + x2)/2;
        yc = (y3 + y2)/2;

        // Рекурсія
        Sierpinski(gl2,count-1, x1, y1, xa, ya, xb, yb );
        Sierpinski(gl2,count-1, xa, ya, x2, y2, xc, yc );
        Sierpinski(gl2,count-1, xb, yb, xc, yc, x3, y3 );

        return 1;
    }


    protected static void render( GL2 gl2, int width, int height ) {

        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );

//        // Вісі координат
//        gl2.glLoadIdentity();
//        gl2.glColor3f(1,0,0);
//        gl2.glBegin(GL2ES3.GL_LINES);
//        gl2.glVertex2f(0,height);
//        gl2.glVertex2f(0, -height);
//        gl2.glEnd();
//        gl2.glBegin(GL2ES3.GL_LINES);
//        gl2.glVertex2f(width,0);
//        gl2.glVertex2f(-width, 0);
//        gl2.glEnd();

        // Початкові точки трикутника
        int x1 = -width/2;
        int y1 = -height/2;
        int x2 = -width/2;
        int y2 = height/2;
        int x3= width/2;
        int y3 = 0;



        gl2.glColor3f(1f,1f,1f); //  Задаємо жовтий колір точок
        Sierpinski(gl2,12,x1,y1,x2,y2,x3,y3); // Рекурсивна фунція побудови трикутника Серпінського




    }
}
