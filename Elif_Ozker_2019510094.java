package AlgorithmAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Elif_Ozker_2019510094 {
	
	public static int GOLD_AMOUNT;
	public static int MAX_LEVEL_ALLOWED;
	public static int NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL;
	public static int n = 0;
	public static List<Hero> Pawns = new ArrayList<Hero>();
	public static List<Hero> Rooks = new ArrayList<Hero>();  
	public static List<Hero> Archers = new ArrayList<Hero>();  
	public static List<Hero> Knights = new ArrayList<Hero>();  
	public static List<Hero> Bishops = new ArrayList<Hero>();  
	public static List<Hero> WarShips = new ArrayList<Hero>();  
	public static List<Hero> Sieges = new ArrayList<Hero>();  
	public static List<Hero> Queens = new ArrayList<Hero>();  
	public static List<Hero> Kings = new ArrayList<Hero>(); 		
		
	public static void main(String[] args){  //Reading file and calling the main functions such as greedy dynammic and random approaches
		 List<String> heroes =new ArrayList<String>();
		
		 Scanner sc3 = new Scanner(System.in);
		 System.out.print("Enter GOLD_AMOUNT: ");
		 GOLD_AMOUNT = sc3.nextInt();
		 System.out.print("Enter MAX_LEVEL_ALLOWED: ");
		 MAX_LEVEL_ALLOWED = sc3.nextInt();
		 System.out.print("Enter NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL: ");
		 NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL = sc3.nextInt();
		 boolean flagControl = extraControl();
		 
		 
		 if(flagControl) {
			 Scanner sc = new Scanner(System.in);
				try {
					sc = new Scanner(new File("input_1.csv"));
				} catch (FileNotFoundException e) {
					System.out.println("No Such File or Directory!");
					e.printStackTrace();
				}
				 sc.useDelimiter(",");
				 sc.nextLine();	
				 while (sc.hasNext()) {			
						 heroes.add(sc.nextLine());		     
				 }
				 sc.close();	
				 n = heroes.size() / 9;//for we could not know the whether there are how many of the same hero type in the file but we know we have 9 hero type
				 creatingHeroes(heroes);
				 List<Hero> SelectedHeroes = Selection();

				
				 System.out.println("#TRIAL 1#");
				 System.out.println("User ->  Dynammic Programming");
				 long start1 = System.nanoTime();
				 dynammicProgrammingApproach(SelectedHeroes);
				 long end1 = System.nanoTime();   
				 System.out.println("Elapsed Time in nano seconds: "+ (end1-start1));
				 System.out.println(" ");
				 System.out.println("Computer -> Greedy Approach");
				 long start2 = System.nanoTime();
				 greedyApproach(SelectedHeroes);
				 long end2 = System.nanoTime();   
				 System.out.println("Elapsed Time in nano seconds: "+ (end2-start2));
				 SelectedHeroes = Selection();
				 System.out.println("------------------------------");
				 System.out.println("#TRIAL 2#");
				 System.out.println("User ->  Dynammic Programming");
				 long start3 = System.nanoTime();
				 dynammicProgrammingApproach(SelectedHeroes);
				 long end3 = System.nanoTime();   
				 System.out.println("Elapsed Time in nano seconds: "+ (end3-start3));
				 System.out.println(" ");
				 System.out.println("Computer -> Random Approach");
				 long start4 = System.nanoTime();
				 randomApproach(SelectedHeroes);
				 long end4 = System.nanoTime();  
				 System.out.println("Elapsed Time in nano seconds: "+ (end4-start4));
		 }
		


	}
	public static boolean extraControl() {//controlling if user enter the invalid gold amount or max level or available pieces per level
		boolean flag = true;
		if(GOLD_AMOUNT <5 || GOLD_AMOUNT >1200) {
			System.out.println("Please enter valid GOLD_AMOUNT!");
			flag = false;
		}
		if(MAX_LEVEL_ALLOWED < 1 || MAX_LEVEL_ALLOWED>9) {
			System.out.println("Please enter valid MAX_LEVEL_ALLOWED!");
			flag = false;
		}
		if(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL <1 || NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL>25) {
			System.out.println("Please enter valid  NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL!");
			flag = false;
		}
		return flag;
		
	}
	public static void creatingHeroes(List<String> heroes) {//after the reading our file we create heroes lists.
		 Pawns = createPawnList(heroes);
		 Rooks = createRookList(heroes);
		 Archers = createArcherList(heroes);
		 Knights = createKnightList(heroes);
		 Bishops =createBishopList(heroes);
		 WarShips =createWarShipList(heroes);
	     Sieges = createSiegeList(heroes);
	     Queens =createQueenList(heroes);
         Kings = createKingList(heroes);    
	}
	
	
	public static List<Hero> Selection() {//selection part according to allowed max level
		List<Hero> SelectedHeroes = new ArrayList<Hero>();		
		if(MAX_LEVEL_ALLOWED == 1) {			 			
			SelectedHeroes.addAll(Pawns);//only pawn
		}
		else if(MAX_LEVEL_ALLOWED == 2) {
			SelectedHeroes.addAll(Pawns);//pawn and rooks
			SelectedHeroes.addAll(Rooks);							
		}
		else if(MAX_LEVEL_ALLOWED == 3) { 	//pawn rooks and archer
			SelectedHeroes.addAll(Pawns);
			SelectedHeroes.addAll(Rooks);
			SelectedHeroes.addAll(Archers);				
		}
		else if(MAX_LEVEL_ALLOWED == 4) {//... and go on like this
			SelectedHeroes.addAll(Pawns);
			SelectedHeroes.addAll(Rooks);
			SelectedHeroes.addAll(Archers);
			SelectedHeroes.addAll(Knights);			
		}
		else if(MAX_LEVEL_ALLOWED == 5) {
			SelectedHeroes.addAll(Pawns);
			SelectedHeroes.addAll(Rooks);
			SelectedHeroes.addAll(Archers);
			SelectedHeroes.addAll(Knights);			
			SelectedHeroes.addAll(Bishops);
		}
		else if(MAX_LEVEL_ALLOWED == 6) {
			SelectedHeroes.addAll(Pawns);
			SelectedHeroes.addAll(Rooks);
			SelectedHeroes.addAll(Archers);
			SelectedHeroes.addAll(Knights);	
			SelectedHeroes.addAll(Bishops);
			SelectedHeroes.addAll(WarShips);
		}
		else if(MAX_LEVEL_ALLOWED == 7) {
			SelectedHeroes.addAll(Pawns);
			SelectedHeroes.addAll(Rooks);
			SelectedHeroes.addAll(Archers);
			SelectedHeroes.addAll(Knights);	
			SelectedHeroes.addAll(Bishops);
			SelectedHeroes.addAll(WarShips);
			SelectedHeroes.addAll(Sieges);
		}
		else if(MAX_LEVEL_ALLOWED == 8) {
			SelectedHeroes.addAll(Pawns);
			SelectedHeroes.addAll(Rooks);
			SelectedHeroes.addAll(Archers);
			SelectedHeroes.addAll(Knights);	
			SelectedHeroes.addAll(Bishops);
			SelectedHeroes.addAll(WarShips);
			SelectedHeroes.addAll(Sieges);
			SelectedHeroes.addAll(Queens);
			
		}
		else if(MAX_LEVEL_ALLOWED == 9) {
			SelectedHeroes.addAll(Pawns);
			SelectedHeroes.addAll(Rooks);
			SelectedHeroes.addAll(Archers);
			SelectedHeroes.addAll(Knights);	
			SelectedHeroes.addAll(Bishops);
			SelectedHeroes.addAll(WarShips);
			SelectedHeroes.addAll(Sieges);
			SelectedHeroes.addAll(Queens);
			SelectedHeroes.addAll(Kings);
		}
		return SelectedHeroes;
	}
	public static void dynammicProgrammingApproach(List<Hero>heroes) {//dynammic approach and printing part
		Hero hero1;
		List<String> selectedStatus = new ArrayList<String>();
		int GOLD_AMOUNT1 = GOLD_AMOUNT;
		int K[][] = new int[heroes.size()+1][GOLD_AMOUNT1+1];
		for (int i = 1; i < K.length; i++) {
			 hero1 = heroes.get(i-1);
			for (int j = 1; j <= GOLD_AMOUNT1; j++) {
				 if (i == 0 || j == 0)
	                    K[i][j] = 0;				 
				 else if(hero1.getGold() <= j) {
					 K[i][j] = Math.max(hero1.getAp()+ K[i - 1][j - hero1.getGold()], K[i - 1][j]);
				 }
				 else
	                 K[i][j] = K[i - 1][j];
			}
		}
		int W = GOLD_AMOUNT1;
		int res = K[heroes.size()][W];
		int n = heroes.size();
		int w = 0;
		w = W;
		int totalAttackPoint = 0;
		int howmuchgoldspend = 0;
		for (int i = n; i > 0 && res>0 ;i--) {
			
			if(res == K[i-1][w])
				continue;
			else {		
				if(!(selectedStatus.contains(heroes.get(i-1).getStatu()) && GOLD_AMOUNT1 >= heroes.get(i-1).getGold())){
					System.out.println(heroes.get(i-1).getName() + "," +heroes.get(i-1).getStatu()+ "," + heroes.get(i-1).getGold() + ","+ heroes.get(i-1).getAp());									
					totalAttackPoint += heroes.get(i-1).getAp();
					selectedStatus.add(heroes.get(i-1).getStatu());
					howmuchgoldspend += heroes.get(i-1).getGold();
					res = res - heroes.get(i-1).getAp();
					w = w - heroes.get(i-1).getGold();
					if(w < 0) {
						break;
					}
				}
			}
		}
		System.out.println("Total Attack Point: " + totalAttackPoint);
		System.out.println("Total Spending Gold : " + howmuchgoldspend);
 		
	}
	public static void greedyApproach(List<Hero> heroess) {	//greedyApproach and printing part
		int GOLD_AMOUNT1 = GOLD_AMOUNT;
		List<String> selectedStatus = new ArrayList<String>();
		List<Hero> heroess1 = heroess;
		heroess1.sort(Comparator.comparing(Hero::getCost).reversed());// with comparator we sort our list in the descending order
		int totalAttackPoint = 0;	
		int howmuchgoldspend = 0;		
		for (int j = 0;j<heroess1.size(); j++) {			
			if(heroess1.get(j).getGold()<= GOLD_AMOUNT1 && !(selectedStatus.contains(heroess1.get(j).getStatu()))) {			
				totalAttackPoint += heroess1.get(j).getAp();
				selectedStatus.add(heroess1.get(j).getStatu());
				GOLD_AMOUNT1 -= heroess1.get(j).getGold();					
				howmuchgoldspend += heroess1.get(j).getGold();
				System.out.println(heroess1.get(j).getName() + "," + heroess1.get(j).getStatu() + "," + heroess1.get(j).getGold() + "," + heroess1.get(j).getAp());
				
			}
		
		}		
		System.out.println("Total Attack Point : " + totalAttackPoint);
		System.out.println("Total Spending Gold : " + howmuchgoldspend);		
	}
	public static void printRandomHeroes(List<Hero> selectedForRandomHeroes) {// random printing part
		int totalAttackPoint = 0;		
		int howmuchgoldspending = 0;		
		for (int i = 0; i < selectedForRandomHeroes.size(); i++) {
			totalAttackPoint += selectedForRandomHeroes.get(i).getAp();
			howmuchgoldspending += selectedForRandomHeroes.get(i).getGold();
			System.out.println(selectedForRandomHeroes.get(i).getName() + "," + selectedForRandomHeroes.get(i).getStatu() + "," + selectedForRandomHeroes.get(i).getGold() + "," + selectedForRandomHeroes.get(i).getAp());
		}
		System.out.println("Total Attack Point: " + totalAttackPoint);
		System.out.println("Total Spending Gold:  " + howmuchgoldspending);
		
		
	}
	public static void randomApproach(List<Hero> heroes) {//random Approach selecting heroes part
		List<Hero> selectedForRandomHeroes = new ArrayList<Hero>();
		int GoldAmount = GOLD_AMOUNT;
		List<String> selectedStatus = new ArrayList<String>();
		Random rnd = new Random();				
		int i = 0;
		int whichLevel = rnd.nextInt( MAX_LEVEL_ALLOWED) + 1; //
		int howManyHeroesIChoose = rnd.nextInt( MAX_LEVEL_ALLOWED) + 1;
		int count = 0;
		while(i != howManyHeroesIChoose) {		
			if(whichLevel == 1) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Pawns.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}						
			}
			else if(whichLevel == 2) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Rooks.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}							
			}
			else if(whichLevel == 3) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Archers.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}								
			}
			else if(whichLevel == 4) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Knights.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}								
			}
			else if(whichLevel == 5) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Bishops.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}														
			}
			else if(whichLevel == 6) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = WarShips.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}																		
			}
			else if(whichLevel == 7) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Sieges.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}																
			}
			else if(whichLevel == 8) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Queens.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}																		
			}
			else if(whichLevel == 9) {
				int whichHero = rnd.nextInt(NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL);
				Hero randomHero = Kings.get(whichHero);
				if(GoldAmount >= randomHero.getGold() && !(selectedStatus.contains(randomHero.getStatu()))) {
					GoldAmount -= randomHero.getGold();
					selectedStatus.add(randomHero.getStatu());
					selectedForRandomHeroes.add(randomHero);
					i++;
				}		
											
			}	
			whichLevel = rnd.nextInt(MAX_LEVEL_ALLOWED) + 1;				
			count++;
			if(count > heroes.size())
				break;
		}
		printRandomHeroes(selectedForRandomHeroes);
	
		
	}	
    public static List<Hero> createPawnList(List<String> heroes) {
		
		for (int i = 0; i < NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero pawn = new Hero(split[0],split[1],gold,ap,cost,1);
			Pawns.add(pawn);
		}
		return Pawns;		
	}
	public static List<Hero> createRookList(List<String> heroes) {
		
		for (int i = n; i < n + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero rook = new Hero(split[0],split[1],gold,ap,cost,2);
			Rooks.add(rook);
		}
		return Rooks;		
	}
	public static List<Hero> createArcherList(List<String> heroes) {		
		
		for (int i = n*2; i < n*2 + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero archer = new Hero(split[0],split[1],gold,ap,cost,3);
			Archers.add(archer);
		}
		return Archers;		
	}
	public static List<Hero> createKnightList(List<String> heroes) {		
		
		for (int i =n*3; i < n*3 + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero knight = new Hero(split[0],split[1],gold,ap,cost,4);
			Knights.add(knight);
		}
		return Knights;		
	}
	public static List<Hero> createBishopList(List<String> heroes) {		
		
		for (int i = n*4; i < n*4 + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero Bishop = new Hero(split[0],split[1],gold,ap,cost,5);
			Bishops.add(Bishop);
		}
		return Bishops;
		
		
	}
	public static List<Hero> createWarShipList(List<String> heroes) {		
		
		for (int i = n*5; i < n*5 + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero WarShip = new Hero(split[0],split[1],gold,ap,cost,6);
			WarShips.add(WarShip);
		}
		return WarShips;				
	}
	public static List<Hero> createSiegeList(List<String> heroes) {		
		
		for (int i = n*6; i < n*6 + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero Siege = new Hero(split[0],split[1],gold,ap,cost,7);
			Sieges.add(Siege);
		}
		return Sieges;				
	}
	public static List<Hero> createQueenList(List<String> heroes) {		
		
		for (int i = n*7; i < n*7 + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero Queen = new Hero(split[0],split[1],gold,ap,cost,8);
			Queens.add(Queen);
		}
		return Queens;				
	}
    public static List<Hero> createKingList(List<String> heroes) {		
		
		for (int i = n*8; i < n*8 + NUMBER_OF_AVAILABLE_PIECES_PER_LEVEL; i++) {
			String[] split = heroes.get(i).split(",");
			int gold = Integer.valueOf(split[2]);
			int ap = Integer.valueOf(split[3]);
			double cost = Integer.valueOf(split[3]) / Integer.valueOf(split[2]);
			Hero King = new Hero(split[0],split[1],gold,ap,cost,9);
			Kings.add(King);
		}
		return Kings;
	}
}
