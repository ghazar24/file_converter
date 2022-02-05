
import java.util.Scanner;
import java.io.*;


public class BibCreator {
	static int count=0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("------------Welcome To BibCreator!------------\n");
		 
		
		Scanner sc=null;
		int i=1;
		String FileName="";
		
        // check if all latex files from 1 to 10 are Exist
        	for (i = 1; i <= 10;i++)
            {
        		try {
				sc= new Scanner(new File("Latex" + i + ".bib"));
				sc.close();
				
        		} catch(FileNotFoundException e){
                    System.out.println("Could not open input file Latex"+i+".bib for reading. \nPlease check if the file is exists! \nProgram will terminate after closing any opened files, and then exits.");
                    System.exit(0);
                    
                }
        		
            }
        	
        	 
             // create IEEE, ACM, NJ Empty files 
                 for (int j=1;j<=10;j++) {
                	 
                     File myfileIEEE = new File("IEEE" + j + ".json");
                     File myfileACM = new File("ACM" + j + ".json");
                     File myfileNJ = new File("NJ" + j + ".json");
                     PrintWriter pw1=null;
                     PrintWriter pw2=null;
                     PrintWriter pw3=null;
                 	try{
                 		
                 	FileName="IEEE";
                 	pw1 = new PrintWriter(myfileIEEE);
                 	  
                     FileName="ACM";
                     pw2 = new PrintWriter(myfileACM);
                     FileName="NJ";
                     pw3 = new PrintWriter(myfileNJ);
                    
                     
                     
                 	}
                 	catch (FileNotFoundException e){
                         System.out.println("Could not create output file "+FileName+j+".json\nWill now terminate!");
                        
                        System.exit(0);
                     }
                 	finally {
                 		 pw1.close();
                         pw2.close();
                         pw3.close();
                 	}
                 }
     
       
            
            for(i=1;i<=10;i++) {
               processFilesForValidation(i);
            }
            
            System.out.println("A total of " + count  +" files were invalid. There are only "+(10-count)+" valid files processed");
            displayJSONFile();
        
	
	
	    }
	
	// Read from latex files ad print to IEEE.. files
	public static void processFilesForValidation(int latexFileNumber) {
		
		String problem = null;
        String authorIEEE=null;
        String authorACM=null;
        String authorNJ=null;
        String journal=null;
        String title=null;
        String year=null;
        String volume=null;
        String number=null;
        String pages=null;
        String keywords=null;
        String doi=null;
        String ISSN=null;
        String month=null;
        int countACM=0;
        
        
        File myfile = new File("Latex"+latexFileNumber+".bib");
        File myfileIEEE = new File("IEEE" + latexFileNumber + ".json");
        File myfileACM = new File("ACM" + latexFileNumber + ".json");
        File myfileNJ = new File("NJ" + latexFileNumber + ".json");
        PrintWriter pw1=null;
        PrintWriter pw2=null;
        PrintWriter pw3=null;
        Scanner sc = null;
        
       
        	try {
				sc= new Scanner(myfile);
				pw1=new PrintWriter(myfileIEEE);
				pw2=new PrintWriter(myfileACM);
				pw3=new PrintWriter(myfileNJ);
				while(sc.hasNext()) {
					String line = sc.nextLine().trim();
					 if (line.equals("")) {
		                    continue;
		                }
					 else if(line.contains("ISSN={},")) {
							problem="ISSN";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("author={},")) {
							problem="author";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("journal={},")) {
							problem="journal";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("title={},")) {
							problem="title";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("year={},")) {
							problem="year";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("volume={},")) {
							problem="volume";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("number={},")) {
							problem="number";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("pages={},")) {
							problem="pages";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("keywords={},")) {
							problem="keywords";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("doi={},")) {
							problem="doi";
							throw new FileInvalidException(problem,latexFileNumber);
						}
						else if(line.contains("month={},")) {
							problem="month";
							throw new FileInvalidException(problem,latexFileNumber);
						}
				
						else if (line.contains("author={")) {
		                    authorIEEE=line.substring(8,line.indexOf("}"));
		                    authorACM=line.substring(8,line.indexOf("}"));
		                    authorNJ=line.substring(8,line.indexOf("}"));
		                    authorIEEE=authorIEEE.replaceAll(" and",",");
		                    authorNJ=authorNJ.replaceAll("and","&");
		                    if (line.contains("and"))
		                        authorACM=authorACM.substring(0,authorACM.indexOf("and")-1)+" et al";
		                    else
		                        authorACM=authorACM+ "et al";
		                    countACM++;
		                } else if (line.contains("journal={")) {
		                    journal=line.substring(9,line.indexOf("}"));
		                } else if (line.contains("title={")) {
		                    title=line.substring(7,line.indexOf("}"));
		                } else if (line.contains("year={")) {
		                    year=line.substring(6,line.indexOf("}"));
		                } else if (line.contains("volume={")) {
		                    volume=line.substring(8,line.indexOf("}"));
		                } else if (line.contains("number={")) {
		                    number=line.substring(8,line.indexOf("}"));
		                } else if (line.contains("pages={")) {
		                    pages=line.substring(7,line.indexOf("}"));
		                } else if (line.contains("keywords={")) {
		                    keywords=line.substring(10,line.indexOf("}"));
		                } else if (line.contains("doi={")) {
		                    doi=line.substring(5,line.indexOf("}"));
		                } else if (line.contains("ISSN={")) {
		                    ISSN=line.substring(6,line.indexOf("}"));
		                } else if (line.contains("month={")) {
		                    month=line.substring(7,line.indexOf("}"));
		                    pw1.println(authorIEEE+". "+"\""+title+"\""+", "+journal+", vol. "+volume+", no. "+number+", p. "+pages+", "+month+" "+year+".\n");
		                    pw2.println("["+countACM+"] "+authorACM+". "+title+". "+year+". "+journal+". "+volume+", "+number+" ("+year+")"+", "+pages+". DOI:https://doi.org/"+doi+".\n");
		                    pw3.println(authorNJ+". "+title+". "+journal+". "+volume+", "+pages+"("+year+")"+".\n");
		                }
						 
					
				}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileInvalidException e) {
					// TODO Auto-generated catch block
					count++;
					System.out.println(e.getMessage());
					pw1.close();
					pw2.close();
					pw3.close();
					myfileIEEE.delete();
					myfileACM.delete();
					myfileNJ.delete();
				}
        	finally {
        		sc.close();
        		pw1.close();
				pw2.close();
				pw3.close();
        	}
				
				
				
			
        
        
       
	}
	
	
	
	// REad from json files in the new formats
	public static void displayJSONFile()
	{
		Scanner sc = new Scanner(System.in);
		
		int Chances = 2;
		
		String fileName;
		
		FileReader fileReader;
				
		while(Chances > 0)
		{
			System.out.print("Please enter the name of one of the files that you need to review:");
			
			fileName = sc.next().trim();
			
			File jsonFile = new File(fileName);
			
			if(jsonFile.exists())
			{		
				try
				{
					fileReader = new FileReader(fileName);
					BufferedReader br = new BufferedReader(fileReader);
					
					String fileLine;
					System.out.println("Here are the contents of succefully created Jason File: "+fileName);			
					while((fileLine = br.readLine()) != null)
					{
						System.out.println(fileLine);	
					}
					
					br.close();
					System.out.println("Goodbye! Hope you have enjoyed creating the needed files using bibCreator");
					System.exit(0);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{		
				Chances = Chances - 1;
				
				System.out.println("Could not open input file. File does not exist; possible it could not be created!. Remaining chance = " + Chances);
			}		
		}//end while
		
		sc.close();
		
		System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
		
		System.exit(0);
	}
	
}
	



	
	
	
	 