import tkinter as tk
from tkinter import messagebox 
from tkinter import PhotoImage
import pandas as pd
import random

df = pd.read_excel('questions.xlsx')
questions = df.sample(n=10).values.tolist()

janela=tk.TK()
janela.title("Jogo do Milhão")
janela.geometry("1920X1080")
background_color= "#ECECEC"
text_color= "#333333" 
button_color= "#4CAF50"
 
janela.config(bg=background_color)
janela.option_add("Front", "Arial")

janela.mainloop()