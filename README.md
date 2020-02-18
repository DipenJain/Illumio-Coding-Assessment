# Illumio
Illumio Coding Challenge, PCE team

# Language Used
Java

# How I tested the solution
1. I created a test file consisting of sample valid rules, taken from the assignment.
2. I have written test cases using JUnit4 to test functionality for different combinations of input to the function (accept_packet)

# Coding/Design decisions made
1. I used a HashSet to stores all the distinct rules. This is to avoid duplicate rules and hence avoid looking for duplicate entries as they cause extra cost while searching.
2. The methods hashcode() and equals() are overriden and used to compare if two rules are equal or not, while adding/retrieving from the hashSet  
4. Multiple helper functions are created to make the program more modularized.
5. I have used external libraries to convert the ipAddress in String to long. Also, added necessary references in code.
6.  With above approach, the code can encounter ip addresses whose long value could be greater than Integer.MAX. To deal with such situations, I have used Bit shift
6. The tradeoff here made was to store multiple rules in a hashset for a range of port and IpAddress values in order to have constant look up time.
7. I also considered using segment trees / other tree data structures and also other searching algorithms like binary search over the ranges but that would increase the complexity to O(log n) and hence I used hashSet as it seemed more efficient in terms of lookups.

# Improvement for the future
1. The performance of this code can be improved by implementing a better hash function.
2. If I have more time, I would explore better implementation of the hashing function or if a good hash function is lacking I'd explore implementing using other data structures like TreeMap/Trie/Segment trees.

# Teams interested
Rank #1. Platform Team

Rank #2. Data Team

Rank #3. Policy Team