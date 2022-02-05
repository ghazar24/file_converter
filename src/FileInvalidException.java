
public class FileInvalidException extends Exception{
    

    
    public FileInvalidException() {
        super("Error: Input file cannot be parsed due to missing information\n" +
                "(i.e. month={}, title={}, etc.)");
    }
    public FileInvalidException(String missing, int fileNumber) {
        super("Error: Detected Empty Field!\n"
        		+ "=========================== \n"
        		+ "Problem detected with input file: Latex" + fileNumber + ".bib \n"
        		+ "file is invalid: Field "+missing+" is empty. Processing stoped at this point. Other Empty fields may be present as well!\n\n");
    }

    
    public String getMessage () {
        return super.getMessage();
    }
}