# Set elements must be unique - sets are defined with curly braces {}

myMenuSet = {'Open', 'Close', 'Exit'}

def printSet(setToPrint):
    for item in setToPrint:
        print(item)

    print("------------------------")


printSet(myMenuSet)

# NO DUPS
myMenuSet.add('Exit')
printSet(myMenuSet)

# Lowercase e is not Uppercase E
print(ord('E'))
print(ord('e'))

myMenuSet.add('exit')
printSet(myMenuSet)

# Reference members by value
myMenuSet.remove('exit')
printSet(myMenuSet)

# You can get a copy of an existing set
# This IS NOT the same reference
myCustomMenu = myMenuSet.copy()

myCustomMenu.add('Print')

printSet(myMenuSet)
printSet(myCustomMenu)

# Compare that to THIS assignment of reference
myOtherMenu = myMenuSet

myOtherMenu.add('Help')

printSet(myMenuSet)
printSet(myOtherMenu)

# -------------------- DICTIONARIES --------------------

# Bob - Rich's Neighbor
# Wednesday - Humpday
# Key:Value Pairs (KVP)

productDict = {}  # Default Dictionary initialization

print(type(productDict))

productDict.update({1234: "Keychain"})
productDict.update({2345: "Hoodie"})
productDict.update({3456: "T-Shirt"})

print("Current Prod List:", productDict.items())

productKeyToLookup = 2345

# Retrieve value associated with a key

print(productDict.get(productKeyToLookup))
print(productDict.get(productKeyToLookup, None))

print(productDict.get(9999, None))
print(productDict.get(3456, None))

print("------------------------")

print(f"Product: {productKeyToLookup} is a: {productDict.get(productKeyToLookup)}")

# ---- CHANGE THE VALUE ASSOCIATED WITH KEY: 1234

productDict.update({1234: "Hat"})

print(productDict.items())

# Direct Value Access Using Key

# This would crash with a KeyError because 9999 doesn't exist
# prodName = productDict[9999]
# print("Your product is: " + str(prodName))

# Safe lookup using .get()
prodName = productDict.get(9999)

print("Your product is: " + str(prodName))

# ==================================
# SET OPERATIONS
# ==================================

stem_set = {'science', 'technology', 'engineering', 'mathematics'}

steam_set = stem_set.copy()
steam_set.add('art')

print("Union Operator")
print(stem_set | steam_set)

print("Union Method")
print(stem_set.union(steam_set))

print("------------------------")

print("Intersection Operator")
print(stem_set & steam_set)

print("Intersection Method")
print(stem_set.intersection(steam_set))

print("------------------------")

print("Difference Operator")
print(steam_set - stem_set)

print(stem_set - steam_set)

print("Difference Method")
print(steam_set.difference(stem_set))

print("------------------------")

print("Symmetric Difference Operator")
print(steam_set ^ stem_set)

print("Symmetric Difference Method")
print(steam_set.symmetric_difference(stem_set))

print("------------------------")

print("Disjoint Checks")

other_studies_set = {'humanities', 'art'}

print(stem_set.isdisjoint(steam_set))
print(stem_set.isdisjoint(other_studies_set))

print("------------------------")

print("Subset / Superset")

print(stem_set.issubset(steam_set))
print(steam_set.issuperset(stem_set))