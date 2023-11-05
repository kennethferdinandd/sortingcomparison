import random


def generate_and_save_dataset(filename, size, data_type):
    if data_type == "sorted":
        dataset = list(range(1, size + 1))
    elif data_type == "random":
        dataset = [random.randint(1, 1000000) for _ in range(size)]
    elif data_type == "reversed":
        dataset = list(range(size, 0, -1))
    else:
        print("Invalid data type")
        return

    with open(filename, "w") as file:
        for number in dataset:
            file.write(f"{number}\n")


generate_and_save_dataset("dataset_2000_sorted.txt", 2000, "sorted")
generate_and_save_dataset("dataset_2000_random.txt", 2000, "random")
generate_and_save_dataset("dataset_2000_reversed.txt", 2000, "reversed")

generate_and_save_dataset("dataset_20000_sorted.txt", 20000, "sorted")
generate_and_save_dataset("dataset_20000_random.txt", 20000, "random")
generate_and_save_dataset("dataset_20000_reversed.txt", 20000, "reversed")

generate_and_save_dataset("dataset_200_sorted.txt", 200, "sorted")
generate_and_save_dataset("dataset_200_random.txt", 200, "random")
generate_and_save_dataset("dataset_200_reversed.txt", 200, "reversed")


