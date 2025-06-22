import pandas as pd
from faker import Faker
import random

fake = Faker('ru_RU')

def generate_data(num_rows, group_number):
    names = set()
    emails = set()

    while len(names) < num_rows:
        name = fake.name()
        names.add(name)

    while len(emails) < num_rows:
        email = fake.email()
        emails.add(email)

    names_list = list(names)
    emails_list = list(emails)

    data = {
        'Имя Фамилия Отчество': names_list,
        'Значение 2': group_number,
        'Email': emails_list
    }

    df = pd.DataFrame(data)
    return df

def main():
    num_rows = int(input("Введите количество строк для генерации: "))
    group_number = int(input("Введите желаемый номер потока: "))
    
    df = generate_data(num_rows, group_number)

    output_file = 'generated_students.xlsx'
    df.to_excel(output_file, index=False, header=False)

    print(f"Данные успешно сгенерированы и сохранены в {output_file}")

if __name__ == "__main__":
    main()
