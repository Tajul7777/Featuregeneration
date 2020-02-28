package com.neaz.protein.imageProtein;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageCreator_pro_lig {
	   	int maxCaCount = 20000;
	    double xyz[][];
	    String folderName;
	    String fileName;
	    String sccs="scCs";
	    

//	    public String runFeatureExtraction(String fileName) {
//	        return runFeatureExtraction(new File(fileName));
//	    }

	    public String runFeatureExtraction(File pdbFormatFile,String folderName) {
	    	this.folderName=folderName;
	    	this.fileName = pdbFormatFile.getName();
	        xyz = new double[3][maxCaCount];
	        String comogphog = "";
	        if (!pdbFormatFile.getName().endsWith(".pdb")) {
	            return comogphog;
	        }
	        try {
	            Scanner sc = new Scanner(pdbFormatFile);
	            int seqNo;
	            seqNo = 0;
	            while (sc.hasNext()) {
	                String line = sc.nextLine();
	                line = line.replace("-", " -"); //for separating attached values
	                StringTokenizer strTok = new StringTokenizer(line, "\t");
	                int numOfTokens = strTok.countTokens();
	                String tokens[] = new String[numOfTokens];
	                int tokenid = 0;
	                while (strTok.hasMoreTokens()) {
	                    tokens[tokenid++] = strTok.nextToken();
	                }
//	                if (!(tokens[0].equalsIgnoreCase("atom") || tokens[0].startsWith("HETATM"))) {
//	                    continue;
//	                }
	                
	             
	                // for protein pdb
	                if (tokens[0].equalsIgnoreCase("atom") && tokens[2].equalsIgnoreCase("ca")) {
                        if (numOfTokens >= 12) {
                            xyz[0][seqNo] = Double.parseDouble(tokens[6]);
                            xyz[1][seqNo] = Double.parseDouble(tokens[7]);
                            xyz[2][seqNo] = Double.parseDouble(tokens[8]);
                        }
                        if (numOfTokens < 12) {
                            if (tokens[6].substring(1).contains("-")) {
                                int xendsAt = tokens[6].indexOf("-", 1);
                                xyz[0][seqNo] = Double.parseDouble(tokens[6].substring(0, xendsAt));
                                if (tokens[6].substring(xendsAt + 1).contains("-")) {
                                    int yendsAt = tokens[6].indexOf("-", xendsAt + 1);
                                    xyz[1][seqNo] = Double.parseDouble(tokens[6].substring(xendsAt + 1, yendsAt));
                                    xyz[2][seqNo] = Double.parseDouble(tokens[6].substring(yendsAt + 1));
                                } else {
                                    xyz[0][seqNo] = Double.parseDouble(tokens[6].substring(xendsAt + 1));
                                    xyz[1][seqNo] = Double.parseDouble(tokens[7]);
                                }
                            } else {
                                xyz[0][seqNo] = Double.parseDouble(tokens[6]);
                                if (tokens[7].substring(1).contains("-")) {
                                    int yendsAt = tokens[7].indexOf("-", 1);
                                    xyz[1][seqNo] = Double.parseDouble(tokens[7].substring(0, yendsAt));
                                    xyz[2][seqNo] = Double.parseDouble(tokens[7].substring(yendsAt + 1));
                                }
                            }
                        }
                        seqNo++;
	                }
	                // end protein pdb
	                
	                
	                
	                //for ligand pdb
	                else if (tokens[0].startsWith("HETATM")) {
	                    //if (tokens[numOfTokens-1].equalsIgnoreCase("c")) {
	                		int lastIndex = numOfTokens-1;
	                		int occurance = tokens[lastIndex-1].length() - tokens[lastIndex-1].replace(".", "").length();
	                		if(occurance == 2)
	                			lastIndex++;
                            xyz[0][seqNo] = Double.parseDouble(tokens[lastIndex-5]);
                            xyz[1][seqNo] = Double.parseDouble(tokens[lastIndex-4]);
                            xyz[2][seqNo] = Double.parseDouble(tokens[lastIndex-3]);
	                        seqNo++;
	                    //}
	                }
	                // end ligand pdb
	                
					//for test peotein pdb
	                else if (pdbFormatFile.getName().endsWith("_pro_cg.pdb")) {
						if (tokens[numOfTokens-1].equalsIgnoreCase("h")) {
							xyz[0][seqNo] = Double.parseDouble(tokens[0]);
						    xyz[1][seqNo] = Double.parseDouble(tokens[1]);
						    xyz[2][seqNo] = Double.parseDouble(tokens[2]);
						    seqNo++;
						}
					}
					// end test protein pdb
	                
	                //for test peotein pdb
	                else if (pdbFormatFile.getName().endsWith("_lig_cg.pdb")) {
							xyz[0][seqNo] = Double.parseDouble(tokens[0]);
						    xyz[1][seqNo] = Double.parseDouble(tokens[1]);
						    xyz[2][seqNo] = Double.parseDouble(tokens[2]);
						    seqNo++;
					}
					// end test protein pdb
	                
	                
	                
	                
	            }
	            int numOfCaAtom = seqNo - 1;
	            

	            return runFeatureExtraction(xyz[0], xyz[1], xyz[2], numOfCaAtom);
	        } catch (FileNotFoundException e) {
	        	//logger.fatal(e.getMessage(),e);
	            return comogphog;
	        } catch (NumberFormatException e) {
	        	//logger.fatal(e.getMessage(),e);
	            return comogphog;
	        }
	    }

	    public String runFeatureExtraction(double x[], double y[], double z[], int numOfCAatom) {
	        try {
	            double[][] calphadistmat = new double[numOfCAatom][numOfCAatom];
	            double maxDistance = -1;
	            double minDistance=100000000;
	            int n = 0;
	            double totalDistData[] = new double[numOfCAatom * numOfCAatom];
	            for (int j = 0; j < numOfCAatom; j++) {
	                for (int k = 0; k < numOfCAatom; k++) {
	                    double dist = Math.sqrt((x[j] - x[k]) * (x[j] - x[k]) + (y[j] - y[k]) * (y[j] - y[k]) + (z[j] - z[k]) * (z[j] - z[k]));
	                    maxDistance = Math.max(maxDistance, dist);
	                    minDistance=Math.min(minDistance, dist);
	                    totalDistData[n++] = dist;
	                }
	            }
//	        System.out.print("Maximum Distance: ");
//	        System.out.println(maxDistance);
	            int noQuantLevel = 255;
	            int camatq1maxval = (int) (maxDistance * 2);
	            for (int j = 0; j < numOfCAatom; j++) {
	                for (int k = 0; k < numOfCAatom; k++) {
	                    int valq2 = (int) ((totalDistData[j * numOfCAatom + k]-minDistance) * noQuantLevel / (maxDistance-minDistance));
	                    calphadistmat[j][k] = valq2;
	                }
	            }
	            
	            //---- printing test
//	            for(int i =0;i<numOfCAatom;i++)
//	            {
//	            	for(int j=0;j<numOfCAatom;j++)
//	            	{
//	            		System.out.print(calphadistmat[i][j]+"  ");
//	            	}
//	            	System.out.println("\n");
//	            }
	            
	            //---Opencv
	            
	            Mat matrix = new Mat(numOfCAatom, numOfCAatom, CvType.CV_8UC1, new Scalar(0));  
	            
	            for(int i=0;i<numOfCAatom;i++)
	            {
	            	for(int j=0;j<numOfCAatom;j++)
	            	{
	            		matrix.put(i, j, calphadistmat[i][j]);
	            	}
	            }
	            //System.out.println(matrix.dump()); 
	            String testOutput = "/home/iamneaz/Downloads/Protein Project/pdbData/";
	            File outDir=new File(testOutput);
    			if(!outDir.exists()||!outDir.isDirectory())
    			{
    				outDir.mkdir();
    			}
	            
    			if(!matrix.empty())
    			{
    				Imgcodecs.imwrite(testOutput+"\\"+fileName+".jpg", matrix);
    				System.out.println("conversion successfull");
    			}
    			else
    			{
    				System.out.println("\t\t\t\t\tconversion unsuccessfull");
    			}
    			
	            
	            
	            
//	            printCalphamatImage(calphadistmat, numOfCAatom);
	            return calphadistmat.toString();
	            
	        } catch (Exception e) {
	        	//logger.fatal(e.getMessage(),e);
	            return "";
	        }
	    }


}
