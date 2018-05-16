import java.awt.PageAttributes.ColorType;


/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author :
 * @date   :
 */

public class PixelMapPlus extends PixelMap implements ImageOperations
{
	ImageType type=imageType ;
	int h=height;
	int w=width;
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}

	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image);
	}

	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image);
	}

	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}

	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				imageData[i][j]=imageData[i][j].Negative();
			}
		}	
	}

	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		AbstractPixel[][] temp;
		temp = new BWPixel[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				temp[i][j] = imageData[i][j].toBWPixel();
			}}
		imageData = new BWPixel[h][w];
		imageData = temp;
	}

	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		AbstractPixel[][] temp;
		temp = new GrayPixel[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				temp[i][j] = imageData[i][j].toGrayPixel();
			}}
		imageData = new GrayPixel[h][w];
		imageData = temp;
	}

	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		AbstractPixel[][] temp;
		temp = new ColorPixel[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				temp[i][j] = imageData[i][j].toColorPixel();
			}}
		imageData = new ColorPixel[h][w];
		imageData = temp;
	}

	public void convertToTransparentImage()
	{
		AbstractPixel[][] temp;
		temp = new TransparentPixel[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				temp[i][j] = imageData[i][j].toTransparentPixel();
			}}
		imageData = new TransparentPixel[h][w];
		imageData = temp;
	}

	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation
	 */
	public void rotate(int x, int y, double angleRadian)
	{

	}

	/**
	 * Modifie la longueur et la largeur de l'image
	 * @param wi : nouvelle largeur
	 * @param he : nouvelle hauteur
	 */
	public void resize(int wi, int he) throws IllegalArgumentException
	{
		if(wi < 0 || he < 0)
			throw new IllegalArgumentException();
		AbstractPixel[][] temp=new AbstractPixel[he][wi];
		for(int i=0;i<he;i++) {
			for(int j=0;j<wi;j++) {
				temp[i][j] = imageData[(int) i*h/he][(int) j*w/wi];
			}}
		imageData = new TransparentPixel[he][wi];
		h=he;
		w=wi;
		height=he;
		width=wi;
		imageData = temp;
	}

	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		int nh=h+pm.height;
		int nw=w+pm.width;
		AbstractPixel[][] temp=new AbstractPixel[nh][nw];
		for(int i=0;i<nh;i++) {
			for(int j=0;j<nw;j++) {
				if(i<row0 && j<col0) {temp[i][j] = imageData[i][j];}
				else if(row0<i && i<pm.height+row0-1 && col0<j && j<pm.width+col0-1) {temp[i][j] = pm.imageData[i-row0][j-col0];}
				else {temp[i][j] = imageData[0][0];}
			}}
		imageData = new TransparentPixel[nh][nw];
		h=nh;
		w=nw;
		height=nh;
		width=nw;
		imageData = temp;
	}

	/**
	 * Decoupe l'image
	 */
	public void crop(int nh, int nw)
	{
	
		AbstractPixel[][] temp = new AbstractPixel[nh][nw];
		AbstractPixel whitePixel = new BWPixel(true);
		for (int i=0; i<nh; i++) {
			for (int j=0; j<nw; j++) {	
				if (i >= h || j >= w) {
					temp[i][j] = whitePixel;
				}	
				else {
					temp[i][j] = imageData[i][j];
				}							
			}
		}
		
		imageData = new TransparentPixel[nh][nw];
		h = nh;
		w = nw;
		height = nh;
		width = nw;
		imageData = temp;
		
	}

	/**
	 * Effectue une translation de l'image
	 */
	public void translate(int rowOffset, int colOffset)
	{
		AbstractPixel[][] temp = new AbstractPixel[h][w];
		AbstractPixel whitePixel = new BWPixel(true); //création d'un examplaire de pixel blanc

		// for (int i=0; i<rowOffset; i++){
		// 	for (int j=0; j<w;j++) {
		// 		temp[i][j] = whitePixel;
		// 	}
		// }
		// for (int i=rowOffset; i < h; i++){
		// 	for (int j=0; j<colOffset; j++){
		// 		temp[i][j] = whitePixel;
		// 	}
		// }

		// for (int i=rowOffset; i<h; i++){
		// 	for (int j=colOffset; j<w;j++){
		// 		temp[i][j] = imageData[i-rowOffset][j-colOffset];
		// 	}
		// }
		for (int i=0; i<h;i++){
			for (int j=0; j<w;j++){
				if (i<rowOffset){
					temp[i][j] = whitePixel;
				}
				else if (j<colOffset){
					temp[i][j] = whitePixel;
				}
				else {
					temp[i][j] = imageData[i-rowOffset][j-colOffset];
				}
			}
		}
		imageData = new TransparentPixel[h][w];
		imageData = temp;
	
	}

	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();

		int nh = (int) (h / zoomFactor);
		int nw = (int) (w /zoomFactor) ;
		
		AbstractPixel[][] temp = new AbstractPixel[nh][nw];
		for (int i=x-nh/2; i<x+nh/2;i++) {
			for (int j=y-nw/2;j<y+nw/2;j++){
				temp[i-(x-nh/2)][j-(y-nw/2)] = imageData[i][j];
			}
		}
		imageData = new TransparentPixel[nh][nw];
		imageData = temp;
	
	}
}
