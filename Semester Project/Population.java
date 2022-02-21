public class Population {
    //Will consist of 0s and 1s
    Person[] wholePop; 
    
    //Initialize population array
    public Population(int populationSize) {
        this.wholePop = new Person[populationSize];
        for (int i = 0; i < populationSize; i++) {
            this.wholePop[i] = new Person();
        }
    }
}
