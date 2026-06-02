def greet(name):
    return f"Hello, {name}!"


class Greeter:
    def __init__(self, name):
        self.name = name

    def greet(self):
        return f"Hello, {self.name}!"


my_variable = 42