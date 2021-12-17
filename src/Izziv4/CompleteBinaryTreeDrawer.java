package APS1.Izziv4;

public class CompleteBinaryTreeDrawer {

    private int dX;
    private int dY;
    private char[] elementi;
    private int[][] koordinate;
    private int n;

    public CompleteBinaryTreeDrawer(char[] elementi, int[][] koordinate, int visina, int sirina){
        this.elementi = elementi;
        this.koordinate = koordinate;
        StdDraw.setCanvasSize(1600, 900);
        StdDraw.setXscale(0, 1600);
        StdDraw.setYscale(0, 900);
        this.dX = 1400/sirina;
        this.dY = 800/visina;
        this.n = sirina;
    }

    private int[] praveKor(int x, int y){
        x = (int)((x+1)*this.dX - (this.dX/2) + 100);
        y = (int)(900 - ((y+0.5)*this.dY - (this.dY/2) + 50));
        return new int[]{x,y};
    }

    public void pobrisi(){
        StdDraw.clear();
    }

/* jst nerazumm čist kaj bi ta metoda mogla delat, se mi zdi da sem njene funkcionalnosti naredu že v razredu Izziv4
    public int traverse(int i, int x, int y){

    }*/

    public void drawNode(int i){
        int x = this.koordinate[0][i];
        int y = this.koordinate[1][i];
        char element = this.elementi[i];
        int[] kor = praveKor(x, y);
        x = kor[0];
        y = kor[1];
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledEllipse(x, y, this.dX/2.0, 30);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(x, y, Character.toString(element));
    }

    public void drawEdgeToParent(int i){
        int x = this.koordinate[0][i];
        int y = this.koordinate[1][i];
        int[] kor = praveKor(x, y);
        x = kor[0];
        y = kor[1];
        int oce = (int)(Math.floor((i-1)/2.0));
        int Px = this.koordinate[0][oce];
        int Py = this.koordinate[1][oce];
        kor = praveKor(Px, Py);
        Px = kor[0];
        Py = kor[1];
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(x, y, Px, Py);
        //drawNode(oce);
    }

    // izpis dreves
    public void drawLevelorder(){
        for (int i = 0; i < this.n; i++){
            if (i != 0)
                drawEdgeToParent(i);
            drawNode(i);
        }
    }

    public void drawPreorder(int i){
        if (i != 0){
            drawEdgeToParent(i);
        }
        drawNode(i);
        int levi = 2*i + 1;
        if (levi < this.n){
            drawPreorder(levi);
            int desni = levi + 1;
            if (desni < this.n)
                drawPreorder(desni);
        }
    }

    public void drawInorder(int i){
        int levi = 2*i + 1;
        if (levi < this.n){
            drawInorder(levi);
        }
        if (i != 0){
            drawEdgeToParent(i);
        }
        drawNode(i);
        int desni = levi + 1;
        if (desni < this.n)
            drawInorder(desni);
    }

    public void drawPostorder(int i){
        int levi = 2*i + 1;
        if (levi < this.n){
            drawPostorder(levi);
            int desni = levi + 1;
            if (desni < this.n)
                drawPostorder(desni);
        }
        if (i != 0){
            drawEdgeToParent(i);
        }
        drawNode(i);
    }
}
