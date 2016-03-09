package jenkins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by P3700509 on 3/2/2016.
 */
public class GatherStatistics extends Utils
{

    public void PassedPercentage(File workFile)
    {
        String fileLine = null;
        float totalTests=0;
        float passedTests=0;
        float failedTests=0;
        float skiped=0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(workFile));

            while ((fileLine = br.readLine()) != null)
            {
                totalTests++;
                String[] words = fileLine.split("\t");
                for (String item : words)
                    if (item.equals("PASSED")) passedTests++;
                    else if (item.equals("FAILED")) failedTests++;
                    else if (item.equals("SKIPED")) skiped++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        writeIntoFile(workFile,"\n"+"The total number of tests ran " + totalTests+"\n");
        writeIntoFile(workFile,"The total PASSED tests :"+passedTests+" representing "+100*(passedTests/totalTests)+"%"+"\n");
        writeIntoFile(workFile,"The total FAILED tests :"+failedTests+" representing "+100*(failedTests/totalTests)+"%"+"\n");
        writeIntoFile(workFile,"The total SKIPED tests :"+skiped+" representing "+100*(skiped/totalTests)+"%"+"\n");
    }

    public void GetTotalRunTime(File workFile)
    {
        String fileLine = null;

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SSS");
        Calendar totalRunTime = Calendar.getInstance();

        totalRunTime.set(Calendar.HOUR_OF_DAY ,0);
        totalRunTime.set(Calendar.MINUTE ,0);
        totalRunTime.set(Calendar.SECOND ,0);
        totalRunTime.set(Calendar.MILLISECOND ,0);

        try
        {
            BufferedReader bufferedReader = new BufferedReader( new FileReader(workFile));
            while ((fileLine = bufferedReader.readLine()) != null)
            {
                String[] words = fileLine.split("\\s");
                for (int i =0 ; i< words.length ; i++)
                {
                       if (words[i].equals("h")) totalRunTime.add(Calendar.HOUR_OF_DAY, Integer.valueOf(words[i-1]));
                       else if (words[i].equals("m")) totalRunTime.add(Calendar.MINUTE ,Integer.valueOf(words[i-1]));
                       else if (words[i].equals("s")) totalRunTime.add(Calendar.SECOND , Integer.valueOf(words[i-1]));
                       else if (words[i].equals("ms")) totalRunTime.add(Calendar.MILLISECOND ,Integer.valueOf(words[i-1]));

                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        writeIntoFile(workFile,"\n"+"Total run time " + sdf.format(totalRunTime.getTime())+"\n");
    }

    }



