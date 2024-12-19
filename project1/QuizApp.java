package project1;

import java.util.Scanner;

class Player 
{
    String name;
    int moneyWon;
    boolean isReadyToPlay;
    boolean hasUsedLifeline1;
    boolean hasUsedLifeline2;

    public Player(String name) 
    {
        this.name = name;
        this.moneyWon = 0;
        this.isReadyToPlay = false;
        this.hasUsedLifeline1 = false;
        this.hasUsedLifeline2 = false;
    }

    public void setReadyToPlay(boolean isReady) 
    {
        this.isReadyToPlay = isReady;
    }

    public void addMoney(int amount) 
    {
        this.moneyWon += amount;
    }
}

public class QuizApp 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Collect player details
        System.out.print("Enter your name:- ");
        
        String playerName = scanner.nextLine();
        System.out.println("Enter your roll number");
        String playerRollNo = scanner.nextLine();
        System.out.println("enter your age:-");
        String playerAge = scanner.nextLine();
        System.out.println("enter your Email:-");
        String playerEmail = scanner.nextLine();
        System.out.println("enter your village name:-");
        String playerVillage = scanner.nextLine();
        System.out.println("Enter you PhoneNumber:-");
        String playerPhoneNumber = scanner.nextLine();
        Player player = new Player(playerName);

        // Ask if the player is ready to take the quiz
        System.out.print(player.name + ", are you ready to take the quiz? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("no")) 
        {
            System.out.println("Game terminated. Goodbye " + player.name + "!");
            return; 
        }

        player.setReadyToPlay(true);
        if (player.isReadyToPlay) 
        {
            // Display rules
            System.out.println("Rules: ");
            System.out.println("1. You will be asked 5 questions.");
            System.out.println("2. You will have 2 lifelines.");
            System.out.println("3. Each lifeline can only be used once.");
            System.out.println("4. If you answer wrong, the game will end and you will win the amount earned till now.");
            System.out.println("5. Correct answers will reward you money.");
            System.out.println("Let's start!");

            // Start the quiz
            askQuestion(scanner, player, 1);
        }

        scanner.close();
    }

    public static void askQuestion(Scanner scanner, Player player, int questionNumber) 
    {
        // Define questions, answers, rewards, and lifelines
        String[] questions = {
                "What is the capital of France?",
                "What is 2 + 2?",
                "Which planet is known as the Red Planet?",
                "Who is the author of Harry Potter?",
                "What is the largest mammal?"
        };

        String[][] options = 
        	{
                {"1. Paris", "2. London", "3. Berlin", "4. Madrid"},
                {"1. 3", "2. 4", "3. 5", "4. 6"},
                {"1. Earth", "2. Mars", "3. Venus", "4. Jupiter"},
                {"1. J.K. Rowling", "2. George Orwell", "3. Mark Twain", "4. J.R.R. Tolkien"},
                {"1. Elephant", "2. Whale", "3. Giraffe", "4. Blue Whale"}
        };

        int[] correctAnswers = {1, 2, 2, 1, 4}; // Correct answer indexes (starting from 1)
        int[] rewards = {1000, 2000, 3000, 4000, 5000}; // Rewards for each correct answer

        // Question 1 to 5 logic
        if (questionNumber <= questions.length) 
        {
            // Display question and options
            System.out.println("\nQuestion " + questionNumber + ": " + questions[questionNumber - 1]);
            for (String option : options[questionNumber - 1]) 
            {
                System.out.println(option);
            }

            // Ask user to choose an answer or use a lifeline
            System.out.println("Choose an option (1-4) or use a lifeline (type 'lifeline1' or 'lifeline2'):");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("lifeline1") || choice.equalsIgnoreCase("lifeline2")) 
            {
                useLifeline(scanner, player, choice, questionNumber, correctAnswers, options);
            } 
            else 
            {
                int answer = Integer.parseInt(choice);

                // Check if the answer is correct
                if (answer == correctAnswers[questionNumber - 1]) 
                {
                    System.out.println("Correct answer! You won: $" + rewards[questionNumber - 1]);
                    player.addMoney(rewards[questionNumber - 1]);
                    System.out.println("Total money won: $" + player.moneyWon);

                    
                    if (questionNumber < questions.length) {
                        askQuestion(scanner, player, questionNumber + 1);
                    }
                    else 
                    {
                        System.out.println("Congratulations! You have completed the quiz!");
                    }
                }
                else 
                {
                    System.out.println("Wrong answer! You won: $" + player.moneyWon);
                    System.out.println("Game over!");
                }
            }
        }
    }

    public static void useLifeline(Scanner scanner, Player player, String lifelineChoice, int questionNumber, int[] correctAnswers, String[][] options) 
    {
        if (lifelineChoice.equalsIgnoreCase("lifeline1") && !player.hasUsedLifeline1) 
        {
            player.hasUsedLifeline1 = true;
            System.out.println("You have used Lifeline 1! Here is the 50:50:");
            
            removeWrongAnswers(options[questionNumber - 1], correctAnswers[questionNumber - 1]);
            askQuestion(scanner, player, questionNumber); 
        } 
        else if (lifelineChoice.equalsIgnoreCase("lifeline2") && !player.hasUsedLifeline2) 
        {
            player.hasUsedLifeline2 = true;
            System.out.println("You have used Lifeline 2! Here is the 'Ask the Audience' lifeline.");
            
            System.out.println("Audience suggests: Option " + correctAnswers[questionNumber - 1]);
            askQuestion(scanner, player, questionNumber); // Ask the question again
        } 
        
        else 
        	
        {
            System.out.println("This lifeline is already used or invalid. Please choose another option or answer directly.");
            askQuestion(scanner, player, questionNumber); 
        }
    }

    public static void removeWrongAnswers(String[] options, int correctAnswer) 
    {
        
        System.out.println("Remaining options: ");
        for (int i = 0; i < options.length; i++) 
        {
            if (i == correctAnswer - 1 || Math.random() < 0.5)
            {
                System.out.println(options[i]);
            }
        }
    }
}
	
					
				
		
		
	
	


