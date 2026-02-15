package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView chatTextView;
    EditText userInput;
    Button sendButton;
    HashMap<String, String> qaPairs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatTextView = findViewById(R.id.chatTextView);
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);

        qaPairs = new HashMap<>();
        loadQuestions();  // Load all Q&A

        sendButton.setOnClickListener(v -> {
            String userMsg = userInput.getText().toString().trim();
            if (!userMsg.isEmpty()) {
                chatTextView.append("\nYou: " + userMsg);
                respond(userMsg);
                userInput.setText("");
            }
        });
    }

    private void respond(String userMsg) {
        String msg = userMsg.toLowerCase(Locale.ROOT);

        // ✔ Step 1 — Perfect direct match
        if (qaPairs.containsKey(msg)) {
            chatTextView.append("\nChatBot: " + qaPairs.get(msg));
            return;
        }

        // ✔ Step 2 — Keyword/Sentence matching
        for (String key : qaPairs.keySet()) {
            if (msg.contains(key)) {  // If user typed something containing the key phrase
                chatTextView.append("\nChatBot: " + qaPairs.get(key));
                return;
            }
        }

        // ✔ Step 3 — Fallback answer
        chatTextView.append("\nChatBot: I'm not sure about that. Can you try asking something related or simple?");
    }

    private void loadQuestions() {


        qaPairs.put("hi", "Hello! How can I help you?");
        qaPairs.put("hello", "Hi there! What would you like to ask?");
        qaPairs.put("hey", "Hey! How can I assist you?");
        qaPairs.put("bye", "Goodbye! Take care.");
        qaPairs.put("thank you", "You're welcome!");
        qaPairs.put("thanks", "Glad I could help!");
        qaPairs.put("how are you", "I'm doing great! What about you?");
        qaPairs.put("who are you", "I'm your friendly ChatBot!");
        qaPairs.put("your name", "I am ChatBot created by a developer learning Android.");
        qaPairs.put("who created you", "I was created by a hardworking Android developer!");


        qaPairs.put("what is your favourite food", "I don't eat, but if I could, I’d love pizza!");
        qaPairs.put("what is your age", "I don’t age. I stay young forever!");
        qaPairs.put("what is the time", "Please check your mobile clock for the exact time.");
        qaPairs.put("tell me a joke", "Why don’t programmers like nature? Too many bugs!");
        qaPairs.put("where do you live", "I live inside your phone as code.");
        qaPairs.put("what are you doing", "Chatting with you!");
        qaPairs.put("good morning", "Good Morning! Have a wonderful day ahead!");
        qaPairs.put("good night", "Good Night! Sleep well.");
        qaPairs.put("good evening", "Good Evening! How was your day?");
        qaPairs.put("i am sad", "It's okay. Everything will be fine. Stay strong!");
        qaPairs.put("i am happy", "That's great! Keep smiling 😊");
        qaPairs.put("what should i eat", "Eat something healthy! Fruits or home food is always good.");
        qaPairs.put("tell me something", "Did you know? The first computer bug was an actual insect!");
        qaPairs.put("who is the prime minister", "Please check Google for the latest information. It may change over time.");
        qaPairs.put("how to study", "Study in short intervals, take notes, revise often!");
        qaPairs.put("motivate me", "Believe in yourself. You are capable of amazing things! ✨");


        qaPairs.put("data structure", "A data structure is a way of organizing data so it can be used efficiently.");
        qaPairs.put("array", "Array is a collection of elements stored at contiguous memory locations.");
        qaPairs.put("linked list", "A linked list is a linear structure where elements are stored in nodes connected by pointers.");
        qaPairs.put("stack", "Stack follows LIFO (Last In First Out).");
        qaPairs.put("queue", "Queue follows FIFO (First In First Out).");
        qaPairs.put("tree", "A tree is a non-linear hierarchical data structure.");
        qaPairs.put("graph", "A graph is a collection of nodes connected by edges.");
        qaPairs.put("dfs", "DFS means Depth First Search.");
        qaPairs.put("bfs", "BFS means Breadth First Search.");
        qaPairs.put("binary tree", "A tree where each node has at most two children.");
        qaPairs.put("bst", "Binary Search Tree: left < root < right.");
        qaPairs.put("heap", "A heap is a tree-based structure that satisfies the heap property.");
        qaPairs.put("hash", "Hashing is used to map keys to values for fast access.");
        qaPairs.put("recursion", "Recursion is a technique where a function calls itself.");
        qaPairs.put("time complexity", "It describes how fast or slow an algorithm runs.");
        qaPairs.put("big o", "Big O notation shows worst-case time complexity.");

        // Searching & Sorting
        qaPairs.put("linear search", "Linear search checks every element one by one.");
        qaPairs.put("binary search", "Binary search divides a sorted list in half repeatedly to find a value.");
        qaPairs.put("sorting", "Sorting means arranging items in ascending or descending order.");
        qaPairs.put("searching", "Searching means finding whether an element exists or not.");

        // Advanced DS
        qaPairs.put("dynamic programming", "DP solves problems by breaking them into overlapping subproblems.");
        qaPairs.put("greedy", "Greedy algorithms make the best choice at each step.");
        qaPairs.put("circular queue", "It connects the last position of queue to the first.");
        qaPairs.put("deque", "Double-ended queue where insertion/deletion happens from both ends.");


        qaPairs.put("what are you doing", "Just chatting with you!");
        qaPairs.put("are you single", "Haha, yes! I'm a single chatbot.");
        qaPairs.put("do you eat food", "Nope, I only consume electricity!");
        qaPairs.put("do you sleep", "I never sleep. I am always active for you!");
        qaPairs.put("are you real", "I'm a virtual assistant made of code.");
        qaPairs.put("what do you like", "I like helping people and answering questions.");
        qaPairs.put("tell me something interesting", "Did you know? Honey never spoils—even after 3000 years!");
        qaPairs.put("how is the weather", "I can't check weather, but I hope it's nice outside!");
        qaPairs.put("who is your best friend", "You are! 😊");
        qaPairs.put("do you have a girlfriend", "No, I'm too busy answering questions!");
        qaPairs.put("do you love me", "I love helping you! ❤️");


        qaPairs.put("how to study effectively", "Use the 25-5 rule: study 25 minutes, break 5 minutes.");
        qaPairs.put("how to focus", "Put your phone away, make a to-do list, and study in a quiet place.");
        qaPairs.put("how to remember things", "Revise the same topic after 1 day, 1 week, and 1 month!");
        qaPairs.put("how to prepare for exam", "Solve previous papers and revise important concepts daily.");
        qaPairs.put("i am stressed", "Take a deep breath. You are stronger than you think.");
        qaPairs.put("i am not able to study", "Start with small topics. Once you begin, momentum will come.");
        qaPairs.put("give me motivation", "Don’t stop until you are proud. You got this! 💪");
        qaPairs.put("i feel tired", "Take a short break and drink some water.");
        qaPairs.put("how to score good marks", "Understand concepts, practice questions, and revise daily.");
        qaPairs.put("i failed", "Failure is not the end. It's a step toward success. Learn & grow.");


        qaPairs.put("how to increase phone speed", "Delete unused apps, clear cache, and restart your phone.");
        qaPairs.put("my phone is hanging", "Try restarting or removing heavy apps.");
        qaPairs.put("how to improve battery life", "Lower brightness, turn off Bluetooth, and close background apps.");
        qaPairs.put("what is android", "Android is an operating system for smartphones.");
        qaPairs.put("what is ram", "RAM helps your device run apps smoothly.");
        qaPairs.put("what is processor", "Processor is the brain of your device that performs operations.");
        qaPairs.put("which phone is best", "Depends on your budget! Samsung, iPhone, Pixel are top options.");


        qaPairs.put("tell me a fact", "Bananas are berries, but strawberries are not!");
        qaPairs.put("tell me a story", "Once upon a time, a curious user met a smart chatbot... and they chatted forever!");
        qaPairs.put("sing a song", "La la la... I can’t sing, but I hope that made you smile!");
        qaPairs.put("i am bored", "Try listening to music or watching something funny!");
        qaPairs.put("make me laugh", "Why did the computer go to the doctor? Because it had a virus!");
        qaPairs.put("tell me a secret", "I don’t hide secrets. I only store answers!");


        qaPairs.put("what's up", "Everything’s good! You tell?");
        qaPairs.put("how was your day", "It’s good! I spent it helping users like you.");
        qaPairs.put("can you help me", "Of course! What do you want to know?");
        qaPairs.put("i miss you", "Aww! I'm always here whenever you need me.");
        qaPairs.put("do you speak hindi", "Yes! But right now I'm answering in English.");
        qaPairs.put("who is your owner", "A talented developer created me.");


        qaPairs.put("i am alone", "You’re not alone. I am here with you.");
        qaPairs.put("i am anxious", "Take a deep breath. Everything will be okay.");
        qaPairs.put("i am angry", "Calm down. Try drinking some water and relaxing.");
        qaPairs.put("no one understands me", "I understand you. Tell me what happened.");
        qaPairs.put("i feel lazy", "Start with 5 minutes. Small steps create big changes.");


        qaPairs.put("largest planet", "Jupiter is the largest planet in our solar system.");
        qaPairs.put("smallest planet", "Mercury is the smallest planet.");
        qaPairs.put("who invented computer", "Charles Babbage is known as the father of computer.");
        qaPairs.put("speed of light", "Approximately 3 x 10^8 meters per second.");
        qaPairs.put("what is ai", "AI means Artificial Intelligence — machines that think like humans.");
        qaPairs.put("what is internet", "A global network connecting millions of computers.");


        qaPairs.put("give me advice", "Be consistent. Small progress every day leads to big results.");
        qaPairs.put("i feel useless", "You are important. You matter more than you realize.");
        qaPairs.put("i feel nervous", "Relax, breathe slowly, you are doing great.");
        qaPairs.put("i dont know what to do in life", "Explore your interests — coding, art, business, anything! You will find your path.");

    }
}
