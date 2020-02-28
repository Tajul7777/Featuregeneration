package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ProteinExtraFeatures {
	//variables
	static String parentFolder = "F:\\Thesis\\Alternate Dataset";
	static String set = "train";
//	static String header = "C_count, N_count, O_count, N1+_count, N+1_count, atom_P_1, atom_P_2, atom_P_3, atom_P_4, atom_P_5, atom_P_6, atom_P_7, atom_P_8, atom_P_9, atom_P_10, atom_P_11, atom_P_12, atom_P_13, atom_P_14, atom_P_15, atom_P_16, atom_P_17, atom_P_18, atom_P_19, atom_P_20, atom_P_21, atom_P_22, atom_P_23, atom_P_24, atom_P_25, atom_P_26, atom_P_27, atom_P_28, atom_P_29, atom_P_30, atom_P_31, atom_P_32, atom_P_33, atom_P_34, atom_P_35, atom_P_36, atom_P_37, atom_P_38, atom_P_39, atom_P_40, atom_P_41, atom_P_42, atom_P_43, atom_P_44, atom_P_45, atom_P_46, atom_P_47, atom_P_48, atom_P_49, atom_P_50, atom_P_51, atom_P_52, atom_P_53, atom_P_54, atom_P_55, atom_P_56, atom_P_57, atom_P_58, atom_P_59, atom_P_60, atom_P_61, atom_P_62, atom_P_63, atom_P_64, atom_P_65, atom_P_66, atom_P_67, atom_P_68, atom_P_69, atom_P_70, atom_P_71, atom_P_72, atom_P_73, atom_P_74, atom_P_75, atom_P_76, atom_P_77, atom_P_78, atom_P_79, atom_P_80, atom_P_81, atom_P_82, atom_P_83, atom_P_84, atom_P_85, atom_P_86, atom_P_87, atom_P_88, atom_P_89, atom_P_90, atom_P_91, atom_P_92, atom_P_93, atom_P_94, atom_P_95, atom_P_96, atom_P_97, atom_P_98, atom_P_99, atom_P_100, C~N_P, min_C~N_distance, max_C~N_distance, pro_name\n";
	static String header = "C_count(%), N_count(%), O_count(%), N1+_count(%), N+1_count(%), atom_P_1, atom_P_2, atom_P_3, atom_P_4, atom_P_5, atom_P_6, atom_P_7, atom_P_8, atom_P_9, atom_P_10, atom_P_11, atom_P_12, atom_P_13, atom_P_14, atom_P_15, atom_P_16, atom_P_17, atom_P_18, atom_P_19, atom_P_20, atom_P_21, atom_P_22, atom_P_23, atom_P_24, atom_P_25, atom_P_26, atom_P_27, atom_P_28, atom_P_29, atom_P_30, atom_P_31, atom_P_32, atom_P_33, atom_P_34, atom_P_35, atom_P_36, atom_P_37, atom_P_38, atom_P_39, atom_P_40, atom_P_41, atom_P_42, atom_P_43, atom_P_44, atom_P_45, atom_P_46, atom_P_47, atom_P_48, atom_P_49, atom_P_50, atom_P_51, atom_P_52, atom_P_53, atom_P_54, atom_P_55, atom_P_56, atom_P_57, atom_P_58, atom_P_59, atom_P_60, atom_P_61, atom_P_62, atom_P_63, atom_P_64, atom_P_65, atom_P_66, atom_P_67, atom_P_68, atom_P_69, atom_P_70, atom_P_71, atom_P_72, atom_P_73, atom_P_74, atom_P_75, atom_P_76, atom_P_77, atom_P_78, atom_P_79, atom_P_80, atom_P_81, atom_P_82, atom_P_83, atom_P_84, atom_P_85, atom_P_86, atom_P_87, atom_P_88, atom_P_89, atom_P_90, atom_P_91, atom_P_92, atom_P_93, atom_P_94, atom_P_95, atom_P_96, atom_P_97, atom_P_98, atom_P_99, atom_P_100, C~N_P(%), min_C~N_distance, max_C~N_distance, pro_name\n";
	static String[] atomArray = {"C", "N", "O", "N1+", "N+1"};
	static int[] chemicalNumber = {12, 14, 16, 14, 14};
	static int sequenceLength = 100;
	static double bondTheshold = 41.10895;
	
	//for info
	static String atoms = "";
	static int[] globalAtomCount = new int[atomArray.length];
	static int[] maxGlobalAtomCount = new int[atomArray.length];
	static int uniqueAtomCount = 0;
	static int maxLineCount = 0;
	static String largestData;
	static File outFile = new File(parentFolder+"\\Extra Features\\Protein Extra Feature Vectors ("+set.toLowerCase()+").csv");
	static File infoFile = new File(parentFolder+"\\Extra Features\\Protein Extra Feature Info ("+set.toLowerCase()+").csv");
	static FileOutputStream output, info;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File folder=new File(parentFolder+"\\"+set.toLowerCase()+"_data");
		File[] listOfFiles = folder.listFiles();
		System.out.println("Data folder name: \"" + folder.getName() +"\"");
		//going inside each folder
		try {
			output = new FileOutputStream(outFile);
			output.write(header.getBytes());
			for(int i=0;i<listOfFiles.length;i++){
				if (listOfFiles[i].isFile()) {
					File fin=new File(listOfFiles[i].getPath());
					if(fin.exists())
					{
						if(listOfFiles[i].getName().endsWith("_pro_cg.pdb")) {
							System.out.println("--File: " + listOfFiles[i].getName());
							String[] tokens = listOfFiles[i].getName().split("_");
							String outString = runFeatureExtraction(fin) + tokens[0] + "\n";
//							System.out.println(atoms);
							output.write(outString.getBytes());
						}
					}
					else
					{
						System.out.println(listOfFiles[i].getName()+": file  doesn't exist");
					}
				}
				else if (listOfFiles[i].isDirectory()) {
					System.out.println("-Directory: " + listOfFiles[i].getName());
				}
			}
			output.close();
			info = new FileOutputStream(infoFile);
			info.write(("Atoms("+uniqueAtomCount+")," +atoms + "\n").getBytes());
			info.write(("Max," + Arrays.toString(maxGlobalAtomCount).replace("[", "").replace("]", "") + "\n").getBytes());
			info.write(("Total," + Arrays.toString(globalAtomCount).replace("[", "").replace("]", "") + "\n").getBytes());
			info.write(("\nLargest Protein PDB," + largestData + "(Length: "+maxLineCount + ")\n").getBytes());
			info.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nAtoms("+uniqueAtomCount+"): " +atoms.replaceAll(",\\s*$", ""));
		System.out.println("Max counts: " + Arrays.toString(maxGlobalAtomCount).replace("[", "").replace("]", "") + "\n");
		System.out.println("Total counts: "+ Arrays.toString(globalAtomCount).replace("[", "").replace("]", ""));
		System.out.print("\nLargest ligand PDB: "+largestData);
		System.out.println(" (Length: "+maxLineCount+")");
		
	}
	
	public static String runFeatureExtraction(File pdbFormatFile) {
		String outString = "";
    	int lineCount = 0;
		
		//for counting atoms
		int countArray[] = new int[atomArray.length];
		Arrays.fill(countArray, 0);
		
		//for atom's position
		int positionArray[] = new int[sequenceLength];
		Arrays.fill(positionArray, 0);
		
		//for atom bond count
		ArrayList<double[]>[][] importantAtoms = new ArrayList[2][1];	//C,N
		importantAtoms[0][0] = new ArrayList<double[]>();	//C
		importantAtoms[1][0] = new ArrayList<double[]>();	//N
		int[] bond = new int[1];	//C~N
		Arrays.fill(bond, 0);
		double[] minDistance = new double[1];	//C~N
		Arrays.fill(minDistance, Double.POSITIVE_INFINITY);
		double[] maxDistance = new double[1];	//C~N
		Arrays.fill(maxDistance, Double.MIN_VALUE);
		
        try {
			Scanner sc = new Scanner(pdbFormatFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                line = line.replace("-", " -"); //for separating attached values
                StringTokenizer strTok = null;
                if(set.equalsIgnoreCase("train"))
                	strTok = new StringTokenizer(line, " ");
//                else if(set.equalsIgnoreCase("test")) //when test
//                	strTok = new StringTokenizer(line, "\t");
                int numOfTokens = strTok.countTokens();
                String tokens[] = new String[numOfTokens];
                int tokenid = 0;
                while (strTok.hasMoreTokens()) {
                    tokens[tokenid++] = strTok.nextToken();
                }
                
                if(set.equalsIgnoreCase("train")) {
                	String str = tokens[numOfTokens-1];
                	
                	//get all atoms name
                	if(!atoms.contains(str)) {
                		atoms += str+", ";
                		uniqueAtomCount++;
                	}
                	
                	//count atoms
                	for(int i=0; i<atomArray.length; i++) {
                		if(str.equalsIgnoreCase(atomArray[i])) {
                			countArray[i]++;
                			globalAtomCount[i]++;
                			if(lineCount < sequenceLength)
                				positionArray[lineCount] = chemicalNumber[i];
                			if(countArray[i] > maxGlobalAtomCount[i])
                				maxGlobalAtomCount[i] = countArray[i];
                		}
                	}
                	
                	//atom bonding
                	double[] xyz = new double[3];
                	int lastIndex = numOfTokens-1;
            		int occurance = tokens[lastIndex-1].length() - tokens[lastIndex-1].replace(".", "").length();
            		if(occurance == 2)
            			lastIndex++;
                	if(str.equalsIgnoreCase("C")) {
                		xyz[0] = Double.parseDouble(tokens[lastIndex-5]);
                        xyz[1] = Double.parseDouble(tokens[lastIndex-4]);
                        xyz[2] = Double.parseDouble(tokens[lastIndex-3]);
                		importantAtoms[0][0].add(xyz);
                	}
                	else if(str.equalsIgnoreCase("N")) {
                		xyz[0] = Double.parseDouble(tokens[lastIndex-5]);
                        xyz[1] = Double.parseDouble(tokens[lastIndex-4]);
                        xyz[2] = Double.parseDouble(tokens[lastIndex-3]);
                		importantAtoms[1][0].add(xyz);
                	}
                }
                lineCount++;
            }
            sc.close();
            if(lineCount > maxLineCount) {
            	maxLineCount = lineCount;
            	largestData = pdbFormatFile.getName();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        //storing atom's count
        for(int i=0; i<atomArray.length; i++) {
//        	outString += countArray[i] + ", ";
        	outString += ( (double)countArray[i]/lineCount)*100 + ", ";
        }
        
        //storing atom's position
        for(int i=0; i<positionArray.length; i++) {
        	outString += positionArray[i]+", ";
        }
        
        //C~N
    	for(int i=0; i<importantAtoms[0][0].size(); i++) {
    		for(int j=0; j<importantAtoms[1][0].size(); j++) {
    			double[] C_xyz = importantAtoms[0][0].get(i);
    			double[] N_xyz = importantAtoms[1][0].get(j);
				double distance = Math.sqrt( Math.pow((C_xyz[0]-N_xyz[0]), 2) + Math.pow((C_xyz[1]-N_xyz[1]), 2) + Math.pow((C_xyz[2]-N_xyz[2]), 2) );
				if(distance <= bondTheshold)
					bond[0]++;
				if(distance > maxDistance[0])
					maxDistance[0] = distance;
				if(distance < minDistance[0])
					minDistance[0] = distance;
    		}
    	}
    	
    	//storing atom's bond count
    	for(int i=0; i<bond.length; i++) {
//    		outString += bond[i] + ", ";
    		
    		int sum = bond[0];
    		if(sum != 0)
    			outString += ( (double)bond[i] / sum )*100 + ", ";
    		else
    			outString += 0 + ", ";
    		
    		if(minDistance[i] == Double.POSITIVE_INFINITY)
    			outString += "-" + ", ";
    		else
    			outString += minDistance[i] + ", ";
    		
    		if(maxDistance[i] == Double.MIN_VALUE)
    			outString += "-" + ", ";
    		else
    			outString += maxDistance[i] + ", ";
        }
    	
		return outString;
    }

}
