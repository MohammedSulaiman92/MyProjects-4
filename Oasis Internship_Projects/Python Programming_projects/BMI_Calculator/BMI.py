def Bmi_Calculator(weight,height):
    bmi = weight/(height**2)
    return bmi
weight =float(input("Enter your weight in Kilograms: "))
Height =float(input("Enter your Height in cms(Centimeters): "))
# Converting cms to meters
height = Height/100
#Calculating Body Mass Index
BMI = Bmi_Calculator(weight,height)
#Checking Your Body Mass Index
print("Your BMI is " ,BMI)
if BMI <18.5:
    print("You r Underweight")
elif(BMI >= 18.5 and BMI<25):
    print("You r Normal & Healthy")
elif(BMI>=25 and BMI< 30):
    print("You r Overweight")
else:
    print("Obesity")
