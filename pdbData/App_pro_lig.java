package com.neaz.protein.imageProtein;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.features2d.*;

//import WaveltTest.wavTest;
//import creatingInstances.CreateInstance;
//import creatingInstances.TotalInstance;
//import com.neaz.protein.imageProtein.*;



/**
 * Hello world!
 *
 */
public class App_pro_lig 
{
	static Scanner sc;
	static Scanner input;

    public static void main( String[] args ) throws FileNotFoundException, InterruptedException, UnsupportedEncodingException
    {
    	//loading ovencv
    	/*nu.pattern.OpenCV.loadShared();
		System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
		
		//pdbFileToImage();
		File file = new File("/media/neaz/Entertainment/18/Downloads/Protein Project/pdbstyle-40/pdbstyle-2.03/2a/d12asa_.ent");
		Mat testMat = new Mat(3, 3, CvType.CV_8UC1);
		testMat.release();
		testMat.put(1, 1, 10);
		
		
		System.out.println(testMat.dump());*/
		
//		ImageCreator ic = new ImageCreator();
//		ic.runFeatureExtraction(file,file.getPath());
		
//		Mat testImage = Imgcodecs.imread("/home/iamneaz/eclipse-JAVAworkspace//ImageProtein/test.jpg");
//		wavTest wavTransform = new wavTest(testImage);
//		//getting the haar wavelet transformed image
//		Mat transformedImage =wavTransform.conversion().clone();
//		
//		if(!transformedImage.empty())
//		{
//			Imgcodecs.imwrite("waveletTransformed.jpg", transformedImage);
//			
//		}
//		else
//		{
//			CreateInstance ci = new CreateInstance(transformedImage);
//			String value = ci.generateInstanceA();
//			System.out.println(value);
//		}

	/*
	 * Generating total instance of a feature group
	 * 
	*/
//	TotalInstance ti = new TotalInstance();
//	ti.generateTotalInstanceBD();
	/*
	 * Getting info	
	 */
//	GetInfo gi = new GetInfo();
//	try {
//		gi.info();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		/*
		 * Setting up strings for localhost
		 */

//		String string="";
//		for(int i=0;i<128;i++)
//		{
//			string = string + "r1c"+i+""+","+"";
//		}
//		for(int i=0;i<256;i++)
//		{
//			string = string + "pdgh"+i+"" + "," + "" +  "";
//		}
//		for(int i=0;i<256;i++)
//		{
//			string = string + "pugh"+i+""+","+"";
//		}
//		for(int i=0;i<256;i++)
//		{
//			string = string + "pdgh"+i+""+","+""+ "";
//		}
		
//		for(int i=0;i<7;i++)
//		{
//			for(int j=0;j<128;j++)
//			{
//				string=string+"s"+i+"c"+j+" "+""+ ","+"";
//			}
//		}
//		string=string+"classx varchar(255)";
//		string=string+"scopeid,";
//		string=string+"class";
//		string=string+"fold  varchar(255),";
//		string=string+"superfamily  varchar(255),";
//		string=string+"family  varchar(255),";
//		string=string+"protein  varchar(255),";
//		string=string+"species  varchar(255)";
		
		
		
//		System.out.println(string);
//		System.gc();

		//resizeImages();
		
		//countFeature();
		
		//correctCSVFile();
		
//		PrintStream console = System.out;
//
//		File file = new File("out.txt");
//		FileOutputStream fos = new FileOutputStream(file);
//		PrintStream ps = new PrintStream(fos);
//		System.setOut(ps);
//		
//		
//		long time1 = System.nanoTime();
//		//A
//		ClassMatch cmA = new ClassMatch();
//		cmA.setNames("A_128x128_withAllInfo.csv", "Table_One_A.csv", "A");
//		cmA.setInitializations(11052, 256,1335);
//		cmA.run();
//		
//		//B
//		ClassMatch cmB = new ClassMatch();
//		cmB.setNames("B_128x128_withAllInfo.csv", "Table_One_B.csv", "B");
//		cmB.setInitializations(11052, 512,1335);
//		cmB.run();
//		
//		//C
//		ClassMatch cmC = new ClassMatch();
//		cmC.setNames("C_128x128_withAllInfo.csv", "Table_One_C.csv", "C");
//		cmC.setInitializations(11052, 256,1335);
//		cmC.run();
//		
//		//D
//		ClassMatch cmD = new ClassMatch();
//		cmD.setNames("D_128x128_withAllInfo.csv", "Table_One_D.csv", "D");
//		cmD.setInitializations(11052, 512,1335);
//		cmD.run();
//		
//		
//		
//		
//		long time2 = System.nanoTime();
//		long timeTaken = time2 - time1;  
//		System.out.println("Time taken " + timeTaken + " ns");  
//		
//		System.setOut(console);

    	nu.pattern.OpenCV.loadShared();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		convertPdbFileToImage();
		
		//uniqueCheck();
    }

    
    private static void uniqueCheck() {
		// TODO Auto-generated method stub

    	//image dataset folder
    	    	File folder = new File("F:\\Thesis\\Alternate Dataset\\train_output_image"); 
    	    	
    	    	File[] listOfFiles = folder.listFiles();
    			 System.out.println("Main folder name " + folder.getName() );
    			 int similarityCount=0;
    			 //going inside each folder   
    			 for (int i =14; i < listOfFiles.length-1; i++) {
    				 	//listing each folders inside the folder
	    				if(!listOfFiles[i].getName().endsWith("_pro_cg.pdb.jpg"))
	    					continue;
	    				File imageFile =new File(listOfFiles[i].getPath());
			        	Mat image = Imgcodecs.imread(imageFile.getPath());
			        	int rows = image.rows();
    			        //going through each file of the folder
    			        for(int j=i+1;j<listOfFiles.length;j++)
    			        {
    			        	if(!listOfFiles[j].getName().endsWith("_pro_cg.pdb.jpg"))
    			        		continue;
    			        	   if (listOfFiles[j].isFile()) {
    			        		   File fin=new File(listOfFiles[j].getPath());
    			        			
    			        		   if(fin.exists())
    			        			{
    			        				Mat checkImage = Imgcodecs.imread(fin.getPath());
    				        			if(rows==checkImage.rows())
    				        			{
    				        				//if(image.equals(checkImage))
    				        				//{
    				        					similarityCount++;
    				        					//System.out.println(image);
    				        					System.out.println("matched: "+listOfFiles[i].getName()+"  &  "+listOfFiles[j].getName()+" | count: "+similarityCount);
    				        				//}
    				        			}
    				        			
    				        			
    			        				
    			        			}
    			        			else
    			        			{
    			        				System.out.println("\t\t--file: "+listOfFiles[j].getName()+" doesn't exist");
    			        			}
    			        			
    			        					
    			        	   }
    			        }
    			        System.gc();
    			 }
    			 System.out.println(""+similarityCount);
	}


	public static void pdbFileToImage()
    {
    	File folder = new File("/home/iamneaz/Downloads/Protein Project/pdbstyle-40/pdbstyle-2.03");
		File[] listOfFiles = folder.listFiles();
		 System.out.println("Main folder name " + folder.getName() );
		 //going inside each folder   
		 for (int i = 0; i < listOfFiles.length; i++) {
			 	//listing each folders inside the folder
		        File[] listOfFilesInsideFile = listOfFiles[i].listFiles();
		        System.out.println("folder name " + listOfFiles[i].getName() );
		        //going through each file of the folder
		        for(int j=0;j<listOfFilesInsideFile.length;j++)
		        {
		        	   if (listOfFilesInsideFile[j].isFile()) {
		        		   File fin=new File(listOfFilesInsideFile[j].getPath());
		        			if(fin.exists())
		        			{
		        				System.out.println("file exists");
		        				
		        			}
		        			else
		        			{
		        				System.out.println("file  doesn't exist");
		        			}
		        			ImageCreator extractor=new ImageCreator();
		        			String comogPhog=extractor.runFeatureExtraction(fin,listOfFiles[i].getName());

//		        			File outDir=new File("output");
//		        			if(!outDir.exists()||!outDir.isDirectory())
//		        			{
//		        				outDir.mkdir();
//		        			}
//		        			File fout=new File(outDir,fin.getName());
//		        			try {
//		        				PrintWriter pw = new PrintWriter(fout);
//		        				pw.println(comogPhog);
//		        				pw.flush();
//		        				pw.close();
//		        			} catch (Exception e) {
//		        		e.printStackTrace();
//		        			}
//		        	   
		   		        System.out.println("--File " + listOfFilesInsideFile[j].getName());
		        	   }
		   		       else if (listOfFilesInsideFile[j].isDirectory()) {
		   		        System.out.println("-Directory " + listOfFilesInsideFile[j].getName());
		   		      }
		        	
		        }
		       
		    } 
		 
		 
    	
    }


	public static void convertPdbFileToImage(){
		String testSrc = "/home/iamneaz/Downloads/pdbData/";
		File folder = new File(testSrc);
		File[] listOfFiles = folder.listFiles();
		System.out.println("Main folder name: >" + folder.getName() +"<");
		//going inside each folder
		for(int j=0;j<listOfFiles.length;j++){
			//System.out.println("folder name " + listOfFiles[j].getName() );
			if (listOfFiles[j].isFile()) {
				File fin=new File(listOfFiles[j].getPath());
				if(fin.exists())
				{
					System.out.println("file exists");

				}
				else
				{
					System.out.println("file  doesn't exist");
				}
				System.out.println("--File: " + listOfFiles[j].getName());
				ImageCreator_pro_lig extractor=new ImageCreator_pro_lig();
				String comogPhog=extractor.runFeatureExtraction(fin,listOfFiles[j].getName());
				
//		        			File outDir=new File("output");
//		        			if(!outDir.exists()||!outDir.isDirectory())
//		        			{
//		        				outDir.mkdir();
//		        			}
//		        			File fout=new File(outDir,fin.getName());
//		        			try {
//		        				PrintWriter pw = new PrintWriter(fout);
//		        	/			pw.println(comogPhog);
//		        				pw.flush();
//		        				pw.close();
//		        			} catch (Exception e) {
//		        		e.printStackTrace();
//		        			}
			}
			else if (listOfFiles[j].isDirectory()) {
				System.out.println("-Directory: " + listOfFiles[j].getName());
			}
		}
	}

    
    public static void getMAXImageSize()
    {
    	int maxWidth = 0 ;
    	int maxHeight = 0 ;
    	String filename="";
    	File folder = new File("/home/neaz/Oxygen-workspace/ImageProtein/imageProtein/imageOutput with sccs");
		File[] listOfFiles = folder.listFiles();
		 System.out.println("Main folder name " + folder.getName() );
		 //going inside each folder   
		 for (int i = 0; i < listOfFiles.length; i++) {
			 	//listing each folders inside the folder
		        File[] listOfFilesInsideFile = listOfFiles[i].listFiles();
		        System.out.println("folder name " + listOfFiles[i].getName() );
		        //going through each file of the folder
		        for(int j=0;j<listOfFilesInsideFile.length;j++)
		        {
		        	   if (listOfFilesInsideFile[j].isFile()) {
		        		   File fin=new File(listOfFilesInsideFile[j].getPath());
		        			if(fin.exists())
		        			{
		        				System.out.println("file exists");
		        				
		        			}
		        			else
		        			{
		        				System.out.println("file  doesn't exist");
		        			}
		        			
		        			Mat img = Imgcodecs.imread(fin.getPath());
		        			int width = img.width();
		        			int height = img.height();
		        			if(width>maxWidth)
		        			{
		        				maxWidth=width;
		        				filename=fin.getName();
		        			}
		        			
		        	   }
		        }
		        
		      
		 }
		  System.out.println("Maximum width:"+maxWidth);
		  System.out.println("fileName"+filename);
    }
    public static void resizeImages()
    {
    	File destinitonFolder = new File("/home/neaz/Oxygen-workspace/tesr/resized Images 128x128"); 
    	Size sz = new Size(128,128);
    	File folder = new File("/home/neaz/Oxygen-workspace/ImageProtein/imageProtein/imageOutput with sccs");
		File[] listOfFiles = folder.listFiles();
		 System.out.println("Main folder name " + folder.getName() );
		 //going inside each folder   
		 for (int i = 0; i < listOfFiles.length; i++) {
			 	//listing each folders inside the folder
		        File[] listOfFilesInsideFile = listOfFiles[i].listFiles();
		        System.out.println("folder name " + listOfFiles[i].getName() );
		        //going through each file of the folder
		        for(int j=0;j<listOfFilesInsideFile.length;j++)
		        {
		        	   if (listOfFilesInsideFile[j].isFile()) {
		        		   File fin=new File(listOfFilesInsideFile[j].getPath());
		        			if(fin.exists())
		        			{
		        				System.out.println("file exists");
		        				
		        			}
		        			else
		        			{
		        				System.out.println("file  doesn't exist");
		        			}
		        			
		        			Mat img = Imgcodecs.imread(fin.getPath());
		        			Mat resizedImage = new Mat();
		        			Imgproc.resize(img, resizedImage, sz);
		        			
		        			Imgcodecs.imwrite(destinitonFolder+"/"+fin.getName()+".jpg", resizedImage);
		        			
		        	   }
		        }
		        System.gc();
		 }
    }
    
    public static void countFeature()
    {
    	String line = "35494,6951,861,492,615,246,1291,738,1537,1599,1783,430,369,615,738,6766,6213,28666,11195,2952,1722,1291,861,615,430,0,61,0,0,246,123,492,861,10949,58686,17408,4306,1783,676,369,184,0,0,0,61,0,61,61,492,1968,13902,14702,15563,3506,738,553,61,0,0,0,0,61,61,246,307,1230,4244,10826,23068,13902,3137,1045,307,61,0,0,61,61,0,307,676,984,2153,2891,9042,17224,13656,2399,2399,61,0,0,184,0,0,184,123,1107,1230,1230,2522,10826,37955,9288,1906,123,246,61,123,0,184,123,676,676,799,799,922,2214,6459,29404,6336,861,123,184,123,184,184,184,1230,307,246,61,369,246,861,4982,34202,5905,984,61,492,61,492,307,492,184,0,61,61,369,553,676,6151,30573,8796,3321,553,676,738,984,492,123,184,123,123,246,799,430,922,10211,62069,11134,3937,1476,1230,1168,369,61,61,0,0,61,61,61,1414,2583,13902,21038,10519,3629,1537,984,184,61,246,123,0,61,0,61,492,1107,4060,13964,22207,11687,3444,1168,615,184,369,123,0,0,61,0,307,615,1045,4306,14087,21038,14210,2522,430,430,553,61,61,0,0,61,123,246,615,984,4982,16486,37893,10150,6090,307,615,0,246,0,0,123,430,615,1045,799,1168,3137,12057,35679,19116,24512,16389,45978,24194,35978,24554,37853,21422,3215,3833,4884,18348,8811,7500,8932,14474,6425,1089,8741,6078,7639,6366,10585,5554,5626,2522,7552,3098,2519,4337,5551,10781,6883,6561,6389,7259,8839,2906,15653,3465,7110,3183,11191,6085,933,628,925,4593,1755,1308,2019,2899,2019,984,775,87,988,896,2641,5911,3744,2038,149,4010,3823,1784,2054,1858,253,2333,609,0,0,254,1618,2156,1550,2693,2586,0,0,1475,3029,4483,5424,3550,859,321,0,1297,954,842,8282,735,0,142,7509,2366,647,4730,277,130,450,6555,2607,561,1193,292,0,1723,4105,1704,621,0,144,719,3165,1690,919,1557,355,1262,2077,1576,2556,4244,1129,1054,511,2827,7173,1978,370,297,277,0,285,9299,573,0,790,5872,2720,4530,3273,435,0,970,3233,466,167,535,0,0,0,1792,2368,1828,1686,2016,179,142,278,544,475,0,517,1141,2597,3355,1819,5098,1096,236,306,3876,1001,292,287,631,467,1088,6117,1615,0,924,4859,2542,121,0,434,2240,321,0,128,1261,1192,505,297,0,0,540,479,1191,421,116,0,0,87,988,499,462,265,0,0,162,0,0,0,132,808,421,2377,1873,149,3237,783,434,114,0,0,0,491,0,321,115,832,1939,1858,131,0,0,0,0,254,1618,1600,884,570,647,0,0,0,0,0,555,665,1066,846,0,305,331,0,469,893,828,698,0,0,0,0,491,1882,0,0,0,1216,711,821,775,0,0,0,0,2087,1367,164,0,0,0,0,264,1370,3136,0,0,0,296,2531,517,0,0,0,0,0,0,154,392,0,0,0,122,2333,118,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1056,1093,0,0,111,211,781,483,462,393,321,0,0,1211,454,1244,1660,521,0,0,0,0,0,0,2628,0,0,0,2516,164,836,0,0,0,0,0,0,3747,1554,493,4248,0,0,0,0,0,0,436,0,164,0,0,0,2884,695,0,0,0,0,450,847,327,133,0,0,0,292,0,0,0,0,0,0,144,719,0,152,2364,2456,718,762,136,0,0,0,0,0,0,2562,1803,329,0,0,0,453,842,3668,253,0,0,0,0,461,501,0,1986,482,0,142,1244,646,154,316,277,0,322,571,136,561,757,0,0,0,130,128,3099,1775,0,0,0,0,1272,2700,274,0,0,0,0,0,0,0,557,1103,487,0,0,0,1990,1374,804,187,0,0,115,677,921,794,0,0,0,0,0,0,148,1526,3714,818,646,0,0,0,0,0,0,160,310,407,511,592,528,889,224,297,0,0,0,2648,290,0,331,1555,552,277,0,0,0,0,0,459,2593,1481,2489,2061,435,0,0,0,0,0,0,807,661,0,0,142,0,0,0,0,0,315,0,0,0,1262,1962,750,0,381,0,115,1369,355,0,0,0,108,369,0,0,0,511,3113,308,0,0,0,0,0,0,1723,3531,780,145,0,0,0,285,3929,282,0,0,0,0,0,0,0,2721,0,0,0,1723,685,1234,550,0,0,0,805,312,167,535,0,0,0,0,828,2428,154,0,0,0,0,0,521,395,780,1142,2016,179,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,453,124,1955,142,0,0,0,0,0,0,2162,1399,1370,3409,427,0,0,0,0,0,287,148,743,506,236,148,956,0,0,0,0,0,316,1949,757,0,443,1394,665,329,0,0,0,0,0,325,2542,868,0,0,0,696,749,796,544,0,0,0,0,0,574,1222,251,0,0,0,0,278,544,475,0,0,0,0,0,0,0,0,0,0,517,688,310,0,300,944,162,0,0,0,0,0,0,0,0,0,0,157,2920,1001,292,0,0,172,466,2641,715,0,0,0,0,302,295,305,1525,142,0,155,922,1007";
        StringTokenizer strTok = new StringTokenizer(line, ",");
        int numOfTokens;
        numOfTokens = strTok.countTokens();
//        String tokens[] = new String[numOfTokens];
//        int tokenid = 0;
//        while (strTok.hasMoreTokens()) {
//            tokens[tokenid++] = strTok.nextToken().replace("\"", "");
//        }
//    }
        System.out.println(numOfTokens);
    }
    
    public static void correctCSVFile() throws FileNotFoundException, UnsupportedEncodingException
    {
    	
    	File infoFile = new File("/home/neaz/Downloads/40_reducedDatasetComogPhog-CLass.csv");
    	File newFile = new File("/home/neaz/Downloads/FeatureVector-class.csv");
		PrintWriter writer = new PrintWriter(newFile);
    	
		String columnNames="class,";
		for(int i=0;i<1021;i++)
		{
				if(i==1020)
				{
					columnNames=columnNames+"f"+i;
				}
				else
				{
					columnNames=columnNames+"f"+i+",";
				}
				
			
		}
		columnNames=columnNames+"\n";
		writer.append(columnNames);
		
    	try {
			sc = new Scanner(infoFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            while (sc.hasNext()) {
            	//System.out.println("SEARCHING");
                String line = sc.nextLine();
                StringTokenizer strTok = new StringTokenizer(line, ",");
                int numOfTokens;
                numOfTokens = strTok.countTokens();
                String tokens[] = new String[numOfTokens];
                int tokenid = 0;
                while (strTok.hasMoreTokens()) {
                    tokens[tokenid++] = strTok.nextToken();
                }
                String vector = tokens[1].replace("-", ",");
                String instance= tokens[0]+","+ vector;
                writer.append(instance+"\n");
               
            }
            writer.close();
    }

    
 


}
