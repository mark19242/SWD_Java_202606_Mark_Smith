import pytest
from user import User

@pytest.fixture
def test_user():
  return User("Alice")

def test_greet(test_user):
  assert test_user.greet() == "Hello, Alice!"