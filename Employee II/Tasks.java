import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Employee
 */
public class Tasks {

    public static void main(String[] args) {
        File file = new File("logFile.txt");

        //Try with resources
        try(BufferedReader reader = new BufferedReader(new FileReader(file));) {
            
            //Store task as key and subtasks as value
            HashMap<String, ArrayList<String>> hashTasks = new HashMap<String, ArrayList<String>>();

            //Store task as key and processing time as value
            HashMap<String, Integer> hashProcessingTime = new HashMap<String, Integer>();
            String line;

            //Store root tasks
            List<String> roots = new ArrayList<String>();
            while((line = reader.readLine())!=null)
            {


                String lines[] = line.split(" ");
                if(!lines[1].equals("null"))
                {
                    ArrayList<String> list;
                    if(!hashTasks.containsKey(lines[1]))
                    {
                        list = new ArrayList<String>();
                    }
                    else
                    {
                        list = hashTasks.get(lines[1]);
                    }
                    list.add(lines[0]);
                    hashTasks.put(lines[1], list);
                    
                }
                else
                    roots.add(lines[0]);

                hashProcessingTime.put(lines[0], Integer.parseInt(lines[2]));
            }
            
            for(String string: roots)
            {
               //DFS
                Integer processingTime = gethashProcessingTime(string, hashTasks, hashProcessingTime);
                System.out.println(string+" "+processingTime);
            }

        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static int  gethashProcessingTime(String string, HashMap<String, ArrayList<String>> hashTasks, HashMap<String, Integer> hashProcessingTime) {
        int processingTime = 0;

        if(hashProcessingTime.containsKey(string))
            processingTime = hashProcessingTime.get(string);

        if(!hashTasks.containsKey(string))
            return processingTime;

        for (String sub: hashTasks.get(string))
        {
            processingTime += gethashProcessingTime(sub, hashTasks, hashProcessingTime);
        }

        return processingTime;
    }
}

