import tkinter as tk
from tkinter import messagebox

# List of programming-related questions, options, and correct answers
questions = [
    {"question": "Which keyword is used to define a function in Python?", "options": ["def", "func", "define", "lambda"], "answer": "def"},
    {"question": "What does OOP stand for?", "options": ["Object-Oriented Programming", "Only Objects Programming", "Object Operation Processing", "Object-Oriented Processing"], "answer": "Object-Oriented Programming"},
    {"question": "Which data type is immutable in Python?", "options": ["List", "Dictionary", "Set", "Tuple"], "answer": "Tuple"},
    {"question": "What is the output of print(type([])) in Python?", "options": ["list", "tuple", "dict", "set"], "answer": "list"},
    {"question": "Which of the following is not a programming language?", "options": ["Python", "Java", "HTML", "C++"], "answer": "HTML"},
    {"question": "Which symbol is used for single-line comments in Python?", "options": ["//", "--", "#", "/*"], "answer": "#"},
    {"question": "What is the default return type of a function in Python?", "options": ["int", "str", "NoneType", "bool"], "answer": "NoneType"},
    {"question": "Which built-in function is used to get the length of a list?", "options": ["size()", "len()", "length()", "count()"], "answer": "len()"},
    {"question": "Which method is used to add an item to a list?", "options": ["insert()", "add()", "append()", "push()"], "answer": "append()"},
    {"question": "Which of the following is used to handle exceptions in Python?", "options": ["try-except", "if-else", "switch-case", "catch-throw"], "answer": "try-except"}
]

class QuizApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Programming Quiz App")
        self.root.geometry("500x400")
        self.root.configure(bg="#f0f0f0")
        self.question_index = 0
        self.score = 0
        self.user_answers = []
        self.create_widgets()

    def create_widgets(self):
        self.question_label = tk.Label(self.root, text="", wraplength=400, font=("Arial", 14, "bold"), bg="#f0f0f0")
        self.question_label.pack(pady=20)

        self.radio_var = tk.StringVar()
        self.radio_buttons = []
        for i in range(4):
            rb = tk.Radiobutton(self.root, text="", variable=self.radio_var, value="", font=("Arial", 12), bg="#f0f0f0")
            rb.pack(anchor="w", padx=20)
            self.radio_buttons.append(rb)

        self.next_button = tk.Button(self.root, text="Next", command=self.next_question, font=("Arial", 12, "bold"), bg="#4CAF50", fg="white", padx=10, pady=5)
        self.next_button.pack(pady=20)
        
        self.show_question()

    def show_question(self):
        question_data = questions[self.question_index]
        self.question_label.config(text=question_data["question"])
        self.radio_var.set("None")
        for i, option in enumerate(question_data["options"]):
            self.radio_buttons[i].config(text=option, value=option)

    def next_question(self):
        selected_answer = self.radio_var.get()
        if selected_answer == "None":
            messagebox.showwarning("Warning", "Please select an answer!")
            return
        
        self.user_answers.append(selected_answer)
        correct_answer = questions[self.question_index]["answer"]
        if selected_answer == correct_answer:
            self.score += 1
        
        self.question_index += 1
        if self.question_index < len(questions):
            self.show_question()
        else:
            self.show_result()

    def show_result(self):
        result_text = f"Your score: {self.score}/{len(questions)}\n\nCorrect Answers:\n"
        for i, q in enumerate(questions):
            result_text += f"{i+1}. {q['question']} - {q['answer']}\n"
        messagebox.showinfo("Quiz Result", result_text)
        self.root.quit()

if __name__ == "__main__":
    root = tk.Tk()
    app = QuizApp(root)
    root.mainloop()
