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
        int peopleStarved = 0;
        int peopleMoved = 0;
        int harvest = 0;
        int harvestPerAcre = 0;
        int bushelsAteByRats = 0;
        int plagueDeaths = 0;

        // statements go after the declarations
        for(int i = 1; i <= 10; i++){

            // make a method for this
            yearlySummary(i, acresOfLand, numberOfPeople, valueOfLand, numberOfBushels, peopleStarved, peopleMoved, harvest, harvestPerAcre, bushelsAteByRats, plagueDeaths);

            //resetting special event values
            peopleMoved = 0;

            //buying land
            int acresToBuy = askHowManyAcresToBuy(valueOfLand, numberOfBushels);
            numberOfBushels -= (acresToBuy * valueOfLand);
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


            //special events
            plagueDeaths = plagueDeaths(numberOfPeople);
            numberOfPeople -= plagueDeaths;
            //putting it in a variable to use in if statement later
            peopleStarved = starvationDeaths(numberOfPeople, feedForPeople);

            if(uprising(numberOfPeople, peopleStarved)){
                System.out.println("Uprising!!!!!");
                break;
            }

            numberOfPeople -= peopleStarved;

            //there are only immigrants if people are not starving
            if(peopleStarved == 0){
                peopleMoved = immigrants(numberOfPeople, acresOfLand, numberOfBushels);
                numberOfPeople += peopleMoved;
            }

            harvest = harvest(acresToPlant);
            harvestPerAcre = harvest / acresToPlant;
            numberOfBushels += harvest;

            //grainEatenByRats is returning a percent
            bushelsAteByRats = (grainEatenByRats(numberOfBushels)/100 * numberOfBushels);
            numberOfBushels -= bushelsAteByRats;

            valueOfLand = newCostOfLand();

        }

        System.out.println("Done");
        // final summary here

    }

    void yearlySummary(int i, int acresOfLand, int numberOfPeople, int valueOfLand, int numberOfBushels, int peopleStarved, int peopleMoved, int numberOfHarvest, int bushelPerAcre, int numberOfDestroyedBushels, int plagueDeaths){
        System.out.println("O GREAT HAMMURABI!");
        System.out.println("You are in year " + i + " of your ten year rule.");
        System.out.println("In the previous year " + peopleStarved + " people starved to death.");
        System.out.println("In the previous year " + peopleMoved + " people entered the kingdom.");

        if(plagueDeaths != 0){
            System.out.println("A plague had beseiged your people! " + plagueDeaths + " people lost their lives.");
        }

        System.out.println("The population is now " + numberOfPeople + ".");
        System.out.println("We harvested " + numberOfHarvest + " bushels at " + bushelPerAcre + " per acre.");
        System.out.println("Rats destroyed " + numberOfDestroyedBushels + " bushels, leaving " + numberOfBushels + " bushels in storage.");
        System.out.println("The city owns " + acresOfLand + " acres of land.");
        System.out.println("Land is currently worth " + valueOfLand + " bushels per acre.");

    }

    int askHowManyAcresToBuy(int price, int bushels){
        int acresToBuy = 0;

        while(true) {
            System.out.println("How many acres of land do you want to buy?: ");
            acresToBuy = scanner.nextInt();

            if (acresToBuy * price <= bushels) {
                // you will do the math in playGame()
                break;
            }
            System.out.println("You're too poor! Great Hammurabi, you only have " + bushels + " bushels.");
        }

        return acresToBuy;
    }

    int askHowManyAcresToSell(int acresOwned){
        // DON'T ASK THIS QUESTION IF THEY ARE BUYING
        int acresToSell = 0;

        while(true){
            System.out.println("How many acres of land do you want to sell?: ");
            acresToSell = scanner.nextInt();

            if(acresToSell <= acresOwned){
                break;
            }

            System.out.println("You don't even have that much land, great Hammurabi! Try again.");
        }

        return acresToSell;
    }

    int askHowMuchGrainToFeedPeople(int bushels){
        int grainToFeed = 0;

        while(true){
            System.out.println("How much would you like to feed the people?: ");
            grainToFeed = scanner.nextInt();

            if(grainToFeed <= bushels){
                break;
            }

            System.out.println("You don't have that much. O great Hammurabi, you only have " + bushels + " bushels.");
        }

        return grainToFeed;
    }

    int askHowManyAcresToPlant(int acresOwned, int population, int bushels){
        int acresToPlant = 0;

        while(true){
            System.out.println("How many acres would you like to plant?");
            acresToPlant = scanner.nextInt();

            if(acresToPlant <= acresOwned && acresToPlant <= population && acresToPlant <= bushels){
                break;
            }
            else if(acresToPlant >= acresOwned){
                System.out.println("Too many acres!");
            }
            else if(acresToPlant > population){
                System.out.println("Not enough people! Great Hammurabi, your population is only " + population + " people.");
            }
            else if(acresToPlant > bushels){
                System.out.println("Too many acres, not enough bushels! O great Hammurabi, you only have " + bushels + " bushels!");
            }
        }

        return acresToPlant;
    }

    public int plagueDeaths(int population) {
        // chances of plague happening
        if(rand.nextInt(100) < 15) {
            return population / 2;
        }
        return 0;
    }

    public int starvationDeaths(int numberOfPeople, int bushelsToFeed) {
        if(bushelsToFeed < numberOfPeople * 20){
            return numberOfPeople - (bushelsToFeed / 20);
        }
        return 0;
    }

    public boolean uprising(int population, int howManyPeopleStarved) {
        // end game if true
        int percent = (int)((double) howManyPeopleStarved / population * 100);
        return percent >= 45;
    }

    public int immigrants(int population, int acresOwned, int grainInStorage) {
        return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
    }

    public int harvest(int acres) {
        return acres * rand.nextInt(6) + 1;
    }

    public int grainEatenByRats(int numberOfBushels) {
        if(rand.nextInt(100) < 40){
            // range 10% - 30% of grain
            // test is expecting percent returned, not actual amount
            // of grain
            return  rand.nextInt(30 - 10 + 1) + 10;
        }
        return 0;
    }

    public int newCostOfLand() {
        return rand.nextInt(23 - 17 + 1) + 17;
    }

    //other methods go here
}