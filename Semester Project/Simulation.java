import java.text.DecimalFormat;

public class Simulation { 
    public static int POPULATION_SIZE = 0;
    public static int GROUP_SIZE = 8;
    public static int GROUP_COUNT = 0;
    public static double INFECTION_RATE = .02;
    public static int testCount = 0;
    public static int case1Count = 0;
    public static int case2Count = 0;
    public static int case3Count = 0;

    public static void main(String[] args) {
        //Run my pooled testing simulation on populations 
        //of various sizes ranging from 1000-1M people
        runSimulation(1000);
        runSimulation(10000);
        runSimulation(100000);
        runSimulation(1000000);
    }

    //Run simulation with given population size
    public static void runSimulation(int popSize) {
        POPULATION_SIZE = popSize;
        
        Population simulatedPop = new Population(POPULATION_SIZE);
        simulatedPop.wholePop = infectPeople(simulatedPop.wholePop);
        testPeople(simulatedPop.wholePop);
        printOutput();

        testCount = 0;
        case1Count = 0;
        case2Count = 0;
        case3Count = 0;
    }

    //Infect people in the population based on the infection rate
    public static Person[] infectPeople(Person[] population) {
        //Infect the first 20 people in the population array
        int peopleToInfect = (int)Math.round(INFECTION_RATE * population.length);
        for (int i = 0; i < peopleToInfect; i++) {
            population[i].isInfected = 1;
        }

        //Randomly distribute infected people throughout
        //the population with a Knuth shuffle
        for (int i = 0; i < population.length; i++) {
            //Create a "random" value
            int rand = i + (int) (Math.random() * (population.length - i));
            
            //Swap the value at this index with the value at 
            //the index of the randomly generated number
            Person temp = population[rand];
            population[rand] = population[i];
            population[i] = temp;
        }
        return population;
    }

    public static void testPeople(Person[] population) {
        //Place people into groups of 8 (125 groups in our case)
        GROUP_COUNT = POPULATION_SIZE / GROUP_SIZE;

        //Perform necessary tests on each group
        for (int i = 0; i < GROUP_COUNT; i++) {
            //Create a new testing pool
            Person[] currentPool = new Person[GROUP_SIZE];
            
            for (int j = i * GROUP_SIZE; j < ((i * GROUP_SIZE) + GROUP_SIZE); j++) {
                currentPool[j%GROUP_SIZE] = population[j];
            }

            //Test the intial group of 8 
            testCount++;
            if (checkForInfection(currentPool)) {
                //If an infection was found, 
                //divide pool into two equal size groups
                Person[] newPool1 =  new Person[GROUP_SIZE / 2];
                for (int j = 0; j < (currentPool.length / 2); j++) {
                    newPool1[j] = currentPool[j];
                }
                Person[] newPool2 =  new Person[GROUP_SIZE / 2];
                for (int j = (currentPool.length / 2); j < currentPool.length; j++) {
                    newPool2[j%(GROUP_SIZE / 2)] = currentPool[j];
                }
                
                //Test the two subgroups
                if ((checkForInfection(newPool1) && !checkForInfection(newPool2)) || (!checkForInfection(newPool1) && checkForInfection(newPool2))) {
                    //Case 2: If only one subgroup showed infection, 
                    //then we used 6 more tests (1 per subgroup + 4 for people in infected group)
                    testCount += 6;
                    case2Count++;
                }
                else {
                    //Case 3: If both subgroups showed infection, 
                    //then we used 10 more tests (1 per subgroup + 8 for people in infected groups)
                    testCount += 10;
                    case3Count++;
                }
            }
            else {
                //Only 1 test was needed because nobody was infected
                case1Count++;
            }
        }
    }

    public static boolean checkForInfection(Person[] pool) {
        int giantTube = 0;
        
        //Combine the isInfected value of everyone in the pool
        //as you would combine the biomarkers in real life
        for (int i = 0; i < pool.length; i++) {
            giantTube += pool[i].isInfected;
        }

        //If the combined value is still 0, then nobody was infected
        if (giantTube == 0) {
            return false;
        } 
        //If the combined value is > 0, then at least one person was infected
        else {
            return true;
        }
    }

    public static void printOutput() {
        DecimalFormat df = new DecimalFormat("#.##");  
        //Print the expected instances and tests
        System.out.println("Expected values for a population of size " + POPULATION_SIZE);
        System.out.println("Case (1): " + GROUP_COUNT + " x 0.8500 = " + Double.valueOf(df.format(GROUP_COUNT * 0.8500)) + " instances requiring " + Math.round(GROUP_COUNT * 0.8500) + " tests");
        System.out.println("Case (2): " + GROUP_COUNT + " x 0.1496 = " + Double.valueOf(df.format(GROUP_COUNT * 0.1496)) + " instances requiring " + Math.round((GROUP_COUNT * 0.1496) * 7) + " tests");
        System.out.println("Case (3): " + GROUP_COUNT + " x 0.0004 = " + Double.valueOf(df.format(GROUP_COUNT * 0.0004)) + " instances requiring " + Math.round((GROUP_COUNT * 0.0004) * 11) + " tests");
        System.out.println("----------------------------------------------------------------------------");
        int expectedTests = (int) (Math.round(GROUP_COUNT * 0.8500) + Math.round((GROUP_COUNT * 0.1496) * 7) + Math.round( (GROUP_COUNT * 0.0004) * 11));
        System.out.println(expectedTests + " tests are expected to screen a population of " + POPULATION_SIZE + " people for a disease with 2% infection rate.\n");

        //Print the actual numbers generated by my simulation
        System.out.println("Actual results for a population of size " + POPULATION_SIZE);
        System.out.println("Case (1): " + case1Count + " instances requiring " + case1Count + " tests");
        System.out.println("Case (2): " + case2Count + " instances requiring " + Math.round(case2Count * 7) + " tests");
        System.out.println("Case (3): " + case3Count + " instances requiring " + Math.round(case3Count * 11) + " tests");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println(testCount + " tests were actually used to screen a population of " + POPULATION_SIZE + " people for a disease with 2% infection rate.\n");
    }
}