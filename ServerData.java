
import java.io.*;
import java.util.*;

/**
 *
 * @author VK
 */
class ServerData {
    /* 
     * Emulating database fields with Map objects, and using the List as an index to the fields.
     */
    private Map<String, String> mdeptName = new HashMap<>();
    private Map<String, String> mdeptUser = new HashMap<>();
    private Map<String, String> mdeptPass = new HashMap<>();
    private Map<String, String> mdeptBalance = new HashMap<>();
    private List<String> listDeptCodes = new ArrayList<>();
    
    public ServerData(String filename) {
        File data = new File(filename);
        if (data.exists()) {
            try {
                readLines(data);
            }
            catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        else {
            try {
                createFile(filename);
                System.out.println("Test file created.");
            }
            catch (IOException e) {
                System.out.println("Test file creation failed.");
                System.out.println(e);
            }
        }
    }
    
    public void printData () {
        for (ListIterator<String> iter = listDeptCodes.listIterator(); iter.hasNext();) {
            String temp = iter.next();
            System.out.println(mdeptName.get(temp) + " "
                    + temp + " "
                    + mdeptUser.get(temp) + " "
                    + mdeptPass.get(temp) + " "
                    + mdeptBalance.get(temp) + " ");
        }
    }
    
    private final void readLines (File filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(filename));
        while (sc.hasNextLine()) {
            processLine(sc.nextLine());
        }
        sc.close();
    }
    
    private void processLine(String line) {
        Scanner sc = new Scanner(line);
        sc.useDelimiter(" ");
        if (sc.hasNext()) {
            String deptName = sc.next();
            String deptCode = sc.next();
            String deptUser = sc.next();
            String deptPass = sc.next();
            String deptBalance = sc.next();

            listDeptCodes.add(deptCode);
            
            mdeptName.put(deptCode, deptName);
            mdeptUser.put(deptCode, deptUser);
            mdeptPass.put(deptCode, deptPass);
            mdeptBalance.put(deptCode, deptBalance);
        }
        else {
            System.out.println("Empty.");
        }
    }
    
    private void createFile (String fileName) throws IOException {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            
            String initialBalance = "5000";
            String dept1 = "CS 60 csadmin hello123 " + initialBalance;
            String dept2 = "Engineering 50 engradmin abc123 " + initialBalance;
            String dept3 = "Math 30 mathadmin 314pi " + initialBalance;
            String dept4 = "English 20 engadmin Shakespere " + initialBalance;
            
            outputStream.println(dept1);
            outputStream.println(dept2);
            outputStream.println(dept3);
            outputStream.println(dept4);
        }
        finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
