import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {         // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }

    void playGame() {
        // declare local variables here: grain, population, etc.
        int numberOfPeople = 100;
        int numberOfBushels = 2800;
        int acresOfLand = 1000;
        int valueOfLand = 19;

        // statements go after the declations
        for(int i = 1; i <= 10; i++){

            // make a method for this
            yearlySummary(i, acresOfLand, numberOfPeople, valueOfLand, numberOfBushels);

            //buying land
            int acresToBuy = askHowManyAcresToBuy(valueOfLand, numberOfBushels);
            numberOfBushels -= acresToBuy * valueOfLand;
            acresOfLand += acresToBuy;

            //selling land
            if(acresToBuy == 0){
                int acresToSell = askHowManyAcresToSell(acresOfLand);
                acresOfLand -= acresToSell;
                numberOfBushels += (acresToSell * valueOfLand);
            }

            //feeding people
            int feedForPeople = askHowMuchGrainToFeedPeople(numberOfBushels);
            numberOfBushels -= feedForPeople;

            //planting acres
            int acresToPlant = askHowManyAcresToPlant(acresOfLand, numberOfPeople, numberOfBushels);
            numberOfBushels -= acresToPlant;



        }

    }

    void yearlySummary(int i, int acresOfLand, int numberOfPeople, int valueOfLand, int numberOfBushels){
        System.out.println("O GREAT HAMMURABI!");
        System.out.println("You are in year " + i + " of your ten year rule.");
        System.out.println("In the previous year (numberOfStarvedPeople) people starved to death.");
        System.out.println("In the previous year (numberOfPeopleMoved) people entered the kingdom.");
        System.out.println("The population is now " + numberOfPeople + ".");
        System.out.println("The city owns " + acresOfLand + " acres of land.");
        System.out.println("Land is currently worth " + valueOfLand + " bushels per acre.");
        System.out.println("There are " + numberOfBushels + " bushels in storage.");
    }

    int askHowManyAcresToBuy(int price, int bushels){
        int acresToBuy = 0;

        while(true) {
            System.out.println("How many acres of land do you want to buy?: ");
            acresToBuy = scanner.nextInt();

            if (acresToBuy * price < bushels) {
                // you will do the math in playGame()
                break;
            }
            System.out.println("You're too poor! Try a different number.");
        }

        return acresToBuy;
    }

    int askHowManyAcresToSell(int acresOwned){
        // DON'T ASK THIS QUESTION IF THEY ARE BUYING
        int acresToSell = 0;

        while(true){
            System.out.println("How many acres of land do you want to sell?: ");
            acresToSell = scanner.nextInt();

            if(acresToSell < acresOwned){
                break;
            }

            System.out.println("You don't even have that much land! Try again.");
        }

        return acresToSell;
    }

    int askHowMuchGrainToFeedPeople(int bushels){
        int grainToFeed = 0;

        while(true){
            System.out.println("How much would you like to feed the people?: ");
            grainToFeed = scanner.nextInt();

            if(grainToFeed < bushels){
                break;
            }

            System.out.println("You don't have that much. Try again.");
        }

        return grainToFeed;
    }

    int askHowManyAcresToPlant(int acresOwned, int population, int bushels){
        int acresToPlant = 0;

        while(true){
            System.out.println("How many acres would you like to plant?");
            acresToPlant = scanner.nextInt();

            if(acresToPlant < acresOwned && acresToPlant < population && acresToPlant < bushels){
                break;
            }
            else if(acresToPlant > acresOwned){
                System.out.println("Too many acres!");
            }
            else if(acresToPlant > population){
                System.out.println("Too many acres, not enough people!");
            }

            System.out.println("Too many acres, not enough bushels!");
        }

        return acresToPlant;
    }

    public int plagueDeaths(int i) {
        return 0;
    }

    public int starvationDeaths(int i, int i1) {
        return 0;
    }

    public boolean uprising(int i, int i1) {
        return false;
    }

    public int immigrants(int i, int i1, int i2) {
        return 0;
    }

    public int harvest(int i) {
        return 0;
    }

    public int grainEatenByRats(int i) {
        return 0;
    }

    public int newCostOfLand() {
        return 0;
    }

    //other methods go here
}